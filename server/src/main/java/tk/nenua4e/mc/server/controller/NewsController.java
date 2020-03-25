package tk.nenua4e.mc.server.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tk.nenua4e.mc.server.dto.PostDto;
import tk.nenua4e.mc.server.dto.PostMetaDto;
import tk.nenua4e.mc.server.service.NewsService;

import java.net.URI;
import java.security.Principal;
import java.util.List;

// TODO HATEOAS

@RestController()
@RequestMapping("api/v1")
public class NewsController
{
    private NewsService news;

    public NewsController(NewsService news)
    {
        this.news = news;
    }

    @GetMapping("/feed")
    public List<PostDto> getFeed()
    {
        return this.news.getAllPosts();
    }

    @GetMapping("/feed/meta")
    public List<PostMetaDto> getFeedMeta()
    {
        return this.news.getAllPostsMeta();
    }

    @GetMapping("/post/{id}")
    public PostDto getPost(@PathVariable("id") long id)
    {
        return this.news.getPost(id);
    }

    @PostMapping("/feed")
    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    public ResponseEntity<PostDto> createPost(Principal principal,
                                              @RequestBody @Validated(PostDto.CreationValidationGroup.class) PostDto dto)
    {
        PostDto created = this.news.createPost(dto.setAuthorUsername(principal.getName()));

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();

        return ResponseEntity.created(location).body(created);
    }

    @PutMapping("/post/{id}")
    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    public PostDto updatePost(Principal principal,
                              @PathVariable("id") long id,
                              @RequestBody @Validated(PostDto.UpdateValidationGroup.class) PostDto dto)
    {
        return this.news.updatePost(dto.setId(id), principal.getName());
    }

    @DeleteMapping("/post/{id}")
    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    public ResponseEntity<Void> deletePost(Principal principal, @PathVariable("id") long id)
    {
        this.news.deletePost(id, principal.getName());

        return ResponseEntity.noContent().build();
    }
}
