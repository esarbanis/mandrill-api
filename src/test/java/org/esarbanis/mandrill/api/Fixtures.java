package org.esarbanis.mandrill.api;

public abstract class Fixtures {

	private Fixtures() {
	}

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
}
