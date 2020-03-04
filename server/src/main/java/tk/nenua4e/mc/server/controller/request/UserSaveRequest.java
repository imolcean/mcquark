package tk.nenua4e.mc.server.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class UserSaveRequest
{
    private String name;

    private String password;

    private String email;

    private List<String> roles;
}
