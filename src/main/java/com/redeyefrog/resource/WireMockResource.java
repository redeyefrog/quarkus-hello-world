package com.redeyefrog.resource;

import com.redeyefrog.model.user.UserInfo;
import com.redeyefrog.model.user.UserInfoRequest;
import com.redeyefrog.model.user.UserInfoResponse;
import com.redeyefrog.rest.client.WireMockClient;
import com.redeyefrog.rest.client.params.MockUser;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Path("/wiremock")
public class WireMockResource {

    @Inject
    @RestClient
    WireMockClient wireMockClient;

    @POST
    @Path("/findUsers")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public UserInfoResponse findMockUser(UserInfoRequest userInfoRequest) {
        log.info("{}", userInfoRequest);
        List<MockUser> userList = wireMockClient.findUserById(userInfoRequest.getId());
        List<UserInfo> userInfoList = userList.stream().map(user -> UserInfo.builder().userId(user.getId()).userName(user.getName()).userAge(user.getAge()).build()).collect(Collectors.toList());
        return new UserInfoResponse(userInfoList);
//        MockUserRequest mockUserRequest = new MockUserRequest();
//        mockUserRequest.setUserId(userInfoRequest.getId());
//        MockUserResponse mockUserResponse = wireMockClient.findUser(mockUserRequest);
//        List<UserInfo> userInfoList = mockUserResponse.getUserList().stream().map(user -> UserInfo.builder().userId(user.getId()).userName(user.getName()).userAge(user.getAge()).build()).collect(Collectors.toList());
//        return new UserInfoResponse(userInfoList);
    }

}
