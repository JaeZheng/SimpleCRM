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
 * 用于添加客户的相关操作
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
		System.out.println("调用AddCompanyServlet doPost...");
		request.setCharacterEncoding("UTF-8");// 设置解码方式
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
	 * 1. Ajax请求检查用户名是否可用
	 */
	protected void checkNameExist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerName = request.getParameter("customerName");
		boolean exist = us.checkCompanyNameExist(customerName);
		if (exist) {
			response.getWriter().write("true"); // 存在
		} else {
			response.getWriter().write("flase");// 不存在
		}
	}

	/*
	 * 
	 * 2. Ajax请求检查手机是否可用
	 */
	protected void checkPhoneExist(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String customerPhone = request.getParameter("customerPhone");
		boolean exist = us.checkLinkPhoneExist(customerPhone);
		if (exist) {
			response.getWriter().write("true");// 存在
		} else {
			response.getWriter().write("flase");// 不存在
		}
	}

	/*
	 * 
	 * 3. 添加客户
	 */
	protected void addCompany(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String companyname = request.getParameter("companyname");
		String linkman = request.getParameter("linkman");
		String linkphone = request.getParameter("linkphone");
		String address = request.getParameter("address");
		String id = UUIDutils.getUUID();// 获取随机UUID作为客户信息的id
		Company tempC = new Company(id, companyname, linkman, linkphone, address);
		UserService us = new UserService();
		if (us.addCompany(tempC)) {
			request.setAttribute("message", "添加成功！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "添加失败！请修改公司名称或联系电话后再次尝试！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addCresult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
