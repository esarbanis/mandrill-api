/**
 * 
 */
package com.efthymis.mandrill.api.tags;

import java.io.IOException;
import java.util.HashMap;

import com.efthymis.mandrill.api.urls.MandrillTimeSeries;
import com.efthymis.mandrill.api.common.MandrillApiError;
import com.efthymis.mandrill.api.common.MandrillSubContext;
import com.efthymis.mandrill.api.common.RequestDispatcher;

/**
 * @since Mar 19, 2013
 */
public class MandrillTagsApi extends MandrillSubContext {
	private final String key;
	private final String rootUrl;

	public MandrillTagsApi(final String key, final String url, RequestDispatcher requestDispatcher) {
		super(requestDispatcher);
		this.key = key;
		this.rootUrl = url;
	}
	
	/**
	 * <p>Access all of the user-defined tag information.</p>
	 * @return All of the user-defined tag information.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTag[] list() 
			throws MandrillApiError, IOException {
		
		return query(
				rootUrl+ "tags/list.json", 
				paramsWithKey(key), 
				MandrillTag[].class);
		
	}
	
	/**
	 * <p>Delete a tag permanently. Deleting a tag removes the 
	 * tag from any messages that have been sent, and also deletes 
	 * the tag's stats. There is no way to undo this operation, 
	 * so use it carefully.</p>
	 * @param tagName The name of the tag.
	 * @return The tag that was deleted.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTag delete(final String tagName) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("tag", tagName);
		return query(rootUrl+ "tags/delete.json", 
				params, MandrillTag.class);
		
	}
	
	/**
	 * <p>Get more detailed information about a single tag, 
	 * including aggregates of recent stats.</p>
	 * @param tagName The name of the tag.
	 * @return Detailed information on the tag.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTag info(final String tagName) 
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("tag", tagName);
		return query(rootUrl+ "tags/info.json", 
				params, MandrillTag.class);
		
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the last 30 
	 * days) for a tag.</p>
	 * @param tagName The name of the tag.
	 * @return The recent history (hourly stats for the last 30 
	 * days) for a tag.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTimeSeries[] timeSeries(final String tagName)
			throws MandrillApiError, IOException{
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("tag", tagName);
		return query(rootUrl+ "tags/time-series.json", 
				params, MandrillTimeSeries[].class);
		
	}
	
	/**
	 * <p>Get the recent history (hourly stats for the 
	 * last 30 days) for all tags.</p>
	 * @return The recent history (hourly stats for 
	 * the last 30 days) for all tags.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	public MandrillTimeSeries[] allTimeSeries() 
			throws MandrillApiError, IOException {
		
		return query(
				rootUrl+ "tags/all-time-series.json", 
				paramsWithKey(key), 
				MandrillTimeSeries[].class);
		
	}
}
