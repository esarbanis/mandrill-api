/**
 * 
 */
package com.efthymis.mandrill.api.senders;

import java.util.Date;

import com.efthymis.mandrill.api.common.StatsBucket;

/**
 * <p>Information on a sending address in the account.</p>
 * @since Mar 16, 2013
 */
public class MandrillSender extends StatsBucket.Stats {
	private String address;
	private Date created_at;
	private StatsBucket stats;
	
	/**
	 * @return The sender's email address.
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @return The time that the sender was first seen by Mandrill, in UTC.
	 */
	public Date getCreatedAt() {
		return created_at;
	}
	/**
	 * @return An aggregate summary of the sender's sending stats.
	 */
	public StatsBucket getStats() {
		return stats;
	}

}
