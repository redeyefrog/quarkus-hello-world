package com.redeyefrog.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoRequest {

    @JsonProperty("USER_ID")
    private String id;

}
