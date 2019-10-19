package tn.esprit.crm.security;

import java.io.IOException;
import java.util.Base64;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;

/**
 * This filter will activate each time a request is made to the server
 * it will check the http request header for an authorization token
 * it will then proceed to verify the token
 * if the token is not valid http error messages will be sent in the 
 * response header
 * 
 * @author Derouiche
 *
 */

/**
* UnComment for the filter to work provider and prematching
*/
//@Provider
//@PreMatching
public class BasicAuthFilter implements ContainerRequestFilter {
	
	@Inject
	HttpServletRequest request;
	/**
	 * This will override the filter method in ContainerRequestFilter interface
	 */
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		// Get authorization token for request header
		String authHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
		// If token is null throw exception and send null token message back
		if (authHeader == null)
			throw new NotAuthorizedException("null_token");
		// If token not null decode using base64
		String token = parseToken(authHeader);
		// if token is not verified throw exception send back invalid token message
		if (verifyToken(token) == false) {
			throw new NotAuthorizedException("invalid_token");
		}
	}
	
	/**
	 * This method will decode the encoded token using base64
	 * 
	 * @param encodedBytes
	 * @return
	 */
	private String parseToken(String encodedBytes) {
		byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));
		return new String(decodedBytes);

	}
	
	/**
	 * This method will verify if the token is valid. If so it will create a session object
	 * 
	 * @param token
	 * @return
	 */
	private boolean verifyToken(String token) {
		String[] attributes=token.split(":");
		String username=attributes[0];
		String passwd=attributes[1];
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		
		return true;
	}

}
