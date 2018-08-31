package org.esarbanis.mandrill.api.common;

import java.io.IOException;

public interface RequestDispatcher {

	<T> T dispatch(final RequestModel<T> requestModel) throws MandrillApiError, IOException;

}
