package streetChase.service;

import streetChase.model.StreetGame;
import streetChase.model.Subscription;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface SGService {

    public List findAll();
    public StreetGame findById(int id);
    public List findByCreator(int id);
    public void deleteStreetGame(StreetGame s);
 //   public List findByUserSubscription (int id);

}
