/**
 * 
 */
package io.github.esarbanis.mandrill.api.urls;

import java.util.Date;

import io.github.esarbanis.mandrill.api.common.StatsBucket;

/**
 * <p>A time series holds stats for a single hour.</p>
 * @since Mar 16, 2013
 */
public class MandrillTimeSeries extends StatsBucket.Stats {
	private Date time;

	/**
	 * @return The hour for this time series.
	 */
	public Date getTime() {
		return time;
	}

}
