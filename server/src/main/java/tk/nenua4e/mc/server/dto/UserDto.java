package tk.nenua4e.mc.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Optional;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;

    private String username;

    private String password;

    private String email;

    private List<String> roles;

    public Optional<String> getPassword()
    {
        return Optional.ofNullable(this.password);
    }

    public Optional<String> getEmail()
    {
        return Optional.ofNullable(this.email);
    }
}
