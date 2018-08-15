package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Company;
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
@WebServlet("/AddCompanyServlet")
public class AddCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public AddCompanyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("����AddCompanyServlet doPost...");
		request.setCharacterEncoding("UTF-8");// ���ý��뷽ʽ
		String key = request.getParameter("method");
		if ("checkNameExist".equals(key)) {
			checkNameExist(request, response);
		}
		if ("checkPhoneExist".equals(key)) {
			checkPhoneExist(request, response);
		}
		if ("addCompany".equals(key)) {
			addCompany(request, response);
		}
	}

	/*
	 * 
	 * 1. Ajax�������û����Ƿ����
	 */
	protected void checkNameExist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerName = request.getParameter("customerName");
		boolean exist = us.checkCompanyNameExist(customerName);
		if (exist) {
			response.getWriter().write("true"); // ����
		} else {
			response.getWriter().write("flase");// ������
		}
	}

	/*
	 * 
	 * 2. Ajax�������ֻ��Ƿ����
	 */
	protected void checkPhoneExist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerPhone = request.getParameter("customerPhone");
		boolean exist = us.checkLinkPhoneExist(customerPhone);
		if (exist) {
			response.getWriter().write("true");// ����
		} else {
			response.getWriter().write("flase");// ������
		}
	}

	/*
	 * 
	 * 3. ��ӿͻ�
	 */
	protected void addCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String companyname = request.getParameter("companyname");
		String linkman = request.getParameter("linkman");
		String linkphone = request.getParameter("linkphone");
		String address = request.getParameter("address");
		String id = UUIDutils.getUUID();// ��ȡ���UUID��Ϊ�ͻ���Ϣ��id
		Company tempC = new Company(id, companyname, linkman, linkphone, address);
		UserService us = new UserService();
		if (us.addCompany(tempC)) {
			request.setAttribute("message", "��ӳɹ���");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "���ʧ�ܣ����޸Ĺ�˾���ƻ���ϵ�绰���ٴγ��ԣ�");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
