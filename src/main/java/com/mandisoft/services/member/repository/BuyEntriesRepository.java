package com.mandisoft.services.member.repository;

import com.mandisoft.services.member.entity.BuyEntries;
import com.mandisoft.services.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete
public interface BuyEntriesRepository extends JpaRepository<BuyEntries, Integer> {
    List<BuyEntries> findByMemberId(int memberId);
}