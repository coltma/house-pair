package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonAlias;
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
    @JsonAlias({"post_id", "postId"})
    private long postId; // craglist_id
    private String title;
    private double price;
    private double bedroom;
    private double bathroom; // 2.5
    @JsonAlias({"area_size", "areaSize"})
    private double areaSize;
    @JsonAlias({"raw_address", "rawAddress"})
    private String rawAddress;
    private List<String> images;
    @JsonAlias({"available_date", "availableDate"})
    private String availableDate;
    @JsonAlias({"post_date", "postDate"})
    private String postDate;
    private List<String> attributes;
    @JsonAlias({"detail_url", "detailUrl"})
    private String detailUrl;
    @JsonAlias({"contact_url", "contactUrl"})
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
    @JsonAlias({"postal_code", ""})
    private int postalCode;
    private String street;
    @JsonAlias({"house_number", ""})
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
