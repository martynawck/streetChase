package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.User;
import streetChase.repository.LoginRepository;

import javax.annotation.Resource;

@Service
public class LoginService {

    @Resource
    private LoginRepository loginRepository;

    @Transactional
    public User findByName(String name) {
        return loginRepository.findByEmail(name);
    }



}