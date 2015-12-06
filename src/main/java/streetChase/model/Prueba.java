package streetChase.model;

import com.vividsolutions.jts.geom.Point;
import org.hibernate.annotations.Type;

import javax.persistence.*;
@Entity
@Table(name="prueba", schema="public")
public class Prueba {

    @Id
    @Column(name = "p_id")
    private Integer id;

    @Column(name = "way")
    @Type(type = "org.hibernate.spatial.GeometryType")
    private Point way;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Point getWay() {
        return way;
    }

    public void setWay(Point way) {
        this.way = way;
    }
}