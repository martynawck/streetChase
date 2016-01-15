package streetChase.service.mobile;

import streetChase.model.StreetGame;
import streetChase.model.Subscription;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface MobileStreetGameService {

    public List findAll();
    public StreetGame findById(int id);
    public List findByCreator(int id);
    public void deleteStreetGame(StreetGame s);

}
