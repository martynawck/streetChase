package streetChase.utils;


import org.springframework.context.support.ClassPathXmlApplicationContext;
import streetChase.model.UserLocation;
import streetChase.model.UserReachedPoint;
import streetChase.repository.UserLocationRepository;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RouteUtils {

    public static double getSectionLength(int gameId, UserReachedPoint beginReached, UserReachedPoint endReached) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "applicationContext.xml");

        UserLocationRepository repository = context.getBean(UserLocationRepository.class);
        List<UserLocation> userLocations = repository.findBetweenTimestamp(beginReached.getUser_id(), gameId , beginReached.getTimestamp(), endReached.getTimestamp());

        context.close();

        return getRouteLengthFromLocations(userLocations);
    }

    public static double getRouteLengthFromLocations(List<UserLocation> userLocationList) {
        ArrayList<String> pointStrings = new ArrayList<String>();
        for (UserLocation ul : userLocationList) {
            pointStrings.add(GeometryUtil.getPointString(ul.getLocation()));
        }

        return lengthOfRoute(pointStrings);
    }

    public static double lengthOfRoute(ArrayList<String> points) {
        if (points == null || points.size() < 2)
            return 0;

        java.sql.Connection conn;
        Double geom = new Double(0);
        String query = getQueryFromStringList(points);

        try {
            Class.forName("org.postgresql.Driver");
            String url = getDbUrl();
            conn = DriverManager.getConnection(url, "postgres", "postgres");
            ((org.postgresql.PGConnection)conn).addDataType("geometry", "org.postgis.PGgeometry");
            ((org.postgresql.PGConnection)conn).addDataType("box3d","org.postgis.PGbox3d");
            Statement s = conn.createStatement();
            String q ="SELECT ST_Length(ST_Transform(ST_GeomFromEWKT('SRID=4326;LINESTRING(" + query + ")'),26986));";
            ResultSet r = s.executeQuery(q);
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

    private static String getQueryFromStringList(ArrayList<String> points) {
        String query = "";
        int iterations = 0;
        for (String point : points){
            String sub = point.substring(6,point.length()-1);
            query+=sub;
            iterations++;

            if (iterations <= points.size() - 1) {
                query+=",";
            }
        }
        return query;
    }

    private static String getDbUrl() {
        return "jdbc:postgresql://localhost:5432/gis";
    }
}
