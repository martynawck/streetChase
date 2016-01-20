package streetChase.dto;


import streetChase.model.StreetGame;
import streetChase.model.User;
import streetChase.model.UserLocation;

import java.util.ArrayList;
import java.util.List;

public class GamePlayerStatsDto {

    private String gameName;
    private String playerName;
    private List<RoutePointDto> route;
    private float routeLength;

    public GamePlayerStatsDto() {
        route = new ArrayList<RoutePointDto>();
    }

    public GamePlayerStatsDto(StreetGame game, User player, List<UserLocation> userLocationList, float routeLength) {
        this();
        if (game != null)
            gameName = game.getName();
        if (player != null)
            playerName = player.getName();
        if (userLocationList != null) {
            for (UserLocation ul : userLocationList)
                route.add(new RoutePointDto(ul));
        }
        this.routeLength = routeLength;
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

    public float getRouteLength() {
        return routeLength;
    }

    public void setRouteLength(float routeLength) {
        this.routeLength = routeLength;
    }
}
