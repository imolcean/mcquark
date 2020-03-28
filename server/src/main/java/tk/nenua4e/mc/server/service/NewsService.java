package tk.nenua4e.mc.server.service;

import org.springframework.stereotype.Service;
import tk.nenua4e.mc.server.dto.PostDto;
import tk.nenua4e.mc.server.dto.PostMetaDto;
import tk.nenua4e.mc.server.dto.mapper.PostMapper;
import tk.nenua4e.mc.server.dto.mapper.PostMetaInfoMapper;
import tk.nenua4e.mc.server.dto.mapper.PostPreviewMapper;
import tk.nenua4e.mc.server.exception.PostAlreadyPublishedException;
import tk.nenua4e.mc.server.exception.PostNotFoundException;
import tk.nenua4e.mc.server.exception.PostSaveException;
import tk.nenua4e.mc.server.exception.UserNotFoundException;
import tk.nenua4e.mc.server.model.Post;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.PostRepository;
import tk.nenua4e.mc.server.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewsService
{
    private PostRepository posts;

    private UserRepository users;

    public NewsService(PostRepository posts, UserRepository users)
    {
        this.posts = posts;
        this.users = users;
    }

    public List<PostDto> getPublishedPosts()
    {
        List<PostDto> feed = new ArrayList<>();

        for(Post post : this.posts.findByPublishedIsNotNullOrderByPublishedDesc())
        {
            feed.add(PostMapper.toDto(post));
        }

        return feed;
    }

    public List<PostDto> getPublishedPostsPreview()
    {
        List<PostDto> feed = new ArrayList<>();

        for(Post post : this.posts.findByPublishedIsNotNullOrderByPublishedDesc())
        {
            feed.add(PostPreviewMapper.toDto(post));
        }

        return feed;
    }

    public List<PostMetaDto> getPublishedPostsMeta()
    {
        List<PostMetaDto> feed = new ArrayList<>();

        for(Post post : this.posts.findByPublishedIsNotNullOrderByPublishedDesc())
        {
            feed.add(PostMetaInfoMapper.toDto(post));
        }

        return feed;
    }

    public List<PostDto> getDrafts()
    {
        List<PostDto> feed = new ArrayList<>();

        for(Post post : this.posts.findByPublishedIsNullOrderByCreatedDesc())
        {
            feed.add(PostMapper.toDto(post));
        }

        return feed;
    }

    public List<PostMetaDto> getDraftsMeta()
    {
        List<PostMetaDto> feed = new ArrayList<>();

        for(Post post : this.posts.findByPublishedIsNullOrderByCreatedDesc())
        {
            feed.add(PostMetaInfoMapper.toDto(post));
        }

        return feed;
    }

    public PostDto getPost(long id)
    {
        return this.posts.findById(id)
                .map(PostMapper::toDto)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    public PostDto createPost(PostDto dto)
    {
        User user = this.users.findByUsername(dto.getAuthorUsername())
                .orElseThrow(() -> new UserNotFoundException(dto.getAuthorUsername()));

        Post post = this.posts.save(new Post(dto.getTitle(), dto.getPreview(), dto.getContent(), LocalDateTime.now(), user));

        return PostMapper.toDto(post);
    }

    public PostDto updatePost(PostDto dto, String usernamePerformingUpdate)
    {
        User user = this.users.findByUsername(usernamePerformingUpdate)
                .orElseThrow(() -> new UserNotFoundException(usernamePerformingUpdate));

        Post post = this.posts.findById(dto.getId())
                .orElseThrow(() -> new PostNotFoundException(dto.getId()));

        // Only admins can modify posts of other users
        if(!post.getAuthor().equals(user) && !user.getRoles().contains("ADMIN"))
        {
            throw new PostSaveException(PostSaveException.Reason.NO_RIGHTS);
        }

        post
                .setTitle(dto.getTitle())
                .setPreview(dto.getPreview())
                .setContent(dto.getContent())
                .setModified(LocalDateTime.now());

        return PostMapper.toDto(this.posts.save(post));
    }

    public PostDto publishPost(long id, String usernamePerformingUpdate)
    {
        User user = this.users.findByUsername(usernamePerformingUpdate)
                .orElseThrow(() -> new UserNotFoundException(usernamePerformingUpdate));

        Post post = this.posts.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        // Only admins can modify posts of other users
        if(!post.getAuthor().equals(user) && !user.getRoles().contains("ADMIN"))
        {
            throw new PostSaveException(PostSaveException.Reason.NO_RIGHTS);
        }

        if(post.getPublished() != null)
        {
            throw new PostAlreadyPublishedException(id);
        }

        post.setPublished(LocalDateTime.now());

        return PostMapper.toDto(this.posts.save(post));
    }

    public void deletePost(long id, String username)
    {
        User user = this.users.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        Post post = this.posts.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        // Only admins can delete posts of other users
        if(!post.getAuthor().equals(user) && !user.getRoles().contains("ADMIN"))
        {
            throw new PostSaveException(PostSaveException.Reason.NO_RIGHTS);
        }

        this.posts.delete(post);
    }
}
