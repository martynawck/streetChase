package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.UserReachedPoint;
import streetChase.repository.mobile.MobileUserReachedPointRepository;

import javax.annotation.Resource;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileUserReachedPointServiceImpl implements MobileUserReachedPointService {

    @Resource
    private MobileUserReachedPointRepository mobileUserReachedPointRepository;

    @Override
    @Transactional
    public void addReachedPoint(UserReachedPoint s) {
          mobileUserReachedPointRepository.save(s);
    }
}