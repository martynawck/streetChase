package streetChase.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import streetChase.model.StreetGame;

import java.util.List;

public interface StreetGameRepository extends Repository<StreetGame,Integer> ,JpaRepository<StreetGame, Integer>, CrudRepository<StreetGame, Integer> {

    public final static String FIND_BY_TIMESTAMP_AND_ID =
            "select sg from StreetGame sg where sg.end_time >= now() and sg.id =:id  ";

    List<StreetGame> findByCreatorId(int id);

    @Query(FIND_BY_TIMESTAMP_AND_ID)
    List<StreetGame> findByIdAndCurrentTime(@Param("id") int id);

}