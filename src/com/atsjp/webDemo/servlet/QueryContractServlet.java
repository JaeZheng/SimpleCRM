package com.atsjp.webDemo.servlet;

import com.atsjp.webDemo.entity.Contract;
import com.atsjp.webDemo.entity.Page;
import com.atsjp.webDemo.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * 处理分页查询获取Contract队列
 */
@WebServlet("/QueryContractServlet")
public class QueryContractServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private List<Contract> tempCu = new LinkedList<Contract>();
	private UserService us = new UserService();
    private Page pageService = new Page();

	public QueryContractServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
        // 处理从前台传回的数据,first,down，up,last分别表示首页，上一页，下一页,末页，0,1，2，3...表示第几页
        String page = "";
        try {
            page = request.getParameter("page");
            page = page.replaceAll(" ", "");// 去掉字符串中的空格
        } catch (Exception e) {
            page = "0";
        }
		String index = request.getParameter("cindex");
//        if(index==null){
//            System.out.println("index null!!!");
//        }
        if(index!=null && !index.equals("")){
            Cookie[] cookies = request.getCookies();
            boolean noCookieFlag = true;
            for(int i=0; cookies!=null && i<cookies.length; i++){
                if(cookies[i].getName().equals("contractIndex")){
                    cookies[i].setValue(URLEncoder.encode(index, "UTF-8"));
//                    System.out.println("重设cookie值");
                    response.addCookie(cookies[i]);
                    noCookieFlag = false;
                    break;
                }
            }
            if(noCookieFlag){
                Cookie cookie = new Cookie("contractIndex", URLEncoder.encode(index, "UTF-8"));
                response.addCookie(cookie);
//                System.out.println("新增cookie");
            }
        }
        Cookie[] cookies = request.getCookies();
        for(int i=0; cookies!=null && i<cookies.length; i++){
            if(cookies[i].getName().equals("contractIndex")){
                index = URLDecoder.decode(cookies[i].getValue(), "UTF-8");
//                System.out.println("index值:" + index);
                break;
            }
        }
        // 获取查询总个数
        pageService.queryContractCount(index);
        // 维护当前页
        int pageSize = pageService.getPageSize();
        int currentPage = pageService.getCurrentPage();
        int totalCount = pageService.getTotalCount();
        if ("down".equals(page) || "up".equals(page) || "first".equals(page)
                || "last".equals(page)) {
            if ("up".equals(page)) {
                currentPage --;
            }
            if ("down".equals(page)) {
                currentPage ++;
            }
            if ("first".equals(page)) {
                currentPage = 0;
            }
            if ("last".equals(page)) {
                currentPage = pageService.getPage() - 1;
            }
        } else {
            currentPage = Integer.valueOf(page) - 1;
        }
        // 进行检查，以防currentPage越界
        if (currentPage < 0) {
            currentPage = 0;
        }

        if (currentPage >= pageService.getPage()) {
            currentPage = pageService.getPage() - 1;
        }
        pageService.setCurrentPage(currentPage);
//        System.out.println("当前页：" + Integer.toString(currentPage));
		// 进行查找Contract信息
		tempCu = us.queryContractList(index, currentPage, pageSize);
        // 传回真正逻辑意义上的总记录,总页数,第一页（首页），当前页,开始打印和结束打印页
        int beginPage = currentPage - 5;// 当前页向前多打印5页
        int endPage = currentPage + 5;// 当前页向后多打印5页
        // 开始和结束页不能越界
        if (beginPage <= 0) {
            beginPage = 1;
        }
        if (endPage > pageService.getPage()) {
            endPage = pageService.getPage();
        }
        request.setAttribute("cindex", index);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("lastPage", pageService.getPage());
        request.setAttribute("firstPage", 1);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("beginPage", beginPage);
        request.setAttribute("endPage", endPage);
		if (!tempCu.isEmpty()) {
			request.setAttribute("ContractList", tempCu);
			request.setAttribute("result", index);
			request.getRequestDispatcher("./manager/queryContract.jsp")
					.forward(request, response);
		} else {
			request.setAttribute("ContractList", null);
            request.setAttribute("result", index);
			request.getRequestDispatcher("./manager/queryContract.jsp")
					.forward(request, response);
		}
	}
}
