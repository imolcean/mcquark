package tk.nenua4e.mc.server;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tk.nenua4e.mc.server.exception.*;

import java.util.HashMap;
import java.util.Map;

// TODO Return correct MIME type

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler({PostNotFoundException.class, UsernameNotFoundException.class, UserNotFoundException.class})
    public final ResponseEntity<Object> handleEntityNotFound(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({PostSaveException.class})
    public final ResponseEntity<Object> handlePostUpdate(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(403), request);
    }

    @ExceptionHandler({InsufficientRightsException.class})
    public final ResponseEntity<Object> handleInsufficientRights(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(403), request);
    }

    @ExceptionHandler({UserExistsException.class})
    public final ResponseEntity<Object> handleUserExists(Exception ex, WebRequest request)
    {
        return this.handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.valueOf(409), request);
    }

    @Override
    public final ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatus status,
                                                                     WebRequest request)
    {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors()
                .forEach(e -> errors.put(((FieldError) e).getField(), e.getDefaultMessage()));

        return this.handleExceptionInternal(ex, errors, new HttpHeaders(), HttpStatus.valueOf(422), request);
    }
}
