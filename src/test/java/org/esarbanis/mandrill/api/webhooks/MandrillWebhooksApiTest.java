/**
 * 
 */
package org.esarbanis.mandrill.api.webhooks;

import java.io.IOException;

import junit.framework.Assert;

import org.junit.Test;

import org.esarbanis.mandrill.api.MandrillTestCase;
import org.esarbanis.mandrill.api.common.MandrillApiError;
import org.esarbanis.mandrill.api.webhooks.MandrillWebhook;

/**
 * @author rschreijer
 * @since Mar 22, 2013
 */
public final class MandrillWebhooksApiTest extends MandrillTestCase {
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		final MandrillWebhook[] webhooks = mandrillApi.webhooks().list();
		Assert.assertNotNull(webhooks);
		for(MandrillWebhook w : webhooks) {
			Assert.assertNotNull(w.getId());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testInfoWithoutId() throws IOException, MandrillApiError {
		mandrillApi.webhooks().info(null);
		Assert.fail();
	}
	
	@Test
	public final void testInfo() throws IOException, MandrillApiError {
		final MandrillWebhook[] webhooks = mandrillApi.webhooks().list();
		Assert.assertNotNull(webhooks);
		if(webhooks.length > 0) {
			final MandrillWebhook w = mandrillApi.webhooks().info(
					webhooks[0].getId());
			Assert.assertNotNull(w);
			Assert.assertEquals(webhooks[0].getId(), w.getId());
		}
	}

}
