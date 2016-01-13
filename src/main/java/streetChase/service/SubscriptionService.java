package streetChase.service;

import streetChase.model.StreetGame;

import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
public interface SubscriptionService {

    public List findAll();
    public List findByUserId(int id);
    public List findByGameId(int id);
}
