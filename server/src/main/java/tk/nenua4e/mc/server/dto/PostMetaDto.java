package tk.nenua4e.mc.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import tk.nenua4e.mc.server.annotation.Html;
import tk.nenua4e.mc.server.annotation.TsOptional;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PostMetaDto
{
    private Long id;

    private String title;

    private LocalDateTime created;

    private LocalDateTime modified;

    private String authorUsername;
}

