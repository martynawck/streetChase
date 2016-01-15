package streetChase.controller;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import streetChase.model.Question;
import streetChase.model.UserLocation;
import streetChase.service.mobile.MobileLoginService;
import streetChase.service.mobile.MobileUserLocationService;

import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/mobile")
public class MobileUserLocationController {

    @Autowired
    private MobileUserLocationService mobileUserLocationService;


    // nie dzia³¹!!!
    @RequestMapping(value = "/question/user_location", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> greeting(@RequestParam(value="id") int user_id, @RequestParam(value="location_x") String location_x, @RequestParam(value="location_y") String location_y,
                                         @RequestParam(value="timestamp") long timestamp, @RequestParam(value="game") int game) {
        Double x = Double.parseDouble(location_x);
        Double y = Double.parseDouble(location_y);
        Coordinate coordinate = new Coordinate(x,y);
        PrecisionModel precisionModel = new PrecisionModel();
        Point point = new Point(coordinate,precisionModel, 4326 );
        Timestamp timestamp1 = new Timestamp(timestamp);
        UserLocation userLocation = new UserLocation();
        userLocation.setUser_id(user_id);
       // userLocation.setLocation(point);
        userLocation.setStreet_game_id(game);
        userLocation.setTimestamp(timestamp1);

        mobileUserLocationService.addUserLocation(userLocation);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}