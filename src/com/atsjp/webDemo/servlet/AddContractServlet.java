package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.service.UserService;
import com.atsjp.webDemo.utils.UUIDutils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用于添加合同的相关操作
 */
@WebServlet("/AddContractServlet")
public class AddContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserService();

	public AddContractServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");// 设置解码方式
		String key = request.getParameter("method");
		if ("checkInvoiceExist".equals(key)) {
		    checkInvoiceExist(request, response);
        }
		if ("addContract".equals(key)) {
			addContract(request, response);
		}
	}

    /*
     *
     * 2. Ajax请求检查发票编号是否可用
     */
    protected void checkInvoiceExist(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        String invoiceNumber = request.getParameter("invoiceNumber");
        boolean exist = us.checkInvoiceExist(invoiceNumber);
        if (exist) {
            response.getWriter().write("true"); // 存在
        } else {
            response.getWriter().write("false");// 不存在
        }
    }
	/*
	 * 
	 * 3. 添加客户
	 */
	protected void addContract(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String contracttime = request.getParameter("contracttime");
		String contractname = request.getParameter("contractname");
		String invoicetitle = request.getParameter("invoicetitle");
		String address = request.getParameter("address");
		String contractcontent = request.getParameter("contractcontent");
		String invoicedetail = request.getParameter("invoicedetail");
		String invoicetime = request.getParameter("invoicetime");
		String invoicenumber = request.getParameter("invoicenumber");
		String invoiceamount = request.getParameter("invoiceamount");
		String id = UUIDutils.getUUID();// 获取随机UUID作为客户信息的id
		Contract tempC = new Contract(id, contracttime, contractname, invoicetitle, address, contractcontent,
				invoicedetail, invoicetime, invoicenumber, invoiceamount);
		UserService us = new UserService();
		if (us.addContract(tempC)) {
			request.setAttribute("message", "添加成功！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addContractResult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "添加失败！请修改公司名称或联系电话后再次尝试！");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addContractResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
