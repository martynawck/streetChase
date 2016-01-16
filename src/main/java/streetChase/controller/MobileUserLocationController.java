package streetChase.controller;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import streetChase.model.Event;
import streetChase.model.Question;
import streetChase.model.UserLocation;
import streetChase.service.EventService;
import streetChase.service.mobile.MobileLoginService;
import streetChase.service.mobile.MobileUserLocationService;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile")
public class MobileUserLocationController {

    @Autowired
    private MobileUserLocationService mobileUserLocationService;

    @Autowired
    private EventService eventService;


    // nie dzia³¹!!!
    @RequestMapping(value = "/user_location", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> greeting(@RequestParam(value="id") int user_id, @RequestParam(value="timestamp") long timestamp, @RequestParam(value="game") int game) {
     //   Double x = Double.parseDouble(location_x);
      //  Double y = Double.parseDouble(location_y);
     //   Coordinate coordinate = new Coordinate(x,y);
        PrecisionModel precisionModel = new PrecisionModel();
     //   Point point = new Point(coordinate,precisionModel, 4326 );
        String point2 = "POINT(10 5)";
        Geometry geom = wktToGeometry(point2);

        if (!geom.getGeometryType().equals("Point")) {
            throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
        }

        Timestamp timestamp1 = new Timestamp(timestamp);
        UserLocation userLocation = new UserLocation();
        userLocation.setUser_id(user_id);
        userLocation.setLocation((Point) geom);
        userLocation.setStreet_game_id(game);
        userLocation.setTimestamp(timestamp1);

        Event event1 = createEvent("Event 1", new Date(), "POINT(10 5)");
        eventService.saveEvent(event1);

      //  Point pk = new Point();

       // org.postgis.PGgeometry m = new org.postgis.PGgeometry(myJtsGeometry.toText());

        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/user_location/x", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Event> hoh() {
        //   Double x = Double.parseDouble(location_x);
        //  Double y = Double.parseDouble(location_y);
        //   Coordinate coordinate = new Coordinate(x,y);
        Event e = eventService.findByEmail((long)1);

        //  Point pk = new Point();

        // org.postgis.PGgeometry m = new org.postgis.PGgeometry(myJtsGeometry.toText());

        return new ResponseEntity<Event>(e, HttpStatus.OK);
    }




    private static Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);
        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }


    private static Event createEvent(String title, Date theDate, String wktPoint) {
        Geometry geom = wktToGeometry(wktPoint);

        if (!geom.getGeometryType().equals("Point")) {
            throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
        }


        Event theEvent = new Event();
        theEvent.setTitle(title);
        theEvent.setDate(theDate);
        theEvent.setLocation((Point) geom);
        return theEvent;
    }


}