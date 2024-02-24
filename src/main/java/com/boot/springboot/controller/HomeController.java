package com.boot.springboot.controller;

import com.boot.springboot.member.MemberEntity;
import com.boot.springboot.member.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boot")
public class HomeController {
    @Autowired
    MemberService memberservice;

    @PostMapping("/login")
    public List<MemberEntity> findByUserid(@RequestBody Map<String, String> loginData) {
        String userid = loginData.get("id");
        String inputPw = loginData.get("pw");

        System.out.println("userid : " + userid);
        System.out.println("inputPw" + inputPw);

        List<MemberEntity> list = memberservice.findByUserid(userid);

        if (list.isEmpty()) {
            // 사용자를 찾을 수 없음
            System.out.println("사용자를 찾을 수 없음");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // 첫 번째 사용자의 비밀번호를 가져옴
        String savedEncodedPassword = list.get(0).getPw();

        // BCryptPasswordEncoder를 사용하여 입력된 비밀번호와 데이터베이스에 저장된 암호화된 비밀번호를 비교
        if (passwordEncoder.matches(inputPw, savedEncodedPassword)) {
            System.out.println("인증 성공");
            // 인증 성공 시 처리
        } else {
            // 인증 실패 시 처리
            System.out.println("인증 실패");
        }

        return list;
    }
}

