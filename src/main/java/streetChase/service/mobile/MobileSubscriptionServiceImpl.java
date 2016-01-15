package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.Subscription;
import streetChase.repository.mobile.MobileSubscriptionRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileSubscriptionServiceImpl implements MobileSubscriptionService {

    @Resource
    private MobileSubscriptionRepository subRepository;


    @Override
    @Transactional
    public List findByUser(int id) {
        return subRepository.findByUser(id);
    }

    @Override
    @Transactional
    public List findByGame(int id) {
        return subRepository.findByGame(id);
    }

    @Override
    @Transactional
    public void deleteSubscription(Subscription s) {
        subRepository.delete(s);
    }

    @Override
    @Transactional
    public Subscription findById(int id) {
        return subRepository.findOne(id);
    }

    @Override
    @Transactional
    public Subscription findByUserAndGame(int user, int game) {
        return subRepository.findByUserAndGame(user, game);//fi.fin(id);
    }

    @Override
    @Transactional
    public void createSubscription(Subscription s) {
        subRepository.save(s);//findByUserAndGame(user, game);//fi.fin(id);
    }


}