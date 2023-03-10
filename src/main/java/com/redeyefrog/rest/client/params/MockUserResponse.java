package com.redeyefrog.rest.client.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MockUserResponse {

    @JsonProperty("users")
    private List<MockUser> userList;

}
