package streetChase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Question;
import streetChase.model.Subscription;
import streetChase.model.UserReachedPoint;

import java.util.List;

public interface UserReachedPointRepository extends CrudRepository<UserReachedPoint, Integer> {
    public final static String FIND_BY_CONTROL_POINT_AND_USER =
            "select t from UserReachedPoint t where t.control_point_id = :control_point and t.user_id = :user";
    @Query(FIND_BY_CONTROL_POINT_AND_USER)
    public UserReachedPoint findByControlPointAndUser(@Param("user") int user, @Param("control_point") int control_point);
}