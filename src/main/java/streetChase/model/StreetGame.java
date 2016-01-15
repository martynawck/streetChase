package streetChase.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Martyna on 2016-01-11.
 */

@Entity
@Table(name = "street_game", schema = "public")
public class StreetGame {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String description;
    private boolean is_private;
    private Timestamp start_time;
    private Timestamp end_time;

    public String getStart_point_description() {
        return start_point_description;
    }

    public void setStart_point_description(String start_point_description) {
        this.start_point_description = start_point_description;
    }

    private String start_point_description;
    @Column(name = "creator_id")
    private int creator;

/*
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    @ManyToMany(fetch=FetchType.LAZY, mappedBy="streetGames")
    private List<User> users;
*/
/*
    @ManyToMany(mappedBy="streetGames")
    private Set<User> users;
    public Set getUsers() { return users; }
*/
    public StreetGame() {}

    public int getCreator() {
        return creator;
    }

    public void setCreator(int creator) {
        this.creator = creator;
    }


    public int getId () { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Timestamp end_time) {
        this.end_time = end_time;
    }

    public Timestamp getStart_time() {
        return start_time;
    }

    public void setStart_time(Timestamp start_time) {
        this.start_time = start_time;
    }

    public boolean is_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof StreetGame){
            StreetGame streetGame = (StreetGame) object;
            return streetGame.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
