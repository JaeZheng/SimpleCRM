package com.atsjp.webDemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.SessionCookieConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atsjp.webDemo.entity.About;
import sun.rmi.server.Dispatcher;

import com.atsjp.webDemo.entity.User;
import com.atsjp.webDemo.service.UserService;

/**
 * 用于与管理员，相关操作的响应
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public UserServlet() {
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String key = request.getParameter("method");
        if ("init".equals(key)) {
            init(request, response);
        }
		if ("login".equals(key)) {
			toCilentUI(request, response);
		}
		if ("logout".equals(key)) {
			logout(request, response);
		}
		if ("aboutUs".equals(key)) {
            aboutUs(request, response);
        }
        if ("modify".equals(key)) {
            modifyAboutUs(request, response);
        }
	}

	/**
	 * 
	 * 1.用户登录
	 */
	protected void toCilentUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// 获得请求中的值
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		// 将对应值封装到user对象中去
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		// 调用service层，检查用户登录信息
		if (us.checkUser(user)) {
            About about = us.getAbout();
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // 保存用户信息到session中
            session.setAttribute("software", about.getSoftware());
			request.setAttribute("message", "登录成功,正在自动跳转...");
			// response.sendRedirect("./manager/main.jsp"); // 重定向
			request.getRequestDispatcher("./manager/main.jsp").forward(request,
					response);
		} else {
			// HttpSession session = request.getSession();
			request.setAttribute("message", "登录失败，您的用户名或者密码输入错误，请重新输入。");
			// response.sendRedirect("./manager/login.jsp"); // 重定向
			request.getRequestDispatcher("./manager/login.jsp").forward(
					request, response);
		}
	}

	/*
	 * 
	 * 2.用户退出
	 */
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获得session
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		// 销毁session
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/manager/login.jsp");
	}

    /*
     *
     * 3.初始化
     */
    protected void init(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 调用service层，检查用户登录信息
        About about = us.getAbout();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(about.getSoftware());
    }

    /*
     *
     * 4.查询"关于我们"的信息
     */
    protected void aboutUs(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // 调用service层，检查用户登录信息
        About about = us.getAbout();
        if (about != null){
            request.setAttribute("software", about.getSoftware());
            request.setAttribute("banquan", about.getBanquan());
            request.setAttribute("address", about.getAddress());
            request.setAttribute("linkman", about.getLinkman());
            request.setAttribute("linkphone", about.getLinkphone());
            request.getRequestDispatcher("./manager/aboutUs.jsp").forward(
                    request, response);
        } else{
            request.setAttribute("software", "");
            request.setAttribute("banquan", "");
            request.setAttribute("address", "");
            request.setAttribute("linkman", "");
            request.setAttribute("linkphone", "");
            request.getRequestDispatcher("./manager/aboutUs.jsp").forward(
                    request, response);
        }
    }

    /*
     *
     * 5.修改"关于我们"的信息
     */
    protected void modifyAboutUs(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String oldSoftware = (String)session.getAttribute("software");
        // 获得请求中的值
        String software = request.getParameter("software");
        String banquan = request.getParameter("banquan");
        String address = request.getParameter("address");
        String linkman = request.getParameter("linkman");
        String linkphone = request.getParameter("linkphone");
        // 将对应值封装到About对象中
        About about = new About();
        about.setSoftware(software);
        about.setBanquan(banquan);
        about.setAddress(address);
        about.setLinkman(linkman);
        about.setLinkphone(linkphone);
        // 调用service层，修改"关于我们"信息
        if (us.modifyAboutUs(about, oldSoftware)) {
            session.setAttribute("software", about.getSoftware());
            request.setAttribute("modifyMessage", "修改成功，请刷新网页。");
            // response.sendRedirect("./manager/main.jsp"); // 重定向
            request.getRequestDispatcher("./manager/modifyAboutUs.jsp").forward(request,
                    response);
        } else {
            // HttpSession session = request.getSession();
            request.setAttribute("modifyMessage", "修改失败，请重新输入。");
            // response.sendRedirect("./manager/login.jsp"); // 重定向
            request.getRequestDispatcher("./manager/modifyAboutUs.jsp").forward(
                    request, response);
        }
    }
}
