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

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data
//@AllArgsConstructor
@NoArgsConstructor
@Document
public class Payment {

    @Id
    private String paymentId;
    private String orderId;
    private Date paymentTimestamp = new Date();
    private boolean isPaid;
    private String customerName;
    private String cardNumber;
    private String expirationMonth;
    private String expirationYear;
    private String securityCode;
    private String billingAddress1;
    private String billingAddress2;
    private String billingCity;
    private String billingState;
    private String billingZipCode;
    private double totalBalance;

    @JsonCreator
    public Payment(@JsonProperty("paymentId") String paymentId,
                   @JsonProperty("orderId") String orderId,
                   @JsonProperty("paymentTimestamp") Date paymentTimestamp,
                   @JsonProperty("isPaid") boolean isPaid,
                   @JsonProperty("customerName") String customerName,
                   @JsonProperty("cardNumber") String cardNumber,
                   @JsonProperty("expirationMonth") String expirationMonth,
                   @JsonProperty("expirationYear") String expirationYear,
                   @JsonProperty("securityCode") String securityCode,
                   @JsonProperty("billingAddress1") String billingAddress1,
                   @JsonProperty("billingAddress2") String billingAddress2,
                   @JsonProperty("billingCity") String billingCity,
                   @JsonProperty("billingState") String billingState,
                   @JsonProperty("billingZipCode") String billingZipCode,
                   @JsonProperty("totalBalance") double totalBalance){
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentTimestamp = paymentTimestamp;
        this.isPaid = isPaid;
        this.customerName = customerName;
        this.cardNumber = cardNumber;
        this.expirationMonth = expirationMonth;
        this.expirationYear = expirationYear;
        this.securityCode = securityCode;
        this.billingAddress1 = billingAddress1;
        this.billingAddress2 = billingAddress2;
        this.billingCity = billingCity;
        this.billingState = billingState;
        this.billingZipCode = billingZipCode;
        this.totalBalance = totalBalance;
    }
}
