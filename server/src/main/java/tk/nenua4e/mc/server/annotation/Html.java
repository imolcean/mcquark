package tk.nenua4e.mc.server.annotation;

import tk.nenua4e.mc.server.controller.validation.HtmlValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = HtmlValidator.class)
@Documented
public @interface Html
{
    String message() default "Invalid HTML";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
