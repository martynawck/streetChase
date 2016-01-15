package streetChase.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by Martyna on 2016-01-11.
 */
//@Entity
//@Table(name = "street_game_user_question_status", schema = "public")
public class UserQuestionStatus {

  //  @Id
   // @GeneratedValue
    private int id;
    private int question_id;
    private int user_id;
    private Timestamp timestamp;

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

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public UserQuestionStatus() {}

    @Override
    public boolean equals(Object object) {
        if (object instanceof UserQuestionStatus){
            UserQuestionStatus userQuestionStatus = (UserQuestionStatus) object;
            return userQuestionStatus.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
