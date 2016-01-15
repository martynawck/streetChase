package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.ControlPoint;
import streetChase.repository.mobile.MobileControlPointRepository;

import javax.annotation.Resource;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileControlPointServiceImpl implements MobileControlPointService {

    @Resource
    private MobileControlPointRepository mobileControlPointRepository;

    @Override
    @Transactional
    public ControlPoint findInitialForGame(int id) {
        return mobileControlPointRepository.findGameStartingPoint(id);
    }

    @Override
    @Transactional
    public ControlPoint findById(int id) {
        return mobileControlPointRepository.findOne(id);

    }


}