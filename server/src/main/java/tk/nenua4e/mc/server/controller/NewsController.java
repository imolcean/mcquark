package tk.nenua4e.mc.server.controller;

import org.springframework.http.ResponseEntity;
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
import tk.nenua4e.mc.server.repository.UserRepository;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/v1/news")
public class NewsController
{
    private UserRepository users;
    private PostRepository posts;

    public NewsController(UserRepository users, PostRepository postRepository)
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

    @PostMapping("/post")
    public ResponseEntity<PostDto> createPost(@RequestBody PostSaveRequest req)
    {
        Optional<User> author_ = this.users.findByNick("Gory26");

        if(author_.isEmpty())
        {
            throw new UserNotFoundException("Gory26");
        }

        User author = author_.get();
        // TODO Authorization (only EDITOR)

        Post post = this.posts.save(new Post(req.getTitle(), req.getContent(), LocalDateTime.now(), author));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(post.getId())
                .toUri();

        return ResponseEntity.created(location).body(PostMapper.toDto(post));

        // TODO Validation
    }

    @PutMapping("/post/{id}")
    public PostDto updatePost(@PathVariable("id") long id, @RequestBody PostSaveRequest req)
    {
        Optional<User> author_ = this.users.findByNick("Gory26");

        if(author_.isEmpty())
        {
            throw new UserNotFoundException("Gory26");
        }

        User author = author_.get();
        // TODO Authorization (only EDITOR)

        Optional<Post> post_ = this.posts.findById(id);

        if(post_.isEmpty())
        {
            throw new PostNotFoundException(id);
        }

        Post post = post_.get();

        if(!post.getAuthor().equals(author))
        {
            throw new PostUpdateException(PostUpdateException.Reason.NO_RIGHTS);
        }
        // TODO Only author or ADMIN

        post.setTitle(req.getTitle()).setContent(req.getContent()).setModified(LocalDateTime.now());

        return PostMapper.toDto(this.posts.save(post));

        // TODO Validation
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") long id)
    {
        Optional<User> author_ = this.users.findByNick("Gory26");

        if(author_.isEmpty())
        {
            throw new UserNotFoundException("Gory26");
        }

        User author = author_.get();
        // TODO Authorization (only EDITOR)

        Optional<Post> post_ = this.posts.findById(id);

        if(post_.isEmpty())
        {
            throw new PostNotFoundException(id);
        }

        Post post = post_.get();

        if(!post.getAuthor().equals(author))
        {
            throw new PostUpdateException(PostUpdateException.Reason.NO_RIGHTS);
        }
        // TODO Only author or ADMIN

        this.posts.delete(post);

        return ResponseEntity.noContent().build();
    }

    // TODO HATEOAS
}
