package com.mandisoft.services.member.controller;

import com.mandisoft.services.member.entity.BuyEntries;
import com.mandisoft.services.member.entity.Member;
import com.mandisoft.services.member.repository.BuyEntriesRepository;
import com.mandisoft.services.member.repository.MemberRepository;
import com.mandisoft.services.member.service.BuyEntryService;
import com.mandisoft.services.member.vo.BuyEntryVO;
import com.mandisoft.services.member.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/buy-entries")
public class BuyEntriesController {
    @Autowired
    private BuyEntriesRepository buyEntriesRepository;
    @Autowired
    private BuyEntryService buyEntryService;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<BuyEntries> getBuyEntriesByMemberId() {
        //TODO: Get Member Id from JWT token
        return buyEntriesRepository.findByMemberId(1);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> postBuyEntryToMember(@RequestBody final BuyEntryVO buyEntryRequest) {
        BuyEntries buyEntries = buyEntryService.createBuyEntry(buyEntryRequest);
        if (buyEntries != null) {
            return new ResponseEntity<Long>(new Long(buyEntries.getId()), HttpStatus.OK);
        } else {
            ResponseVO responseVO = new ResponseVO();
            responseVO.setMessage("failure");
            responseVO.setStatusCode("400");
            return new ResponseEntity<ResponseVO>(responseVO, HttpStatus.NOT_FOUND);
        }
    }
}