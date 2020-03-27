package tk.nenua4e.mc.server.dto.mapper;

import tk.nenua4e.mc.server.dto.PostDto;
import tk.nenua4e.mc.server.model.Post;

public class PostPreviewMapper
{
    public static PostDto toDto(Post post)
    {
        return new PostDto(
                post.getId(),
                post.getTitle(),
                post.getPreview(),
                null,
                post.getCreated(),
                post.getModified(),
                post.getPublished(),
                post.getAuthor().getUsername());
    }
}
