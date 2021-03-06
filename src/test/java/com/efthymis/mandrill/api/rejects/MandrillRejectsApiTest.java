/**
 * 
 */
package com.efthymis.mandrill.api.rejects;

import java.io.IOException;

import com.efthymis.mandrill.api.Fixtures;
import com.efthymis.mandrill.api.MandrillTestCase;
import junit.framework.Assert;

import org.junit.Test;

import com.efthymis.mandrill.api.common.MandrillApiError;

public final class MandrillRejectsApiTest extends MandrillTestCase {
	
	@Test(expected= MandrillApiError.class)
	public final void testAdd() throws IOException, MandrillApiError {
		mockResponse("/rejects/add.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.rejects().add(null, null, null);
		Assert.fail();
	}
	
	@Test
	public final void testList() throws IOException, MandrillApiError {
		mockResponse("/rejects/list.json", 200, Fixtures.Rejects.LIST_RESPONSE);

		Assert.assertNotNull( mandrillApi.rejects().list(null, null) );
		
	}
	
	@Test(expected=MandrillApiError.class)
	public final void testDelete() throws IOException, MandrillApiError {
		mockResponse("/rejects/delete.json", 400, Fixtures.VALIDATION_ERROR_RESPONSE);

		mandrillApi.rejects().delete(null);
		Assert.fail();
	}

}
