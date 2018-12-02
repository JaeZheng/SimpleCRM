package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Lost;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 修改lost的封装操作
 */
@WebServlet("/ModifyLostServlet")
public class ModifyLostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyLostServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String joindate = request.getParameter("joindate");
		String lossdate = request.getParameter("lossdate");
		String companyname = request.getParameter("companyname");
		String reason = request.getParameter("reason");
		Lost tempC = new Lost(id, joindate, lossdate, companyname, reason);
		UserService us = new UserService();
		if (us.modifyLost(tempC)) {
			request.setAttribute("message", "修改成功!");
			request.setAttribute("ResultCName", tempC.getId());
			request.getRequestDispatcher("./manager/modifyLostResult.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("message", "修改失败，请尝试重新搜索或者再次尝试。");
			request.setAttribute("ResultCName", tempC.getId());
			request.getRequestDispatcher("./manager/modifyLostResult.jsp")
					.forward(request, response);
		}
	}

}
