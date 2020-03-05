package tk.nenua4e.mc.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tk.nenua4e.mc.server.model.Post;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.PostRepository;
import tk.nenua4e.mc.server.repository.UserRepository;

import java.time.LocalDateTime;

@Component
public class DummyDataLoader implements ApplicationRunner
{
    private UserRepository users;
    private PostRepository posts;

    private PasswordEncoder encoder;

    @Autowired
    public DummyDataLoader(UserRepository userRepository, PostRepository postRepository, PasswordEncoder encoder) {
        this.users = userRepository;
        this.posts = postRepository;
        this.encoder = encoder;
    }

    public void run(ApplicationArguments args) {
        User gory26 = new User("Gory26", this.encoder.encode("abc123"), null, "OEA");
        User quarkian = new User("Quarkian", this.encoder.encode("abc123"), null, "E");
        User shushumiga = new User("Shushumiga", this.encoder.encode("abc123"), "shushumiga@creeperov.net");

        this.users.save(gory26);
        this.users.save(quarkian);
        this.users.save(shushumiga);

        this.posts.save(new Post("First post", "Lorem ipsum...", LocalDateTime.now(), gory26));
        this.posts.save(new Post("Second post", "Lorem ipsum...", LocalDateTime.now(), quarkian));
        this.posts.save(new Post("Third post", "Lorem ipsum...", LocalDateTime.now(), shushumiga));
        this.posts.save(new Post("Fourth post", "Lorem ipsum...", LocalDateTime.now(), shushumiga));
    }
}
