/**
 * 
 */
package com.efthymis.mandrill.api;

import com.efthymis.mandrill.api.common.HttpClientRequestDispatcher;
import com.efthymis.mandrill.api.common.RequestDispatcher;
import com.efthymis.mandrill.api.exports.MandrillExportsApi;
import com.efthymis.mandrill.api.inbound.MandrillInboundApi;
import com.efthymis.mandrill.api.ips.MandrillIpsApi;
import com.efthymis.mandrill.api.messages.MandrillMessagesApi;
import com.efthymis.mandrill.api.rejects.MandrillRejectsApi;
import com.efthymis.mandrill.api.senders.MandrillSendersApi;
import com.efthymis.mandrill.api.subaccounts.MandrillSubaccountsApi;
import com.efthymis.mandrill.api.tags.MandrillTagsApi;
import com.efthymis.mandrill.api.templates.MandrillTemplatesApi;
import com.efthymis.mandrill.api.urls.MandrillUrlsApi;
import com.efthymis.mandrill.api.users.MandrillUsersApi;
import com.efthymis.mandrill.api.webhooks.MandrillWebhooksApi;
import com.efthymis.mandrill.api.whitelists.MandrillWhitelistsApi;

/**
 * @since Mar 17, 2013
 */
public class MandrillApi {
	public static final String rootUrl = "https://mandrillapp.com/api/1.0/";

	private String key;
	private final MandrillUsersApi users;
	private final MandrillMessagesApi messages;
	private final MandrillTagsApi tags;
	private final MandrillRejectsApi rejects;
	private final MandrillWhitelistsApi whitelists;
	private final MandrillSendersApi senders;
	private final MandrillUrlsApi urls;
	private final MandrillTemplatesApi templates;
	private final MandrillWebhooksApi webhooks;
	private final MandrillSubaccountsApi subaccounts;
	private final MandrillInboundApi inbound;
	private final MandrillExportsApi exports;
	private final MandrillIpsApi ips;
	
	public MandrillApi(final String key) {
		this(key, rootUrl);
	}
	
	public MandrillApi(final String key, final String rootUrl) {
		this(key, rootUrl, new HttpClientRequestDispatcher());
	}
	public MandrillApi(String key, String rootUrl, RequestDispatcher requestDispatcher) {
		if(key == null) {
			throw new NullPointerException(
					"'key' is null; please provide Mandrill API key");
		}
		if(rootUrl == null) {
			throw new NullPointerException(
					String.format("'rootUrl' is null; please provide Mandrill URL (default: %s)", rootUrl));
		}
		this.key = key;
		users = new MandrillUsersApi(key, rootUrl, requestDispatcher);
		messages = new MandrillMessagesApi(key, rootUrl, requestDispatcher);
		tags = new MandrillTagsApi(key, rootUrl, requestDispatcher);
		rejects = new MandrillRejectsApi(key, rootUrl, requestDispatcher);
		whitelists = new MandrillWhitelistsApi(key, rootUrl, requestDispatcher);
		senders = new MandrillSendersApi(key, rootUrl, requestDispatcher);
		urls = new MandrillUrlsApi(key, rootUrl, requestDispatcher);
		templates = new MandrillTemplatesApi(key, rootUrl, requestDispatcher);
		webhooks = new MandrillWebhooksApi(key, rootUrl, requestDispatcher);
		subaccounts = new MandrillSubaccountsApi(key, rootUrl, requestDispatcher);
		inbound = new MandrillInboundApi(key, rootUrl, requestDispatcher);
		exports = new MandrillExportsApi(key, rootUrl, requestDispatcher);
		ips = new MandrillIpsApi(key, rootUrl, requestDispatcher);
	}

	/**
	 * @return Your Mandrill API key.
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * <p>Get access to 'users' calls.</p>
	 * @return An object with access to user calls.
	 */
	public MandrillUsersApi users() {
		return users;
	}
	
	public MandrillMessagesApi messages() {
		return messages;
	}
	
	public MandrillTagsApi tags() {
		return tags;
	}
	
	public MandrillRejectsApi rejects() {
		return rejects;
	}
	
	public MandrillWhitelistsApi whitelists() {
		return whitelists;
	}
	
	public MandrillSendersApi senders() {
		return senders;
	}
	
	public MandrillUrlsApi urls() {
		return urls;
	}
	
	public MandrillTemplatesApi templates() {
		return templates;
	}
	
	public MandrillWebhooksApi webhooks() {
		return webhooks;
	}
	
	public MandrillSubaccountsApi subaccounts() {
		return subaccounts;
	}
	
	public MandrillInboundApi inbound() {
		return inbound;
	}
	
	public MandrillExportsApi exports() {
		return exports;
	}
	
	public MandrillIpsApi ips() {
		return ips;
	}
	
}
