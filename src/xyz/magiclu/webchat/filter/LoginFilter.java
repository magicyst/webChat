package xyz.magiclu.webchat.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/8/13.
 */
@WebFilter(filterName = "LoginFilter",

        urlPatterns = {
                "/Login"
        }
)
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)resp;
        String flag = request.getParameter("flag");
        if(flag != null && "1".equals(flag)) {
            chain.doFilter(req, resp);
            return;
        }else {
            request.setAttribute("message","非法访问");
            request.getRequestDispatcher("/prompt.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}
