package streetChase.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import streetChase.dto.GamePlayerStatsDto;
import streetChase.dto.RouteSectionDto;
import streetChase.dto.StatsDto;
import streetChase.dto.StreetGameDto;
import streetChase.model.*;
import streetChase.repository.*;
import streetChase.utils.RouteUtils;

import javax.annotation.Resource;
import java.util.*;

@Service
public class StreetGameService {

    @Resource
    private StreetGameRepository streetGameRepository;

    @Resource
    private ControlPointService controlPointService;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SubscriptionRepository subscriptionRepository;

    @Resource
    private UserReachedPointRepository userReachedPointRepository;

    @Transactional
    public List findAll() {
        return streetGameRepository.findAll();
    }

    @Transactional
    public StreetGame findById(int id) {
        return streetGameRepository.findOne(id);
    }

    @Transactional
    public List<StreetGame> findByCreator(int id) {
        return streetGameRepository.findByCreatorId(id);
    }

    @Transactional
    public void deleteStreetGame(StreetGame game) { streetGameRepository.delete(game); }

    @Transactional
    public List<StreetGame> findByIdAndCurrentTime (int id) { return streetGameRepository.findByIdAndCurrentTime(id); }

    public boolean save(StreetGameDto gameDto, String creatorEmail) {
        if (gameDto == null || gameDto.getRoute() == null)
            return false;

        int creatorId = userRepository.findByEmail(creatorEmail).getId();

        if (gameDto.getId() == 0) {
            int gameId = streetGameRepository.save(new StreetGame(gameDto, creatorId)).getId();
            controlPointService.saveRoute(gameDto.routeAsList(), gameId);
            return true;
        }

        StreetGame streetGame = streetGameRepository.findOne(gameDto.getId());
        if (streetGame.getCreatorId() != creatorId)
            return false;

        streetGame.applyChanges(gameDto);
        streetGameRepository.save(streetGame);
        return true;
    }

    @Secured("ROLE_ADMIN")
    public void delete(int streetGameId) {
        streetGameRepository.delete(streetGameId);
    }

    @Transactional(readOnly = true)
    public List<StreetGameDto> findDtoListForCreator(String creatorEmail) {
        int creatorId = userRepository.findByEmail(creatorEmail).getId();
        Iterable<StreetGame> gamesList = streetGameRepository.findByCreatorId(creatorId);
        List<StreetGameDto> resultList = new ArrayList<StreetGameDto>();
        for (StreetGame s : gamesList) {
            resultList.add(new StreetGameDto(s));
        }
        return resultList;
    }

    @Transactional(readOnly = true)
    public List<StreetGameDto> findAllPublic() {
        return convertToDtoList(findAllPublicGames());
    }

    @Transactional(readOnly = true)
    public List<StreetGameDto> findAllAsDtos() {
        return convertToDtoList(streetGameRepository.findAll());
    }

    private List<StreetGameDto> convertToDtoList(Iterable<StreetGame> gamesList) {
        if (gamesList == null)
            return Collections.emptyList();

        List<StreetGameDto> resultList = new ArrayList<StreetGameDto>();
        for (StreetGame s : gamesList) {
            resultList.add(new StreetGameDto(s));
        }
        return resultList;
    }

    private List<StreetGame> findAllPublicGames() {
        //return streetGameRepository.findByIs_private(false);
        return null;
    }

    public List<StatsDto> getStatsForUser(String creatorEmail) {
        int creatorId = userRepository.findByEmail(creatorEmail).getId();
        List<StreetGame> games = streetGameRepository.findByCreatorId(creatorId);
        List<StatsDto> result = new ArrayList<StatsDto>();

        for (StreetGame game : games) {
            if (game.getEnd_time().getTime() < new Date().getTime())
                result.add(new StatsDto(game, getPlayerList(game)));
        }

        return result;
    }

    private List<User> getPlayerList(StreetGame game) {
        List<Subscription> subs = subscriptionRepository.findByGamePlayed(game.getId());
        List<User> players = new ArrayList<User>();
        for (Subscription sub : subs) {
            players.add(userRepository.findOne(sub.getUser()));
        }
        return players;
    }


    public GamePlayerStatsDto getGamePlayerStats(int gameId, int playerId) {
        StreetGame game = streetGameRepository.findOne(gameId);
        User player = userRepository.findOne(playerId);
        Subscription subs = subscriptionRepository.findByUserAndGame(playerId, gameId);
        List<UserLocation> userLocationList = getUserLocations(gameId, playerId);

        double routeLength = RouteUtils.getRouteLengthFromLocations(userLocationList);
        List<RouteSectionDto> sections = getRouteSectionsStats(gameId, playerId);

        return new GamePlayerStatsDto(game, player, subs, userLocationList, routeLength, sections);
    }

    private List<RouteSectionDto> getRouteSectionsStats(int gameId, int playerId) {
        List<ControlPoint> controlPoints = getControlPoints(gameId);
        if (controlPoints == null || controlPoints.size() < 2)
            return Collections.emptyList();

        Collections.sort(controlPoints, new Comparator<ControlPoint>() {
            @Override
            public int compare(ControlPoint o1, ControlPoint o2) {
                if (o1.getId() > o1.getId())
                    return 1;
                else if (o1.getId() == o2.getId())
                    return 0;
                return -1;
            }
        });

        List<RouteSectionDto> sections = new ArrayList<RouteSectionDto>();
        ControlPoint begin = controlPoints.get(0);
        UserReachedPoint beginReached = userReachedPointRepository.findByControlPointAndUser(playerId, begin.getId());
        ControlPoint end = null;
        UserReachedPoint endReached = null;
        for (int i = 1; i < controlPoints.size(); ++i) {
            end = controlPoints.get(i);
            endReached = userReachedPointRepository.findByControlPointAndUser(playerId, end.getId());
            // wyliczyć długość przejechanej trasy między tymi pointsami
            double length = RouteUtils.getSectionLength(gameId, beginReached, endReached);

            // wyliczyć czas
            long timeInSeconds = (endReached.getTimestamp().getTime() - beginReached.getTimestamp().getTime())/1000;

            sections.add(new RouteSectionDto(begin.getName(), end.getName(), length, timeInSeconds));

            begin = end;
            beginReached = endReached;
        }

        return sections;
    }


    private List<ControlPoint> getControlPoints(int gameId) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        ControlPointRepository repository = context.getBean(ControlPointRepository.class);
        List<ControlPoint> result =  repository.findByGameId(gameId);

        context.close();

        return result;
    }


    private List<UserLocation> getUserLocations(int gameId, int playerId) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        UserLocationRepository repository = context.getBean(UserLocationRepository.class);
        List<UserLocation> result =  repository.getByGameAndUser(gameId, playerId);

        context.close();

        return result;
    }
}