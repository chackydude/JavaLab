package ru.itis.trofimoff.task.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.itis.trofimoff.task.dto.EmailPasswordDto;
import ru.itis.trofimoff.task.dto.TokenDto;
import ru.itis.trofimoff.task.services.login.LoginService;

@Controller
public class DefaultController {

    @Autowired
    public LoginService loginService;

    @GetMapping("/")
    public String rootRedirect() {
        return "redirect:/todos";
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestBody EmailPasswordDto emailPassword) {
        System.out.println(emailPassword);
        System.out.println(loginService.login(emailPassword));
        return ResponseEntity.ok(loginService.login(emailPassword));
    }
}