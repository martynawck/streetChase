package streetChase.dto;


import org.postgis.Point;
import streetChase.model.ControlPoint;
import streetChase.model.Question;
import streetChase.model.StreetGame;

public class PointDto {

    private int id;
    private String name;
    private String lat;
    private String lon;
    private String hint;
    private String question;
    private String answer;

    public PointDto() { }

    public PointDto(ControlPoint point, Question question) {
        this.id = point.getId();
        this.name = point.getName();
        this.question = question.getQuestion();
        this.answer = question.getAnswer();
        // TODO
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

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getHint() { return hint; }

    public void setHint(String hint) { this.hint = hint; }

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
