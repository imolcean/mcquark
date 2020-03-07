package tk.nenua4e.mc.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostDto
{
    private Long id;

    private String title;

    private String content;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String authorUsername;

    public Optional<LocalDateTime> getCreated()
    {
        return Optional.ofNullable(this.created);
    }

    public Optional<LocalDateTime> getModified()
    {
        return Optional.ofNullable(this.modified);
    }

    public Optional<String> getAuthorUsername()
    {
        return Optional.ofNullable(this.authorUsername);
    }
}
