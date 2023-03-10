package com.redeyefrog.rest.client.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MockUserRequest {

    @JsonProperty("user_id")
    private String userId;

}
