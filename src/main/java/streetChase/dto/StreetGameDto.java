package streetChase.dto;


import streetChase.model.StreetGame;

import java.sql.Timestamp;
import java.util.List;

public class StreetGameDto {

    private int id;
    private String name;
    private String description;
    private boolean isPrivate;
    private Timestamp startTime;
    private Timestamp endTime;
    private String startPointDesc;
    private List<PointDto> route;

    public StreetGameDto(StreetGame game) {
        this.id = game.getId();
        this.name = game.getName();
        this.description = game.getDescription();
        this.isPrivate = game.is_private();
        this.startTime = game.getStart_time();
        this.endTime = game.getEnd_time();
        this.startPointDesc = game.getStart_point_description();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public List<PointDto> getRoute() {
        return route;
    }

    public void setRoute(List<PointDto> route) {
        this.route = route;
    }

    public String getStartPointDesc() {
        return startPointDesc;
    }

    public void setStartPointDesc(String startPointDesc) {
        this.startPointDesc = startPointDesc;
    }
}
