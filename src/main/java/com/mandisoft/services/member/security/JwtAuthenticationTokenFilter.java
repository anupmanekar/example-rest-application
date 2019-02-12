package com.mandisoft.services.member.security;

import com.mandisoft.services.member.utility.AuthenticatedUser;
import com.mandisoft.services.member.utility.constants.ApplicationConstants;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.utility.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

    public JwtAuthenticationTokenFilter() {
        super("/**");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
	      final HttpServletRequest request = (HttpServletRequest) req;
	      final HttpServletResponse response = (HttpServletResponse) res;
	      if(request.getMethod().equals("OPTIONS")) {
	    	  chain.doFilter(request, response);
	      } else {
	    	  super.doFilter(request, response, chain);
	      }
      
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response){
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
        	throw new ApplicationException(ApplicationConstants.UNAUTHORIZED, ErrorCode.ACCESS_DENIED.getCodeId(), HttpStatus.UNAUTHORIZED);
            
        }

        String authToken = header.substring(7);

        JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

        return getAuthenticationManager().authenticate(authRequest);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult)
            throws IOException, ServletException {
        super.successfulAuthentication(request, response, chain, authResult);

        AuthenticatedUser authenticatedUser = (AuthenticatedUser) authResult.getPrincipal();
        
        chain.doFilter(request, response);

 
    }
}