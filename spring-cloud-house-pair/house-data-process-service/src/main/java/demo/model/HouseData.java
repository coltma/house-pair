package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

//TODO: alter, change field name to camel; using Date.
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document(collection = "san_francisco")
public class HouseData {

    @Id
    private String id;
    @JsonProperty("post_id")
    private long post_id; // craglist_id
    private String title;
    private double price;
    private double bedroom;
    private double bathroom; // 2.5
    @JsonProperty("area_size")
    private double areaSize;
    @JsonProperty("raw_address")
    private String rawAddress;
    private List<String> images;
    @JsonProperty("available_date")
    private String availableDate;
    @JsonProperty("post_date")
    private String postDate;
    private List<String> attributes;
    @JsonProperty("detail_url")
    private String detailUrl;
    @JsonProperty("contact_url")
    private String contactUrl;
    // geo
    // private double latitude;
    // private double longitude;
    //    GeoJSONPoint location;
    @JsonIgnore
    private final
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    GeoJsonPoint location;
    private String country;
    private String state;
    private String county;
    private String city;
    @JsonProperty("postal_code")
    private int postalCode;
    private String street;
    @JsonProperty("house_number")
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
