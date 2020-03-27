package tk.nenua4e.mc.server.dto.mapper;

import tk.nenua4e.mc.server.dto.PostDto;
import tk.nenua4e.mc.server.model.Post;

public class PostMapper
{
    public static PostDto toDto(Post post)
    {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getPreview(),
                post.getContent(),
                post.getCreated(),
                post.getModified(),
                post.getPublished(),
                post.getAuthor().getUsername());
    }
}
