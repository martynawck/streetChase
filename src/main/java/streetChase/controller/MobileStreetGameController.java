package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import streetChase.model.ControlPoint;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.model.UserReachedPoint;
import streetChase.repository.ControlPointRepository;
import streetChase.service.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/mobile/games")
public class MobileStreetGameController {

    @Autowired
    private StreetGameService sgService;

    @Autowired
    private SubscriptionService subService;

    @Autowired
    private UserLocationService userLocationService;

    @Autowired
    private ControlPointService controlPointService;

    @Autowired
    private UserReachedPointService userReachedPointService;

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
        List<StreetGame> currentGames = new ArrayList<StreetGame>();

        java.util.Date date= new java.util.Date();
        Timestamp now = new Timestamp(date.getTime());

        for (StreetGame sg: streetGames) {
            if (now.before(sg.getEnd_time())) {
                currentGames.add(sg);
            }
        }

        return new ResponseEntity<List<StreetGame>>(currentGames, HttpStatus.OK);
    }

    @RequestMapping(value = "/takingpartingames/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> findGamesForParticipation(@PathVariable("id") int id) {

        java.util.Date date= new java.util.Date();
        Timestamp now = new Timestamp(date.getTime());

        List<StreetGame> games = new ArrayList<StreetGame>();
        List<Subscription> subscriptions = subService.findByUserNotPlayed(id);//findByUser(id);
        for (Subscription s : subscriptions) {
            games.add(sgService.findById(s.getGame()));
        }

        List<StreetGame> currentGames = new ArrayList<StreetGame>();

        for (StreetGame sg: games) {
            if (now.before(sg.getEnd_time())) {
                currentGames.add(sg);
            }
        }

        return new ResponseEntity<List<StreetGame>>(currentGames, HttpStatus.OK);
    }

    @RequestMapping(value = "/completed/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> findCompletedGames(@PathVariable("id") int id) {

        java.util.Date date= new java.util.Date();
        Timestamp now = new Timestamp(date.getTime());

        List<StreetGame> games = new ArrayList<StreetGame>();
        List<Subscription> subscriptions = subService.findByUserPlayed(id);
        for (Subscription s : subscriptions) {
            games.add(sgService.findById(s.getGame()));
        }

        return new ResponseEntity<List<StreetGame>>(games, HttpStatus.OK);
    }


    @RequestMapping(value = "/othergames/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> findOtherGames(@PathVariable("id") int id) {

        java.util.Date date= new java.util.Date();
        Timestamp now = new Timestamp(date.getTime());

        List<StreetGame> games = new ArrayList<StreetGame>();
        List<Subscription> subscriptionsNotPlayed = subService.findByUserNotPlayed(id);
        List<Subscription> subscriptionsPlayed = subService.findByUserPlayed(id);
        List<Subscription> subscriptions = new ArrayList<Subscription>(subscriptionsNotPlayed);
        subscriptions.addAll(subscriptionsPlayed);
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

        List<StreetGame> currentGames = new ArrayList<StreetGame>();

        for (StreetGame sg: otherGames2) {
            if (now.before(sg.getEnd_time())) {
                currentGames.add(sg);
            }
        }

        return new ResponseEntity<List<StreetGame>>(currentGames, HttpStatus.OK);
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

        Subscription subscription = subService.findByUserAndGame(user, game);
        if (subscription == null) {
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        }

        subService.deleteSubscription(subscription);
        return new ResponseEntity<Subscription>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/subscription/{user}/{game}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Subscription> get(@PathVariable("user") int user, @PathVariable("game") int game) {

        Subscription subscription = subService.findByUserAndGame(user, game);
        if (subscription == null) {
            return new ResponseEntity<Subscription>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Subscription>(subscription, HttpStatus.OK);
    }

    @RequestMapping(value = "/subscription/{user}/{game}", method = RequestMethod.POST, produces = "application/json")
     public ResponseEntity<Void> createSubscription(@PathVariable("user") int user, @PathVariable("game") int game) {

        Subscription subscription = new Subscription();
        subscription.setGame(game);
        subscription.setUser(user);
        subscription.setPlayed(false);

        if (subService.findByUserAndGame(user,game) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        subService.createSubscription(subscription);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subscription/played/{user}/{game}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> setPlayed(@PathVariable("user") int user, @PathVariable("game") int game) {

        subService.setGamePlayed(user, game);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subscription/unplayed/{user}/{game}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> resetPlayed(@PathVariable("user") int user, @PathVariable("game") int game) {

        subService.setGameUnPlayed(user, game);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subscription/played/start/{user}/{game}/{timestamp}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> setStartTime(@PathVariable("user") int user, @PathVariable("game") int game, @PathVariable("timestamp") long timestamp) {

        subService.setStartTime(user, game, timestamp);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subscription/played/end/{user}/{game}/{timestamp}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<Void> setEndTime(@PathVariable("user") int user, @PathVariable("game") int game, @PathVariable("timestamp") long timestamp) {

        subService.setEndTime(user, game, timestamp);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/subscription/played/rollback/{user}/{game}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<Void> rollbackGamePlaying(@PathVariable("user") int user, @PathVariable("game") int game

    ) {

        userLocationService.deleteByGameAndUser(user,game);

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        ControlPointRepository repository = context.getBean(ControlPointRepository.class);
        List<ControlPoint> controlPoints = repository.findByGameId(game);//;//findOne(location_id);//repository.save(userLocation);

        context.close();

        for (ControlPoint p : controlPoints) {
            UserReachedPoint userReachedPoint = userReachedPointService.findByControlPointAndUser(user, p.getId());
            if (userReachedPoint != null)
                userReachedPointService.deleteUserReachedPoint(userReachedPoint);
        }

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}