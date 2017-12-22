package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@RequiredArgsConstructor
public class HouseData {
    @Id
    private String id;
    private long postId; // craglist_id
    private String title;
    private double price;
    private double bedroom;
    private double bathroom; // 2.5
    private double areaSize;
    private String rawAddress;
    private List<String> images;
    private String availableDate;
    private String postDate;
    private List<String> attributes;
    private String detailUrl;
    private String contactUrl;
    @JsonIgnore
    private final
    GeoJsonPoint location;
    private String country;
    private String state;
    private String county;
    private String city;
    private int postalCode;
    private String street;
    private int houseNumber;

    @SuppressWarnings("unused")
    private HouseData() {
        this.location = new GeoJsonPoint(0, 0);
    }

    @JsonCreator
    public HouseData(@JsonProperty("latitude") double latitude,
                     @JsonProperty("longitude") double longitude) {
        //If specifying latitude and longitude coordinates, list the longitude first and then latitude:
        this.location = new GeoJsonPoint(longitude, latitude);
    }

    public double getLatitude() {
        return this.location.getY();
    }

    public double getLongitude() {
        return this.location.getX();
    }

}
