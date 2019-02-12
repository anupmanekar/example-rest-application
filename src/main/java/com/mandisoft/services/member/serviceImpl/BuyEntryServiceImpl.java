package com.mandisoft.services.member.serviceImpl;

import com.mandisoft.services.member.entity.BuyEntries;
import com.mandisoft.services.member.entity.GrainType;
import com.mandisoft.services.member.entity.Member;
import com.mandisoft.services.member.repository.BuyEntriesRepository;
import com.mandisoft.services.member.repository.GrainTypeRepository;
import com.mandisoft.services.member.repository.MemberRepository;
import com.mandisoft.services.member.service.BuyEntryService;
import com.mandisoft.services.member.utility.exception.ApplicationException;
import com.mandisoft.services.member.vo.BuyEntryVO;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.time.Instant;

public class BuyEntryServiceImpl implements BuyEntryService{

    @Autowired
    public BuyEntriesRepository buyEntriesRepository;
    @Autowired
    public GrainTypeRepository grainTypeRepository;
    @Autowired
    public MemberRepository memberRepository;

    public BuyEntryServiceImpl() {
        super();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }

    @Override
    public BuyEntries createBuyEntry(BuyEntryVO buyEntryDetails) throws ApplicationException {
        BuyEntries buyEntries = new BuyEntries();
        Member member = memberRepository.findOne(1);
        buyEntries.setMember(member);
        buyEntries.setEntryDate(buyEntryDetails.getEntryDate());
        buyEntries.setSellerName(buyEntryDetails.getSellerName());
        GrainType grainType = grainTypeRepository.findOne(buyEntryDetails.getGrainTypeId());
        buyEntries.setGrainType(grainType);
        buyEntries.setTown(buyEntryDetails.getTown());
        buyEntries.setWeight(buyEntryDetails.getWeight());
        buyEntries.setLabourCharge(buyEntryDetails.getLabourCharges());
        buyEntries.setRatePerKg(buyEntryDetails.getRatePerKg());
        buyEntries.setTotalAmount(buyEntryDetails.getTotalAmount());
        buyEntries.setTransactionReference(buyEntryDetails.getTransactionReference());
        buyEntries.setCreatedAt(java.util.Date.from(Instant.now()));
        buyEntries.setCreatedBy("MandiMicroservices");
        return buyEntriesRepository.save(buyEntries);
    }
}

