package tk.nenua4e.mc.server.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController
{
    @RequestMapping({"/project", "/contact", "/profile"})
    public String index()
    {
        return "forward:/index.html";
    }
}
