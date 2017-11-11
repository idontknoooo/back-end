package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.GeneratedValue;
import java.util.List;

// TODO: What does JsonInclude, JsonCreator & JsonProperty do here?
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@Document
public class Restaurant {

    @Id
//    @GeneratedValue
    private String restaurantId;
    private String restaurantName;
    private Address restaurantAddress;
    private List<Dish> menu;

    @JsonCreator
    public Restaurant(@JsonProperty("restaurantId") String restaurantId,
                      @JsonProperty("restaurantName") String restaurantName,
                      @JsonProperty("restaurantAddress") Address restaurantAddress,
                      @JsonProperty("menu") List<Dish> menu){
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.menu = menu;
    }

}
