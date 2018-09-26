/**
 *
 */
package com.efthymis.mandrill.api.senders;

import java.io.IOException;

import com.efthymis.mandrill.api.Fixtures;
import com.efthymis.mandrill.api.MandrillTestCase;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import com.efthymis.mandrill.api.common.MandrillApiError;
import com.efthymis.mandrill.api.urls.MandrillTimeSeries;

public final class MandrillSendersApiTest extends MandrillTestCase {

	@Test
	public final void testList() throws IOException, MandrillApiError {
		mockResponse("/senders/list.json", 200, Fixtures.Senders.LIST_RESPONSE);

		final MandrillSender[] senders = mandrillApi.senders().list();
		Assert.assertNotNull(senders);
		for (MandrillSender s : senders) {
			Assert.assertNotNull(s.getAddress());
		}
	}

	@Test
	public final void testDomains() throws IOException, MandrillApiError {
		mockResponse("/senders/domains.json", 200, Fixtures.Senders.DOMAINS_RESPONSE);

		final MandrillDomain[] domains = mandrillApi.senders().domains();
		Assert.assertNotNull(domains);
		for (MandrillDomain d : domains) {
			Assert.assertNotNull(d.getDomain());
		}
	}

	@Test(expected = MandrillApiError.class)
	public final void testAddDomain() throws IOException, MandrillApiError {
		mockResponse("/senders/add-domain.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.senders().addDomain(null);
	}

	@Test(expected = MandrillApiError.class)
	public final void testInfo01() throws IOException, MandrillApiError {
		mockResponse("/senders/info.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.senders().info(null);
		Assert.fail();
	}

	@Test
	public final void testInfo02() throws IOException, MandrillApiError {
		mockResponse("/senders/info.json", 200, Fixtures.Senders.INFO_RESPONSE);

		final MandrillSender s = mandrillApi.senders().info("sender.example@mandrillapp.com");
		Assert.assertNotNull(s);
		Assert.assertNotNull(s.getAddress());
		Assert.assertNotNull(s.getCreatedAt());
	}

	@Test(expected = MandrillApiError.class)
	public final void testTimeSeries01() throws MandrillApiError, IOException {
		mockResponse("/senders/time-series.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.senders().timeSeries(null);
		Assert.fail();
	}

	@Test
	public final void testTimeSeries02() throws MandrillApiError, IOException {
		mockResponse("/senders/time-series.json", 200, Fixtures.Senders.TIME_SERIES_RESPONSE);

		final MandrillTimeSeries[] series = mandrillApi.senders().timeSeries("sender.example@mandrillapp.com");
		Assert.assertNotNull(series);
		for (MandrillTimeSeries s : series) {
			Assert.assertNotNull(s.getTime());
		}
	}

}
