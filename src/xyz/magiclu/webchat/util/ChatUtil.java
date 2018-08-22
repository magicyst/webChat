package xyz.magiclu.webchat.util;

import xyz.magiclu.webchat.model.SessionUserManage;
import xyz.magiclu.webchat.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/13.
 */
public class ChatUtil {

    public static HashMap<String,User> getOnlineUser(HttpServletRequest request){

        HashMap<String,User> online_user = new HashMap<>();

        //取出在线session表
        HashMap<String,HttpSession> online_session = (HashMap<String, HttpSession>) request.getServletContext()
                .getAttribute("online_user");

        for (String session : online_session.keySet()){

            SessionUserManage users = (SessionUserManage) online_session.get(session).getAttribute("userTable");

            online_user.putAll(users.getUsertable());
        }

        return online_user;

    }


}
