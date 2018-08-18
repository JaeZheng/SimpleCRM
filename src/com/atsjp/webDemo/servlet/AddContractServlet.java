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
 * ������Ӻ�ͬ����ز���
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
		request.setCharacterEncoding("UTF-8");// ���ý��뷽ʽ
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
     * 2. Ajax�����鷢Ʊ����Ƿ����
     */
    protected void checkInvoiceExist(HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        String invoiceNumber = request.getParameter("invoiceNumber");
        boolean exist = us.checkInvoiceExist(invoiceNumber);
        if (exist) {
            response.getWriter().write("true"); // ����
        } else {
            response.getWriter().write("false");// ������
        }
    }
	/*
	 * 
	 * 3. ��ӿͻ�
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
		String id = UUIDutils.getUUID();// ��ȡ���UUID��Ϊ�ͻ���Ϣ��id
		Contract tempC = new Contract(id, contracttime, contractname, invoicetitle, address, contractcontent,
				invoicedetail, invoicetime, invoicenumber, invoiceamount);
		UserService us = new UserService();
		if (us.addContract(tempC)) {
			request.setAttribute("message", "��ӳɹ���");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addContractResult.jsp");
			dispatcher.forward(request, response);
		} else {
			request.setAttribute("message", "���ʧ�ܣ����޸Ĺ�˾���ƻ���ϵ�绰���ٴγ��ԣ�");
			RequestDispatcher dispatcher = request
					.getRequestDispatcher("./manager/addContractResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
