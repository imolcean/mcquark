package tk.nenua4e.mc.server.exception;

public class InsufficientRightsException extends RuntimeException
{
    public InsufficientRightsException()
    {
        super("You have insufficient rights to perform the requested operation");
    }
}
