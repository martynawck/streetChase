package streetChase.repository.mobile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.ControlPoint;
import streetChase.model.Subscription;

import java.util.List;

public interface MobileControlPointRepository extends CrudRepository<ControlPoint, Integer> {
    public final static String FIND_GAME_STARTING_POINT =
            "select t from ControlPoint t where t.street_game_id = :game_id and t.starting_point = true";
    @Query(FIND_GAME_STARTING_POINT)
    public ControlPoint findGameStartingPoint(@Param("game_id") int game_id);
}