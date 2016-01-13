package streetChase.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Subscription;
import streetChase.model.User;

import java.util.List;

public interface SubRepository extends CrudRepository<Subscription, Integer> {
    List<Subscription> findByUser(int id);
    List<Subscription> findByGame (int id);
    public final static String FIND_BY_KEYWORD_SUPERVISOR_QUERY =
            "select t from Subscription t where t.game = :game and t.user = :user";
    @Query(FIND_BY_KEYWORD_SUPERVISOR_QUERY)
    public Subscription findByUserAndGame(@Param("user") int user, @Param("game") int game);
}