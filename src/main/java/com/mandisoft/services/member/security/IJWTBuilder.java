
package com.mandisoft.services.member.security;

import java.util.Date;

public interface IJWTBuilder {

	/**
	 * This method will generate the token for authentication
	 * 
	 * @param jwtClaimVO
	 * @param expiry date of token
	 * @return string token
	 */
	public String createJWT(JWTClaimVO jwtClaimVO, Date expirationDate);

	/**
	 * This method will check for the valid token and expiration of a token.
	 * 
	 * @param jwtClaimVO
	 * @throws Exception
	 */
	public JWTClaimVO validateToken(String jwtToken);

	/**
	 * @param jwtToken
	 * @param expiryDate
	 * @return updated token
	 */
	public String updateToken(String jwtToken, Date expiryDate);

}
