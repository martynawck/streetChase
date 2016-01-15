package streetChase.repository.mobile;

import org.springframework.data.repository.CrudRepository;
import streetChase.model.Question;
import streetChase.model.UserReachedPoint;

public interface MobileUserReachedPointRepository extends CrudRepository<UserReachedPoint, Integer> {
}