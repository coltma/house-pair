package demo.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Slf4j
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document(collection = "subscription")
public class Subscription {
    @Id
    private String id;
    @JsonIgnore
    private final
    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    GeoJsonPoint location;
    private int houseNumber;
    private String emailAddress;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;

    @SuppressWarnings("unused")
    private Subscription() {
        this.location = new GeoJsonPoint(0, 0);
    }

    @JsonCreator
    public Subscription(@JsonProperty("latitude") double latitude, @JsonProperty("longitude") double longitude) {
        //If specifying latitude and longitude coordinates, list the longitude first and then latitude:
        this.location = new GeoJsonPoint(longitude, latitude);
        log.info(String.format("Subscription location: %s", location));
    }


}
