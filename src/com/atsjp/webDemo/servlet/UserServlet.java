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
 * ���������Ա����ز�������Ӧ
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
	 * 1.�û���¼
	 */
	protected void toCilentUI(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// ��������е�ֵ
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		// ����Ӧֵ��װ��user������ȥ
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		// ����service�㣬����û���¼��Ϣ
		if (us.checkUser(user)) {
            About about = us.getAbout();
			HttpSession session = request.getSession();
			session.setAttribute("user", user); // �����û���Ϣ��session��
            session.setAttribute("software", about.getSoftware());
			request.setAttribute("message", "��¼�ɹ�,�����Զ���ת...");
			// response.sendRedirect("./manager/main.jsp"); // �ض���
			request.getRequestDispatcher("./manager/main.jsp").forward(request,
					response);
		} else {
			// HttpSession session = request.getSession();
			request.setAttribute("message", "��¼ʧ�ܣ������û���������������������������롣");
			// response.sendRedirect("./manager/login.jsp"); // �ض���
			request.getRequestDispatcher("./manager/login.jsp").forward(
					request, response);
		}
	}

	/*
	 * 
	 * 2.�û��˳�
	 */
	protected void logout(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// ���session
		HttpSession session = request.getSession();
		session.setAttribute("user", null);
		// ����session
		session.invalidate();
		response.sendRedirect(request.getContextPath() + "/manager/login.jsp");
	}

    /*
     *
     * 3.��ʼ��
     */
    protected void init(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // ����service�㣬����û���¼��Ϣ
        About about = us.getAbout();
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(about.getSoftware());
    }

    /*
     *
     * 4.��ѯ"��������"����Ϣ
     */
    protected void aboutUs(HttpServletRequest request,
                        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        // ����service�㣬����û���¼��Ϣ
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
     * 5.�޸�"��������"����Ϣ
     */
    protected void modifyAboutUs(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String oldSoftware = (String)session.getAttribute("software");
        // ��������е�ֵ
        String software = request.getParameter("software");
        String banquan = request.getParameter("banquan");
        String address = request.getParameter("address");
        String linkman = request.getParameter("linkman");
        String linkphone = request.getParameter("linkphone");
        // ����Ӧֵ��װ��About������
        About about = new About();
        about.setSoftware(software);
        about.setBanquan(banquan);
        about.setAddress(address);
        about.setLinkman(linkman);
        about.setLinkphone(linkphone);
        // ����service�㣬�޸�"��������"��Ϣ
        if (us.modifyAboutUs(about, oldSoftware)) {
            session.setAttribute("software", about.getSoftware());
            request.setAttribute("modifyMessage", "�޸ĳɹ�����ˢ����ҳ��");
            // response.sendRedirect("./manager/main.jsp"); // �ض���
            request.getRequestDispatcher("./manager/modifyAboutUs.jsp").forward(request,
                    response);
        } else {
            // HttpSession session = request.getSession();
            request.setAttribute("modifyMessage", "�޸�ʧ�ܣ����������롣");
            // response.sendRedirect("./manager/login.jsp"); // �ض���
            request.getRequestDispatcher("./manager/modifyAboutUs.jsp").forward(
                    request, response);
        }
    }
}
