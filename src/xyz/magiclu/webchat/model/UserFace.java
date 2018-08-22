package xyz.magiclu.webchat.model;

import xyz.magiclu.webchat.dao.ProperFilePo;
import xyz.magiclu.webchat.util.LoginException;
import xyz.magiclu.webchat.util.MD5Util;

import java.io.IOException;

/**
 * user门面模式
 * 内部只注入
 * Created by Administrator on 2018/8/13.
 */
public class UserFace extends User{

    //持久对象
    private ProperFilePo user;

    //门面禁用构造方法
    private UserFace(){}

    /**
     * 门面增加的login方法
     * @param username
     * @param password
     * @return
     * @throws LoginException
     */
    public static UserFace login(String username, String password) throws LoginException{

        UserFace user = new UserFace();

        user.load(username,password);

        return user;
    }

    private void load(String username, String password) throws LoginException {

        //创建门面
        user = new ProperFilePo();

        //加载用户数据
        user.load(username);

        if(!user.getPassword().equals(MD5Util.getMD5(password)))
            throw new LoginException("密码不正确");

        //注入数据
        this.setUsername(username);
        this.setMessage(new ChatMessage(user.getChat_msg()));
        System.out.println("注入数据："+this.getMessage().getMessages());
    }

    /**
     * 注销用户
     * @throws IOException
     */
    public void logOut() throws IOException {

        user.setChat_msg(this.getMessage().toStringFormat());
        user.store();
    }

    @Override
    public void receive(String msg) {
        super.receive(msg);
    }

    @Override
    public String getSuserid() {
        return super.getSuserid();
    }

    @Override
    public void setSuserid(String suserid) {
        super.setSuserid(suserid);
    }

    @Override
    public ChatMessage getMessage() {
        return super.getMessage();
    }

    @Override
    public void setMessage(ChatMessage message) {
        super.setMessage(message);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public void setUsername(String username) {
        super.setUsername(username);
    }

    @Override
    public String getSessionid() {
        return super.getSessionid();
    }

    @Override
    public void setSessionid(String sessionid) {
        super.setSessionid(sessionid);
    }
}
