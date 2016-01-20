package streetChase.dto;


import streetChase.model.User;

public class PlayerStatsDto {

    private int id;
    private String name;

    PlayerStatsDto() { }
    PlayerStatsDto(User user) {
        if (user == null)
            return;
        this.id = user.getId();
        this.name = user.getName();
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
}
