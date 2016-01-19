package streetChase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Subscription;
import streetChase.model.User;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    List<Subscription> findByUser(int id);
    List<Subscription> findByGame (int id);
    public final static String FIND_BY_GAME_AND_USER =
            "select t from Subscription t where t.game = :game and t.user = :user";
    @Query(FIND_BY_GAME_AND_USER)
    public Subscription findByUserAndGame(@Param("user") int user, @Param("game") int game);

    public final static String FIND_BY_USER_PLAYED =
            "select t from Subscription t where t.played = TRUE and t.user = :user";
    @Query(FIND_BY_USER_PLAYED)
    public List<Subscription> findByUserPlayed(@Param("user") int user);

    public final static String FIND_BY_USER_NOT_PLAYED =
            "select t from Subscription t where t.played = FALSE and t.user = :user";
    @Query(FIND_BY_USER_NOT_PLAYED)
    public List<Subscription> findByUserNotPlayed(@Param("user") int user);
}