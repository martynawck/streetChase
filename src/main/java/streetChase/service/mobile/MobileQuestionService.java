package streetChase.service.mobile;

import streetChase.model.Question;
import streetChase.model.Subscription;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface MobileQuestionService {

    public Question findById(int id);
    public Question findByControlPoint (int id);


}
