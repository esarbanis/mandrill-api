/**
 * 
 */
package io.github.esarbanis.mandrill.api.inbound;

import java.io.IOException;

import io.github.esarbanis.mandrill.api.MandrillTestCase;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import io.github.esarbanis.mandrill.api.common.MandrillApiError;

/**
 * @since Mar 22, 2013
 */
@Ignore("Need to wire mocks")
public final class MandrillInboundApiTest extends MandrillTestCase {
			
	@Test
	public final void testDomains() throws IOException, MandrillApiError {
		MandrillInboundDomain[] domains = mandrillApi.inbound().domains();
		Assert.assertNotNull(domains);
		for(MandrillInboundDomain d : domains) {
			Assert.assertNotNull(d.getDomain());
		}
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testRoutesWithoutDomain() throws IOException, MandrillApiError {
		mandrillApi.inbound().routes(null);
		Assert.fail();
	}
	
	@Test
	public final void testRoutes() throws IOException, MandrillApiError {
		MandrillInboundDomain[] domains = mandrillApi.inbound().domains();
		Assert.assertNotNull(domains);
		if(domains.length > 0) {
			MandrillMailboxRoute[] routes = mandrillApi.inbound().routes(
					domains[0].getDomain());
			Assert.assertNotNull(routes);
		}
	}
}
