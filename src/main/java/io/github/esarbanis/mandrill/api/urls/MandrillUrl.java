/**
 * 
 */
package io.github.esarbanis.mandrill.api.urls;

import io.github.esarbanis.mandrill.api.common.StatsBucket;

/**
 * <p>A URL and its statistics.</p>
 * @author rschreijer
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
