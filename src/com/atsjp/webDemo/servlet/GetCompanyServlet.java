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
 * ��õ���Company���󣬲����в���
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
				index = new String(index.trim().getBytes("ISO-8859-1"), "UTF-8"); // ���url��ȡ������������
			}
		} catch (Exception e) {
			// ������ת�����ı��뷽ʽ��ȷ�������ܳ���encoding��ֵ
		}
		if (index != "" && index != null) {
			// �ж�index�����������ֻ�����
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
			// ȡ��Company���ظ�jsp
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
