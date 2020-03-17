package tk.nenua4e.mc.server.controller.validation;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import tk.nenua4e.mc.server.annotation.Html;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class HtmlValidator implements ConstraintValidator<Html, String>
{
    private Whitelist whitelist;

    public HtmlValidator()
    {
        this.whitelist = new Whitelist()
                .addTags("br", "p", "h1", "h2", "span", "strong", "em", "u", "img", "ul", "ol", "li", "a", "pre")
                .addAttributes("p", "class")
                .addAttributes("h1", "class")
                .addAttributes("h2", "class")
                .addAttributes("li", "class")
                .addAttributes("span", "style", "class")
                .addAttributes("strong", "style", "class")
                .addAttributes("em", "style", "class")
                .addAttributes("u", "style", "class")
                .addAttributes("pre", "class", "spellcheck")
                .addAttributes("img", "src")
                .addAttributes("a", "class", "style", "target", "href", "rel")
                .addProtocols("a", "href", "http", "https")
                .addProtocols("img", "src", "data", "http", "https");
    }

    @Override
    public void initialize(Html constraintAnnotation) {}

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context)
    {
        return Jsoup.isValid(value, this.whitelist);
    }
}
