package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

// The @Embeddable annotation allows to specify a class whose instances are stored as intrinsic part of the owning entity.
// This annotation has no attributes.
// https://stackoverflow.com/questions/2578530/java-jpa-basic-and-embedded-annotations
public @Data @NoArgsConstructor @Embeddable @JsonInclude(JsonInclude.Include.NON_NULL) class UserInfo {

    // Indicates that the persistence provider must assign primary keys for the entity using a database sequence.
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int userId;
    private String name, address;

    @JsonCreator
    public UserInfo(@JsonProperty("userId") Integer userId, @JsonProperty("username") String username, @JsonProperty("address") String address) {

        if(userId != null) this.userId = userId;
        this.name = username;
        this.address = address;

    }
}