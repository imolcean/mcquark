package tk.nenua4e.mc.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class AuthController
{
    @PostMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}
