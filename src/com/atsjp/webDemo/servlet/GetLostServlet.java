package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Lost;
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
 * ��õ���Lost���󣬲����в���
 */
@WebServlet("/GetLostServlet")
public class GetLostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Lost tempC = new Lost();
	private Lost ResultC = new Lost();
	private UserService us = new UserService();

	public GetLostServlet() {
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
				index = new String(index.trim().getBytes("ISO-8859-1"), "UTF-8"); // ���url��ȡ������������
			}
			*/
		} catch (Exception e) {
			// ������ת�����ı��뷽ʽ��ȷ�������ܳ���encoding��ֵ
		}
		if (index != "" && index != null) {
			tempC.setId(index);
			// ȡ��Lost���ظ�jsp
			ResultC = us.getLost(tempC);
			if (ResultC.getId() != null) {
				request.setAttribute("Lost", ResultC);
				// response.sendRedirect("./manager/getLost.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyLost.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("result", index);
				// response.sendRedirect("./manager/getLost.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyLost.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("result", index);
			// response.sendRedirect("./manager/getLost.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/modifyLost.jsp");
			dispatcher.forward(request, response);
		}

	}
}
