package streetChase.repository.mobile;

import org.springframework.data.repository.CrudRepository;
import streetChase.model.UserLocation;
import streetChase.model.UserQuestionStatus;

public interface MobileUserLocationRepository extends CrudRepository<UserLocation, Integer> {
}