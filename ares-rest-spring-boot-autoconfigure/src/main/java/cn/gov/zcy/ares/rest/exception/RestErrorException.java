package cn.gov.zcy.ares.rest.exception;

import org.springframework.http.client.ClientHttpResponse;

/**
 * @author <a href="mailto:youming@cai-inc.com">斜照</a>
 * @datetime 2021-11-29 19:36:57
 */
public class RestErrorException extends RuntimeException {
    public ClientHttpResponse getClientHttpResponse() {
        return clientHttpResponse;
    }

    public RestErrorException() {
        super();
    }

    public RestErrorException(ClientHttpResponse response) {
        clientHttpResponse = response;
    }

    private ClientHttpResponse clientHttpResponse;

    public RestErrorException(Throwable cause, ClientHttpResponse response) {
        clientHttpResponse = response;
        this.initCause(cause);
    }
}
