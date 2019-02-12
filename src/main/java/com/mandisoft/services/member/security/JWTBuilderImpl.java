package com.mandisoft.services.member.security;

import com.mandisoft.services.member.utility.ApplicationLogger;
import com.mandisoft.services.member.utility.LocaleMessageUtility;
import com.mandisoft.services.member.utility.constants.ApplicationConstants;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.utility.exception.ErrorCode;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;


/**
 * This class implements all the abstract methods of IJWTBuilder interface
 * containing the generate token and validate same.
 *
 */
@Component("jwtBuilder")
@PropertySource(value = "jwt.properties")
public class JWTBuilderImpl implements IJWTBuilder {
	
	private static final ApplicationLogger LOGGER = new ApplicationLogger(JWTBuilderImpl.class);

	private static final String ROLES = "role";

	private static final String CUSTOMER_ID = "CustomerID";

	private static final String MEMBER_PK = "MemberPK";

	private static final String USER_PK = "UserPK";

	private static final String NAME = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name";

	private static final String NAME_IDENTIFIRE = "http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier";

	private static final String SUBJECT = "sub";

	private static final String TOKEN_EXPIRED = "token.expired";

	private static final String SCOPE = "scope";
	
	private static final String ISSUER = "iss";
	
	private static final String FIRST_NAME = "firstName";
	
	private static final String LAST_NAME = "lastName";
	
	private static final String AGE = "age";
	
	private static final String GENDER = "gender";

	@Autowired
	LocaleMessageUtility messageUtility;

	@Value("${javastartup.secretKey}")
	public String secret;
	// public String secret = "jaD8nvRN6CWC6vVM3M5vy1PF/gxEj7ODHYtK0qFkEIM=";

	@Override
	public String createJWT(JWTClaimVO jwtClaimVO, Date expirationDate) {

		try {
			if (secret != null) {
				JwtBuilder builder = populateClaims(jwtClaimVO);

				// if it has been specified, let's add the expiration

				builder.setExpiration(expirationDate);
				// Builds the JWT and serializes it to a compact, URL-safe
				// string
				return builder.compact();
			} else {
				throw new ApplicationException("secret key not found", ErrorCode.INTERNAL_SERVER_ERROR.getCodeId(), HttpStatus.INTERNAL_SERVER_ERROR);

			}
		} catch (Exception e) {
			throw new ApplicationException("Error while creating token",ErrorCode.INTERNAL_SERVER_ERROR.getCodeId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	private JwtBuilder populateClaims(JWTClaimVO jwtClaimVO) {

		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		// We will sign our JWT with our APIKey secret
		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(SUBJECT, jwtClaimVO.getUsername());
		claims.put(NAME_IDENTIFIRE, jwtClaimVO.getUsername());
		claims.put(NAME, jwtClaimVO.getUsername());

		claims.put(USER_PK, jwtClaimVO.getUserPk());
		claims.put(SCOPE, jwtClaimVO.getScope());
		claims.put(ROLES, jwtClaimVO.getRoles());
		claims.put(ISSUER, jwtClaimVO.getIssuer());
		claims.put(FIRST_NAME, jwtClaimVO.getFirstName());
		claims.put(LAST_NAME, jwtClaimVO.getLastName());
		claims.put(AGE, jwtClaimVO.getAge());
		claims.put(GENDER, jwtClaimVO.getGender());

		JwtBuilder builder = Jwts.builder().setIssuedAt(now).signWith(signatureAlgorithm, signingKey).setClaims(claims);
		return builder;
	}

	public JWTClaimVO validateToken(String jwtToken) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		Claims claims = null;
		JWTClaimVO jwtClaimVO = null;
		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("" + secret))
					.parseClaimsJws(jwtToken).getBody();
		} catch (JwtException jwtException) {
			throw new ApplicationException(ApplicationConstants.TOKEN_INVALID,ErrorCode.INVALID_TOKEN.getCodeId(), HttpStatus.UNAUTHORIZED);
			
		}

		if (claims.getExpiration().compareTo(now) > 0 || claims.getExpiration().compareTo(now) == 0) {
			jwtClaimVO = getJWTClaimVO(claims);
			LOGGER.info("Request received for issuer: " + jwtClaimVO.getIssuer() + " And Roles assigned are " + jwtClaimVO.getRoles());
		} else { 
			throw new ApplicationException(ApplicationConstants.TOKEN_INVALID,ErrorCode.INVALID_TOKEN.getCodeId(), HttpStatus.UNAUTHORIZED);
		}

		return jwtClaimVO;
	}

	@SuppressWarnings("unchecked")
	private JWTClaimVO getJWTClaimVO(Claims claims) {
		try {
			JWTClaimVO jwtClaimVO = new JWTClaimVO();
			
			String issuer = (String) claims.get(ISSUER);
			
			if (issuer != null ) {
				jwtClaimVO.setIssuer(issuer);
			}
			
			jwtClaimVO.setUsername(claims.get("sub").toString());


			Object role = claims.get("role");
			if (role instanceof String) {
				/*ArrayList<String> roles = new ArrayList<String>();
				roles.add(role.toString());*/
				jwtClaimVO.setRoles(role.toString());
			} else if (role instanceof List) {
				jwtClaimVO.setRoles((String) role);
			}

			//jwtClaimVO.setScope(claims.get("scope").toString());
			//jwtClaimVO.setUserPk(Long.valueOf(claims.get("UserPK").toString()));

			return jwtClaimVO;
		} catch (Exception exception) {
			throw new ApplicationException(ApplicationConstants.TOKEN_INVALID,ErrorCode.INVALID_TOKEN.getCodeId(), HttpStatus.UNAUTHORIZED);
		}
	}

	public String generateToken(String jwtToken) {
		long nowMillis = System.currentTimeMillis();
		Date now = new Date(nowMillis);
		// String email = null;
		Claims claims = null;

		try {
			claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("" + secret))
					.parseClaimsJws(jwtToken).getBody();
		} catch (JwtException jwtException) {
			throw new ApplicationException(ApplicationConstants.TOKEN_INVALID,ErrorCode.INVALID_TOKEN.getCodeId(), HttpStatus.UNAUTHORIZED);
		}

		claims.put("sub", "anjanarajagopalan@yahoo.com");
		claims.put("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/nameidentifier",
				"anjanarajagopalan@yahoo.com");
		claims.put("http://schemas.xmlsoap.org/ws/2005/05/identity/claims/name", "anjanarajagopalan@yahoo.com");

		claims.put("UserPK", 19094);
		claims.put("MemberPK", 120048);
		claims.put("CustomerID", 517);

		List<String> a = new ArrayList<String>();
		a.add("SUPERADMIN");
		// a.add("IdentityServerUser");
		claims.put("role", a);

		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

		byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
		Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

		JwtBuilder builder = Jwts.builder().setIssuedAt(now).signWith(signatureAlgorithm, signingKey).setClaims(claims);

		// if it has been specified, let's add the expiration
		Calendar cal = Calendar.getInstance();
		cal.setTime(new java.sql.Timestamp(cal.getTime().getTime()));
		cal.add(Calendar.HOUR, 2400);

		builder.setExpiration(new Date(cal.getTime().getTime()));

		return builder.compact();
	}

	@Override
	public String updateToken(String jwtToken, Date expiryDate) {
		try {
			Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("" + secret))
					.parseClaimsJws(jwtToken).getBody();
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);

			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

			// We will sign our JWT with our APIKey secret
			byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			JwtBuilder builder = Jwts.builder().setIssuedAt(now).signWith(signatureAlgorithm, signingKey)
					.setClaims(claims);
			builder.setExpiration(expiryDate);
			// Builds the JWT and serializes it to a compact, URL-safe
			// string
			return builder.compact();
		} catch (Exception e) {
			throw new ApplicationException("Exception while generating token",ErrorCode.INTERNAL_SERVER_ERROR.getCodeId(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

//	public static void main(String[] args) {
//		String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUyIsImF1ZCI6InVzZXJzIiwibmJmIjoxNDYwNjQzNjgxLCJleHAiOjE0NzIzMTA1ODIsImNsaWVudF9pZCI6InJvY2xpZW50Iiwic2NvcGUiOiJyZWFkIiwic3ViIjoiYW5qYW5hcmFqYWdvcGFsYW5AeWFob28uY29tIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZWlkZW50aWZpZXIiOiJhbmphbmFyYWphZ29wYWxhbkB5YWhvby5jb20iLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYW5qYW5hcmFqYWdvcGFsYW5AeWFob28uY29tIiwiVXNlclBLIjoxMjAwMjcsIk1lbWJlclBLIjoxMjAwNDgsIkN1c3RvbWVySUQiOjUxNywiU2Vzc2lvbkdVSUQiOiJmMzM1NDc1Ni1kNmQ5LTRiZTEtYTlhMS03ZjRjNTRhNWM4NWUiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiTWVtYmVyUG9ydGFsLlVzZXIiLCJJZGVudGl0eVNlcnZlclVzZXIiXSwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9hdXRoZW50aWNhdGlvbm1ldGhvZCI6Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9hdXRoZW50aWNhdGlvbm1ldGhvZC9wYXNzd29yZCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvYXV0aGVudGljYXRpb25pbnN0YW50IjoiMjAxNi0wNC0xNFQxNDoyMToyMC4xNjBaIn0.eTC6Ka56n0abexoaFUDpWpoUDJxLGwgjGQy76cWSpoo";
//
//		/*
//		 * JWTBuilderImpl j = new JWTBuilderImpl();
//		 * System.out.println(j.generateToken(token));
//		 */
//		JWTBuilderImpl j = new JWTBuilderImpl();
//		// String nirajToken
//		// ="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUyIsImF1ZCI6InVzZXJzIiwibmJmIjoxNDY1OTg2NzA5LCJleHAiOjE0NjU5OTAzMDksImNsaWVudF9pZCI6InJvY2xpZW50Iiwic2NvcGUiOiJyZWFkIiwic3ViIjoiYWRtaW4iLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6ImFkbWluIiwiaHR0cDovL3NjaGVtYXMueG1sc29hcC5vcmcvd3MvMjAwNS8wNS9pZGVudGl0eS9jbGFpbXMvbmFtZSI6ImFkbWluIiwiVXNlclBLIjoiMTkwOTQiLCJDdXN0b21lcklEIjoiNTE3IiwiU2Vzc2lvbkdVSUQiOiI1NWQ2NjFkYy1lODdiLTQ4YTMtOGRlZi1kYWY1NzI2NDQxYjgiLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJTVVBFUkFETUlOIiwiaHR0cDovL3NjaGVtYXMubWljcm9zb2Z0LmNvbS93cy8yMDA4LzA2L2lkZW50aXR5L2NsYWltcy9hdXRoZW50aWNhdGlvbm1ldGhvZCI6Imh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9hdXRoZW50aWNhdGlvbm1ldGhvZC9wYXNzd29yZCIsImh0dHA6Ly9zY2hlbWFzLm1pY3Jvc29mdC5jb20vd3MvMjAwOC8wNi9pZGVudGl0eS9jbGFpbXMvYXV0aGVudGljYXRpb25pbnN0YW50IjoiMjAxNi0wNi0xNVQxMDozMTo0OS4wMTFaIn0.Y0dBdsrfk6tvYRYv_4RU4GNK-BfDslOVHDq6vMvB0QM";
//		j.validateToken(token);
//	}
}
