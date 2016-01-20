package streetChase.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Subscription;
import streetChase.model.UserLocation;

import java.util.List;

public interface UserLocationRepository extends CrudRepository<UserLocation, Integer> {
    public final static String FIND_BY_GAME_AND_USER =
            "select t from UserLocation t where t.street_game_id = :game and t.user_id = :user order by t.timestamp";
    @Query(FIND_BY_GAME_AND_USER)
    public List<UserLocation> getByGameAndUser(@Param("game") int gameId, @Param("user") int userId);

    public final static String DELETE_BY_GAME_AND_USER =
            "delete from UserLocation t where t.street_game_id = :game and t.user_id = :user";
    @Query(DELETE_BY_GAME_AND_USER)
    @Modifying
    public void deleteAllByUserAndGame(@Param("user") int user, @Param("game") int game);
}