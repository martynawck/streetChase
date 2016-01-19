package streetChase.service;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import streetChase.model.UserLocation;
import streetChase.repository.UserLocationRepository;
import streetChase.utils.GeometryUtil;


import javax.annotation.Resource;
import java.sql.Timestamp;

@Service
public class UserLocationService {

    @Resource
    private UserLocationRepository userLocationRepository;

    @Transactional
    public void addUserLocation(UserLocation s) {
        userLocationRepository.save(s);
    }

    @Transactional
    public void deleteByGameAndUser(int user_id, int game_id) {

        userLocationRepository.deleteAllByUserAndGame(user_id, game_id);//ave(s);
    }

}