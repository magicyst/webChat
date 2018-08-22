package xyz.magiclu.webchat.model;

import xyz.magiclu.webchat.util.UserNotFindException;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/13.
 */
public class SessionUserManage {

    //同一浏览器session的用户表
    private HashMap<String,User> usertable = new HashMap<>();


    public HashMap<String,User> getUsertable(){

        return this.usertable;
    }
    /**
     * 加人用户
     * @param user
     */
    public void addUser(User user){

        usertable.put(user.getSessionid(),user);
    }

    /**
     * 移除用户
     * @param suserid
     */
    public void remove(String suserid) throws IOException {

        if(this.usertable.isEmpty())
            return;

        usertable.get(suserid).logOut();

        User user = usertable.remove(suserid);
        System.out.println("logout:"+user.getUsername());

    }

    /**
     * 是否包含用户
     * @param suserid
     * @return
     */
    public boolean containsUser(String suserid){

        return usertable.containsKey(suserid);
    }

    /**
     * 获取用户空间
     * @param suserid
     * @return
     */
    public User getUser(String suserid) throws UserNotFindException {

        User user = usertable.get(suserid);

        if(user == null)
            throw new UserNotFindException("用户未登录");

        return user;
    }

    /**
     * 用户接受消息
     * @param suserid
     * @param msg
     */
    public void receive(String suserid, String msg) throws UserNotFindException {

        User user = usertable.get(suserid);

        if(user == null)
            throw new UserNotFindException("用户未登录");

        user.receive(msg);
    }

    /**
     * 所有用接受消息
     * @param msg
     */
    public void allReceive(String msg){

        if(usertable.isEmpty())
            return;

        for (String user : usertable.keySet()){

            usertable.get(user).receive(msg);
        }
    }

    /**
     *
     */
    @Override
    public String toString() {

        String s = "["+"\n";
        for(String user : this.usertable.keySet()){

            s += "user:"+this.usertable.get(user).getUsername()+"  :"+this.usertable.get(user).getSessionid()+"\n";
        }
        s += "]"+"\n";

        return "user Manage"+s;
    }
}
