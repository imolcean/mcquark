package tk.nenua4e.mc.server.controller.validation;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import tk.nenua4e.mc.server.annotation.Html;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HtmlValidator implements ConstraintValidator<Html, String>
{
    @Override
    public void initialize(Html constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        return Jsoup.isValid(value, Whitelist.basicWithImages());
    }
}
