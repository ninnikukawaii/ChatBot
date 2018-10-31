package service.exceptions;

public class SSLContextCreationException extends Exception {
    public SSLContextCreationException(String problem){ super(problem); }

    public SSLContextCreationException(String problem, Throwable cause) { super(problem, cause); }
}
