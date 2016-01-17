package streetChase.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.UserLocation;
import streetChase.repository.UserLocationRepository;


import javax.annotation.Resource;

@Service
public class UserLocationService {

    @Resource
    private UserLocationRepository userLocationRepository;

    @Transactional
    public void addUserLocation(UserLocation s) {
       userLocationRepository.save(s);
   }
}