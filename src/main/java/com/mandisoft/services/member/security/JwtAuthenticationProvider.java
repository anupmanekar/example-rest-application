package com.mandisoft.services.member.security;

import com.mandisoft.services.member.utility.AuthenticatedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	

	@Autowired
	private IJWTBuilder jwtBuilder;
	
	 @Override
	    public boolean supports(Class<?> authentication) {
	        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
	    }

	    @Override
	    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	    }

	    @Override
	    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
	        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
	        String token = jwtAuthenticationToken.getToken();
	        
	       JWTClaimVO parsedUser = jwtBuilder.validateToken(token);
	       
	      List<GrantedAuthority> authorityList = mapToGrantedAuthorities(parsedUser.getRoles());
	       return new AuthenticatedUser(parsedUser.getUserPk(), parsedUser.getUsername(), token,authorityList);
	    }
	    
	    private  List<GrantedAuthority> mapToGrantedAuthorities(String string) {
	    	List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
	    	/*for (String authority : string) {
	    		grantedAuthorities.add( new SimpleGrantedAuthority("ROLE_" + authority.toUpperCase()));
	    	}*/
	    	SimpleGrantedAuthority authority=new SimpleGrantedAuthority(string);
	    	grantedAuthorities.add(authority);
	        return grantedAuthorities;
	    }

}
