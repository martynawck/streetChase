package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.dto.GamePlayerStatsDto;
import streetChase.dto.StatsDto;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.repository.SubscriptionRepository;
import streetChase.repository.UserRepository;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    @Resource
    private SubscriptionRepository subRepository;

    @Resource
    private UserRepository userRepository;

    @Transactional
    public List findByUser(int id) {
        return subRepository.findByUser(id);
    }

    @Transactional
    public List findByGame(int id) {
        return subRepository.findByGame(id);
    }

    @Transactional
    public List findByUserPlayed(int id) {
        return subRepository.findByUserPlayed(id);
    }

    @Transactional
    public List findByUserNotPlayed(int id) {
        return subRepository.findByUserNotPlayed(id);
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

    @Transactional
    public void setGamePlayed(int user, int game) {
        Subscription subscription = subRepository.findByUserAndGame(user, game);
        subscription.setPlayed(true);
        subRepository.save(subscription);//findByUserAndGame(user, game);//fi.fin(id);
    }

    @Transactional
    public void setGameUnPlayed(int user, int game) {
        Subscription subscription = subRepository.findByUserAndGame(user, game);
        subscription.setPlayed(false);
        subRepository.save(subscription);//findByUserAndGame(user, game);//fi.fin(id);
    }

    @Transactional
    public void setStartTime(int user, int game, long timestamp) {
        Subscription subscription = subRepository.findByUserAndGame(user, game);
        subscription.setGame_started(new Timestamp(timestamp));
        subRepository.save(subscription);//findByUserAndGame(user, game);//fi.fin(id);
    }

    @Transactional
    public void setEndTime(int user, int game, long timestamp) {
        Subscription subscription = subRepository.findByUserAndGame(user, game);
        subscription.setGame_finished(new Timestamp(timestamp));
        subRepository.save(subscription);//findByUserAndGame(user, game);//fi.fin(id);
    }

}