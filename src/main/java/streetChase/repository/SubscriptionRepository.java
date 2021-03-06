package streetChase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Subscription;

import java.util.List;

public interface SubscriptionRepository extends CrudRepository<Subscription, Integer> {
    public final static String FIND_BY_USER =
            "select t from Subscription t where t.user = :user";
    @Query(FIND_BY_USER)
    List<Subscription> findByUser(@Param("user") int id);


    public final static String FIND_BY_GAME =
            "select t from Subscription t where t.game = :game";
    @Query(FIND_BY_GAME)
    List<Subscription> findByGame ( @Param("game")int id);


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


    public final static String FIND_BY_GAME_PLAYED =
            "select s from Subscription s where s.played = true and s.game = :game";
    @Query(FIND_BY_GAME_PLAYED)
    List<Subscription> findByGamePlayed( @Param("game")int id);
}