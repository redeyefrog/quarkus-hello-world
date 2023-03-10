package com.redeyefrog.model.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfoResponse {

    @JsonProperty("USER_INFO")
    private List<UserInfo> userInfoList;

}
