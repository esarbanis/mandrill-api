package com.efthymis.mandrill.api.common;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class MandrillSubContext {

	private final RequestDispatcher requestDispatcher;

	protected MandrillSubContext(RequestDispatcher requestDispatcher) {
		this.requestDispatcher = requestDispatcher;
	}

	/**
	 * @param key
	 * @return
	 */
	protected final HashMap<String,Object> paramsWithKey(final String key) {
		final HashMap<String,Object> params = new HashMap<>();
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

		final MandrillRequestRequest<OUT> requestModel =
				new MandrillRequestRequest<>(url, params, responseType);
		return requestDispatcher.dispatch(requestModel);

	}

}
