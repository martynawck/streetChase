package streetChase.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Martyna on 2016-01-14.
 */
@Entity
@Table(name = "user_location", schema = "public")
public class UserLocation {

    @Id
    @GeneratedValue
    private int id;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;
    private Timestamp timestamp;
    private int street_game_id;
    private int user_id;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getStreet_game_id() {
        return street_game_id;
    }

    public void setStreet_game_id(int street_game_id) {
        this.street_game_id = street_game_id;
    }

}
