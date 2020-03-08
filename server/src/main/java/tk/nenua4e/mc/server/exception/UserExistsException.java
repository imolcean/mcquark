package tk.nenua4e.mc.server.exception;

import lombok.Getter;

@Getter
public class UserExistsException extends RuntimeException
{
    public UserExistsException(String username)
    {
        super(String.format("User with the name %s already exists", username));
    }
}
