package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
public class PredictedPrice {
    private String result;
    @JsonCreator
    public PredictedPrice(@JsonProperty("result") String result) {
        this.result = result;
    }
}
