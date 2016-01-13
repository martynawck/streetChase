package streetChase.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.repository.StreetGameRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class SGServiceImpl implements SGService {

    @Resource
    private StreetGameRepository streetGameRepository;



    @Override
    @Transactional
    public List findAll() {
        return streetGameRepository.findAll();
    }

    @Override
    @Transactional
    public StreetGame findById(int id) {
        return streetGameRepository.findOne(id);
    }

    @Override
    @Transactional
    public List findByCreator(int id) {
        return streetGameRepository.findByCreator(id);
    }

    @Override
    @Transactional
    public void deleteStreetGame(StreetGame s) {
        streetGameRepository.delete(s);
    }

  /*  @Override
    @Transactional
    public  List findByUserSubscription(int id) {
        //final PageRequest pageRequest = new PageRequest(page, maxResults, sortByTitleASC());
        return streetGameRepository.findSubscribedByUserId(id);//findByTitleAndSupervisor("%" + keyword + "%", supervisor, pageRequest);
    }
*/




}