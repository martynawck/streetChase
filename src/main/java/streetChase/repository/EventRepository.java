package streetChase.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import streetChase.model.Event;

/**
 * Created by Martyna on 2016-01-16.
 */
@Repository
public interface EventRepository extends CrudRepository<Event, Long> {
    Event getById(Long id);
}
