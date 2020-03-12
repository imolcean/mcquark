package tk.nenua4e.mc.server.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tk.nenua4e.mc.server.dto.UserDto;
import tk.nenua4e.mc.server.dto.mapper.UserMapper;
import tk.nenua4e.mc.server.exception.InsufficientRightsException;
import tk.nenua4e.mc.server.exception.UserExistsException;
import tk.nenua4e.mc.server.exception.UserNotFoundException;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService
{
    private UserRepository users;

    private PasswordEncoder encoder;

    public UserService(UserRepository users, PasswordEncoder encoder)
    {
        this.users = users;
        this.encoder = encoder;
    }

    public List<UserDto> getAllUsers()
    {
        List<UserDto> list = new ArrayList<>();

        for(User user : this.users.findAll())
        {
            list.add(UserMapper.toDto(user));
        }

        return list;
    }

    public UserDto getUser(long id)
    {
        return this.users.findById(id)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    public UserDto getUser(String username)
    {
        return this.users.findByUsername(username)
                .map(UserMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public UserDto createUser(UserDto dto)
    {
        if(this.users.findByUsername(dto.getUsername()).isPresent())
        {
            throw new UserExistsException(dto.getUsername());
        }

        User user = new User(
                dto.getUsername(),
                this.encoder.encode(dto.getPassword()),
                dto.getEmail(),
                dto.getRoles());

        return UserMapper.toDto(this.users.save(user));
    }

    public UserDto updateUser(UserDto dto)
    {
        User user = this.users.findById(dto.getId())
                .orElseThrow(() -> new UserNotFoundException(dto.getId()));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setRoles(dto.getRoles());

        return UserMapper.toDto(this.users.save(user));
    }

    public UserDto changePassword(long id, String oldPassword, String newPassword)
    {
        User user = this.users.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        if(!this.encoder.matches(oldPassword, user.getPassword()))
        {
            throw new InsufficientRightsException();
        }

        return UserMapper.toDto(this.users.save(user.setPassword(this.encoder.encode(newPassword))));
    }

    public void deleteUser(long id)
    {
        User user = this.users.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        this.users.delete(user);
    }
}
