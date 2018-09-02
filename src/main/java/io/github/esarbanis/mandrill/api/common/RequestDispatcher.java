package io.github.esarbanis.mandrill.api.common;

import java.io.IOException;

public interface RequestDispatcher {

	<T> T dispatch(final RequestContext<T> requestContext) throws MandrillApiError, IOException;

}
