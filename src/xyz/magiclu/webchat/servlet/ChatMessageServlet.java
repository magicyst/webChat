package xyz.magiclu.webchat.servlet;

import xyz.magiclu.webchat.service.ChatService;
import xyz.magiclu.webchat.util.UserNotFindException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 接受数据：
 *      1.domain 发送的信息给谁，即给对应session里面的userTable的chatMessage.append()
 *      2.send message 发送的信息
 * Created by Administrator on 2018/8/13.
 */
@WebServlet(name = "ChatMessageServlet",
        urlPatterns = {
                "/Chat"
        }
)
public class ChatMessageServlet extends HttpServlet {

    //对聊天信息的处理逻辑
    private ChatService chatService;

    public ChatMessageServlet(){

        this.chatService = new ChatService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取domain send_message domain=MD5(username+domain)
        String domain = request.getParameter("domain");

        String send_message = request.getParameter("send_message");

        String suid = request.getParameter("suid");
        System.out.println(domain);

        try {
            chatService.doChat(domain,send_message,request);
            System.out.println("succeed");
            response.setStatus(200);

        } catch (UserNotFindException e) {
            response.setStatus(500);

        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        this.doPost(request,response);
    }
}
