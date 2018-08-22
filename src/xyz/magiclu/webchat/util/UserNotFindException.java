package xyz.magiclu.webchat.util;

/**
 * Created by Administrator on 2018/8/13.
 */
public class UserNotFindException extends Throwable {

    @Override
    public String getMessage() {
        return super.getMessage();
    }

    public UserNotFindException(String message) {
        super(message);
    }

    public UserNotFindException() {
        super();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public void printStackTrace() {
        super.printStackTrace();
    }
}
