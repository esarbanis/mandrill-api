package org.esarbanis.mandrill.api.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MandrillSubContext {

	private final RequestDispatcher requestDispatcher;
	private final JsonSerializer jsonSerializer;

	protected MandrillSubContext(RequestDispatcher requestDispatcher, JsonSerializer jsonSerializer) {
		this.requestDispatcher = requestDispatcher;
		this.jsonSerializer = jsonSerializer;
	}

	/**
	 * @param key
	 * @return
	 */
	protected final HashMap<String,Object> paramsWithKey(final String key) {
		final HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("key",key);
		return params;

	}

	/**
	 * @param url
	 * @param params
	 * @param responseType
	 * @return
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	protected final <OUT> OUT query(final String url,
			final Map<String,Object> params, Class<OUT> responseType)
			throws MandrillApiError, IOException {

		final MandrillRequest<OUT> requestModel =
				new MandrillRequest<OUT>(url, params, responseType, jsonSerializer);
		return requestDispatcher.dispatch(requestModel);

	}

}
