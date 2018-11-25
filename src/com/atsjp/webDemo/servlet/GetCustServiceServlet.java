package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.CustService;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 获得单个CustService对象，并进行操作
 */
@WebServlet("/GetCustServiceServlet")
public class GetCustServiceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CustService tempC = new CustService();
	private CustService ResultC = new CustService();
	private UserService us = new UserService();

	public GetCustServiceServlet() {
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
		try {
			String encoding = request.getParameter("encoding");
			/*
			if (encoding.equals("ISO-8859-1")) {
				index = new String(index.trim().getBytes("ISO-8859-1"), "UTF-8"); // 解决url获取中文乱码问题
			}
			*/
		} catch (Exception e) {
			// 由于跳转过来的编码方式不确定，可能出现encoding无值
		}
		if (index != "" && index != null) {
			tempC.setId(index);
			// 取出CustService返回给jsp
			ResultC = us.getCustService(tempC);
			if (ResultC.getId() != null) {
				request.setAttribute("CustService", ResultC);
				// response.sendRedirect("./manager/getCustService.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyCustService.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("result", index);
				// response.sendRedirect("./manager/getCustService.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyCustService.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("result", index);
			// response.sendRedirect("./manager/getCustService.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/modifyCustService.jsp");
			dispatcher.forward(request, response);
		}

	}
}
