package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Company;
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
 * 获得单个Company对象，并进行操作
 */
@WebServlet("/GetCompanyServlet")
public class GetCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Company tempC = new Company();
	private Company ResultC = new Company();
	private UserService us = new UserService();

	public GetCompanyServlet() {
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
			if (encoding.equals("ISO-8859-1")) {
				index = new String(index.trim().getBytes("ISO-8859-1"), "UTF-8"); // 解决url获取中文乱码问题
			}
		} catch (Exception e) {
			// 由于跳转过来的编码方式不确定，可能出现encoding无值
		}
		if (index != "" && index != null) {
			// 判断index是姓名还是手机号码
			boolean flag = false;
			for (int i = 0; i < index.length(); i++) {
				flag = Character.isDigit(index.charAt(i));
				if (!flag) {
					break;
				}
			}
			if (!flag) {
				tempC.setCompanyname(index);
				tempC.setLinkphone(null);
			} else {
				tempC.setCompanyname(null);
				tempC.setLinkphone(index);
			}
			// 取出Company返回给jsp
			ResultC = us.getCompany(tempC);
			if (ResultC.getId() != null) {
				request.setAttribute("Company", ResultC);
				// response.sendRedirect("./manager/getCompany.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyCompany.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("result", index);
				// response.sendRedirect("./manager/getCompany.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyCompany.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("result", index);
			// response.sendRedirect("./manager/getCompany.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/modifyCompany.jsp");
			dispatcher.forward(request, response);
		}

	}
}
