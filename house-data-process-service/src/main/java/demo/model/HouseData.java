package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document
public class HouseData {
    @Id
    private String id;
    private long post_id; // craglist_id
    private String title;
    private double price;
    private double bedroom;
    private double bathroom; // 2.5
    private double area_size;
    private String raw_address;
    private List<String> images;
    private String available_date;
    private String post_date;
    private List<String> attributes;
    private String detail_url;
    private String contact_url;
    // geo
    // private double latitude;
    // private double longitude;
    @JsonIgnore
    private final
    @GeoSpatialIndexed
    Point location;
    private String country;
    private String state;
    private String county;
    private String city;
    private int postal_code;
    private String street;
    private int house_number;

    @SuppressWarnings("unused")
    private HouseData() {
        this.location = new Point(0, 0);
    }

    @JsonCreator
    public HouseData(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude) {
        //If specifying latitude and longitude coordinates, list the longitude first and then latitude:
        this.location = new Point(longitude, latitude);
    }

    public double getLatitude() {
        return this.location.getY();
    }

    public double getLongitude() {
        return this.location.getX();
    }

}
