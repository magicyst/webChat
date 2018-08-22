package xyz.magiclu.webchat.listener;


/**
 * session即用户会话：完整性约束(必须含有key：username)
 *
 * Created by Administrator on 2018/8/12.
 */

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.HashMap;

@WebListener
public class UserLogOutListener implements HttpSessionListener{

    /**
     *
     * @param se session创建或者用户上线,
     *           在用户session没有setAttribute("username","username")的情况下,是不会加入在线池的。
     *           set username为该应用的完整性约束
     */
    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }

    /**
     * 用户下线或者session过期触发
     * @param se 过期session或者下线用户
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        //获取下线或者是session过期用户
        HttpSession user = se.getSession();

        HashMap<String,HttpSession> online_user = (HashMap<String, HttpSession>)user.getServletContext()
                .getAttribute("online_user");

        //溢出在线池
        online_user.remove((String)user.getAttribute("username"));
    }
}
