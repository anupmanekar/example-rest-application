package com.mandisoft.services.member.security;

import com.mandisoft.services.member.utility.constants.ApplicationConstants;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.utility.exception.ErrorCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;

@Component("jWTAuthenticationDetails")
@PropertySource(value = "jwt.properties")
public class JWTAuthenticationDetailsImpl implements JWTAuthenticationDetails {

 @Autowired
 private IJWTBuilder jwtBuilder;

 private static final String SUBJECT = "sub";

 private static final String USER_PK = "UserPK";

 @Value("${javastartup.secretKey}")
 public String secret;

 @Override
 public JWTClaimVO retrieveUserDetails(String authToken) {

  JWTClaimVO jwtClaimVO = null;
  Claims claims = null;
  claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary("" + secret)).parseClaimsJws(authToken)
    .getBody();

  jwtClaimVO = getJWTClaimVO(claims);
  return jwtClaimVO;

 }

 private JWTClaimVO getJWTClaimVO(Claims claims) {
  try {
   JWTClaimVO jwtClaimVO = new JWTClaimVO();

   jwtClaimVO.setUsername(claims.get("sub").toString());

   String id = claims.get("UserPK").toString();
   String role = claims.get("role").toString();
   Integer age = (Integer) claims.get("age");
   String gender = claims.get("gender").toString();
   jwtClaimVO.setUserPk(id);
   jwtClaimVO.setRoles(role);
   if(age != null){
   jwtClaimVO.setAge(age);
   }
   if(gender != null){
	   jwtClaimVO.setGender(gender);
   }
   
   return jwtClaimVO;
  } catch (Exception exception) {
   throw new ApplicationException(ApplicationConstants.TOKEN_INVALID, ErrorCode.INVALID_TOKEN.getCodeId(),
     HttpStatus.UNAUTHORIZED);
  }
 }

}