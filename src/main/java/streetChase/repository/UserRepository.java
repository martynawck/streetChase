package streetChase.repository;

import org.springframework.data.repository.CrudRepository;
import streetChase.model.User;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
