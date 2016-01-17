package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import streetChase.model.UserReachedPoint;
import streetChase.service.mobile.MobileLoginService;
import streetChase.service.mobile.MobileUserReachedPointService;

import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/mobile")
public class MobileUserReachedPointController {

    @Autowired
    private MobileUserReachedPointService mobileUserReachedPointService;

    @RequestMapping(value = "/user_reached_point/{user_id}/{control_point}/{timestamp}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> saveUserQuestionStatus(@PathVariable("control_point") int control_point,
                                                       @PathVariable("user_id") int user_id, @PathVariable("timestamp") long timestamp) {

        UserReachedPoint userReachedPoint = new UserReachedPoint();
        userReachedPoint.setUser_id(user_id);
        userReachedPoint.setTimestamp(new Timestamp(timestamp));
        userReachedPoint.setControl_point_id(control_point);

        mobileUserReachedPointService.addReachedPoint(userReachedPoint);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}