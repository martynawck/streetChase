package streetChase.repository;

import org.springframework.data.repository.CrudRepository;
import streetChase.model.Question;
import streetChase.model.UserReachedPoint;

public interface UserReachedPointRepository extends CrudRepository<UserReachedPoint, Integer> {
}