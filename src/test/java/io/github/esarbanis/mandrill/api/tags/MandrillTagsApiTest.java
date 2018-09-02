/**
 *
 */
package io.github.esarbanis.mandrill.api.tags;

import java.io.IOException;

import io.github.esarbanis.mandrill.api.Fixtures;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import io.github.esarbanis.mandrill.api.MandrillTestCase;
import io.github.esarbanis.mandrill.api.common.MandrillApiError;
import io.github.esarbanis.mandrill.api.urls.MandrillTimeSeries;

public final class MandrillTagsApiTest extends MandrillTestCase {

	@Test
	public final void testList() throws IOException, MandrillApiError {
		mockResponse("/tags/list.json", 200, Fixtures.Tags.LIST_RESPONSE);

		final MandrillTag[] tags = mandrillApi.tags().list();
		Assert.assertNotNull(tags);
		for (MandrillTag tag : tags) {
			Assert.assertNotNull(tag.getTag());
			Assert.assertNull(tag.getStats());
		}
	}

	@Test(expected = MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mockResponse("/tags/delete.json", 400, Fixtures.Tags.INVALID_TAG_NAME_RESPONSE);
		mandrillApi.tags().delete(null);
		Assert.fail();
	}

	@Test(expected = MandrillApiError.class)
	public final void testInfo01() throws IOException, MandrillApiError {
		mockResponse("/tags/info.json", 400, Fixtures.Tags.INVALID_TAG_NAME_RESPONSE);

		mandrillApi.tags().info(null);
		Assert.fail();
	}

	@Test
	public final void testInfo02() throws IOException, MandrillApiError {
		mockResponse("/tags/info.json", 200, Fixtures.Tags.INFO_RESPONSE);

		final MandrillTag tag = mandrillApi.tags().info("sometag");
		Assert.assertNotNull(tag.getTag());
		Assert.assertNotNull(tag.getStats());
	}

	@Test(expected = MandrillApiError.class)
	public final void testTimeSeries01() throws IOException, MandrillApiError {
		mockResponse("/tags/time-series.json", 400, Fixtures.Tags.INVALID_TAG_NAME_RESPONSE);

		mandrillApi.tags().timeSeries(null);
		Assert.fail();
	}

	@Test
	public final void testTimeSeries02() throws IOException, MandrillApiError {
		mockResponse("/tags/time-series.json", 200, Fixtures.Tags.TIME_SERIES_RESPONSE);

		final MandrillTimeSeries[] series = mandrillApi.tags().timeSeries("sometag");
		Assert.assertNotNull(series);
		for (MandrillTimeSeries s : series) {
			Assert.assertNotNull(s.getTime());
		}
	}

	@Test
	public final void testAllTimeSeries() throws IOException, MandrillApiError {
		mockResponse("/tags/all-time-series.json", 200, Fixtures.Tags.ALL_TIME_SERIES_RESPONSE);

		final MandrillTimeSeries[] series = mandrillApi.tags().allTimeSeries();
		Assert.assertNotNull(series);
		for (MandrillTimeSeries s : series) {
			Assert.assertNotNull(s.getTime());
		}
	}

}
