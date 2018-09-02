package io.github.esarbanis.mandrill.api.whitelists;

import java.io.IOException;

import io.github.esarbanis.mandrill.api.MandrillTestCase;
import junit.framework.Assert;

import io.github.esarbanis.mandrill.api.common.MandrillApiError;

import org.junit.Ignore;
import org.junit.Test;

@Ignore("Need to wire mocks")
public final class MandrillWhitelistsApiTest extends MandrillTestCase {
	
	@Test(expected= MandrillApiError.class)
	public final void testAdd() throws IOException, MandrillApiError {
		mandrillApi.whitelists().add(null);
		Assert.fail();
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillWhitelistEntry[] entries =
				mandrillApi.whitelists().list(null);
		Assert.assertNotNull(entries);
		for(MandrillWhitelistEntry entry : entries) {
			Assert.assertNotNull(entry.getEmail());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.whitelists().delete(null);
		Assert.fail();
	}
	
}
