/**
 * 
 */
package com.efthymis.mandrill.api.urls;

import com.efthymis.mandrill.api.common.StatsBucket;

/**
 * <p>A URL and its statistics.</p>
 * @since Mar 18, 2013
 */
public class MandrillUrl extends StatsBucket.Stats {
	private String url;

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

}
