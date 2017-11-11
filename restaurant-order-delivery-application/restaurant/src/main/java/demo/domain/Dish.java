package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
//@Document
public class Dish {

//    @Id
    private String dishId;
    private String dishName;
    private String dishDescription;
    private double dishPrice;

//    @JsonCreator
//    public Dish(@JsonProperty("dishId") String dishId,
//                @JsonProperty("dishName") String dishName,
//                @JsonProperty("dishDescription") String dishDescription,
//                @JsonProperty("dishPrice") double dishPrice){
//
//        this.dishId = dishId;
//        this.dishName = dishName;
//        this.dishDescription = dishDescription;
//        this.dishPrice = dishPrice;
//    }
}
