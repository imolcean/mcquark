package tk.nenua4e.mc.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import tk.nenua4e.mc.server.model.Post;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.repository.PostRepository;
import tk.nenua4e.mc.server.repository.UserRepository;

@Slf4j
@SpringBootApplication
public class ServerApplication
{
    public static void main(String[] args)
    {
        SpringApplication.run(ServerApplication.class, args);
    }
}
