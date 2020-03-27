package tk.nenua4e.mc.server.repository;

import org.springframework.data.repository.CrudRepository;
import tk.nenua4e.mc.server.model.User;
import tk.nenua4e.mc.server.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends CrudRepository<Post, Long>
{
    List<Post> findByPublishedIsNullOrderByCreated();

    List<Post> findByPublishedIsNotNullOrderByPublished();

    Optional<Post> findById(long id);

    List<Post> findByTitle(String title);

    List<Post> findByAuthor(User author);
}
