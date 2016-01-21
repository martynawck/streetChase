package streetChase.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streetChase.dto.GamePlayerStatsDto;
import streetChase.model.UserLocation;
import streetChase.model.UserReachedPoint;
import streetChase.repository.UserLocationRepository;
import streetChase.service.StreetGameService;
import streetChase.service.UserReachedPointService;
import streetChase.utils.GeometryUtil;
import streetChase.utils.RouteUtils;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martyna on 2016-01-20.
 */
@Controller
@RequestMapping(value = "/postgis")
public class PostGisController {

    @Autowired
    UserReachedPointService userReachedPointService;

    @Autowired
    StreetGameService streetGameService;

    @RequestMapping(value = "/x/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONObject> x(@PathVariable(value="id") int location_id) {

        String point1 = "POINT(-72.1235 42.3521)";
        String point2 = "POINT(-72.1260 42.45)";
        double distance = distanceBetweenPoints(point1, point2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("distance", distance);

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/z/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONObject> z(@PathVariable(value="id") int location_id) {

        String point1 = "POINT(-72.1235 42.3521)";
        String point2 = "POINT(-72.1260 42.45)";
        ArrayList<String> points = new ArrayList<String>();
        points.add(point1);
        points.add(point2);
        double distance = RouteUtils.lengthOfRoute(points);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("route", distance);

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/xyz/{userId}/{gameId}/{cp1}/{cp2}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONArray> xyz(@PathVariable(value="userId") int userId, @PathVariable(value="gameId") int gameId,
                                         @PathVariable(value="cp1") int controlPoint1,@PathVariable(value="cp2") int controlPoint2) {

        UserReachedPoint userReachedPoint1 = userReachedPointService.findByControlPointAndUser(userId, controlPoint1);//.getControlPoint(8);
        UserReachedPoint userReachedPoint2 = userReachedPointService.findByControlPointAndUser(userId, controlPoint2);//getControlPoint(9);

        List<UserLocation> userLocations;

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        UserLocationRepository repository = context.getBean(UserLocationRepository.class);
        userLocations = repository.findBetweenTimestamp(userId,gameId ,userReachedPoint1.getTimestamp(), userReachedPoint2.getTimestamp());

        context.close();

        GeometryUtil geometryUtil = new GeometryUtil();

        JSONArray jsonArray = new JSONArray();
        for (UserLocation userLocation :userLocations ) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("location", geometryUtil.wktFromGeometry(userLocation.getLocation()));
            jsonObject.put("timestamp",userLocation.getTimestamp());
            jsonArray.add(jsonObject);
        }

        return new ResponseEntity<JSONArray>(jsonArray, HttpStatus.CREATED);
    }

    /*Odleg�o�� mi�dzy punktami */

    public double distanceBetweenPoints(String point1, String point2) {
        java.sql.Connection conn;
        Double geom = new Double(0);

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, "postgres", "postgres");
            ((org.postgresql.PGConnection)conn).addDataType("geometry", "org.postgis.PGgeometry");
            ((org.postgresql.PGConnection)conn).addDataType("box3d","org.postgis.PGbox3d");
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT ST_Distance(\n" +
                    "\t\t\tST_Transform(ST_GeomFromText('"+point1+"',4326),26986),\n" +
                    "\t\t\tST_Transform(ST_GeomFromText('"+point2+"', 4326),26986)\n" +
                    "\t\t);");
            while( r.next() ) {

                geom = (Double)r.getDouble(1);//.getObject(1);
            }
            s.close();
            conn.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return geom;

    }

    @RequestMapping(method = RequestMethod.GET, value="/stats/{gameId}/{playerId}", produces = "application/json")
    public ResponseEntity<?> getStatsPerGamePlayer(@PathVariable("gameId") int gameId, @PathVariable("playerId") int playerId) {
        GamePlayerStatsDto dto = streetGameService.getGamePlayerStats(gameId, playerId);
        return new ResponseEntity<GamePlayerStatsDto>(dto, HttpStatus.OK);
    }

}