/**
 * 
 */
package org.esarbanis.mandrill.api.urls;

import java.io.IOException;
import java.util.HashMap;

import org.esarbanis.mandrill.api.common.JsonSerializer;
import org.esarbanis.mandrill.api.common.MandrillApiError;
import org.esarbanis.mandrill.api.common.MandrillSubContext;
import org.esarbanis.mandrill.api.common.RequestDispatcher;

/**
 * @author rschreijer
 * @since Mar 19, 2013
 */
public class MandrillUrlsApi extends MandrillSubContext {
	private final String key;
	private final String rootUrl;

	public MandrillUrlsApi(final String key, final String url, RequestDispatcher requestDispatcher, JsonSerializer jsonSerializer) {
		super(requestDispatcher, jsonSerializer);
		this.key = key;
		this.rootUrl = url;
	}
	
	/**
	 * <p>Get the 100 most clicked URLs.</p>
	 * @return
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 * @since Mar 19, 2013
	 */
	public MandrillUrl[] list() 
			throws MandrillApiError, IOException {
		
		return query(
				rootUrl+ "urls/list.json", 
				paramsWithKey(key), 
				MandrillUrl[].class);
		
	}
	
	/**
	 * <p>Get the 100 most clicked URLs that match 
	 * the search query given.</p>
	 * @param query A search query.
	 * @return An array of {@link MandrillUrl} objects with 
	 * the 100 most clicked URLs matching the search query.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillUrl[] search(final String query) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("q", query);
		return query(rootUrl+ "urls/search.json", 
				params, MandrillUrl[].class);
		
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the 
	 * last 30 days) for a url.</p>
	 * @param url An existing URL.
	 * @return An array of {@link MandrillTimeSeries} objects.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTimeSeries[] timeSeries(final String url) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("url", url);
		return query(rootUrl+ "urls/time-series.json", 
				params, MandrillTimeSeries[].class);
		
	}
}
