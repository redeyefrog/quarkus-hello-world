package com.redeyefrog.rest.client.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Inject;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.*;
import java.util.stream.Collectors;

@Slf4j
public class RestLoggingFilter implements ClientResponseFilter {

    @Inject
    ObjectMapper objectMapper;

    @Override
    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
        logRequest(clientRequestContext);

        logResponse(clientResponseContext);
    }

    private void logRequest(ClientRequestContext clientRequestContext) {
        try {
            log.info("Rest Request URI: {}", clientRequestContext.getUri());
            log.info("Rest Request Method: {}", clientRequestContext.getMethod());
            log.info("Rest Request Body: {}", objectMapper.writeValueAsString(clientRequestContext.getEntity()));
        } catch (Exception e) {
        }
    }

    private void logResponse(ClientResponseContext clientResponseContext) {
        try {
            log.info("Rest Response Status: {} {}", clientResponseContext.getStatus(), clientResponseContext.getStatusInfo());
            log.info("Rest Response Body: {}", getResponseBody(clientResponseContext));
        } catch (Exception e) {
        }
    }

    private String getResponseBody(ClientResponseContext clientResponseContext) {
        String responseBody = null;

        InputStream responseStream = clientResponseContext.getEntityStream();
        if (responseStream != null) {
            // read stream
            responseBody = new BufferedReader(new InputStreamReader(responseStream)).lines().collect(Collectors.joining("\n"));
            // reset stream
            clientResponseContext.setEntityStream(new ByteArrayInputStream(responseBody.getBytes()));
        }

        return responseBody;
    }

}
