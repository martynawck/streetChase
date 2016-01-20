package streetChase.controller;

import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Martyna on 2016-01-20.
 */
@Controller
@RequestMapping(value = "/postgis")
public class PostGisController {

    @RequestMapping(value = "/x/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<JSONObject> x(@PathVariable(value="id") int location_id) {

        String point1 = "POINT(-72.1235 42.3521)";
        String point2 = "POINT(-72.1260 42.45)";
        double distance = distanceBetweenPoints(point1, point2);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("distance", distance);

        return new ResponseEntity<JSONObject>(jsonObject, HttpStatus.CREATED);
    }

    /*Odleg³oœæ miêdzy punktami */

    public double distanceBetweenPoints(String point1, String point2) {
        java.sql.Connection conn;
        Double geom = new Double(0);

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, "postgres", "postgres");
            ((org.postgresql.PGConnection)conn).addDataType("geometry", "org.postgis.PGgeometry");
            ((org.postgresql.PGConnection)conn).addDataType("box3d","org.postgis.PGbox3d");
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT ST_Distance(\n" +
                    "\t\t\tST_Transform(ST_GeomFromText('"+point1+"',4326),26986),\n" +
                    "\t\t\tST_Transform(ST_GeomFromText('"+point2+"', 4326),26986)\n" +
                    "\t\t);");
            while( r.next() ) {

                geom = (Double)r.getDouble(1);//.getObject(1);
            }
            s.close();
            conn.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return geom;

    }

    public double lengthOfRoute(ArrayList<String> points) {
        java.sql.Connection conn;
        Double geom = new Double(0);
        String query = "";
        for (String point : points){

        }
        //fsfsd

        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, "postgres", "postgres");
            ((org.postgresql.PGConnection)conn).addDataType("geometry", "org.postgis.PGgeometry");
            ((org.postgresql.PGConnection)conn).addDataType("box3d","org.postgis.PGbox3d");
            Statement s = conn.createStatement();
            ResultSet r = s.executeQuery("SELECT ST_Length(\n" +
                    "\tST_Transform(\n" +
                    "\t\tST_GeomFromEWKT('SRID=4326;LINESTRING(-72.1260 42.45, -72.1240 42.45666, -72.123 42.1546)'),\n" +
                    "\t\t26986\n" +
                    "\t)\n" +
                    ");");
            while( r.next() ) {

                geom = (Double)r.getDouble(1);//.getObject(1);
            }
            s.close();
            conn.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }
        return geom;

    }
}