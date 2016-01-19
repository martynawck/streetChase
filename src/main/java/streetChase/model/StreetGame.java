package streetChase.model;

import org.apache.commons.lang.StringUtils;
import streetChase.dto.StreetGameDto;

import javax.persistence.*;
import java.sql.Timestamp;

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
    private String start_point_description;
    @Column(name = "creator_id")
    private int creatorId;



    public StreetGame(StreetGameDto gameDto, int creatorId) {
        if (gameDto == null || creatorId == 0)
            return;

        this.name = gameDto.getName();
        this.description = gameDto.getDescription();
        this.is_private = gameDto.isPrivateGame();
        this.start_point_description = gameDto.getStartPointDesc();
        this.creatorId = creatorId;

        if (gameDto.getStartTime() != null)
            this.start_time = new Timestamp(gameDto.getStartTime().getTime());
        if (gameDto.getEndTime() != null)
            this.end_time = new Timestamp(gameDto.getEndTime().getTime());
    }

    public String getStart_point_description() {
        return start_point_description;
    }

    public void setStart_point_description(String start_point_description) {
        this.start_point_description = start_point_description;
    }


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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
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

    public void applyChanges(StreetGameDto gameDto) {
        if (gameDto == null)
            return;

        if (StringUtils.isNotBlank(gameDto.getName()) && !this.name.equals(gameDto.getName()))
            this.name = gameDto.getName();
        if (StringUtils.isNotBlank(gameDto.getDescription()) && !this.description.equals(gameDto.getDescription()))
            this.description = gameDto.getDescription();
        if (StringUtils.isNotBlank(gameDto.getStartPointDesc()) && !this.start_point_description.equals(gameDto.getStartPointDesc()))
            this.start_point_description = gameDto.getStartPointDesc();

        this.is_private = gameDto.isPrivateGame();

        if (gameDto.getStartTime() != null && this.start_time.getTime() != gameDto.getStartTime().getTime())
            this.start_time = new Timestamp(gameDto.getStartTime().getTime());
        if (gameDto.getEndTime() != null && this.end_time.getTime() != gameDto.getEndTime().getTime())
            this.end_time = new Timestamp(gameDto.getEndTime().getTime());
    }
}
