/**
 * 
 */
package com.efthymis.mandrill.api.inbound;

/**
 * <p>A mailbox route.</p>
 * @since Mar 19, 2013
 */
public class MandrillMailboxRoute {
	private String pattern, url;

	/**
	 * @return The search pattern that the mailbox 
	 * name should match.
	 */
	public String getPattern() {
		return pattern;
	}

	/**
	 * @return The webhook URL where inbound messages 
	 * will be published.
	 */
	public String getUrl() {
		return url;
	}

}
