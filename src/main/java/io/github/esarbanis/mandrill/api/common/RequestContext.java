/**
 * 
 */
package io.github.esarbanis.mandrill.api.common;

import java.util.Map;

/**
 * 
 * @since Jan 7, 2013
 * @param <V> The type that response-data/ response-content is parsed to.
 */
public interface RequestContext<V> {
	
	/**
	 * @return The url for this request, as {@link String}.
	 */
	String getUrl();
	
	/**
	 * <p>Checks weather the response-status is as-expected 
	 * for this request.</p>
	 * @param httpResponseStatus The HTTP response status
	 * @return <code>true</code> if the response status is as expected,
	 * <code>false</code> otherwise.
	 */
	boolean validateResponseStatus(int httpResponseStatus);

	Class<V> getResponseContentType();

	Map<String, ? extends Object> getRequestParams();
	
}
