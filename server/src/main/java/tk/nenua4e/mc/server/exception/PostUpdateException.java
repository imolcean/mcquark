package tk.nenua4e.mc.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostUpdateException extends RuntimeException
{
    @Getter
    @AllArgsConstructor
    public enum Reason
    {
        NO_RIGHTS("You have no rights to perform an update of this post.");

        String value;
    }

    public PostUpdateException(Reason reason)
    {
        super(reason.getValue());
    }
}
