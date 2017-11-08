package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.GeneratedValue;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Restaurant {

    @Id
//    @GeneratedValue
    private String restaurantId;
    private String restaurantName;
    private Menu menu;
    private String description;
    private String address1;
    private String address2;
    private String city;
    private String state;
    private String zipCode;

    @JsonCreator
    public Restaurant(@JsonProperty("restaurantId") String restaurantId,
                      @JsonProperty("name") String restaurantName,
                      @JsonProperty("description") String description,
                      @JsonProperty("address1") String address1,
                      @JsonProperty("address2") String address2,
                      @JsonProperty("city") String city,
                      @JsonProperty("state") String state,
                      @JsonProperty("zipCode") String zipCode)
    {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.description = description;
        this.address1 = address1;
        this.address2 = address2;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }
}
