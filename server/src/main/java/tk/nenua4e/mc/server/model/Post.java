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

    private String content;

    private LocalDateTime created;

    private LocalDateTime modified;

    @ManyToOne
    @JoinColumn
    private User author;

    protected Post() {}

    public Post(String title, String content, LocalDateTime created, User author)
    {
        this(title, content, created, created, author);
    }

    public Post(String title, String content, LocalDateTime created, LocalDateTime modified, User author)
    {
        this.title = title;
        this.content = content;
        this.created = created;
        this.modified = modified;
        this.author = author;
    }

    public String toString()
    {
        return String.format("Post(title=%s, content=%s, dateTime=%s, author=%s)",
                this.title, this.content, this.created, this.author.getUsername());
    }
}
