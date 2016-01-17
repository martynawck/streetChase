package streetChase.controller;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import org.codehaus.jackson.map.util.JSONPObject;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import streetChase.service.UserLocationService;
import streetChase.utils.GeometryUtil;
import streetChase.model.UserLocation;

import streetChase.repository.UserLocationRepository;
import streetChase.service.UserLocationService;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(value = "/mobile")
public class MobileUserLocationController {

    @Autowired
    private UserLocationService userLocationService;


    @RequestMapping(value = "/user_location", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> saveUserLocation(@RequestParam(value="id") int user_id, @RequestParam(value="timestamp") long timestamp, @RequestParam(value="game") int game,
                                                 @RequestParam(value="y") String y, @RequestParam(value="x") int x) {

        String point = "POINT("+x+" "+y+")";
        GeometryUtil geometryUtil = new GeometryUtil();
        Geometry geom = geometryUtil.wktToGeometry(point);

        if (!geom.getGeometryType().equals("Point")) {
            throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
        }

        Timestamp timestamp1 = new Timestamp(timestamp);
        UserLocation userLocation = new UserLocation();
        userLocation.setUser_id(user_id);
        userLocation.setLocation((Point) geom);
        userLocation.setStreet_game_id(game);
        userLocation.setTimestamp(timestamp1);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        UserLocationRepository repository = context.getBean(UserLocationRepository.class);
        repository.save(userLocation);

        context.close();
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user_location/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONObject> getUserLocation(@PathVariable(value="id") int location_id) {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        UserLocationRepository repository = context.getBean(UserLocationRepository.class);
        UserLocation userLocation = repository.findOne(location_id);//repository.save(userLocation);

        context.close();

        GeometryUtil geometryUtil = new GeometryUtil();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", userLocation.getId());
        jsonObject.put("location", geometryUtil.wktFromGeometry(userLocation.getLocation()));
        jsonObject.put("timestamp", userLocation.getTimestamp());
        jsonObject.put("user_id",userLocation.getUser_id());
        jsonObject.put("street_game_id", userLocation.getStreet_game_id());
        //  jsonObject.put("")

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.CREATED);
    }


}