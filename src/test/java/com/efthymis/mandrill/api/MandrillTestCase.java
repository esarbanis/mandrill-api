/**
 *
 */
package com.efthymis.mandrill.api;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.Assert;
import org.junit.Before;

import com.efthymis.mandrill.api.common.GsonJsonSerializer;
import com.efthymis.mandrill.api.common.HttpClientRequestDispatcher;
import com.efthymis.mandrill.api.common.RequestDispatcher;

/**
 *
 */
public abstract class MandrillTestCase {

	protected MandrillApi mandrillApi;
	private CloseableHttpClient httpClient;

	protected void mockResponse(String apiPath, int status, String json) throws IOException {
		CloseableHttpResponse response = mock(CloseableHttpResponse.class);
		StatusLine statusLine = mock(StatusLine.class);
		HttpEntity httpEntity = mock(HttpEntity.class);

		when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(json.getBytes()));
		when(httpEntity.isStreaming()).thenReturn(true);
		when(statusLine.getStatusCode()).thenReturn(status);
		when(response.getStatusLine()).thenReturn(statusLine);
		when(response.getEntity()).thenReturn(httpEntity);
		when(httpClient.execute(argThat(argument -> {
			URI uri = argument.getURI();
			boolean match = uri.getPath().equals(apiPath);
			if (!match) {
				Assert.fail("URI " + uri + " does not contain the desired path " + apiPath);
			}
			return true;
		}))).thenReturn(response);
	}

	@Before
	public void setup() {
		httpClient = mock(CloseableHttpClient.class);
		RequestDispatcher requestDispatcher = new HttpClientRequestDispatcher(new GsonJsonSerializer(), httpClient);

		String rootUrl = "http://example.com/";
		String key = "key";
		mandrillApi = new MandrillApi(key, rootUrl, requestDispatcher);
	}

	protected static String mailToAddress() {
		return "someone.mandrill@gmail.com";
	}

}
