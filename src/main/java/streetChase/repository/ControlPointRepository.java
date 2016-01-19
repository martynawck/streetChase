package streetChase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.ControlPoint;
import streetChase.model.Subscription;

import java.util.List;

public interface ControlPointRepository extends CrudRepository<ControlPoint, Integer> {
    public final static String FIND_GAME_STARTING_POINT =
            "select t from ControlPoint t where t.street_game_id = :game_id and t.starting_point = true";
    @Query(FIND_GAME_STARTING_POINT)
    public ControlPoint findGameStartingPoint(@Param("game_id") int game_id);

    public final static String FIND_BY_GAME_ID =
            "select t from ControlPoint t where t.street_game_id = :game_id";
    @Query(FIND_BY_GAME_ID)
    public List<ControlPoint> findByGameId(@Param("game_id") int game_id);
}