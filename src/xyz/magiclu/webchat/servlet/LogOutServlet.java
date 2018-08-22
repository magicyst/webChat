package xyz.magiclu.webchat.servlet;

import xyz.magiclu.webchat.model.SessionUserManage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/13.
 */
@WebServlet(name = "LogOutServlet",
        urlPatterns = {
                "/logout"
        }
)
public class LogOutServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //用户名不能为空
        HttpSession session = req.getSession();
        String suid = req.getParameter("suid");
        System.out.println("logout");


        if(suid==null)
            return;

        //获取对应session
        SessionUserManage userManage = (SessionUserManage) session.getAttribute("userTable");

        try {
            //注销
            userManage.remove(suid);
            req.setAttribute("message","注销成功");
            req.getRequestDispatcher("/prompt.jsp").forward(req,resp);
            System.out.println("注销成功");
        }catch (IOException e){
            System.out.println("error");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
