package streetChase.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import streetChase.model.Contact;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.service.*;
import streetChase.vo.ContactListVO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping(value = "/mobile/games")
public class MobileStreetGameController {

    //private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";


    @Autowired
    private SGService sgService;

  //  @Autowired
   // private SubscriptionService subscriptionService;

    @Autowired
    private MobileLoginService mobileLoginService;

    @Autowired
    private SubService subService;

    //@Autowired
    //private MessageSource messageSource;

    @Value("5")
    private int maxResults;

    @RequestMapping(value="/controller", method = RequestMethod.GET)
    @ResponseBody
    public String foo() {
        return "Response!";
    }
/*
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<StreetGame>> listAllUsers() {
        List<StreetGame> users = streetGameService.findAllGames();
        if(users.isEmpty()){
            return new ResponseEntity<List<StreetGame>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<StreetGame>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String listPersons(Model model) {
        model.addAttribute("person", new StreetGame());
        model.addAttribute("listPersons", this.streetGameService2.listStreetGames());
        return "person";
    }
*/
    @RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<StreetGame>> shopListPage() {
        List<StreetGame> users = sgService.findAll();
        if(users.isEmpty()){
            return new ResponseEntity<List<StreetGame>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<StreetGame>>(users, HttpStatus.OK);

    }

    @RequestMapping(value = "/list/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<StreetGame> getUser(@PathVariable("id") int id) {
     //   System.out.println("Fetching User with id " + id);
        /*User user = userService.findById(id);
        if (user == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }*/
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
          //  System.out.println("Unable to delete. User with id " + id + " not found");
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
            //  System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        subService.createSubscription(subscription);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }



    /*@RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("contactsList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        return createListAllResponse(page, locale);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("contact") Contact contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        contactService.save(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int contactId,
                                    @RequestBody Contact contact,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        if (contactId != contact.getId()) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        contactService.save(contact);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }

    @RequestMapping(value = "/{contactId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("contactId") int contactId,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
            contactService.delete(contactId);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.delete.success");
        }

        return createListAllResponse(page, locale, "message.delete.success");
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> search(@PathVariable("name") String name,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        return search(name, page, locale, null);
    }

    private ResponseEntity<?> search(String name, int page, Locale locale, String actionMessageKey) {
        ContactListVO contactListVO = contactService.findByNameLike(page, maxResults, name);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(contactListVO, locale, actionMessageKey, null);
        }

        Object[] args = {name};

        addSearchMessageToVO(contactListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<ContactListVO>(contactListVO, HttpStatus.OK);
    }

    private ContactListVO listAll(int page) {
        return contactService.findAll(page, maxResults);
    }

    private ResponseEntity<ContactListVO> returnListToUser(ContactListVO contactList) {
        return new ResponseEntity<ContactListVO>(contactList, HttpStatus.OK);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        ContactListVO contactListVO = listAll(page);

        addActionMessageToVO(contactListVO, locale, messageKey, null);

        return returnListToUser(contactListVO);
    }

    private ContactListVO addActionMessageToVO(ContactListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }

    private ContactListVO addSearchMessageToVO(ContactListVO contactListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return contactListVO;
        }

        contactListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return contactListVO;
    }

    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
    */
}