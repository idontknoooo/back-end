package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public @Embeddable @JsonInclude(JsonInclude.Include.NON_NULL) @Data @NoArgsConstructor
class UserInfo {

    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer userId;

    private String username;
    private String address;

    @JsonCreator
    public UserInfo(@JsonProperty("userId") Integer userId,
                    @JsonProperty("username") String username,
                    @JsonProperty("address") String address){

        if(userId != null) this.userId = userId;
        this.username = username;
        this.address = address;
    }
}
