package streetChase.model;

import java.sql.Timestamp;

/**
 * Created by Martyna on 2016-01-11.
 */
public class UserQuestionStatus {

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    private int id;
    private int question_id;
    private int user_id;
    private String status;
    private Timestamp timestamp;

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
