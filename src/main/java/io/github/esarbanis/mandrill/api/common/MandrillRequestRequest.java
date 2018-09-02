/**
 *
 */
package io.github.esarbanis.mandrill.api.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @since Mar 16, 2013
 */
public final class MandrillRequestRequest<OUT> implements RequestContext<OUT> {

	private static final Logger log = LoggerFactory.getLogger(MandrillRequestRequest.class);

	private final String url;
	private final Class<OUT> responseContentType;
	private final Map<String, ? extends Object> requestParams;

	public MandrillRequestRequest(final String url, final Map<String, ? extends Object> params, final Class<OUT> responseType) {
		if (responseType == null) {
			throw new NullPointerException();

		}
		this.url = url;
		this.requestParams = params;
		this.responseContentType = responseType;
	}

	public final String getUrl() {
		return url;
	}

	public final boolean validateResponseStatus(final int httpResponseStatus) {
		return (httpResponseStatus == 200);
	}

	@Override
	public Class<OUT> getResponseContentType() {
		return responseContentType;
	}

	@Override
	public Map<String, ?> getRequestParams() {
		return requestParams;
	}

}
