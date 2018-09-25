package com.efthymis.mandrill.api.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientRequestDispatcher implements RequestDispatcher {

	private static final Logger LOG = LoggerFactory.getLogger(HttpClientRequestDispatcher.class);

	private final CloseableHttpClient httpClient;
	private final GsonJsonSerializer serializer;

	public HttpClientRequestDispatcher() {
		this(new GsonJsonSerializer());
	}

	public HttpClientRequestDispatcher(GsonJsonSerializer serializer) {
		this.serializer = serializer;
		PoolingHttpClientConnectionManager connexionManager = new PoolingHttpClientConnectionManager();
		connexionManager.setDefaultMaxPerRoute(50);
		RequestConfig defaultRequestConfig = RequestConfig.custom().setSocketTimeout(0).setConnectTimeout(0).setConnectionRequestTimeout(0).build();
		httpClient = HttpClients.custom()
				.setUserAgent("/Lutung-0.1")
				.setDefaultRequestConfig(defaultRequestConfig)
				.setConnectionManager(connexionManager)
				.useSystemProperties()
				.build();
	}

	public HttpClientRequestDispatcher(GsonJsonSerializer serializer, CloseableHttpClient httpClient) {
		this.serializer = serializer;
		this.httpClient = httpClient;
	}

	@Override
	public <T> T dispatch(RequestContext<T> requestContext) throws MandrillApiError, IOException {
		HttpResponse response = null;
		String responseString = null;
		try {
			// use proxy?
			final HttpClientRequestDispatcher.ProxyData proxyData = detectProxyServer(requestContext.getUrl());
			if (proxyData != null) {
				if (LOG.isDebugEnabled()) {
					LOG.debug("Using proxy @{}:{}", proxyData.host, proxyData.port);
				}
				final HttpHost proxy = new HttpHost(proxyData.host, proxyData.port);
				httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
			}
			LOG.debug("starting request '{}'", requestContext.getUrl());
			Map<String, ?> requestParams = requestContext.getRequestParams();
			response = httpClient.execute(getRequest(requestParams, requestContext.getUrl()));
			final StatusLine status = response.getStatusLine();
			responseString = EntityUtils.toString(response.getEntity());
			if (requestContext.validateResponseStatus(status.getStatusCode())) {
				try {
					return handleResponse(responseString, requestContext.getResponseContentType());

				} catch (final HandleResponseException e) {
					throw new IOException("Failed to parse response from request '" + requestContext.getUrl() + "'", e);

				}

			} else {
				// ==> compile mandrill error!
				MandrillApiError.MandrillError error = null;
				try {
					error = serializer.fromJsonString(responseString, MandrillApiError.MandrillError.class);
				} catch (Throwable ex) {
					error = new MandrillApiError.MandrillError("Invalid Error Format", "Invalid Error Format", responseString,
							status.getStatusCode());
				}

				throw new MandrillApiError(
						"Unexpected http status in response: " + status.getStatusCode() + " (" + status.getReasonPhrase() + ")").withError(error);

			}

		} finally {
			try {
				EntityUtils.consume(response.getEntity());
			} catch (IOException e) {
				LOG.error("Error consuming entity", e);
				throw e;
			}
		}
	}

	public final HttpRequestBase getRequest(Map<String, ? extends Object> requestParams, String url) {
		final String paramsStr = serializer.toJsonString(
				requestParams, requestParams.getClass());
		LOG.debug("raw content for request:\n" +paramsStr);
		final StringEntity entity = new StringEntity(paramsStr, "UTF-8");
		entity.setContentType("application/json");
		final HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		return request;

	}

	private <T> T handleResponse(final String responseString, Class<T> responseType)
			throws HandleResponseException {

		try {
			LOG.debug("raw content from response:\n" +responseString);
			return serializer.fromJsonString(responseString, responseType);

		} catch(final Throwable t) {
			String msg = "Error handling Mandrill response " +
					((responseString != null)?": '"+responseString+"'" : "");
			throw new HandleResponseException(msg, t);

		}
	}

	private HttpClientRequestDispatcher.ProxyData detectProxyServer(final String url) {
		try {
			final List<Proxy> proxies = ProxySelector.getDefault().select(new URI(url));
			if (proxies != null) {
				for (Proxy proxy : proxies) {
					InetSocketAddress addr = (InetSocketAddress) proxy.address();
					if (addr != null) {
						return new HttpClientRequestDispatcher.ProxyData(addr.getHostName(), addr.getPort());
					}
				}
			}
			// no proxy detected!
			return null;

		} catch (final Throwable t) {
			LOG.error("Error detecting proxy server", t);
			return null;

		}
	}

	private static final class ProxyData {

		String host;
		int port;

		protected ProxyData(final String host, final int port) {
			this.host = host;
			this.port = port;
		}

	}
}
