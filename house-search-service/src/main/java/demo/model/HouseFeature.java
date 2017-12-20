package demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HouseFeature {
    private double bedroom;
    private double bathroom;
    private double area_size;
    private int postal_code;
    private double latitude;
    private double longitude;
}
