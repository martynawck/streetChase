package streetChase.model;

import javax.persistence.*;

/**
 * Created by Martyna on 2016-01-11.
 */
@Entity
@Table(name = "street_game_question", schema = "public")
public class Question {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getControl_point_id() {
        return controlPoint;
    }

    public void setControl_point_id(int control_point_id) {
        this.controlPoint = control_point_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    @Id
    @GeneratedValue
    private int id;
    private String question;
    private String answer;
    @Column(name = "control_point_id")
    private int controlPoint;

    public Question() {}

    public Question(String question, String answer, int controlPoint) {
        this.question = question;
        this.answer = answer;
        this.controlPoint = controlPoint;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Question){
            Question question = (Question) object;
            return question.id == id;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
