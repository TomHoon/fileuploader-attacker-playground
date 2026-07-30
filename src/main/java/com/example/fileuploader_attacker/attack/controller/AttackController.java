package com.example.fileuploader_attacker.attack.controller;

import com.example.fileuploader_attacker.attack.dto.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AttackController {

    @PostMapping("/user-info")
    public void getUserInfo(@RequestBody UserInfo userInfo){
        String email = userInfo.getEmail();
        String password = userInfo.getPassword();

    }

}
