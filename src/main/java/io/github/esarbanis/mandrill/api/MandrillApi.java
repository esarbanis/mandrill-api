/**
 * 
 */
package io.github.esarbanis.mandrill.api;

import io.github.esarbanis.mandrill.api.common.GsonJsonSerializer;
import io.github.esarbanis.mandrill.api.common.HttpClientRequestDispatcher;
import io.github.esarbanis.mandrill.api.common.JsonSerializer;
import io.github.esarbanis.mandrill.api.common.RequestDispatcher;
import io.github.esarbanis.mandrill.api.exports.MandrillExportsApi;
import io.github.esarbanis.mandrill.api.inbound.MandrillInboundApi;
import io.github.esarbanis.mandrill.api.ips.MandrillIpsApi;
import io.github.esarbanis.mandrill.api.messages.MandrillMessagesApi;
import io.github.esarbanis.mandrill.api.rejects.MandrillRejectsApi;
import io.github.esarbanis.mandrill.api.senders.MandrillSendersApi;
import io.github.esarbanis.mandrill.api.subaccounts.MandrillSubaccountsApi;
import io.github.esarbanis.mandrill.api.tags.MandrillTagsApi;
import io.github.esarbanis.mandrill.api.templates.MandrillTemplatesApi;
import io.github.esarbanis.mandrill.api.urls.MandrillUrlsApi;
import io.github.esarbanis.mandrill.api.users.MandrillUsersApi;
import io.github.esarbanis.mandrill.api.webhooks.MandrillWebhooksApi;
import io.github.esarbanis.mandrill.api.whitelists.MandrillWhitelistsApi;

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
		this(key, rootUrl, new HttpClientRequestDispatcher(), new GsonJsonSerializer());
	}
	public MandrillApi(String key, String rootUrl, RequestDispatcher requestDispatcher, JsonSerializer jsonSerializer) {
		if(key == null) {
			throw new NullPointerException(
					"'key' is null; please provide Mandrill API key");
		}
		if(rootUrl == null) {
			throw new NullPointerException(
					String.format("'rootUrl' is null; please provide Mandrill URL (default: %s)", rootUrl));
		}
		this.key = key;
		users = new MandrillUsersApi(key, rootUrl, requestDispatcher, jsonSerializer);
		messages = new MandrillMessagesApi(key, rootUrl, requestDispatcher, jsonSerializer);
		tags = new MandrillTagsApi(key, rootUrl, requestDispatcher, jsonSerializer);
		rejects = new MandrillRejectsApi(key, rootUrl, requestDispatcher, jsonSerializer);
		whitelists = new MandrillWhitelistsApi(key, rootUrl, requestDispatcher, jsonSerializer);
		senders = new MandrillSendersApi(key, rootUrl, requestDispatcher, jsonSerializer);
		urls = new MandrillUrlsApi(key, rootUrl, requestDispatcher, jsonSerializer);
		templates = new MandrillTemplatesApi(key, rootUrl, requestDispatcher, jsonSerializer);
		webhooks = new MandrillWebhooksApi(key, rootUrl, requestDispatcher, jsonSerializer);
		subaccounts = new MandrillSubaccountsApi(key, rootUrl, requestDispatcher, jsonSerializer);
		inbound = new MandrillInboundApi(key, rootUrl, requestDispatcher, jsonSerializer);
		exports = new MandrillExportsApi(key, rootUrl, requestDispatcher, jsonSerializer);
		ips = new MandrillIpsApi(key, rootUrl, requestDispatcher, jsonSerializer);
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
