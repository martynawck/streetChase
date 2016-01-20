package streetChase.dto;

import streetChase.utils.TimeUtils;

public class RouteSectionDto {

    private String name;
    private double length;
    private String time;
    private double speed;

    public RouteSectionDto() { }

    public RouteSectionDto(String beginName, String endName, double length, long timeInSeconds) {
        this.name = beginName + " - " + endName;
        this.length = length;
        this.time = TimeUtils.formatTimeInterval(timeInSeconds);
        this.speed = length/timeInSeconds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
