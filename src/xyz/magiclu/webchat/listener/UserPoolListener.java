package xyz.magiclu.webchat.listener;


/**
 *
 * application保存的用户在线池:
 *      1.key为用户名，value为用户对象的sessionId
 *      2.ServletContext创建的时创建用户在线池
 *
 * Created by Administrator on 2018/8/12.
 */

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@WebListener
public class UserPoolListener implements ServletContextListener{


    public UserPoolListener() {}

    /**
     * 在应用启动的时候创建在线用户池
     * @param sce application初始化事件
     */
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("user_pool create");
        //ServletContext一创建，就创建在线池
        ServletContext app = sce.getServletContext();

        //在线池,用户名-JSESessionID映射
        HashMap<String,HttpSession> online_user = new HashMap<>();

        //放入应用域
        app.setAttribute("online_user",online_user);

    }

    public void contextDestroyed(ServletContextEvent sce) {}

}
