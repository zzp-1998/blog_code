package controller;

import model.Blog;
import model.BlogDao;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * User: Administrator
 * Date: 2022-06-01
 * Time: 上午 8:13
 * 描述：
 */

@WebServlet("/blogDelete")
public class BlogDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、先检测当前用户是否登录
        HttpSession session = req.getSession(false);
        if (session == null){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前用户未登录！不能删除！");
            return;
        }
        User user = (User) session.getAttribute("user");
        if (user == null){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前用户未登录！不能删除！");
            return;
        }

        // 2、获取到当前参数的blogId
        String blogId = req.getParameter("blogId");
        if (blogId == null || "".equals(blogId)){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前 blogId参数 不正确！");
            return;
        }

        // 3、获取到要删除的博客信息
        BlogDao blogDao = new BlogDao();
        Blog blog = blogDao.selectOne(Integer.parseInt(blogId));
        if (blog == null){
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前要删除的博客不存在！");
            return;
        }

        // 4、再次校验，当前用户是否为博客的作者
        if (user.getUserId() != blog.getUserId()){
            // 这一点在前端也处理过，但是再校验一次也不是坏事。
            resp.setContentType("text/html;charset=utf8");
            resp.getWriter().write("当前登录的用户不是作者，没有权限删除！");
            return;
        }

        // 5、开始删除
        blogDao.delete(Integer.parseInt(blogId));

        // 6、跳转（重定向）到博客列表页
        resp.sendRedirect("blog_list.html");
    }
}
