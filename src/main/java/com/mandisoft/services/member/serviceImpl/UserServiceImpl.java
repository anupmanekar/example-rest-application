package com.mandisoft.services.member.serviceImpl;

import java.util.Date;

import com.mandisoft.services.member.entity.User;
import com.mandisoft.services.member.repository.UserRepository;
import com.mandisoft.services.member.security.IJWTBuilder;
import com.mandisoft.services.member.security.JWTClaimVO;
import com.mandisoft.services.member.service.UserService;
import com.mandisoft.services.member.utility.ApplicationLogger;
import com.mandisoft.services.member.utility.constants.ApplicationConstants;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.utility.exception.EncryptionUtil;
import com.mandisoft.services.member.utility.exception.ErrorCode;
import com.mandisoft.services.member.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class UserServiceImpl implements UserService {

    /**
     * Instance for jwtBuilder
     */
    @Autowired
    private IJWTBuilder jwtBuilder;

    /**
     * Instance for userRepository
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * instance for ApplicationLogger
     */
    private static final ApplicationLogger LOGGER = new ApplicationLogger(UserServiceImpl.class);


    /**
     * Method for find user by Name
     */
    @Override
    public User findByuserName(String userName) {
        User user = userRepository.findByUsername(userName);
        return user;
    }

    /**
     * Method for authenticate User
     */
    @Override
    public UserLoginVO authenticateUser(UserLoginVO userLoginDetails) throws ApplicationException {
        LOGGER.info("Authenticate user into Database");
        User user = userRepository.findByUsername(userLoginDetails.getUsername());
        if (user != null) {
            LOGGER.info("User with given credentials returned from database");
            String decryptPassword = EncryptionUtil.decryptData(user.getPassword());
            LOGGER.info("Authentication user password");
            if (decryptPassword.equals(userLoginDetails.getPassword())) {
                UserLoginVO userLoginVO = new UserLoginVO();
                userLoginVO.setId(user.getId());
                userLoginVO.setUsername(user.getUserName());
                userLoginVO.setRole(user.getRole());
                JWTClaimVO jwtClaimVO = createClaim(userLoginVO);
                Date today = new Date();
                Date expirationDate = new Date(today.getTime() + (1000 * 60 * 60 * 24));
                userLoginVO.setToken(jwtBuilder.createJWT(jwtClaimVO, expirationDate));
                userLoginVO.setMessage("Success");
                return userLoginVO;
            } else {
                LOGGER.info("Password incorrect");
                throw new ApplicationException(ApplicationConstants.INVALID_PASSWORD,
                        ErrorCode.ACCESS_DENIED.getCodeId(), HttpStatus.UNAUTHORIZED);
            }

        } else {
            LOGGER.info("Username incorrect");
            throw new ApplicationException(ApplicationConstants.USER_NOT_FOUND, ErrorCode.ACCESS_DENIED.getCodeId(),
                    HttpStatus.UNAUTHORIZED);
        }
    }

    /**
     * Method for create Claim
     */
    private JWTClaimVO createClaim(UserLoginVO user) {

        JWTClaimVO jwtClaimVO = new JWTClaimVO();
        jwtClaimVO.setUsername(user.getUsername());
        jwtClaimVO.setRoles(user.getRole());
        jwtClaimVO.setUserPk(user.getId());
        return jwtClaimVO;

    }

}
