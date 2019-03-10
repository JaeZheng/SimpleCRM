package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.CustService;
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
@WebServlet("/AddCustServiceServlet")
public class AddCustServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public AddCustServiceServlet() {
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
		if ("addCustService".equals(key)) {
			addCustService(request, response);
		}
	}

	/*
	 * 
	 * 3. ��ӿͻ�������Ϣ
	 */
	protected void addCustService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String customername = request.getParameter("customername");
		String linkman = request.getParameter("linkman");
		String linkphone = request.getParameter("linkphone");
		String servicetype = request.getParameter("servicetype");
		String servicedate = request.getParameter("servicedate");
		String estimatedcost = request.getParameter("estimatedcost");
		String actualcost = request.getParameter("actualcost");
		String satisfaction = request.getParameter("satisfaction");
		CustService tempC = new CustService(null, customername, linkman, linkphone, servicetype,
				servicedate, estimatedcost, actualcost, satisfaction);
		UserService us = new UserService();
		if (us.addCustService(tempC)) {
			request.setAttribute("addResult", "��ӳɹ���");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/GetPageCustServiceServlet?page=0");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("addResult", "���ʧ�ܣ����޸Ŀͻ������ٴγ��ԣ�");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/GetPageCustServiceServlet?page=0");
			dispatcher.forward(request, response);
		}
	}

}
