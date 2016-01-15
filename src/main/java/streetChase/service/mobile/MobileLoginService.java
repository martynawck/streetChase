package streetChase.service.mobile;

import streetChase.model.StreetGame;
import streetChase.model.User;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface MobileLoginService {

    public User findByName(String name);

}
