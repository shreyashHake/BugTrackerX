package com.springBoot.eBugTracker.controller;

import com.springBoot.eBugTracker.entity.JwtRequest;
import com.springBoot.eBugTracker.entity.JwtResponse;
import com.springBoot.eBugTracker.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin("http://localhost:4200")
public class JwtController {
    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        log.info("Inside:- /JwtController/authenticate");
        try {
            return jwtService.createJwtToken(jwtRequest);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new JwtResponse();
    }
}
