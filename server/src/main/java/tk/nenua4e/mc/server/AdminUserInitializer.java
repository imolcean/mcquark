package tk.nenua4e.mc.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tk.nenua4e.mc.server.model.Post;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.PostRepository;
import tk.nenua4e.mc.server.repository.UserRepository;

import java.time.LocalDateTime;

@Component
@Profile("prod")
public class AdminUserInitializer implements ApplicationRunner
{
    private UserRepository users;

    private PasswordEncoder encoder;

    @Autowired
    public AdminUserInitializer(UserRepository userRepository, PasswordEncoder encoder) {
        this.users = userRepository;
        this.encoder = encoder;
    }

    public void run(ApplicationArguments args) {
        if(this.users.count() > 0)
        {
            return;
        }

        this.users.save(new User("admin", this.encoder.encode("GKLIbUgU29h0jGL8sCv5"), null, "A"));
    }
}
