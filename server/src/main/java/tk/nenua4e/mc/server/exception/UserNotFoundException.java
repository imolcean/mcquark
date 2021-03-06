package tk.nenua4e.mc.server.exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(long id)
    {
        super(String.format("User with the ID %d not found", id));
    }

    public UserNotFoundException(String username)
    {
        super(String.format("User with the name %s not found", username));
    }
}
