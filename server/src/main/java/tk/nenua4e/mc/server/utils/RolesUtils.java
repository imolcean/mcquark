package tk.nenua4e.mc.server.utils;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

// TODO Replace with a map
public class RolesUtils
{
    public static String abbreviateStrings(List<String> roles)
    {
        StringBuilder abbr = new StringBuilder();

        for(String str : roles)
        {
            switch (str.toUpperCase())
            {
                case "OP":
                    abbr.append("O");
                    break;
                case "EDITOR":
                    abbr.append("E");
                    break;
                case "ADMIN":
                    abbr.append("A");
                    break;
                default:
                    break;
            }
        }

        return abbr.toString();
    }

    public static String abbreviateGrantedAuthorities(List<GrantedAuthority> roles)
    {
        return abbreviateStrings(roles.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
    }

    public static List<String> toStrings(String abbr)
    {
        // TODO
        return null;
    }

    public static List<GrantedAuthority> toGrantedAuthorities(String abbr)
    {
        // TODO
        return null;
    }
}
