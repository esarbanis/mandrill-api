/**
 * 
 */
package io.github.esarbanis.mandrill.api.rejects;

import java.io.IOException;
import java.util.HashMap;

import io.github.esarbanis.mandrill.api.common.MandrillApiError;
import io.github.esarbanis.mandrill.api.common.MandrillHelperClasses.MandrillRejectsAdded;
import io.github.esarbanis.mandrill.api.common.MandrillHelperClasses.MandrillRejectsDeleted;
import io.github.esarbanis.mandrill.api.common.MandrillSubContext;
import io.github.esarbanis.mandrill.api.common.RequestDispatcher;

/**
 * @since Mar 19, 2013
 */
public class MandrillRejectsApi extends MandrillSubContext {
	private final String key;
	private final String rootUrl;

	public MandrillRejectsApi(final String key, final String url, RequestDispatcher requestDispatcher) {
		super(requestDispatcher);
		this.key = key;
		this.rootUrl = url;
	}
	
	public Boolean add(final String email, final String comment,
			final String subaccount) throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("email", email);
		params.put("comment", comment);
		params.put("subaccount", subaccount);
		return query(rootUrl+ "rejects/add.json", 
				params, MandrillRejectsAdded.class).getAdded();
		
	}
	
	/**
	 * <p>Retrieve your email rejection blacklist. You can 
	 * provide an email address to limit the results. Returns 
	 * up to 1000 results. By default, entries that have expired 
	 * are excluded from the results; use includeExpired to 
	 * true to include them.</p>
	 * @param email An optional email address to search by.
	 * @param includeExpired Whether to include rejections that 
	 * have already expired.
	 * @return Up to 1000 {@link MandrillRejectsEntry} objects.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillRejectsEntry[] list(final String email, 
			final Boolean includeExpired) throws MandrillApiError, IOException {
	
		return list(email, includeExpired, null);
		
	}
	
	/**
	 * <p>Retrieve your email rejection blacklist. You can 
	 * provide an email address to limit the results. Returns 
	 * up to 1000 results. By default, entries that have expired 
	 * are excluded from the results; use includeExpired to 
	 * true to include them.</p>
	 * @param email An optional email address to search by.
	 * @param includeExpired Whether to include rejections that 
	 * have already expired.
	 * @param subaccount An optional unique identifier for the 
	 * subaccount to limit the blacklist.
	 * @return Up to 1000 {@link MandrillRejectsEntry} objects.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillRejectsEntry[] list(final String email, 
			final Boolean includeExpired, final String subaccount) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("email", email);
		params.put("include_expired", includeExpired);
		if(subaccount != null) {
			params.put("subaccount", subaccount);
		}
		return query(rootUrl+ "rejects/list.json", 
				params, MandrillRejectsEntry[].class);
		
	}
	
	/**
	 * <p>Delete an email rejection. There is no limit to 
	 * how many rejections you can remove from your blacklist, 
	 * but keep in mind that each deletion has an affect on 
	 * your reputation.</p>
	 * @param email The email address that was removed from 
	 * the blacklist.
	 * @return Whether the address was deleted successfully.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public Boolean delete(final String email) 
			throws MandrillApiError, IOException {
		
		return delete(email, null);
		
	}
	
	/**
	 * <p>Delete an email rejection. There is no limit to 
	 * how many rejections you can remove from your blacklist, 
	 * but keep in mind that each deletion has an affect on 
	 * your reputation.</p>
	 * @param email The email address that was removed from 
	 * the blacklist.
	 * @param subaccount An optional unique identifier for the 
	 * subaccount to limit the blacklist.
	 * @return Whether the address was deleted successfully.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public Boolean delete(final String email, final String subaccount) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("email", email);
		if(subaccount != null) {
			params.put("subaccount", subaccount);
		}
		return query(rootUrl+ "rejects/delete.json", 
				params, MandrillRejectsDeleted.class).getDeleted();
		
	}
	
}
