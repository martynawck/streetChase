package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.repository.StreetGameRepository;
import streetChase.repository.SubRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class SubServiceImpl implements SubService {

    @Resource
    private SubRepository subRepository;


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

  /*  @Override
    @Transactional
    public  List findByUserSubscription(int id) {
        //final PageRequest pageRequest = new PageRequest(page, maxResults, sortByTitleASC());
        return streetGameRepository.findSubscribedByUserId(id);//findByTitleAndSupervisor("%" + keyword + "%", supervisor, pageRequest);
    }
*/




}