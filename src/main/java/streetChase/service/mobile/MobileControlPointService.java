package streetChase.service.mobile;

import streetChase.model.ControlPoint;
import streetChase.model.Subscription;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface MobileControlPointService {

    public ControlPoint findInitialForGame(int id);
    public ControlPoint findById(int id);


}
