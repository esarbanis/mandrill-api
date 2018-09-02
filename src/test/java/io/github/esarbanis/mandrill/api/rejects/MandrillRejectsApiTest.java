/**
 * 
 */
package io.github.esarbanis.mandrill.api.rejects;

import java.io.IOException;

import io.github.esarbanis.mandrill.api.MandrillTestCase;
import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import io.github.esarbanis.mandrill.api.common.MandrillApiError;

/**
 * @since Mar 21, 2013
 */
@Ignore("Need to wire mocks")
public final class MandrillRejectsApiTest extends MandrillTestCase {
	
	@Test(expected=MandrillApiError.class)
	public final void testAdd() throws IOException, MandrillApiError {
		mandrillApi.rejects().add(null, null, null);
		Assert.fail();
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		Assert.assertNotNull( mandrillApi.rejects().list(null, null) );
		
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mandrillApi.rejects().delete(null);
		Assert.fail();
	}

}
