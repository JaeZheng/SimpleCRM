package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Opinion;
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
 * ��õ���Opinion���󣬲����в���
 */
@WebServlet("/GetOpinionServlet")
public class GetOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Opinion tempC = new Opinion();
	private Opinion ResultC = new Opinion();
	private UserService us = new UserService();

	public GetOpinionServlet() {
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
			// ȡ��Opinion���ظ�jsp
			ResultC = us.getOpinion(tempC);
			if (ResultC.getId() != null) {
				request.setAttribute("Opinion", ResultC);
				// response.sendRedirect("./manager/getOpinion.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyOpinion.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("result", index);
				// response.sendRedirect("./manager/getOpinion.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyOpinion.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("result", index);
			// response.sendRedirect("./manager/getOpinion.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/modifyOpinion.jsp");
			dispatcher.forward(request, response);
		}

	}
}
