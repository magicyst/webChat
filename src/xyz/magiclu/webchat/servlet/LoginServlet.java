package xyz.magiclu.webchat.servlet;

import xyz.magiclu.webchat.model.SessionUserManage;
import xyz.magiclu.webchat.model.UserFace;
import xyz.magiclu.webchat.util.LoginException;
import xyz.magiclu.webchat.util.MD5Util;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 所有页面禁用session的自动创建
 * session的创建受LoginFilter的控制
 * 每个用户保证一个session对象及一个sessionId
 *      验证用户是否存在用户池
 *          存在:从用户池拿到用户名映射的sessionID,然后response.encodeRedirectURL("/Login")
 *          不存在:request.getSession(true)c串session ,然后url重写，重定向/Login
 * Created by Administrator on 2018/7/14.
 */
@WebServlet(
        name = "LoginServlet",
        urlPatterns = {
                "/Login"
        }
)
public class LoginServlet extends HttpServlet {

    /**
     * 处理doPost
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("username:"+request.getParameter("username")+
                "\nid:"+request.getSession().getId());

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {

            //获取会话
            HttpSession session = request.getSession();

            //获取user管理器
            SessionUserManage userTable = (SessionUserManage) session.getAttribute("userTable");

            //userId
            String suid = MD5Util.getMD5(username+session.getId());

            //如果存在用户
            if(userTable!=null){
                if(userTable.containsUser(suid))
                    throw new LoginException("该用户已经在线");
            }

            //登录用户
            UserFace user = UserFace.login(username,password);

            //注入和session关联的id
            user.setSessionid(suid);

            //user add session
            if(userTable == null)
                userTable = new SessionUserManage();

            //userTable hashMap存储，key=MD5(username+session)
            userTable.addUser(user);

            //添加用户后重新写入session域(该session面向的是浏览器状态)
            session.setAttribute("userTable",userTable);

            request.setAttribute("suid",suid);
            request.setAttribute("username",username);
            request.getRequestDispatcher("/chat.jsp").forward(request,response);
        } catch (LoginException e) {
            request.setAttribute("message",e.getMessage());
            request.getRequestDispatcher("/prompt.jsp").forward(request,response);
        }
    }

    /**
     * doGet
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }


}
