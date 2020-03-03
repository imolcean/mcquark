package tk.nenua4e.mc.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tk.nenua4e.mc.server.controller.request.PostSaveRequest;
import tk.nenua4e.mc.server.dto.PostDto;
import tk.nenua4e.mc.server.dto.mapper.PostMapper;
import tk.nenua4e.mc.server.exception.PostNotFoundException;
import tk.nenua4e.mc.server.exception.PostUpdateException;
import tk.nenua4e.mc.server.exception.UserNotFoundException;
import tk.nenua4e.mc.server.model.Post;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.PostRepository;
import tk.nenua4e.mc.server.service.UserService;

import java.net.URI;
import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/news")
public class NewsController
{
    private UserService users;
    private PostRepository posts;

    public NewsController(UserService users, PostRepository postRepository)
    {
        this.users = users;
        this.posts = postRepository;
    }

    @GetMapping("/feed")
    public List<PostDto> getFeed()
    {
        List<PostDto> feed = new ArrayList<>();

        for(Post post : this.posts.findAll())
        {
            feed.add(PostMapper.toDto(post));
        }

        return feed;
    }

    @GetMapping("/post/{id}")
    public PostDto getPost(@PathVariable("id") long id)
    {
        return this.posts.findById(id)
                .map(PostMapper::toDto)
                .orElseThrow(() -> new PostNotFoundException(id));
    }

    @PostMapping("/feed")
    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    public ResponseEntity<PostDto> createPost(Principal principal, @RequestBody PostSaveRequest req)
    {
        User author = this.users.loadUserByUsername(principal.getName());

        Post post = this.posts.save(new Post(req.getTitle(), req.getContent(), LocalDateTime.now(), author));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).body(PostMapper.toDto(post));

        // TODO Validation (creation using PostService)
    }

    @PutMapping("/post/{id}")
    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    public PostDto updatePost(Principal principal, @PathVariable("id") long id, @RequestBody PostSaveRequest req)
    {
        User user = this.users.loadUserByUsername(principal.getName());

        Optional<Post> post_ = this.posts.findById(id);

        if(post_.isEmpty())
        {
            throw new PostNotFoundException(id);
        }

        Post post = post_.get();

        // Only admins can modify posts of other users
        if(!post.getAuthor().equals(user) && !user.getRoles().contains("ADMIN"))
        {
            throw new PostUpdateException(PostUpdateException.Reason.NO_RIGHTS);
        }

        post
                .setTitle(req.getTitle())
                .setContent(req.getContent())
                .setModified(LocalDateTime.now());

        return PostMapper.toDto(this.posts.save(post));

        // TODO Validation (update using PostService)
    }

    @DeleteMapping("/post/{id}")
    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    public ResponseEntity<Void> deletePost(Principal principal, @PathVariable("id") long id)
    {
        User user = this.users.loadUserByUsername(principal.getName());

        Optional<Post> post_ = this.posts.findById(id);

        if(post_.isEmpty())
        {
            throw new PostNotFoundException(id);
        }

        Post post = post_.get();

        // Only admins can delete posts of other users
        if(!post.getAuthor().equals(user) && !user.getRoles().contains("ADMIN"))
        {
            throw new PostUpdateException(PostUpdateException.Reason.NO_RIGHTS);
        }

        this.posts.delete(post);

        return ResponseEntity.noContent().build();
    }

    // TODO HATEOAS
}
