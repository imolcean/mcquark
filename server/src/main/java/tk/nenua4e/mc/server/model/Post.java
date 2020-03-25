package tk.nenua4e.mc.server.model;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@Entity
public class Post
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @Column(columnDefinition = "text")
    private String preview;

    @Column(columnDefinition = "text")
    private String content;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn
    private User author;

    protected Post() {}

    public Post(String title, String preview, String content, LocalDateTime created, User author)
    {
        this(title, preview, content, created, created, author);
    }

    public Post(String title, String preview, String content, LocalDateTime created, LocalDateTime modified, User author)
    {
        this.title = title;
        this.preview = preview;
        this.content = content;
        this.created = created;
        this.modified = modified;
        this.author = author;
    }

    public String toString()
    {
        return String.format("Post(title=%s, created=%s, author=%s)",
                this.title, this.created, this.author.getUsername());
    }
}
