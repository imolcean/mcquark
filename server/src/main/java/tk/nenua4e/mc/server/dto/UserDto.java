package tk.nenua4e.mc.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.nenua4e.mc.server.annotation.TsOptional;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    public interface RegistrationValidationGroup {}
    public interface UpdateValidationGroup {}

    @NotNull(groups = { RegistrationValidationGroup.class, UpdateValidationGroup.class })
    private Long id;

    @NotEmpty(groups = { RegistrationValidationGroup.class, UpdateValidationGroup.class })
    @NotBlank(groups = { RegistrationValidationGroup.class, UpdateValidationGroup.class })
    @Size(min = 3, max = 16, groups = { RegistrationValidationGroup.class, UpdateValidationGroup.class })
    private String username;

    @NotEmpty(groups = { RegistrationValidationGroup.class })
    @NotBlank(groups = { RegistrationValidationGroup.class })
    @Size(min = 6, max = 64, groups = { RegistrationValidationGroup.class })
    @Getter(onMethod_ = { @TsOptional })
    private String password;

    @Email(groups = { RegistrationValidationGroup.class, UpdateValidationGroup.class })
    @Getter(onMethod_ = { @TsOptional })
    private String email;

    @NotNull(groups = { RegistrationValidationGroup.class, UpdateValidationGroup.class })
    private List<String> roles;
}
