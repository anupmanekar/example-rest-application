package com.mandisoft.services.member.service;

import com.mandisoft.services.member.entity.BuyEntries;
import com.mandisoft.services.member.entity.User;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.vo.BuyEntryVO;
import com.mandisoft.services.member.vo.UserLoginVO;

public interface BuyEntryService {
    /**
     * Method for creating buy entry
     * @param buyEntryDetails
     * @return
     * @throws ApplicationException
     */
    public BuyEntries createBuyEntry(BuyEntryVO buyEntryDetails) throws ApplicationException;

}
