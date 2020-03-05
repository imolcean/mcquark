package tk.nenua4e.mc.server.controller.request;

import lombok.Data;
import lombok.experimental.Accessors;

// TODO Remove

@Data
@Accessors(chain = true)
public class PostSaveRequest
{
    private String title;

    private String content;
}
