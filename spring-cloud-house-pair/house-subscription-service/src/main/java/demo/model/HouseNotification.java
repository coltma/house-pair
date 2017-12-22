package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class HouseNotification {

    private String emailAddress;
    private double originPrice;
    private double currentPrice;
    private String percent;
    private String postDate;
    private String availableDate;
    private String detailUrl;
    private String address;

}
