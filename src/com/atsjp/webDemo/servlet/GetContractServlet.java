package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Contract;
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
 * ��õ���Contract���󣬲����в���
 */
@WebServlet("/GetContractServlet")
public class GetContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Contract tempC = new Contract();
	private Contract ResultC = new Contract();
	private UserService us = new UserService();

	public GetContractServlet() {
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
			tempC.setInvoicenumber(index);
			// ȡ��Contract���ظ�jsp
			ResultC = us.getContract(tempC);
			if (ResultC.getId() != null) {
				request.setAttribute("Contract", ResultC);
				// response.sendRedirect("./manager/getContract.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyContract.jsp");
				dispatcher.forward(request, response);
			} else {
				request.setAttribute("result", index);
				// response.sendRedirect("./manager/getContract.jsp");
				RequestDispatcher dispatcher = request
						.getRequestDispatcher("./manager/modifyContract.jsp");
				dispatcher.forward(request, response);
			}
		} else {
			request.setAttribute("result", index);
			// response.sendRedirect("./manager/getContract.jsp");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/modifyContract.jsp");
			dispatcher.forward(request, response);
		}

	}
}
