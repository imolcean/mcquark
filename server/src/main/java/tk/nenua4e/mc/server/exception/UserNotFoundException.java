package tk.nenua4e.mc.server.exception;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(long id)
    {
        super(String.format("User with the ID %d not found", id));
    }

    public UserNotFoundException(String nick)
    {
        super(String.format("User with the nick %s not found", nick));
    }
}
