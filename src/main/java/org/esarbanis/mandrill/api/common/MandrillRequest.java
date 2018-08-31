/**
 * 
 */
package org.esarbanis.mandrill.api.common;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * @author rschreijer
 * @since Mar 16, 2013
 */
public final class MandrillRequest<OUT> implements RequestModel<OUT> {
    private static final Logger log = LoggerFactory.getLogger(MandrillRequest.class);

	private final String url;
	private final Class<OUT> responseContentType;
	private final Map<String,? extends Object> requestParams;
	private final JsonSerializer serializer;
	
	public MandrillRequest(final String url, final Map<String, ? extends Object> params, final Class<OUT> responseType, JsonSerializer jsonSerializer) {
		this.serializer = jsonSerializer;

		if(responseType == null) {
			throw new NullPointerException();
			
		}
		this.url = url;
		this.requestParams = params;
		this.responseContentType = responseType;
	}

	public final String getUrl() {
		return url;
	}

	public final HttpRequestBase getRequest() {
		final String paramsStr = serializer.toJsonString(
				requestParams, requestParams.getClass());
        log.debug("raw content for request:\n" +paramsStr);
		final StringEntity entity = new StringEntity(paramsStr, "UTF-8");
		entity.setContentType("application/json");
		final HttpPost request = new HttpPost(url);
		request.setEntity(entity);
		return request;
		
	}

	public final boolean validateResponseStatus(final int httpResponseStatus) {
		return (httpResponseStatus == 200);
	}

	public final OUT handleResponse(final String responseString) 
			throws HandleResponseException {
		
		try {
            log.debug("raw content from response:\n" +responseString);
			return serializer.fromJsonString(
					responseString, responseContentType);
			
		} catch(final Throwable t) {
			String msg = "Error handling Mandrill response " +
					((responseString != null)?": '"+responseString+"'" : "");
			throw new HandleResponseException(msg, t);
			
		}
	}

}
