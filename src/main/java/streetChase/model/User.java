package streetChase.model;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "system_user", schema = "public", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email") })
public class User {


    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String enabled;
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "user_role")
    private Role role;
/*
    @ManyToMany
    @JoinTable(name="street_game_subscription",
            joinColumns=
            @JoinColumn(name="user_id", referencedColumnName="id"),
            inverseJoinColumns=
            @JoinColumn(name="street_game_id", referencedColumnName="id")
    )
    private Set<StreetGame> streetGames;// = new HashSet<StreetGame>(0);
    public Set<StreetGame> getStreetGames() { return streetGames; }
*/
/*
    public List<StreetGame> getStreetGames() {
        return streetGames;
    }

    public void setStreetGames(List<StreetGame> streetGames) {
        this.streetGames = streetGames;
    }

    @JsonIgnore
    @ManyToMany (fetch=FetchType.LAZY)
    @JoinTable(name = "street_game_subscription", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "street_game_id"))
    private List<StreetGame> streetGames;
*/
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
}