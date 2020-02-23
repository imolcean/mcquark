package tk.nenua4e.mc.server.exception;

public class PostNotFoundException extends RuntimeException
{
    public PostNotFoundException(long id)
    {
        super(String.format("Post with the ID %d not found", id));
    }
}
