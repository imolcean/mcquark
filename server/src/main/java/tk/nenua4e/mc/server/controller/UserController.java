package tk.nenua4e.mc.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tk.nenua4e.mc.server.controller.request.ChangePasswordRequest;
import tk.nenua4e.mc.server.dto.UserDto;
import tk.nenua4e.mc.server.service.UserService;

import java.net.URI;
import java.util.List;

// TODO HATEOAS
// TODO Roles

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
    public List<UserDto> getUsers()
    {
        return this.users.getAllUsers();
    }

    @GetMapping("user/{id}")
    public UserDto getUser(@PathVariable("id") long id)
    {
        return this.users.getUser(id);
    }

    @PostMapping("users")
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto user)
    {
        UserDto created = this.users.createUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("user/{id}")
    public UserDto updateUser(@PathVariable("id") long id, @RequestBody UserDto user)
    {
        return this.users.updateUser(user.setId(id));
    }

    @PutMapping("user/{id}/password")
    public UserDto changePassword(@PathVariable("id") long id, @RequestBody ChangePasswordRequest request)
    {
        return this.users.changePassword(id, request.getOldPassword(), request.getNewPassword());
    }

    @DeleteMapping("user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") long id)
    {
        this.users.deleteUser(id);

        return ResponseEntity.noContent().build();
    }
}
