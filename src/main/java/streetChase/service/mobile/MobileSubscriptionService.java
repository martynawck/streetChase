package streetChase.service.mobile;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.model.User;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface MobileSubscriptionService {

    public List findByUser(int id);
    public List findByGame(int id);

    public Subscription findByUserAndGame (int user, int game);
    public void deleteSubscription(Subscription s);
    public Subscription findById(int id);
    public void createSubscription (Subscription s);


}
