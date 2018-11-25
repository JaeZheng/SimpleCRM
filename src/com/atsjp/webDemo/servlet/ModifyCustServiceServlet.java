package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.CustService;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 修改custService的封装操作
 */
@WebServlet("/ModifyCustServiceServlet")
public class ModifyCustServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyCustServiceServlet() {
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
		String customername = request.getParameter("customername");
		String linkman = request.getParameter("linkman");
		String linkphone = request.getParameter("linkphone");
		String servicetype = request.getParameter("servicetype");
		String servicedate = request.getParameter("servicedate");
		String estimatedcost = request.getParameter("estimatedcost");
		String actualcost = request.getParameter("actualcost");
		String satisfaction = request.getParameter("satisfaction");
		CustService tempC = new CustService(id, customername, linkman, linkphone, servicetype,
				servicedate, estimatedcost, actualcost, satisfaction);
		UserService us = new UserService();
		if (us.modifyCustService(tempC)) {
			request.setAttribute("message", "修改成功!");
			request.setAttribute("ResultCName", tempC.getId());
			request.getRequestDispatcher("./manager/modifyCustServiceResult.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("message", "修改失败，请尝试重新搜索是否有该客户或者再次尝试。");
			request.setAttribute("ResultCName", tempC.getId());
			request.getRequestDispatcher("./manager/modifyCustServiceResult.jsp")
					.forward(request, response);
		}
	}

}
