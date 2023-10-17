package com.example.demo.controllers;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class LoginController {

    private final AuthenticationManager authenticationManager;

    public LoginController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

   /* @PostMapping("/login")
    public String permitLogin(@RequestParam("login") String username,
                              @RequestParam("password") String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        return "index";

    }
*/
}
