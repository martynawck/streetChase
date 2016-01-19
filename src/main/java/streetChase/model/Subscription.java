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

    @Column(name = "street_game_id")
    private int game;
    @Column(name = "user_id")
    private int user;
    private boolean played;
    private Timestamp game_started;
    private Timestamp game_finished;

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
        return user;
    }

    public void setUser(int user_id) {
        this.user = user_id;
    }

    public int getGame() {
        return game;
    }

    public void setGame(int street_game_id) {
        this.game = street_game_id;
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
