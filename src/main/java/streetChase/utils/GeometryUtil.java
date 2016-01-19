package streetChase.utils;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;

import java.util.Date;

/**
 * Created by Martyna on 2016-01-17.
 */
public class GeometryUtil {

    public static Point getPointFromStrings(String lat, String lon) {
        String point = "POINT(" + lat + " " + lon + ")";
        GeometryUtil geometryUtil = new GeometryUtil();
        Geometry geom = geometryUtil.wktToGeometry(point);

        if (!geom.getGeometryType().equals("Point")) {
            throw new RuntimeException("Geometry must be a point. Got a " + geom.getGeometryType());
        }

        return (Point) geom;
    }

    public static Geometry wktToGeometry(String wktPoint) {
        WKTReader fromText = new WKTReader();
        Geometry geom = null;
        try {
            geom = fromText.read(wktPoint);

        } catch (ParseException e) {
            throw new RuntimeException("Not a WKT string:" + wktPoint);
        }
        return geom;
    }

    public static String wktFromGeometry(Geometry wktPoint) {
        WKTWriter toText = new WKTWriter();
        String geom = toText.write(wktPoint);
        return geom;
    }
}
