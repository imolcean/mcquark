package tk.nenua4e.mc.server.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(exclude = "posts")
@Entity
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String email;

    private String rolesAbbr;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> posts;

    protected User() {}

    public User(String username, String password)
    {
        this(username, password, null, (String) null);
    }

    public User(String username, String password, String email)
    {
        this(username, password, email, (String) null);
    }

    public User(String username, String password, String email, Collection<String> roles)
    {
        this(username, password, email, (String) null);
        this.setRoles(roles);
    }

    public User(String username, String password, String email, String rolesAbbr)
    {
        this.username = username;
        this.password = password;
        this.email = email;
        this.rolesAbbr = rolesAbbr;
        this.posts = new HashSet<>();
    }

    public List<String> getRoles()
    {
        List<String> list = new ArrayList<>();

        if(this.getRolesAbbr() == null)
        {
            return list;
        }

        for(char c : this.getRolesAbbr().toUpperCase().toCharArray())
        {
            switch (c)
            {
                case 'O':
                    list.add("OP");
                    break;
                case 'E':
                    list.add("EDITOR");
                    break;
                case 'A':
                    list.add("ADMIN");
                    break;
                default:
                    break;
            }
        }

        return list;
    }

    public void setRoles(Collection<String> roles)
    {
        StringBuilder abbr = new StringBuilder();

        for(String str : roles)
        {
            switch (str.toUpperCase())
            {
                case "OP":
                    abbr.append("O");
                    break;
                case "EDITOR":
                    abbr.append("E");
                    break;
                case "ADMIN":
                    abbr.append("A");
                    break;
                default:
                    break;
            }
        }

        this.setRolesAbbr(abbr.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return this.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
