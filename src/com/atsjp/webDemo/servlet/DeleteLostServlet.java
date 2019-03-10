package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Lost;
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
 * 处理删除lost的信息
 */
@WebServlet("/DeleteLostServlet")
public class DeleteLostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();
	private Page page = new Page();

	public DeleteLostServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String lostId = request.getParameter("lostId");
		boolean deleteResult;
		if (lostId == null) {
			deleteResult = us.deleteLostAll();
		} else {
			Lost tempC = new Lost();
			tempC.setId(lostId);
			deleteResult = us.deleteLost(tempC);
		}
        page.getLostNew();
        // 维护当前页
        int lastPage = page.getPage();
        int totalCount = page.getTotalCount();
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("lastPage", lastPage);
		if (deleteResult) {
			request.setAttribute("deleteResult", "删除成功！");
			request.getRequestDispatcher("/GetPageLostServlet?page=0")
					.forward(request, response);
		} else {
			request.setAttribute("deleteResult", "删除失败！");
			request.getRequestDispatcher("/GetPageLostServlet?page=0")
					.forward(request, response);
		}

	}

}
