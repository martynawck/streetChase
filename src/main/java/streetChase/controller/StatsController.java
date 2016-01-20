package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import streetChase.dto.GamePlayerStatsDto;
import streetChase.dto.StatsDto;
import streetChase.service.StreetGameService;
import streetChase.service.SubscriptionService;

import java.util.List;

@Controller
@RequestMapping(value = "/protected/stats")
public class StatsController {

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    StreetGameService streetGameService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView stats() {
        return new ModelAndView("stats");
    }

    @RequestMapping(method = RequestMethod.GET, value="/{gameId}/{playerId}")
    public ModelAndView statsPlayer(@PathVariable("gameId") int gameId, @PathVariable("playerId") int playerId) {
        return new ModelAndView("statsForGamePlayer");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getGamesList() {
        List<StatsDto> result = subscriptionService.getStatsForUser(getUserEmail());

        return new ResponseEntity<List<StatsDto>>(result, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value="/{gameId}/{playerId}", produces = "application/json")
    public ResponseEntity<?> getStatsPerGamePlayer(@PathVariable("gameId") int gameId, @PathVariable("playerId") int playerId) {
        GamePlayerStatsDto dto = streetGameService.getGamePlayerStats(gameId, playerId);
        return new ResponseEntity<GamePlayerStatsDto>(dto, HttpStatus.OK);
    }

    private String getUserEmail() {
        return ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
    }
}
