package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import streetChase.model.StreetGame;
import streetChase.model.User;
import streetChase.service.MobileLoginService;

@Controller
@RequestMapping(value = "/mobile")
public class MobileLoginController {

    @Autowired
    private MobileLoginService mobileLoginService;

    @RequestMapping(value="/controller", method = RequestMethod.GET)
    @ResponseBody
    public String foo() {
        return "Response!";
    }

    @RequestMapping(value = "/login/{username}/{password}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<User> getUser(@PathVariable("username") String username, @PathVariable("password") String password) {

        User user = mobileLoginService.findByName(username);
        if (!user.getPassword().equals(password))
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}