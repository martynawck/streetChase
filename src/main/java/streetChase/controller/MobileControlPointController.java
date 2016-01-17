package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streetChase.model.ControlPoint;
import streetChase.service.ControlPointService;

@Controller
@RequestMapping(value = "/mobile")
public class MobileControlPointController {

    @Autowired
    private ControlPointService controlPointService;

    @RequestMapping(value = "/control_point/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ControlPoint> getControlPoint(@PathVariable("id") int id) {

        ControlPoint controlPoint = controlPointService.findById(id);
        return new ResponseEntity<ControlPoint>(controlPoint, HttpStatus.OK);
    }

    @RequestMapping(value = "/control_point/initial/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<ControlPoint> getInitialControlPointForGame(@PathVariable("id") int gameId) {

        ControlPoint controlPoint = controlPointService.findInitialForGame(gameId);
        return new ResponseEntity<ControlPoint>(controlPoint, HttpStatus.OK);
    }
}