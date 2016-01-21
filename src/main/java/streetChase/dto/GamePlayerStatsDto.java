package streetChase.dto;


import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.model.User;
import streetChase.model.UserLocation;
import streetChase.utils.GeometryUtil;
import streetChase.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class GamePlayerStatsDto {

    private String gameName;
    private String playerName;
    private double routeLength;
    private String routeTime;
    private double routeSpeed;

    private List<RoutePointDto> route;
    private List<RouteSectionDto> sections;

    public GamePlayerStatsDto() {
        route = new ArrayList<RoutePointDto>();
        sections = new ArrayList<RouteSectionDto>();
    }

    public GamePlayerStatsDto(StreetGame game, User player, Subscription subs, List<UserLocation> userLocationList, double routeLength, List<RouteSectionDto> sections) {
        this();

        if (game != null) {
            this.gameName = game.getName();
        }
        if (player != null) {
            this.playerName = player.getName();
        }

        if (userLocationList != null) {
            for (UserLocation ul : userLocationList)
                this.route.add(new RoutePointDto(ul));
        }

        this.routeLength = Math.round(routeLength);

        if (subs != null && subs.getGame_finished() != null && subs.getGame_started() != null) {
            long routeTimeInSeconds = (subs.getGame_finished().getTime() - subs.getGame_started().getTime()) / 1000;
            this.routeSpeed = GeometryUtil.round(routeLength / routeTimeInSeconds, 2);
            this.routeTime = TimeUtils.formatTimeInterval(routeTimeInSeconds);
        }

        this.sections = sections;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public List<RoutePointDto> getRoute() {
        return route;
    }

    public void setRoute(List<RoutePointDto> route) {
        this.route = route;
    }

    public double getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(double routeLength) {
        this.routeLength = routeLength;
    }

    public String getRouteTime() {
        return routeTime;
    }

    public void setRouteTime(String routeTime) {
        this.routeTime = routeTime;
    }

    public double getRouteSpeed() {
        return routeSpeed;
    }

    public void setRouteSpeed(double routeSpeed) {
        this.routeSpeed = routeSpeed;
    }

    public List<RouteSectionDto> getSections() {
        return sections;
    }

    public void setSections(List<RouteSectionDto> sections) {
        this.sections = sections;
    }
}
