package com.mandisoft.services.member.controller;


import com.mandisoft.services.member.security.JWTAuthenticationDetails;
import com.mandisoft.services.member.service.UserService;
import com.mandisoft.services.member.utility.ApplicationLogger;
import com.mandisoft.services.member.utility.exception.ResponseDTO;
import com.mandisoft.services.member.vo.ResponseVO;
import com.mandisoft.services.member.vo.UserLoginVO;
import com.mandisoft.services.member.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
public class UserController {

	/**
	 * Method for userService
	 */
	@Autowired
	private UserService userService;
	
	/**
	 * Object of JWTAunthenticationDetails
	 */
	@Autowired
	private JWTAuthenticationDetails jWTAuthenticationDetails;

	private static final ApplicationLogger LOGGER = new ApplicationLogger(UserController.class);

	
	/**
	 * Method for Authentication of user
	 * @param userData
	 * @return responseEntity
	 */
	@RequestMapping(value = "/api/user/login", method = RequestMethod.POST)
	public ResponseEntity<?> authenticateUser(@RequestBody final UserLoginVO userData) {
		LOGGER.info("Recieving user ceredentials for user login");
		UserLoginVO userLoginVO = userService.authenticateUser(userData);
		if (userLoginVO != null) {
			return new ResponseEntity<UserLoginVO>(userLoginVO, HttpStatus.OK);
		} else {
			ResponseVO responseVO = new ResponseVO();
			responseVO.setMessage("failure");
			responseVO.setStatusCode("400");
			return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.NOT_FOUND);
		}
	}


	
}
