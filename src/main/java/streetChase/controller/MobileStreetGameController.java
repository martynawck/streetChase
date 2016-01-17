package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.service.LoginService;
import streetChase.service.StreetGameService;
import streetChase.service.SubscriptionService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile/games")
public class MobileStreetGameController {

    @Autowired
    private StreetGameService sgService;

    @Autowired
    private SubscriptionService subService;

    @RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> getAllGames() {
        List<StreetGame> users = sgService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<StreetGame>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<StreetGame>>(users, HttpStatus.OK);

    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StreetGame> getGame(@PathVariable("id") int id) {
        StreetGame streetGame = sgService.findById(id);
        return new ResponseEntity<StreetGame>(streetGame, HttpStatus.OK);
    }

    @RequestMapping(value = "/mygames/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> findCreatedGames(@PathVariable("id") int id) {

        List<StreetGame> streetGames = sgService.findByCreator(id);
        return new ResponseEntity<List<StreetGame>>(streetGames, HttpStatus.OK);
    }


    @RequestMapping(value = "/takingpartingames/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> findGamesForParticipation(@PathVariable("id") int id) {

        List<StreetGame> games = new ArrayList<StreetGame>();
        List<Subscription> subscriptions = subService.findByUser(id);
        for (Subscription s : subscriptions) {
            games.add(sgService.findById(s.getGame()));
        }
        return new ResponseEntity<List<StreetGame>>(games, HttpStatus.OK);
    }


    @RequestMapping(value = "/othergames/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> findOtherGames(@PathVariable("id") int id) {

        List<StreetGame> games = new ArrayList<StreetGame>();
        List<Subscription> subscriptions = subService.findByUser(id);
        for (Subscription s : subscriptions) {
            games.add(sgService.findById(s.getGame()));
        }

        boolean ourgame = false;
        List<StreetGame> otherGames = new ArrayList<StreetGame>();
        List<StreetGame> otherGames2 = new ArrayList<StreetGame>();
        List<StreetGame> allGames = sgService.findAll();
        List<StreetGame> createdByMe = sgService.findByCreator(id);
        for (StreetGame sg : allGames) {
            for (StreetGame g: games) {
                if (g.equals(sg)) {
                    ourgame = true;
                }
            }
            if (ourgame == true) {
                ourgame = false;
            } else {
                otherGames.add(sg);
            }

        }

        ourgame = false;
        for (StreetGame sg : otherGames) {
            for (StreetGame g: createdByMe) {
                if (g.equals(sg)) {
                    ourgame = true;
                }
            }
            if (ourgame == true) {
                ourgame = false;
            } else {
                otherGames2.add(sg);
            }

        }

        return new ResponseEntity<List<StreetGame>>(otherGames2, HttpStatus.OK);
    }


    @RequestMapping(value = "/mygames/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<StreetGame> deleteMyGame(@PathVariable("id") int id) {

        List<Subscription> subscriptions = subService.findByGame(id);
        for (Subscription s : subscriptions)
            subService.deleteSubscription(s);
        StreetGame streetGame = sgService.findById(id);
        sgService.deleteStreetGame(streetGame);
        return new ResponseEntity<StreetGame>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subscription/{user}/{game}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Subscription> delete(@PathVariable("user") int user, @PathVariable("game") int game) {

        Subscription subscription = subService.findByUserAndGame(user,game);//.findById(id);
        if (subscription == null) {
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        }

        subService.deleteSubscription(subscription);
        return new ResponseEntity<Subscription>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subscription/{user}/{game}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> create(@PathVariable("user") int user, @PathVariable("game") int game) {

        Subscription subscription = new Subscription();
        subscription.setGame(game);
        subscription.setUser(user);

        if (subService.findByUserAndGame(user,game) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        subService.createSubscription(subscription);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

}