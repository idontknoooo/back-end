package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
@NoArgsConstructor
@Document
public class Payment {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private String paymentId;
    private String orderId;
    private String cardHolderName;
    private String cardNumber;
    private String expirationYear;
    private String expirationMonth;
    private String securityCode;
    private Date paymentTime;
    //TODO add totalPrice
    @JsonCreator
    public Payment(@JsonProperty("paymentId") String paymentId,
                   @JsonProperty("orderId") String orderId,
                   @JsonProperty("cardHolderName") String cardHolderName ,
                   @JsonProperty("cardNumber") String cardNumber,
                   @JsonProperty("expirationYear") String expirationYear,
                   @JsonProperty("expirationMonth") String expirationMonth,
                   @JsonProperty("securityCode") String securityCode){
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.cardHolderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationYear = expirationYear;
        this.expirationMonth = expirationMonth;
        this.securityCode = securityCode;
        // Set paymentTime
        this.paymentTime = new Date();
    }
}
