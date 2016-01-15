package streetChase.repository.mobile;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import streetChase.model.Question;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;

import java.util.List;

public interface MobileQuestionRepository extends CrudRepository<Question, Integer> {
    Question findByControlPoint(int id);
}