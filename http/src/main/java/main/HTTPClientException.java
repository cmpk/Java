package main;

public class HTTPClientException extends Exception {
    public HTTPClientException(final String message) {
        super(message);
    }

    public HTTPClientException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
