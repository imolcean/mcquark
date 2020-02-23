package tk.nenua4e.mc.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "posts")
@Entity
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String nick;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> posts;

    protected User() {}

    public User(String nick)
    {
        this.nick = nick;
        this.posts = new HashSet<>();
    }
}
