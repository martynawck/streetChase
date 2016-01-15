package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.User;
import streetChase.repository.mobile.MobileLoginRepository;

import javax.annotation.Resource;

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