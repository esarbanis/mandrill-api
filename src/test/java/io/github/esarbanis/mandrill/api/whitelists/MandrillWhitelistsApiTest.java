package io.github.esarbanis.mandrill.api.whitelists;

import java.io.IOException;

import io.github.esarbanis.mandrill.api.Fixtures;
import io.github.esarbanis.mandrill.api.MandrillTestCase;
import junit.framework.Assert;

import io.github.esarbanis.mandrill.api.common.MandrillApiError;

import org.junit.Ignore;
import org.junit.Test;

public final class MandrillWhitelistsApiTest extends MandrillTestCase {
	
	@Test(expected= MandrillApiError.class)
	public final void testAdd() throws IOException, MandrillApiError {
		mockResponse("/whitelists/add.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.whitelists().add(null);
		Assert.fail();
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		mockResponse("/whitelists/list.json", 200, Fixtures.Whitelists.LIST_RESPONSE);

		final MandrillWhitelistEntry[] entries =
				mandrillApi.whitelists().list(null);
		Assert.assertNotNull(entries);
		Assert.assertTrue(entries.length > 0);
		for(MandrillWhitelistEntry entry : entries) {
			Assert.assertNotNull(entry.getEmail());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mockResponse("/whitelists/delete.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.whitelists().delete(null);
		Assert.fail();
	}
	
}
