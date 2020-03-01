package tk.nenua4e.mc.server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.nenua4e.mc.server.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long>
{
    Optional<User> findById(long id);

    Optional<User> findByUsername(String username);
}
