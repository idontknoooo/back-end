package demo.domain;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;
import java.util.Random;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Order {

    @Id
    private String orderId;
    private Date orderTimestamp = new Date();
    private String orderNote; // Note for user to add preference
//    private int estimatedDeliveryTime;
    private String paymentId;

    // If there are repeat item, just list all of them
    // CalculateTotalBalance() will calculate total occurrences
    // and return to totalBalance
    private List<Dish> orderDetail;
    private double totalBalance;
    private boolean isCancelled;
    private boolean isPaid;
    // TODO: Add shipping address

    @JsonCreator
    public Order(@JsonProperty("orderId") String orderId,
                 @JsonProperty("orderTimestamp") Date orderTimestamp,
                 @JsonProperty("orderNote") String orderNote,
                 @JsonProperty("paymentId") String paymentId,
                 @JsonProperty("orderDetail") List<Dish> orderDetail,
                 @JsonProperty("inCancelled") boolean isCancelled,
                 @JsonProperty("isPaid") boolean isPaid){
        this.orderId = orderId;
        this.orderTimestamp = orderTimestamp;
        this.orderNote = orderNote;
//        this.estimatedDeliveryTime = RandomDeliveryTime();
        this.paymentId = paymentId;
        this.orderDetail = orderDetail;
        this.totalBalance = CalculateTotalBalance(orderDetail);
        this.isCancelled = isCancelled;
        this.isPaid = isPaid;
    }

    // Calculate Balance for order
    public double CalculateTotalBalance(List<Dish> orderDetail){

        double balance = 0;
        for(int i = 0; i < orderDetail.size(); ++i) {
            balance += orderDetail.get(i).getDishPrice();
        }
        return balance;
    }

    // Randomly generate a estimated delivery time
//    public int RandomDeliveryTime(){
//        Random rand = new Random();
//        return rand.nextInt(55) + 5;
//    }
}
