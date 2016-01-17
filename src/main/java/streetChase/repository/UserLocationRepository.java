package streetChase.repository;

import org.springframework.data.repository.CrudRepository;
import streetChase.model.UserLocation;

public interface UserLocationRepository extends CrudRepository<UserLocation, Integer> {
}