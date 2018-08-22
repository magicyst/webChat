package xyz.magiclu.webchat.listener;


/**
 * @explain 在用户账户上线的时候，拦截器查看用户池，选择分配sessionId重写url
 *          在给session域加入username属性的时候才添加用户如在线池
 * Created by Administrator on 2018/8/12.
 */

import xyz.magiclu.webchat.model.SessionUserManage;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.HashMap;

@WebListener()
public class UserAddedListener implements HttpSessionAttributeListener {


    /**
     * 该事件触发后，判断用户是否是给session添加username属性
     * @param se 触发session
     */
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {

        //获取用户
        HttpSession user = se.getSession();

        //加入用户池
        HashMap<String, HttpSession> online_user = (HashMap<String, HttpSession>) user
                .getServletContext().getAttribute("online_user");

        System.out.println("session create:"+online_user);

        online_user.put(user.getId(),user);
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent se) {


    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent se) {

        SessionUserManage s = (SessionUserManage) se.getValue();
        System.out.println("replaced:"+s);
    }
}
