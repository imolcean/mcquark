package tk.nenua4e.mc.server.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class UserUpdateException extends RuntimeException
{
    @Getter
    @AllArgsConstructor
    public enum Reason
    {
        NO_RIGHTS("You have no rights to perform the requested action."),
        VALIDATION_FAILED("Validation failed");

        String value;
    }

    private Reason reason;

    public UserUpdateException(Reason reason)
    {
        super(reason.getValue());

        this.reason = reason;
    }
}