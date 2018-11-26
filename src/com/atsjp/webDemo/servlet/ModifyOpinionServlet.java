package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Opinion;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 修改customer的封装操作
 */
@WebServlet("/ModifyOpinionServlet")
public class ModifyOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyOpinionServlet() {
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
		String companyname = request.getParameter("companyname");
		String linkman = request.getParameter("linkman");
		String linkphone = request.getParameter("linkphone");
		String opiniondetail = request.getParameter("opiniondetail");
		String opinionstate = request.getParameter("opinionstate");
		Opinion tempC = new Opinion(id, companyname, linkman, linkphone, opiniondetail, opinionstate);
		UserService us = new UserService();
		if (us.modifyOpinion(tempC)) {
			request.setAttribute("message", "修改成功!");
			request.setAttribute("ResultCName", tempC.getId());
			request.getRequestDispatcher("./manager/modifyOpinionResult.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("message", "修改失败，请尝试重新搜索是否有该客户或者再次尝试。");
			request.setAttribute("ResultCName", tempC.getId());
			request.getRequestDispatcher("./manager/modifyOpinionResult.jsp")
					.forward(request, response);
		}
	}

}
