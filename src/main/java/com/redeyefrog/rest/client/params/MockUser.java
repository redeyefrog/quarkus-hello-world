package com.redeyefrog.rest.client.params;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MockUser {

    @JsonProperty("USER_ID")
    private String id;

    @JsonProperty("USER_NAME")
    private String name;

    @JsonProperty("USER_AGE")
    private Integer age;

}
