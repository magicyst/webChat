package xyz.magiclu.webchat.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/13.
 */
@WebFilter(filterName = "ChatFilter",

        urlPatterns = {
                "/Chat"
        }
)
public class ChatFilter implements Filter {

    public void destroy() {}

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;

        //拦截非法访问chat
        String chat_flag = request.getParameter("chat_flag");
        if(chat_flag == null || !chat_flag.equals("1")) {
            response.setStatus(404);
            return;
        }else {

            chain.doFilter(req,resp);
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
