package tk.nenua4e.mc.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class PostSaveException extends RuntimeException
{
    @Getter
    @AllArgsConstructor
    public enum Reason
    {
        NO_RIGHTS("You have no rights to perform the requested action."),
        VALIDATION_FAILED("Validation failed");

        String value;
    }

    public PostSaveException(Reason reason)
    {
        super(reason.getValue());
    }
}
