package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Opinion;
import com.atsjp.webDemo.service.UserService;
import com.atsjp.webDemo.utils.UUIDutils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ������ӿͻ�����ز���
 */
@WebServlet("/AddOpinionServlet")
public class AddOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public AddOpinionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// ���ý��뷽ʽ
		String key = request.getParameter("method");
		if ("addOpinion".equals(key)) {
			addOpinion(request, response);
		}
	}

	/*
	 * 
	 * 3. ��ӿͻ�
	 */
	protected void addOpinion(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String companyname = request.getParameter("companyname");
		String linkman = request.getParameter("linkman");
		String linkphone = request.getParameter("linkphone");
		String opiniondetail = request.getParameter("opiniondetail");
		String opinionstate = request.getParameter("opinionstate");
		Opinion tempC = new Opinion(null, companyname, linkman, linkphone, opiniondetail, opinionstate);
		UserService us = new UserService();
		if (us.addOpinion(tempC)) {
			request.setAttribute("message", "��ӳɹ���");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addOpinionResult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "���ʧ�ܣ�");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addOpinionResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
