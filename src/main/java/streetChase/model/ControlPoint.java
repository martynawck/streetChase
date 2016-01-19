package streetChase.model;



import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;
import streetChase.dto.PointDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Martyna on 2016-01-11.
 */
@Entity
@Table(name = "street_game_control_point", schema = "public")
public class ControlPoint {

    @Id
    @GeneratedValue
    private int id;
    private int street_game_id;
    private String name;
    private int next_point_id;
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point location;
    private boolean starting_point;

    public ControlPoint(int street_game_id, String name, int next_point_id, Point location, boolean starting_point) {
        this.street_game_id = street_game_id;
        this.name = name;
        this.next_point_id = next_point_id;
        this.location = location;
        this.starting_point = starting_point;
    }

    public int getNext_point_id() {
        return next_point_id;
    }

    public void setNext_point_id(int next_point_id) {
        this.next_point_id = next_point_id;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public boolean isStarting_point() {
        return starting_point;
    }

    public void setStarting_point(boolean starting_point) {
        this.starting_point = starting_point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStreet_game_id() {
        return street_game_id;
    }

    public void setStreet_game_id(int street_game_id) {
        this.street_game_id = street_game_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public ControlPoint() {}

    @Override
    public boolean equals(Object object) {
        if (object instanceof ControlPoint){
            ControlPoint controlPoint = (ControlPoint) object;
            return controlPoint.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
