package streetChase.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import streetChase.model.StreetGame;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public ModelAndView doGet() {
        return new ModelAndView("login");
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StreetGame> getUser(@PathVariable("id") long id) {
        System.out.println("Fetching User with id " + id);
        /*User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }*/
        StreetGame streetGame = new StreetGame();
        streetGame.setId((int)id);
        streetGame.setName("Game1");
        return new ResponseEntity<StreetGame>(streetGame, HttpStatus.OK);
    }
}
