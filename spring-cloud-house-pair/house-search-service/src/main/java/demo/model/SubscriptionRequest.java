package demo.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * DTO for Subscription-service
 */
@Slf4j
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@RequiredArgsConstructor
public class SubscriptionRequest {
    private double latitude;
    private double longitude;
    private double bedroom;
    private double bathroom;
    private double areaSize;
    private double price;
    private int houseNumber;
    private String emailAddress;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdDate;

}
