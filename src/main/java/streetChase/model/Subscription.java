package streetChase.model;

import javax.persistence.*;

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

    public  Subscription () {}

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
