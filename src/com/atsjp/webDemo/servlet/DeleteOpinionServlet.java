package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Opinion;
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
 * ����ɾ��company����Ϣ
 */
@WebServlet("/DeleteOpinionServlet")
public class DeleteOpinionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private Page page = new Page();

	public DeleteOpinionServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String opinionId= request.getParameter("opinionId");
		boolean deleteResult;
		if (opinionId == null) {
			deleteResult = us.deleteOpinionAll();
		} else {
			Opinion tempC = new Opinion();
			tempC.setId(opinionId);
			deleteResult = us.deleteOpinion(tempC);
		}
        page.getOpinionNew();
        // ά����ǰҳ
        int lastPage = page.getPage();
        int totalCount = page.getTotalCount();
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("lastPage", lastPage);
		if (deleteResult) {
			request.setAttribute("deleteResult", "ɾ���ɹ���");
			request.getRequestDispatcher("./manager/getAllOpinion.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("deleteResult", "ɾ��ʧ�ܣ�");
			request.getRequestDispatcher("./manager/getAllOpinion.jsp")
					.forward(request, response);
		}

	}

}
