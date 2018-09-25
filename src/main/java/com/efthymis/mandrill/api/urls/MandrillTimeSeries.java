/**
 * 
 */
package com.efthymis.mandrill.api.urls;

import java.util.Date;

import com.efthymis.mandrill.api.common.StatsBucket;

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
