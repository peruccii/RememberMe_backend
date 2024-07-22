package com.rememberme.rememberMe.controller;

import com.rememberme.rememberMe.dtos.ValidPasswordDTO;
import com.rememberme.rememberMe.presenters.PasswordFailurePresenter;
import com.rememberme.rememberMe.strategy.pack.UserStrategy;
import com.rememberme.rememberMe.strategy.pack.UserStrategyInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/password")
public class PasswordController { // PROVISIONAL TEST VALIDATE PASSWORD CONTROLLER

    @Autowired
    private UserStrategy.UserValidations userStrategy;

    @PostMapping("/validate")
    public ResponseEntity<PasswordFailurePresenter> password(@RequestBody @Valid  ValidPasswordDTO data) {
        var pass = this.userStrategy.validate(data.password());

        if (pass.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.badRequest().body(new PasswordFailurePresenter(pass));
    }
}
