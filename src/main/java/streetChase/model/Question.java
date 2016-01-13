package streetChase.model;

/**
 * Created by Martyna on 2016-01-11.
 */
public class Question {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getControl_point_id() {
        return control_point_id;
    }

    public void setControl_point_id(int control_point_id) {
        this.control_point_id = control_point_id;
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

    private int id;
    private String question;
    private String answer;
    private int control_point_id;

    public Question() {}

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
