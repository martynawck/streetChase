package streetChase.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streetChase.GeometryUtil;
import streetChase.model.ControlPoint;
import streetChase.service.mobile.MobileControlPointService;
import streetChase.service.mobile.MobileLoginService;

@Controller
@RequestMapping(value = "/mobile")
public class MobileControlPointController {

    @Autowired
    private MobileControlPointService mobileControlPointService;

    @RequestMapping(value = "/control_point/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONObject> getControlPoint(@PathVariable("id") int id) {

      //  ControlPoint controlPoint = mobileControlPointService.findById(id);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        MobileControlPointService repository = context.getBean(MobileControlPointService.class);
        ControlPoint controlPoint = repository.findById(id);//findOne(location_id);//repository.save(userLocation);

        context.close();

        GeometryUtil geometryUtil = new GeometryUtil();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", controlPoint.getId());
        jsonObject.put("location", geometryUtil.wktFromGeometry(controlPoint.getLocation()));
        jsonObject.put("name", controlPoint.getName());
        jsonObject.put("next_point_id", controlPoint.getNext_point_id());
        jsonObject.put("street_game_id", controlPoint.getStreet_game_id());
        //  jsonObject.put("")


        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }

    @RequestMapping(value = "/control_point/initial/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONObject> getInitialControlPointForGame(@PathVariable("id") int gameId) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        MobileControlPointService repository = context.getBean(MobileControlPointService.class);
        ControlPoint controlPoint = repository.findInitialForGame(gameId);//findOne(location_id);//repository.save(userLocation);

        context.close();

        GeometryUtil geometryUtil = new GeometryUtil();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", controlPoint.getId());
        jsonObject.put("location", geometryUtil.wktFromGeometry(controlPoint.getLocation()));
        jsonObject.put("name", controlPoint.getName());
        jsonObject.put("next_point_id",controlPoint.getNext_point_id());
        jsonObject.put("street_game_id", controlPoint.getStreet_game_id());
        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.OK);
    }


}