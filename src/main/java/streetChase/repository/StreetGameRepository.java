package streetChase.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import streetChase.model.StreetGame;

import java.util.List;

public interface StreetGameRepository extends Repository<StreetGame,Integer> ,JpaRepository<StreetGame, Integer>, CrudRepository<StreetGame, Integer> {

    //List<StreetGame> findByIs_private(boolean is_private);

    List<StreetGame> findByCreatorId(int id);
    public final static String FIND_BY_TIMESTAMP_AND_ID =
                      "select sg from StreetGame sg where sg.end_time >= now() and sg.id =:id  ";
    @Query(FIND_BY_TIMESTAMP_AND_ID)
    List<StreetGame> findByIdAndCurrentTime(@Param("id") int id);

 //   public final static String FIND_BY_SUBSCRIPTION_QUERY =
  //          "select sg from StreetGame sg, Subscription sgs where sg.id = sgs.street_game_id and sgs.user_id =:user_id  ";

   // @Query(FIND_BY_SUBSCRIPTION_QUERY)
   // List<StreetGame> findSubscribedByUserId(@Param("user_id") int user_id );

}