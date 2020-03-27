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
public class PostDto
{
    public interface CreationValidationGroup {}
    public interface UpdateValidationGroup {}

    @NotNull(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    private Long id;

    @NotEmpty(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    @NotBlank(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    private String title;

    @NotEmpty(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    @NotBlank(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    @Html(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    private String preview;

    @Html(groups = { CreationValidationGroup.class, UpdateValidationGroup.class })
    @Getter(onMethod_ = { @TsOptional})
    private String content;

    @Getter(onMethod_ = { @TsOptional})
    private LocalDateTime created;

    @Getter(onMethod_ = { @TsOptional })
    private LocalDateTime modified;

    @Getter(onMethod_ = { @TsOptional })
    private LocalDateTime published;

    @Getter(onMethod_ = { @TsOptional })
    private String authorUsername;
}
