package streetChase.dto;

import streetChase.model.UserLocation;

public class RoutePointDto {

    private double lat;
    private double lng;

    public RoutePointDto() { }

    public RoutePointDto(UserLocation ul) {
        if (ul == null)
            return;
        lat = ul.getLat();
        lng = ul.getLon();
    }


    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
