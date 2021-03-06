package tk.nenua4e.mc.server.dto.mapper;

import tk.nenua4e.mc.server.dto.UserDto;
import tk.nenua4e.mc.server.model.User;

public class UserMapper
{
    public static UserDto toDto(User user)
    {
        return new UserDto(user.getId(), user.getUsername(), null, user.getEmail(), user.getRoles());
    }
}
