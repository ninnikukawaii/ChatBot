package service.alice;

public class WebHookException extends Exception {
    public WebHookException(String problem){ super(problem); }

    public WebHookException(String problem, Throwable cause) { super(problem, cause); }
}
