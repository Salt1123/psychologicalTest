package com.boot.springboot.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository; // 필드명 변경

    public List<MemberEntity> findByUserid(String userid) {
        List<MemberEntity> members = memberRepository.findByUserid(userid);
        if (members != null) {
            for (MemberEntity member : members) {
                if (member != null) {
                    System.out.println("User ID: " + member.getUserid() + ", Password: " + member.getPw());
                } else {
                    System.out.println("Member is null.");
                }
            }
        } else {
            System.out.println("Members list is null.");
        }
        return members;
    }

    public void updatePasswordByUserId(String userid, String pw){
        memberRepository.updatePasswordByUserId(userid, pw);

    }




}
