package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 
 * 修改contract的封装操作
 */
@WebServlet("/ModifyContractServlet")
public class ModifyContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ModifyContractServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        System.out.println("进入modifycontractservlet...");
		String id = request.getParameter("id");
		System.out.println("拿到contract id 为："+ id);
		String contracttime = request.getParameter("contracttime");
		String contractname = request.getParameter("contractname");
		String invoicetitle = request.getParameter("invoicetitle");
		String address = request.getParameter("address");
        String contractcontent = request.getParameter("contractcontent");
        String invoicedetail = request.getParameter("invoicedetail");
        String invoicetime = request.getParameter("invoicetime");
        String invoicenumber = request.getParameter("invoicenumber");
        String invoiceamount = request.getParameter("invoiceamount");
		Contract tempC = new Contract(id, contracttime, contractname, invoicetitle, address, contractcontent,
		                                invoicedetail, invoicetime, invoicenumber, invoiceamount);
		UserService us = new UserService();
		if (us.modifyContract(tempC)) {
		    System.out.println("修改成功");
			request.setAttribute("message", "修改成功!");
			request.setAttribute("ResultInvoicenumber", tempC.getInvoicenumber());
			request.getRequestDispatcher("./manager/modifyContractResult.jsp")
					.forward(request, response);
		} else {
            System.out.println("修改失败");
			request.setAttribute("message", "修改失败，请尝试重新搜索是否有该发票编号或者再次尝试。");
			request.setAttribute("ResultInvoicenumber", tempC.getInvoicenumber());
			request.getRequestDispatcher("./manager/modifyContractResult.jsp")
					.forward(request, response);
		}
	}

}
