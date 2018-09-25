/**
 *
 */
package com.efthymis.mandrill.api.users;

import java.io.IOException;

import com.efthymis.mandrill.api.Fixtures;
import com.efthymis.mandrill.api.MandrillTestCase;
import com.efthymis.mandrill.api.common.MandrillApiError;
import com.efthymis.mandrill.api.senders.MandrillSender;
import org.junit.Test;

import junit.framework.Assert;

public final class MandrillUsersApiTest extends MandrillTestCase {

	@Test
	public final void testInfo() throws IOException, MandrillApiError {
		mockResponse("/users/info.json", 200, Fixtures.Users.INFO_RESPONSE);

		final MandrillUserInfo userInfoResult = mandrillApi.users().info();
		Assert.assertNotNull(userInfoResult);
		Assert.assertNotNull(userInfoResult.getPublicId());
		Assert.assertNotNull(userInfoResult.getUsername());
		Assert.assertNotNull(userInfoResult.getCreatedAt());
		Assert.assertTrue(userInfoResult.getReputation() >= 0);
		Assert.assertTrue(userInfoResult.getReputation() <= 100);
		Assert.assertTrue(userInfoResult.getHourlyQuota() >= 0);
		Assert.assertNotNull(userInfoResult.getStats());
	}

	@Test
	public final void testPing() throws IOException, MandrillApiError {
		mockResponse("/users/ping.json", 200, Fixtures.Users.PING_RESPONSE);

		final String pong = mandrillApi.users().ping();
		Assert.assertEquals("PONG!", pong);
	}

	@Test
	public final void testPing2() throws IOException, MandrillApiError {
		mockResponse("/users/ping2.json", 200, Fixtures.Users.PING2_RESPONSE);

		final MandrillUserPingInfo pong = mandrillApi.users().ping2();
		Assert.assertNotNull(pong);
		Assert.assertEquals("PONG!", pong.getPING());
	}

	@Test
	public final void testSenders() throws IOException, MandrillApiError {
		mockResponse("/users/senders.json", 200, Fixtures.Users.SENDERS_RESPONSE);

		final MandrillSender[] senders = mandrillApi.users().senders();
		Assert.assertNotNull(senders);
		if (senders.length > 0) {
			Assert.assertNotNull(senders[0].getAddress());
			Assert.assertNotNull(senders[0].getCreatedAt());
		}
	}

}
