package demo.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class HouseDataDto {
    private HouseData houseData;
    private double predictPrice;
}
