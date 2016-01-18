package streetChase.dto;


import streetChase.model.StreetGame;

import java.util.Date;
import java.util.List;

public class StreetGameDto {

    private int id;
    private String name;
    private String description;
    private boolean isPrivate;
    private Date startTime;
    private Date endTime;
    private String startPointDesc;
    private List<PointDto> route;

    public StreetGameDto(StreetGame game) {
        if (game == null)
            return;

        this.id = game.getId();
        this.name = game.getName();
        this.description = game.getDescription();
        this.isPrivate = game.is_private();
        this.startPointDesc = game.getStart_point_description();

        if (game.getStart_time() != null)
            this.startTime = new Date(game.getStart_time().getTime());
        if (game.getEnd_time() != null)
            this.endTime = new Date(game.getStart_time().getTime());
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
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
