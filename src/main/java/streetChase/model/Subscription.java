package streetChase.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Martyna on 2016-01-11.
 */
@Entity
@Table(name = "street_game_subscription", schema = "public")
public class Subscription {

    @Id
    @GeneratedValue
    private int id;
    private boolean played;
    private Timestamp game_started;
    private Timestamp game_finished;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", unique = false)
    private User player;

    @ManyToOne(optional = false)
    @JoinColumn(name = "street_game_id", unique = false)
    private StreetGame streetGame;

    public  Subscription () {}

    public Timestamp getGame_started() {
        return game_started;
    }

    public void setGame_started(Timestamp game_started) {
        this.game_started = game_started;
    }

    public Timestamp getGame_finished() {
        return game_finished;
    }

    public void setGame_finished(Timestamp game_finished) {
        this.game_finished = game_finished;
    }

    public boolean isPlayed() {
        return played;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return this.player.getId();
    }

    public void setUser(int user_id) {
        this.player.setId(user_id);
    }

    public int getGame() {
        return this.streetGame.getId();
    }

    public void setGame(int street_game_id) {
        this.streetGame.setId(street_game_id);
    }

    public User getPlayer() {
        return player;
    }

    public StreetGame getStreetGame() {
        return streetGame;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Subscription){
            Subscription subscription = (Subscription) object;
            return subscription.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
