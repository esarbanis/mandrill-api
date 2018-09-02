/**
 * 
 */
package io.github.esarbanis.mandrill.api.inbound;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;

import io.github.esarbanis.mandrill.api.common.MandrillApiError;
import io.github.esarbanis.mandrill.api.common.MandrillSubContext;
import io.github.esarbanis.mandrill.api.common.RequestDispatcher;

/**
 * @since Mar 19, 2013
 */
public class MandrillInboundApi extends MandrillSubContext {
	private final String key;
	private final String rootUrl;

	public MandrillInboundApi(final String key, final String url, RequestDispatcher requestDispatcher) {
		super(requestDispatcher);
		this.key = key;
		this.rootUrl = url;
	}

	/**
	 * <p>List the domains that have been configured for 
	 * inbound delivery.</p>
	 * @return The inbound domains associated with the account.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	 MandrillInboundDomain[] domains() 
			throws MandrillApiError, IOException {
		
		return query(
				rootUrl+ "inbound/domains.json", 
				paramsWithKey(key), 
				MandrillInboundDomain[].class);
		
	}
	
	/**
	 * <p>List the mailbox routes defined for an inbound domain.</p>
	 * @param domain The domain to check.
	 * @return The routes associated with the domain.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	 MandrillMailboxRoute[] routes(final String domain)
			throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("domain", domain);
		return query(rootUrl+ "inbound/routes.json", 
				params, MandrillMailboxRoute[].class);
		
	}
	
	/**
	 * <p>Take a raw MIME document destined for a domain with inbound 
	 * domains set up, and send it to the inbound hook exactly as if 
	 * it had been sent over SMTP $sparam string $to[] the email address 
	 * of the recipient.</p>
	 * @param rawMessage A full MIME document of an email message.
	 * @param to Optionally define the recipients to receive the 
	 * message &ndash; otherwise we'll use the To, Cc, and Bcc headers 
	 * provided in the document.
	 * @return An array of the information for each recipient in the 
	 * message (usually one) that matched an inbound route.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 * @since Mar 19, 2013
	 */
	 MandrillInboundRecipient[] sendRaw(
			final String rawMessage, final Collection<String> to) 
					throws MandrillApiError, IOException {
		
		return sendRaw(rawMessage, to, null, null, null);
		
	}
	
	/**
	 * <p>Take a raw MIME document destined for a domain with inbound 
	 * domains set up, and send it to the inbound hook exactly as if 
	 * it had been sent over SMTP $sparam string $to[] the email address 
	 * of the recipient.</p>
	 * @param rawMessage A full MIME document of an email message.
	 * @param to Optionally define the recipients to receive the 
	 * message &ndash; otherwise we'll use the To, Cc, and Bcc headers 
	 * provided in the document.
	 * @param mailFrom The address specified in the MAIL FROM stage 
	 * of the SMTP conversation. Required for the SPF check.
	 * @param helo The identification provided by the client mta in 
	 * the MTA state of the SMTP conversation. Required for the SPF check.
	 * @param clientAddress The remote MTA's ip address. Optional; 
	 * required for the SPF check.
	 * @return An array of the information for each recipient in the 
	 * message (usually one) that matched an inbound route.
	 * @throws MandrillApiError Mandrill API Error
	 * @throws IOException IO Error
	 */
	 MandrillInboundRecipient[] sendRaw(final String rawMessage, 
			final Collection<String> to, final String mailFrom, 
			final String helo, final String clientAddress) 
					throws MandrillApiError, IOException {
		
		final HashMap<String,Object> params = paramsWithKey(key);
		params.put("raw_message", rawMessage);
		params.put("to", to);
		params.put("mail_from", mailFrom);
		params.put("helo", helo);
		params.put("client_address", clientAddress);
		return query(rootUrl+ "inbound/send-raw.json", 
				params, MandrillInboundRecipient[].class);
		
	}
}
