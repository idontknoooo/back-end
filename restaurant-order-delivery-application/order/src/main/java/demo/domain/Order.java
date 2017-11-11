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
import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Order {

    @Id
//    @GeneratedValue
    private String orderId;
    private String restaurantId;
    private List<Dish> orderedFood;
    private String note;
    private Address deliveryAddress;
    private Date orderTime;
    private double totalPrice;
    private boolean isPaid;
    private boolean isCancelled;
    private String estimatedDeliveryTime;

    @JsonCreator
    public Order(@JsonProperty("orderId") String orderId,
                 @JsonProperty("restaurantId") String restaurantId,
                 @JsonProperty("orderedFood") List<Dish> orderedFood,
                 @JsonProperty("note") String note,
                 @JsonProperty("deliveryAddress") Address deliveryAddress){
        this.orderId = orderId;
        this.restaurantId = restaurantId;
        this.orderedFood = orderedFood;
        this.note = note;
        this.deliveryAddress = deliveryAddress;
        // Set other
        this.orderTime = new Date();
        double totalPrice = 0;
        if(orderedFood.size()>0){
            for(Dish dish : orderedFood) {
                totalPrice += dish.getDishPrice();
            }
        }
        this.totalPrice = totalPrice;
        this.isPaid = false;
        this.isCancelled = false;
    }
}
