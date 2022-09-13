package com.microservices.sales.configuration;

import com.microservices.sales.exception.ObjectNotFoundException;
import com.microservices.sales.exception.SalesException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

@Component
public class RestTemplateErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return (
                httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR ||
                httpResponse.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
        );
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR && httpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new ObjectNotFoundException(httpResponse.getStatusText());

        throw new SalesException(httpResponse.getStatusText());
    }
}
