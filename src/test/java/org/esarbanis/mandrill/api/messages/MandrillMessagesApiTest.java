/**
 *
 */
package org.esarbanis.mandrill.api.messages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.esarbanis.mandrill.api.Fixtures;
import org.esarbanis.mandrill.api.MandrillTestCase;
import org.esarbanis.mandrill.api.common.GsonJsonSerializer;
import org.esarbanis.mandrill.api.common.JsonSerializer;
import org.esarbanis.mandrill.api.common.MandrillApiError;
import org.esarbanis.mandrill.api.messages.MandrillMessage.Recipient;
import org.esarbanis.mandrill.api.messages.MandrillMessage.Recipient.Type;
import org.esarbanis.mandrill.api.messages.MandrillMessageInfo.SMTPEvent;
import org.esarbanis.mandrill.api.templates.MandrillTemplate;
import org.junit.Assume;
import org.junit.Test;

import junit.framework.Assert;

/**
 * <p>Tests for the messages api implementations.</p>
 *

 * @since Mar 21, 2013
 */
public final class MandrillMessagesApiTest extends MandrillTestCase {

	JsonSerializer jsonSerializer = new GsonJsonSerializer();

	@Test(expected = MandrillApiError.class)
	public final void testSend01() throws IOException, MandrillApiError {
		mockResponse("/messages/send.json", 400, Fixtures.Messages.SEND_NO_MESSAGE_ERROR_RESPONSE);

		mandrillApi.messages().send(null, null);
		Assert.fail();
	}

	@Test
	public final void testSend02() throws IOException, MandrillApiError {
		mockResponse("/messages/send.json", 200, Fixtures.Messages.SEND_RESPONSE);

		Recipient to = new Recipient();
		to.setEmail(mailToAddress());
		to.setType(Type.TO);
		List<Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
		recipients.add(to);
		MandrillMessage message = new MandrillMessage();
		message.setFromEmail("from@test.com");
		message.setTo(recipients);
		MandrillMessageStatus[] status = mandrillApi.messages().send(message, false);
		Assert.assertNotNull(status);
		Assert.assertTrue("sent".equals(status[0].getStatus()) || "rejected".equals(status[0].getStatus()) || "queued".equals(status[0].getStatus()));
	}

	@Test(expected = MandrillApiError.class)
	public final void testSendTemplate01() throws IOException, MandrillApiError {
		mockResponse("/messages/send-template.json", 400, Fixtures.Messages.SEND_TEMPLATE_NO_TEMPLATE_ERROR_RESPONSE);

		final HashMap<String, String> templateContent = new HashMap<String, String>();
		templateContent.put("test", "value");
		final MandrillMessage message = new MandrillMessage();
		mandrillApi.messages().sendTemplate(null, templateContent, message, null);
		Assert.fail();
	}

	@Test(expected = MandrillApiError.class)
	public final void testSendTemplate02() throws IOException, MandrillApiError {
		mockResponse("/messages/send-template.json", 400, Fixtures.Messages.SEND_NO_MESSAGE_ERROR_RESPONSE);

		final HashMap<String, String> templateContent = new HashMap<String, String>();
		templateContent.put("test", "value");
		mandrillApi.messages().sendTemplate("bvy38q34v93vzn39u4bvu9ewvbi349", templateContent, null, null);
		Assert.fail();
	}

	@Test(expected = MandrillApiError.class)
	public final void testSendTemplate03() throws IOException, MandrillApiError {
		mockResponse("/messages/send-template.json", 400, Fixtures.Messages.SEND_TEMPLATE_NO_SUBACCOUNT_ERROR_RESPONSE);

		final MandrillMessage message = new MandrillMessage();
		mandrillApi.messages().sendTemplate("bvy38q34v93vzn39u4bvu9ewvbi349", null, message, null);
		Assert.fail();
	}

	@Test
	public final void testSendTemplate04() throws IOException, MandrillApiError {
		mockResponse("/messages/send-template.json", 200, Fixtures.Messages.SEND_TEMPLATE_RESPONSE);

		String templateName = "lutung_templatename_unit_test_sendTemplate04_" + System.currentTimeMillis();

		Recipient to = new Recipient();
		to.setEmail(mailToAddress());
		to.setType(Type.TO);
		List<Recipient> recipients = new ArrayList<MandrillMessage.Recipient>();
		recipients.add(to);
		MandrillMessage message = new MandrillMessage();
		message.setFromEmail("from@test.com");
		message.setTo(recipients);
		MandrillMessageStatus[] status = mandrillApi.messages().sendTemplate(templateName, null, message, null);
		Assert.assertNotNull(status);

	}

	@Test
	public final void testSearch01() throws IOException, MandrillApiError {
		mockResponse("/messages/search.json", 200, Fixtures.Messages.SEARCH_RESPONSE);
		Assert.assertNotNull(mandrillApi.messages().search(null));
	}

	@Test
	public final void testSearch02() throws IOException, MandrillApiError {
		mockResponse("/messages/search.json", 200, Fixtures.Messages.SEARCH_RESPONSE);

		final MandrillSearchMessageParams params = new MandrillSearchMessageParams();
		params.setQuery("email:test.com");
		final MandrillMessageInfo[] info = mandrillApi.messages().search(params);
		Assert.assertNotNull(info);
		for (MandrillMessageInfo i : info) {
			Assert.assertNotNull(i.getId());
			Assert.assertNotNull(i.getSender());
		}
	}

	@Test
	public void testContent01() throws Exception {
		mockResponse("/messages/content.json", 200, Fixtures.Messages.CONTENT_RESPONSE);

		MandrillMessageContent content = mandrillApi.messages().content("abc123abc123abc123abc123");
		Assert.assertNotNull(content);
	}

	@Test
	public void testParse01() throws IOException, MandrillApiError {
		mockResponse("/messages/parse.json", 200, Fixtures.Messages.PARSE_RESPONSE);

		String testUnparsedMsg = "From: sender@example.com To: recipient.email@example.com Subject: Some Subject\\n\\nSome text content";
		MandrillMessage parsedMessage = mandrillApi.messages().parse(testUnparsedMsg);
		Assume.assumeNotNull(parsedMessage);
		Assert.assertEquals(1, parsedMessage.getTo().size());
		Assert.assertEquals("recipient.email@example.com", parsedMessage.getTo().get(0).getEmail());
		Assert.assertEquals("Some Subject", parsedMessage.getSubject());
		Assert.assertEquals("Some text content", parsedMessage.getText());
	}

	@Test(expected = MandrillApiError.class)
	public void testParse02() throws IOException, MandrillApiError {
		mockResponse("/messages/parse.json", 400, Fixtures.Messages.PARSE_ERROR_RESPONSE);

		MandrillMessage parsedMessage = mandrillApi.messages().parse(null);
		Assert.fail();
	}

	@Test
	public void testSmtpInfoResponse() {
		String responseString =
				"{\"ts\":1234567890," + "\"_id\":\"12345678901234567890123456789012\"," + "\"state\":\"sent\"," + "\"subject\":\"Subject\","
						+ "\"email\":\"test@test.com\"," + "\"tags\":[\"deliverable\"]," + "\"opens\":0," + "\"clicks\":0," + "\"smtp_events\":["
						+ "{\"ts\":1234567890,\"type\":\"sent\",\"diag\":\"250 Queued mail for delivery\","
						+ "\"source_ip\":\"127.0.0.1\",\"destination_ip\":\"127.0.0.2\",\"size\":12345},"
						+ "{\"ts\":1234567890,\"type\":\"sent\",\"diag\":\"250 Queued mail for delivery\","
						+ "\"source_ip\":\"127.0.0.1\",\"destination_ip\":\"127.0.0.2\",\"size\":12345}]," + "\"resends\":[],"
						+ "\"sender\":\"hello@hello.com\"," + "\"template\":null," + "\"metadata\":{}," + "\"opens_detail\":[],"
						+ "\"clicks_detail\":[]}";

		MandrillMessageInfo m = jsonSerializer.fromJsonString(responseString, MandrillMessageInfo.class);
		Assert.assertEquals(2, m.getSmtpEvents().size());
		SMTPEvent event = m.getSmtpEvents().get(1);
		Assert.assertEquals(1234567890, (int) event.getTs());
		Assert.assertEquals("sent", event.getType());
		Assert.assertEquals("250 Queued mail for delivery", event.getDiag());
		Assert.assertEquals("127.0.0.1", event.getSourceIp());
		Assert.assertEquals("127.0.0.2", event.getDestinationIp());
		Assert.assertEquals(12345, event.getSize());

	}
}
