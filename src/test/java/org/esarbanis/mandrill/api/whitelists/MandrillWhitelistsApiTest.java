package org.esarbanis.mandrill.api.whitelists;

import java.io.IOException;

import junit.framework.Assert;

import org.esarbanis.mandrill.api.MandrillTestCase;
import org.esarbanis.mandrill.api.common.MandrillApiError;
import org.esarbanis.mandrill.api.whitelists.MandrillWhitelistEntry;
import org.junit.Test;

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
