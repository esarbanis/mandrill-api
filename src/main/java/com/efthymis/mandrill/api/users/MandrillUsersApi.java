/**
 *
 */
package com.efthymis.mandrill.api.users;

import java.io.IOException;

import com.efthymis.mandrill.api.common.MandrillApiError;
import com.efthymis.mandrill.api.common.MandrillSubContext;
import com.efthymis.mandrill.api.common.RequestDispatcher;
import com.efthymis.mandrill.api.senders.MandrillSender;

/**
 * <p></p>
 *
 * @since Mar 19, 2013
 */
public class MandrillUsersApi extends MandrillSubContext {

	private final String key;
	private final String rootUrl;

	public MandrillUsersApi(final String key, final String url, RequestDispatcher requestDispatcher) {
		super(requestDispatcher);
		this.key = key;
		this.rootUrl = url;
	}

	/**
	 * <p>Get information about the account for the given api key.</p>
	 *
	 * @return The information about the API-connected user.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException      IO Error
	 */
	public MandrillUserInfo info() throws MandrillApiError, IOException {
		return query(rootUrl + "users/info.json", paramsWithKey(key), MandrillUserInfo.class);

	}

	/**
	 * <p>Validate an API key and respond to a ping.</p>
	 *
	 * @return The String literal "PONG!"
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException      IO Error
	 */
	public String ping() throws MandrillApiError, IOException {
		return query(rootUrl + "users/ping.json", paramsWithKey(key), String.class);

	}

	/**
	 * <p>Validate an API key and respond to a ping.</p>
	 *
	 * @return The String literal "PONG!"
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException      IO Error
	 */
	public MandrillUserPingInfo ping2() throws MandrillApiError, IOException {
		return query(rootUrl + "users/ping2.json", paramsWithKey(key), MandrillUserPingInfo.class);

	}


	/**
	 * <p>Return the senders that have tried to use this account,
	 * both verified and unverified.</p>
	 *
	 * @return The senders that have tried to use this account,
	 * both verified and unverified.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException      IO Error
	 */
	public MandrillSender[] senders() throws MandrillApiError, IOException {

		return query(rootUrl + "users/senders.json", paramsWithKey(key), MandrillSender[].class);

	}

}
