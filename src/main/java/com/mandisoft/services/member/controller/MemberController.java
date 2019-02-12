package com.mandisoft.services.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mandisoft.services.member.entity.Member;
import com.mandisoft.services.member.repository.MemberRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/member") // This means URL's start with /demo (after Application path)
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;

    @GetMapping(path="/add") // Map ONLY GET Requests
    public @ResponseBody String addNewMember (@RequestParam String name
            , @RequestParam String email) {

        Member n = new Member();
        n.setFirstName(name);
        n.setEmail(email);
        memberRepository.save(n);
        return "Saved";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Member> getAllUsers() {
        return memberRepository.findAll();
    }
}