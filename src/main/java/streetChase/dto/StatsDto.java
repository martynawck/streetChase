package streetChase.dto;


import org.codehaus.jackson.annotate.JsonIgnore;
import streetChase.model.StreetGame;
import streetChase.model.Subscription;
import streetChase.model.User;

import java.util.ArrayList;
import java.util.List;

public class StatsDto {

    private int id;
    private String name;
    private List<PlayerStatsDto> players;

    public StatsDto() {
        this.players = new ArrayList<PlayerStatsDto>();
    }

    public StatsDto(StreetGame game, List<User> players) {
        this();
        this.id = game.getId();
        this.name = game.getName();
        for (User player : players) {
            this.players.add(new PlayerStatsDto(player));
        }
    }

    public void addPlayer(User player) {
        players.add(new PlayerStatsDto(player));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PlayerStatsDto> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerStatsDto> players) {
        this.players = players;
    }
}
