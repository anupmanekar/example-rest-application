package com.mandisoft.services.member.service;

import com.mandisoft.services.member.entity.User;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.vo.UserLoginVO;
import com.mandisoft.services.member.vo.UserVO;

public interface UserService {
    /**
     * Method for authenticateUser
     * @param userLoginDetails
     * @return
     * @throws ApplicationException
     */
    public UserLoginVO authenticateUser(UserLoginVO userLoginDetails) throws ApplicationException;

    /**
     * Method for findByuserName
     * @param userName
     * @return
     */
    public User findByuserName(String userName);
}
