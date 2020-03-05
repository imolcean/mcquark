package tk.nenua4e.mc.server.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ChangePasswordRequest
{
    private String oldPassword;

    private String newPassword;
}
