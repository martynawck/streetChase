package streetChase.repository;

import org.springframework.data.repository.CrudRepository;
import streetChase.model.UserLocation;
import streetChase.model.UserQuestionStatus;

public interface UserLocationRepository extends CrudRepository<UserLocation, Integer> {
}