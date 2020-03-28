package tk.nenua4e.mc.server.exception;

public class PostAlreadyPublishedException extends RuntimeException
{
    public PostAlreadyPublishedException(long id)
    {
        super(String.format("Post with the ID %d is already published", id));
    }
}
