package xyz.magiclu.webchat.servlet;

import org.json.JSONObject;
import xyz.magiclu.webchat.model.User;
import xyz.magiclu.webchat.util.ChatUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * Created by Administrator on 2018/8/14.
 */
@WebServlet(name = "RefreshServlet",

        urlPatterns = {
                "/refresh"
        }
)
public class RefreshServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //拿到suid,和用户列表
        String suid = request.getParameter("suid");
        HashMap<String, User> users = ChatUtil.getOnlineUser(request);

        User user = users.get(suid);

        //拿到在线列表（username,suid）发向到前台
        HashMap<String, String> userlist = new HashMap<>();
        for (String u : users.keySet()) {

            userlist.put(u, users.get(u).getUsername());
        }

        //json数据
        String data = "{" +
                    "\"list\":"+new JSONObject(userlist).toString()+","+
                    "\"msg\":"+new JSONObject(user.getMessage()).toString()+
                "}";

        //writer发送
        System.out.println(data);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        out.print(data);
        out.close();
        System.out.println(123);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}
