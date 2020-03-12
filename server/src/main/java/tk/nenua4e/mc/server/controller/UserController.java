package tk.nenua4e.mc.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tk.nenua4e.mc.server.controller.request.ChangePasswordRequest;
import tk.nenua4e.mc.server.dto.UserDto;
import tk.nenua4e.mc.server.service.UserService;

import java.net.URI;
import java.security.Principal;
import java.util.Collections;
import java.util.List;

// TODO HATEOAS

@Slf4j
@RestController
@RequestMapping("api/v1")
public class UserController
{
    private UserService users;

    public UserController(UserService users)
    {
        this.users = users;
    }

    @GetMapping("users")
    @Secured({ "ROLE_ADMIN" })
    public List<UserDto> getUsers()
    {
        return this.users.getAllUsers();
    }

    @GetMapping("user/me")
    public UserDto getCurrentUser(Principal principal)
    {
        UserDto currentUser = this.users.getUser(principal.getName());

        return this.getUser(currentUser.getId());
    }

    @GetMapping("user/{id}")
    @Secured({ "ROLE_ADMIN" })
    public UserDto getUser(@PathVariable("id") long id)
    {
        return this.users.getUser(id);
    }

    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(Principal principal, @RequestBody @Validated(UserDto.RegistrationValidationGroup.class) UserDto dto)
    {
        if(principal == null || !this.users.getUser(principal.getName()).getRoles().contains("ADMIN"))
        {
            dto.setRoles(Collections.emptyList());
        }

        UserDto created = this.users.createUser(dto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("user/me")
    public UserDto updateCurrentUser(Principal principal, @RequestBody @Validated(UserDto.UpdateValidationGroup.class) UserDto dto)
    {
        UserDto currentUser = this.users.getUser(principal.getName());

        if(!currentUser.getRoles().contains("ADMIN"))
        {
            dto.setRoles(Collections.emptyList());
        }

        return this.updateUser(currentUser.getId(), dto);
    }

    @PutMapping("user/{id}")
    @Secured({ "ROLE_ADMIN" })
    public UserDto updateUser(@PathVariable("id") long id, @RequestBody @Validated(UserDto.UpdateValidationGroup.class) UserDto dto)
    {
        return this.users.updateUser(dto.setId(id));
    }

    @PutMapping("user/me/password")
    public UserDto changePasswordOfCurrentUser(Principal principal, @RequestBody @Validated ChangePasswordRequest request)
    {
        UserDto currentUser = this.users.getUser(principal.getName());

        return this.changePassword(currentUser.getId(), request);
    }

    @PutMapping("user/{id}/password")
    @Secured({ "ROLE_ADMIN" })
    public UserDto changePassword(@PathVariable("id") long id, @RequestBody @Validated ChangePasswordRequest request)
    {
        return this.users.changePassword(id, request.getOldPassword(), request.getNewPassword());
    }

    @DeleteMapping("user/{id}")
    @Secured({ "ROLE_ADMIN" })
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id)
    {
        this.users.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
