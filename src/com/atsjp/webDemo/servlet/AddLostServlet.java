package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Lost;
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
@WebServlet("/AddLostServlet")
public class AddLostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public AddLostServlet() {
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
		if ("addLost".equals(key)) {
			addLost(request, response);
		}
	}

	protected void addLost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String joindate = request.getParameter("joindate");
		String lossdate = request.getParameter("lossdate");
		String companyname = request.getParameter("companyname");
		String reason = request.getParameter("reason");
		String id = UUIDutils.getUUID();// 获取随机UUID作为客户信息的id
		Lost tempC = new Lost(id, joindate, lossdate, companyname, reason);
		UserService us = new UserService();
		if (us.addLost(tempC)) {
			request.setAttribute("addResult", "添加成功！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/GetPageLostServlet?page=0");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("addResult", "添加失败！请再次尝试！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("/GetPageLostServlet?page=0");
			dispatcher.forward(request, response);
		}
	}
}
