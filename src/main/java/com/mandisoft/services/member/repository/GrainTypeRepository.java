package com.mandisoft.services.member.repository;

import com.mandisoft.services.member.entity.GrainType;
import com.mandisoft.services.member.entity.Member;
import org.springframework.data.repository.CrudRepository;
// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface GrainTypeRepository extends CrudRepository<GrainType, Integer> {

}