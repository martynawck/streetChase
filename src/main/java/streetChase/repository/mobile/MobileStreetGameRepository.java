package streetChase.repository.mobile;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Contact;
import streetChase.model.StreetGame;

import java.io.Serializable;
import java.util.List;

public interface MobileStreetGameRepository extends Repository<StreetGame,Integer> ,JpaRepository<StreetGame, Integer>, CrudRepository<StreetGame, Integer> {

    List<StreetGame> findByCreator(int id);

 //   public final static String FIND_BY_SUBSCRIPTION_QUERY =
  //          "select sg from StreetGame sg, Subscription sgs where sg.id = sgs.street_game_id and sgs.user_id =:user_id  ";

    public final static String FIND_BY_KEYWORD_SUPERVISOR_QUERY =
            "select t from Topic t where t.title like :keyword and t.supervisor = :supervisor";

   // @Query(FIND_BY_SUBSCRIPTION_QUERY)
   // List<StreetGame> findSubscribedByUserId(@Param("user_id") int user_id );

}