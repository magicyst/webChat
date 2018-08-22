package xyz.magiclu.webchat.util;

/**
 * Created by Administrator on 2018/8/13.
 */
public class LoginException extends Throwable {

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
