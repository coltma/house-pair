package demo.model;

// https://docs.mongodb.com/v3.4/reference/geojson/
// For mongodb geo query
// Follow org.springframework.data.geo.Point;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeoJSONPoint {
    private final String type = "Point";
    //just an array
//    private final List<Double> coordinates = new ArrayList<Double>();
    private double[] coordinates = new double[2];
    public GeoJSONPoint() {
//        coordinates.add(0.0);
//        coordinates.add(0.0);
        coordinates[0] = 0.0;
        coordinates[1] = 0.0;
    }

//    @PersistenceConstructor
    public GeoJSONPoint(double longitude, double latitude) {
//        coordinates.add(longitude);
//        coordinates.add(latitude);
        coordinates[0] = longitude;
        coordinates[1] = latitude;
    }


    public double getLongitude() {
//        return coordinates.get(0);
        return coordinates[0];
    }

    public double getLatitude() {
//        return coordinates.get(1);
        return coordinates[1];
    }
}
