package tk.nenua4e.mc.server.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tk.nenua4e.mc.server.dto.UserDto;
import tk.nenua4e.mc.server.dto.mapper.UserMapper;
import tk.nenua4e.mc.server.service.UserService;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{
    private UserService users;

    public AuthController(UserService users)
    {
        this.users = users;
    }

    @PostMapping("/user")
    public UserDto user(Principal principal)
    {
        return UserMapper.toDto(this.users.loadUserByUsername(principal.getName()));
    }
}
