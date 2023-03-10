package com.redeyefrog.rest.client;

import com.redeyefrog.rest.client.filter.RestLoggingFilter;
import com.redeyefrog.rest.client.params.MockUserRequest;
import com.redeyefrog.rest.client.params.MockUserResponse;
import com.redeyefrog.rest.client.params.MockUser;
import org.eclipse.microprofile.rest.client.annotation.RegisterProvider;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import java.util.List;

@RegisterProvider(RestLoggingFilter.class)
@RegisterRestClient
public interface WireMockClient {

    @POST
    @Path("/findUserById")
    List<MockUser> findUserById(@QueryParam("user_id") String userId);

    @POST
    @Path("/findUser")
    MockUserResponse findUser(MockUserRequest request);

}
