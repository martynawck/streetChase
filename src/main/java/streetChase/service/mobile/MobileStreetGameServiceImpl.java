package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.StreetGame;
import streetChase.repository.mobile.MobileStreetGameRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileStreetGameServiceImpl implements MobileStreetGameService {

    @Resource
    private MobileStreetGameRepository streetGameRepository;

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
        return streetGameRepository.findByCreatorId(id);
    }

    @Override
    @Transactional
    public void deleteStreetGame(StreetGame s) {
        streetGameRepository.delete(s);
    }

}