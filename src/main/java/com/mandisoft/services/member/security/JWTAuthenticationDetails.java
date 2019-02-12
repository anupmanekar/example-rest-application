package com.mandisoft.services.member.security;

public interface JWTAuthenticationDetails {

	public JWTClaimVO retrieveUserDetails(String authToken);
	
}
