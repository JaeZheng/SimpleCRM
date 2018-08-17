package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Company;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * �޸�customer�ķ�װ����
 */
@WebServlet("/ModifyCompanyServlet")
public class ModifyCompanyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyCompanyServlet() {
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
		String address = request.getParameter("address");
		Company tempC = new Company(id, companyname, linkman, linkphone, address);
		UserService us = new UserService();
		if (us.modifyCompany(tempC)) {
			request.setAttribute("message", "�޸ĳɹ�!");
			request.setAttribute("ResultCName", tempC.getCompanyname());
			request.getRequestDispatcher("./manager/modifyCompanyResult.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("message", "�޸�ʧ�ܣ��볢�����������Ƿ��иÿͻ������ٴγ��ԡ�");
			request.setAttribute("ResultCName", tempC.getCompanyname());
			request.getRequestDispatcher("./manager/modifyCompanyResult.jsp")
					.forward(request, response);
		}
	}

}
