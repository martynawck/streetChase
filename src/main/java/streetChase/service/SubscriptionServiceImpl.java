package streetChase.service;

import org.springframework.transaction.annotation.Transactional;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.repository.StreetGameRepository;
import streetChase.repository.SubscriptionRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public class SubscriptionServiceImpl implements SubscriptionService {


    @Resource
    private SubscriptionRepository subscriptionRepository;

    @Override
    @Transactional
    public List findAll() {
        return subscriptionRepository.findAll();
    }


    @Override
    @Transactional
    public List<Subscription> findByUserId(int id) {
        return subscriptionRepository.findByUser(id);//.find.findOne(id);
    }

    @Override
    @Transactional
    public List<Subscription> findByGameId(int id) {
        return subscriptionRepository.findByGame(id);//.findByCreator(id);
    }
}
