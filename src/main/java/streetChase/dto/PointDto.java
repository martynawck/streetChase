package streetChase.dto;


import org.postgis.Point;
import streetChase.model.ControlPoint;
import streetChase.model.Question;

public class PointDto {

    private int id;
    private String name;
    //private Point location;     // TODO JAK TO PRZEKAZYWAC
    private String question;
    private String answer;

    public PointDto(ControlPoint point, Question question) {
        this.id = point.getId();
        this.name = point.getName();
        //this.location = point.getLocation();
        this.question = question.getQuestion();
        this.answer = question.getAnswer();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Point getLocation() {
//        return location;
//    }
//
//    public void setLocation(Point location) {
//        this.location = location;
//    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
