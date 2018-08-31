/**
 *
 */
package io.github.esarbanis.mandrill.api;

import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import io.github.esarbanis.mandrill.api.common.GsonJsonSerializer;
import io.github.esarbanis.mandrill.api.common.HttpClientRequestDispatcher;
import io.github.esarbanis.mandrill.api.common.RequestDispatcher;
import org.junit.Before;

/**
 *
 */
public abstract class MandrillTestCase {

	protected static String KEY = "KEY";
	protected static String ROOT_URL = "http://example.com/";

	protected MandrillApi mandrillApi;
	protected CloseableHttpClient httpClient;

	protected void mockResponse(String apiPath, int status, String json) throws IOException {
		CloseableHttpResponse response = mock(CloseableHttpResponse.class);
		StatusLine statusLine = mock(StatusLine.class);
		HttpEntity httpEntity = mock(HttpEntity.class);

		when(httpEntity.getContent()).thenReturn(new ByteArrayInputStream(json.getBytes()));
		when(httpEntity.isStreaming()).thenReturn(true);
		when(statusLine.getStatusCode()).thenReturn(status);
		when(response.getStatusLine()).thenReturn(statusLine);
		when(response.getEntity()).thenReturn(httpEntity);
		when(httpClient.execute(argThat(argument -> argument.getURI().getPath().equals(apiPath)))).thenReturn(response);
	}

	@Before
	public void setup() {
		httpClient = mock(CloseableHttpClient.class);
		RequestDispatcher requestDispatcher = new HttpClientRequestDispatcher(new GsonJsonSerializer(), httpClient);

		mandrillApi = new MandrillApi(KEY, ROOT_URL, requestDispatcher, new GsonJsonSerializer());
	}

	protected static String mailToAddress() {
		return "someone.mandrill@gmail.com";
	}

}
