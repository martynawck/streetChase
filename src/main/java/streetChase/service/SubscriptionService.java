package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.Subscription;
import streetChase.repository.SubscriptionRepository;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SubscriptionService {

    @Resource
    private SubscriptionRepository subRepository;

    @Transactional
    public List findByUser(int id) {
        return subRepository.findByUser(id);
    }

    @Transactional
    public List findByGame(int id) {
        return subRepository.findByGame(id);
    }

    @Transactional
    public void deleteSubscription(Subscription s) {
        subRepository.delete(s);
    }

    @Transactional
    public Subscription findById(int id) {
        return subRepository.findOne(id);
    }

    @Transactional
    public Subscription findByUserAndGame(int user, int game) {
        return subRepository.findByUserAndGame(user, game);//fi.fin(id);
    }

    @Transactional
    public void createSubscription(Subscription s) {
        subRepository.save(s);//findByUserAndGame(user, game);//fi.fin(id);
    }
}