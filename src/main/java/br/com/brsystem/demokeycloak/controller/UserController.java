package br.com.brsystem.demokeycloak.controller;

import br.com.brsystem.demokeycloak.dto.UserNameUpdate;
import br.com.brsystem.demokeycloak.dto.UserResetPassword;
import br.com.brsystem.demokeycloak.services.KeycloakUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private KeycloakUserService keycloakUserService;

    @GetMapping
    public void hello() {
        System.out.println("Hello");
    }

    @PatchMapping({"/{idUser}"})
    public void userNameUpdate(@PathVariable String idUser, @Valid @RequestBody UserNameUpdate userNameUpdate) {
        keycloakUserService.updateNameUser(idUser, userNameUpdate);
    }

    @PostMapping("/reset-password")
    public void changePasswordUser(@Valid @RequestBody UserResetPassword userResetPassword) {
        keycloakUserService.resetPasswordUser(userResetPassword);
    }
}
