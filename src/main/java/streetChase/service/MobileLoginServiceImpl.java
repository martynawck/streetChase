package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.StreetGame;
import streetChase.model.User;
import streetChase.repository.MobileLoginRepository;
import streetChase.repository.StreetGameRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileLoginServiceImpl implements MobileLoginService {

    @Resource
    private MobileLoginRepository mobileLoginRepository;

    @Override
    @Transactional
    public User findByName(String name) {
        return mobileLoginRepository.findByEmail(name);
    }



}