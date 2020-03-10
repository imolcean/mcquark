package tk.nenua4e.mc.server.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Accessors(chain = true)
public class ChangePasswordRequest
{
    @NotEmpty
    @NotBlank
    @Size(min = 6, max = 64)
    private String oldPassword;

    @NotEmpty
    @NotBlank
    @Size(min = 6, max = 64)
    private String newPassword;
}
