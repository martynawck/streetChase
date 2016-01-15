package streetChase.repository.mobile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import streetChase.model.User;

import java.io.Serializable;

public interface MobileLoginRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}