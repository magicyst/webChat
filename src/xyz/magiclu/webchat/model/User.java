package xyz.magiclu.webchat.model;

import xyz.magiclu.webchat.dao.ProperFilePo;

import java.io.IOException;

/**
 * Created by Administrator on 2018/8/13.
 */
public abstract class User {

    private String suserid;
    private String username;
    private String sessionid;
    private ChatMessage message;
    private ProperFilePo dao;

    public User(String usersessionid, ChatMessage message) {
        this.suserid = usersessionid;
        this.message = message;
    }

    public User(){

    }

    public abstract void logOut() throws IOException;
    /**
     * 接受信息
     * @param msg
     */
    public void receive(String msg){

        this.message.append(msg);
    }
    public String getSuserid() {
        return suserid;
    }

    public void setSuserid(String suserid) {
        this.suserid = suserid;
    }

    public ChatMessage getMessage() {
        return message;
    }

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSessionid() {
        return sessionid;
    }

    public void setSessionid(String sessionid) {
        this.sessionid = sessionid;
    }
}
