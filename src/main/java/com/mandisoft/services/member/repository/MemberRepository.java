package com.mandisoft.services.member.repository;

import org.springframework.data.repository.CrudRepository;
import com.mandisoft.services.member.entity.Member;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface MemberRepository extends CrudRepository<Member, Integer> {


}