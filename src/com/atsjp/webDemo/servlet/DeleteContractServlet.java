package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.entity.Page;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 * 
 * ����ɾ��contract����Ϣ
 */
@WebServlet("/DeleteContractServlet")
public class DeleteContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private Page page = new Page();

	public DeleteContractServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id= request.getParameter("id");
		Contract tempC = new Contract();
		tempC.setId(id);
		boolean deleteResult = us.deleteContract(tempC);
        page.getContractNew();
        // ά����ǰҳ
        int lastPage = page.getPage();
        int totalCount = page.getTotalCount();
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("lastPage", lastPage);
		if (deleteResult) {
			request.setAttribute("deleteResult", "ɾ���ɹ���");
			request.getRequestDispatcher("./manager/getAllContract.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("deleteResult", "ɾ��ʧ�ܣ�");
			request.getRequestDispatcher("./manager/getAllContract.jsp")
					.forward(request, response);
		}

	}

}
