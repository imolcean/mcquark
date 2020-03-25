package tk.nenua4e.mc.server.dto.mapper;

import tk.nenua4e.mc.server.dto.PostMetaDto;
import tk.nenua4e.mc.server.model.Post;

public class PostMetaInfoMapper
{
    public static PostMetaDto toDto(Post post)
    {
        return new PostMetaDto(
                post.getId(),
                post.getTitle(),
                post.getCreated(),
                post.getModified(),
                post.getAuthor().getUsername());
    }
}
