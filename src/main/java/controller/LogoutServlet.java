package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: Administrator
 * Date: 2022-05-31
 * Time: 下午 8:11
 * 描述：
 */

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 先找到当前用户的会话
        HttpSession session = req.getSession(false);
        if (session == null){
            // 用户没有登录，也就谈不上注销
            resp.getWriter().write("当前用户未登录！无法退出！");
            return;
        }
        // 然后把这个用户的会话中的信息给删掉就行了
        session.removeAttribute("user");
        // 接来下重定向操作，跳转到登录页面：
        resp.sendRedirect("blog_login.html");
    }
}
