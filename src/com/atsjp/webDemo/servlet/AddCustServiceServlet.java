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
 * 用于添加客户的相关操作
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
		request.setCharacterEncoding("UTF-8");// 设置解码方式
		String key = request.getParameter("method");
		if ("addCustService".equals(key)) {
			addCustService(request, response);
		}
	}

	/*
	 * 
	 * 3. 添加客户服务信息
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
			request.setAttribute("addResult", "添加成功！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/GetPageCustServiceServlet?page=0");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("addResult", "添加失败！请修改客户名称再次尝试！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/GetPageCustServiceServlet?page=0");
			dispatcher.forward(request, response);
		}
	}

}
