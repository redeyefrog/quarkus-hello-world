package com.redeyefrog.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserInfo {

    @JsonProperty("USER_ID")
    private String userId;

    @JsonProperty("USER_NAME")
    private String userName;

    @JsonProperty("USER_AGE")
    private Integer userAge;

}
