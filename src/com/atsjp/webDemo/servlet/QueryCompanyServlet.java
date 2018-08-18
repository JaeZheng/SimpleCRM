package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Company;
import com.atsjp.webDemo.entity.Page;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 处理分页获取company队列
 */
@WebServlet("/QueryCompanyServlet")
public class QueryCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Company> tempCu = new LinkedList<Company>();
	private UserService us = new UserService();

	public QueryCompanyServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String index = request.getParameter("cindex");
		// 进行查找company信息
		tempCu = us.queryCompany(index);
		if (!tempCu.isEmpty()) {
			request.setAttribute("CompanyList", tempCu);
			request.getRequestDispatcher("./manager/getAllCompany.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("CompanyList", null);
			request.getRequestDispatcher("./manager/getAllCompany.jsp")
					.forward(request, response);
		}
	}
}
