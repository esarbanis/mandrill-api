package io.github.esarbanis.mandrill.api;

public abstract class Fixtures {

	private Fixtures() {
	}

	public static final String VALIDATION_ERROR_RESPONSE = "{\"status\":\"error\",\"code\":12,\"name\":\"ValidationError\",\"message\":\"The "
			+ "parameters passed to the API call are invalid or not provided when required\"}";

	public static class Users {

		public static final String INFO_RESPONSE =
				"{\"username\":\"myusername\",\"created_at\":\"2013-01-01 15:30:27\"," + "\"public_id\":\"aaabbbccc112233\","
						+ "\"reputation\":42,\"hourly_quota\":42,\"backlog\":42,\"stats\":{\"today\":{\"sent\":42,\"hard_bounces\":42,"
						+ "\"soft_bounces\":42,"
						+ "\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42},"
						+ "\"last_7_days\":{\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,"
						+ "\"opens\":42,"
						+ "\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42},\"last_30_days\":{\"sent\":42,\"hard_bounces\":42,"
						+ "\"soft_bounces\":42,"
						+ "\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42},"
						+ "\"last_60_days\":{\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,"
						+ "\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42},\"last_90_days\":{\"sent\":42,\"hard_bounces\":42,"
						+ "\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,"
						+ "\"unique_clicks\":42},\"all_time\":{\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,"
						+ "\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42}}}";

		public static final String PING_RESPONSE = "PONG!";

		public static final String PING2_RESPONSE = "{ PING: \"PONG!\"}";

		public static final String SENDERS_RESPONSE = "[{\"address\":\"sender.example@mandrillapp.com\",\"created_at\":\"2013-01-01 15:30:27\","
				+ "\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"clicks\":42,"
				+ "\"unique_opens\":42,\"unique_clicks\":42}]";

	}

	public static class Messages {

		public static final String SEND_NO_MESSAGE_ERROR_RESPONSE =
				"{\"status\":\"error\",\"code\":-1,\"name\":\"ValidationError\",\"message\":\"You must " + "specify a message value\"}";

		public static final String SEND_RESPONSE =
				"[{\"email\":\"recipient.email@example.com\",\"status\":\"sent\",\"reject_reason\":\"hard-bounce\","
						+ "\"_id\":\"abc123abc123abc123abc123abc123\"}]";

		public static final String SEND_TEMPLATE_RESPONSE = "[{\"email\":\"recipient.email@example.com\",\"status\":\"sent\","
				+ "\"reject_reason\":\"hard-bounce\",\"_id\":\"abc123abc123abc123abc123abc123\"}]";

		public static final String SEND_TEMPLATE_NO_TEMPLATE_ERROR_RESPONSE =
				"{\"status\":\"error\",\"code\":12,\"name\":\"Unknown_Template\",\"message\":\"The" + " requested template does not exist\"}";

		public static final String SEND_TEMPLATE_NO_SUBACCOUNT_ERROR_RESPONSE = "{\"status\":\"error\",\"code\":12,\"name\":\"Unknown_Subaccount\","
				+ "\"message\":\"The provided subaccount id does not exist.\"}";

		public static final String SEARCH_RESPONSE =
				"[\n" + "    {\n" + "        \"ts\": 1365190000,\n" + "        \"_id\": \"abc123abc123abc123abc123\",\n"
						+ "        \"sender\": \"sender@example.com\",\n" + "        \"template\": \"example-template\",\n"
						+ "        \"subject\": \"example subject\",\n" + "        \"email\": \"recipient.email@example.com\",\n"
						+ "        \"tags\": [\n" + "            \"password-reset\"\n" + "        ],\n" + "        \"opens\": 42,\n"
						+ "        \"opens_detail\": [\n" + "            {\n" + "                \"ts\": 1365190001,\n"
						+ "                \"ip\": \"55.55.55.55\",\n" + "                \"location\": \"Georgia, US\",\n"
						+ "                \"ua\": \"Linux/Ubuntu/Chrome/Chrome 28.0.1500.53\"\n" + "            }\n" + "        ],\n"
						+ "        \"clicks\": 42,\n" + "        \"clicks_detail\": [\n" + "            {\n" + "                \"ts\": "
						+ "1365190001,\n"
						+ "                \"url\": \"http://www.example.com\",\n" + "                \"ip\": \"55.55.55.55\",\n"
						+ "                \"location\": \"Georgia, US\",\n" + "                \"ua\": \"Linux/Ubuntu/Chrome/Chrome 28.0.1500"
						+ ".53\"\n"
						+ "            }\n" + "        ],\n" + "        \"state\": \"sent\",\n" + "        \"metadata\": {\n"
						+ "            \"user_id\": \"123\",\n" + "            \"website\": \"www.example.com\"\n" + "        }\n" + "    }\n" + "]";

		public static final String CONTENT_RESPONSE = "{\n" + "    \"ts\": 1365190000,\n" + "    \"_id\": \"abc123abc123abc123abc123\",\n"
				+ "    \"from_email\": \"sender@example.com\",\n" + "    \"from_name\": \"Sender Name\",\n"
				+ "    \"subject\": \"example subject\",\n" + "    \"to\": {\n" + "        \"email\": \"recipient.email@example.com\",\n"
				+ "        \"name\": \"Recipient Name\"\n" + "    },\n" + "    \"tags\": [\n" + "        \"password-reset\"\n" + "    ],\n"
				+ "    \"headers\": {\n" + "        \"Reply-To\": \"replies@example.com\"\n" + "    },\n" + "    \"text\": \"Some text content\",\n"
				+ "    \"html\": \"<p>Some HTML content</p>\",\n" + "    \"attachments\": [\n" + "        {\n"
				+ "            \"name\": \"example.txt\",\n" + "            \"type\": \"text/plain\",\n"
				+ "            \"content\": \"QSBzaW1wbGUgdGV4dCBzdHJpbmcgYXR0YWNobWVudA==\"\n" + "        }\n" + "    ]\n" + "}";

		public static final String PARSE_RESPONSE = "{\"subject\":\"Some Subject\",\"from_email\":\"sender@example.com\",\"from_name\":\"Sender "
				+ "Name\",\"to\":[{\"email\":\"recipient.email@example.com\",\"name\":\"Recipient Name\"}],"
				+ "\"headers\":{\"Reply-To\":\"replies@example.com\"},\"text\":\"Some text content\",\"html\":\"<p>Some HTML content</p>\","
				+ "\"attachments\":[{\"name\":\"example.txt\",\"type\":\"text/plain\",\"binary\":false,\"content\":\"example non-binary content\"}],"
				+ "\"images\":[{\"name\":\"IMAGEID\",\"type\":\"image/png\",\"content\":\"ZXhhbXBsZSBmaWxl\"}]}";

		public static final String PARSE_ERROR_RESPONSE = "{\"status\":\"error\",\"code\":-1,\"name\":\"ValidationError\",\"message\":\"You must "
				+ "specify a raw_message value\"}";
	}

	public static class Tags {
		public static final String LIST_RESPONSE = "[{\"tag\":\"example-tag\",\"reputation\":42,\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,"
				+ "\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"clicks\":42,\"unique_opens\":42,\"unique_clicks\":42}]";

		public static final String INVALID_TAG_NAME_RESPONSE = "{\"status\":\"error\",\"code\":-1,\"name\":\"Invalid_Tag_Name\",\"message\":\"The "
				+ "requested tag does not exist or contains invalid characters\"}";

		public static final String INFO_RESPONSE = "{\"tag\":\"example-tag\",\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,"
				+ "\"complaints\":42,\"unsubs\":42,\"opens\":42,\"clicks\":42,\"stats\":{\"today\":{\"sent\":42,\"hard_bounces\":42,"
				+ "\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,"
				+ "\"unique_clicks\":42},\"last_7_days\":{\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,"
				+ "\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42},\"last_30_days\":{\"sent\":42,"
				+ "\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,"
				+ "\"clicks\":42,\"unique_clicks\":42},\"last_60_days\":{\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,"
				+ "\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42},"
				+ "\"last_90_days\":{\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,"
				+ "\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42}}}";

		public static final String TIME_SERIES_RESPONSE = "[{\"time\":\"2013-01-01 15:00:00\",\"sent\":42,\"hard_bounces\":42,\"soft_bounces\":42,"
				+ "\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,\"unique_clicks\":42}]";

		public static final String ALL_TIME_SERIES_RESPONSE = "[{\"time\":\"2013-01-01 15:00:00\",\"sent\":42,\"hard_bounces\":42,"
				+ "\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"unique_opens\":42,\"clicks\":42,"
				+ "\"unique_clicks\":42}]";
	}

	public static class Rejects {

		public static final String LIST_RESPONSE = "[{\"email\":\"example email\",\"reason\":\"hard-bounce\",\"detail\":\"550 mailbox does not "
				+ "exist\",\"created_at\":\"2013-01-01 15:30:27\",\"last_event_at\":\"2013-01-01 15:30:27\",\"expires_at\":\"2013-01-01 15:30:49\","
				+ "\"expired\":false,\"sender\":{\"address\":\"sender.example@mandrillapp.com\",\"created_at\":\"2013-01-01 15:30:27\",\"sent\":42,"
				+ "\"hard_bounces\":42,\"soft_bounces\":42,\"rejects\":42,\"complaints\":42,\"unsubs\":42,\"opens\":42,\"clicks\":42,"
				+ "\"unique_opens\":42,\"unique_clicks\":42},\"subaccount\":\"example subaccount\"}]";
	}

	public static class Whitelists {

		public static final String LIST_RESPONSE = "[{\"email\":\"example email\",\"detail\":\"Whitelisted internal address\","
				+ "\"created_at\":\"2013-01-01 15:30:32\"}]";
	}
}
