package tk.nenua4e.mc.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.nenua4e.mc.server.dto.UserDto;
import tk.nenua4e.mc.server.dto.mapper.UserMapper;
import tk.nenua4e.mc.server.service.AuthService;

import java.security.Principal;

// TODO Deprecated?

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{
    private AuthService auth;

    public AuthController(AuthService auth)
    {
        this.auth = auth;
    }

    @PostMapping("/user")
    public UserDto user(Principal principal)
    {
        return UserMapper.toDto(this.auth.loadUserByUsername(principal.getName()));
    }
}
