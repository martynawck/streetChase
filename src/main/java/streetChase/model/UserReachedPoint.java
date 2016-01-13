package streetChase.model;

import java.sql.Timestamp;

/**
 * Created by Martyna on 2016-01-11.
 */
public class UserReachedPoint {

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

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getControl_point_id() {
        return control_point_id;
    }

    public void setControl_point_id(int control_point_id) {
        this.control_point_id = control_point_id;
    }

    private int id;
    private int user_id;
    private int control_point_id;
    private Timestamp timestamp;


    public UserReachedPoint() {}

    @Override
    public boolean equals(Object object) {
        if (object instanceof UserReachedPoint){
            UserReachedPoint userReachedPoint = (UserReachedPoint) object;
            return userReachedPoint.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
