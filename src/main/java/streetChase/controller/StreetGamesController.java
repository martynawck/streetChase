package streetChase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import streetChase.dto.StreetGameDto;
import streetChase.model.User;
import streetChase.service.StreetGameService;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/protected/streetGames")
public class StreetGamesController {

    @Autowired
    private StreetGameService streetGameService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView gamesList() {
        return new ModelAndView("streetGamesList");
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView newGame() { return new ModelAndView("streetGamesAdd");   }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(Locale locale) {
        return createGamesListResponse();
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create( @RequestBody StreetGameDto streetGame, Locale locale) {
        if (!streetGameService.save(streetGame, getUserId())) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("OK", HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int gameId,
                                    @RequestBody StreetGameDto gameDto,
                                    Locale locale) {
        if (gameId != gameDto.getId() || !streetGameService.save(gameDto, getUserId())) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        streetGameService.save(gameDto, getUserId());

        return createGamesListResponse();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("id") int gameId, Locale locale) {
        try {
            streetGameService.delete(gameId);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }

        return createGamesListResponse();
    }

    private int getUserId() {
        return ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
    }

    private ResponseEntity<List<StreetGameDto>> createGamesListResponse() {
        List<StreetGameDto> list = null;
        if (isAdmin()) {
            list = streetGameService.findAllAsDtos();
        } else {
            list = streetGameService.findDtoListForCreator(getUserId());
        }

        return new ResponseEntity< List<StreetGameDto> >(list, HttpStatus.OK);
    }

    private boolean isAdmin() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
