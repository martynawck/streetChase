package streetChase.service.mobile;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.model.UserLocation;
import streetChase.repository.mobile.MobileUserLocationRepository;


import javax.annotation.Resource;

/**
 * Created by Martyna on 2016-01-12.
 */
@Service
public class MobileUserLocationServiceImpl implements MobileUserLocationService {

    @Resource
    private MobileUserLocationRepository mobileUserLocationRepository;

   @Override
   @Transactional
    public void addUserLocation(UserLocation s) {
       mobileUserLocationRepository.save(s);
   }
}