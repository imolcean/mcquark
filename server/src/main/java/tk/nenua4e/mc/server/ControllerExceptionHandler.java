package tk.nenua4e.mc.server;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tk.nenua4e.mc.server.exception.PostNotFoundException;
import tk.nenua4e.mc.server.exception.PostUpdateException;
import tk.nenua4e.mc.server.exception.UserNotFoundException;
import tk.nenua4e.mc.server.exception.UserUpdateException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({PostNotFoundException.class, UsernameNotFoundException.class, UserNotFoundException.class})
    public final ResponseEntity<Object> handleEntityNotFoundException(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({PostUpdateException.class})
    public final ResponseEntity<Object> handlePostUpdateException(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(403), request);
    }

    @ExceptionHandler({UserUpdateException.class})
    public final ResponseEntity<Object> handleUserUpdateException(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(400), request);
    }
}
