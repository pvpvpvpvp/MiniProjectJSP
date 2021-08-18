package com.numberlist.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.numberlist.dao.NumberListDao;
import com.numberlist.dao.NumberListDaoImpl;
import com.numberlist.vo.NumberListVo;
import com.sun.jdi.event.Event;

@WebServlet(urlPatterns = "/",
			initParams = @WebInitParam(name="search",value = "")) //초기상태에서 ./에서 home.jsp 포워딩
public class HomeServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String actionName = req.getParameter("a");
		if ("insert".equals(actionName))
		{
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/numbers/insert.jsp");
			rd.forward(req, resp);
		}else {
			String keyword = req.getParameter("search");
			if(keyword==null) //null 초기화
				keyword="";
			System.out.println(keyword);
		// DAO에서 목록을 받아서 JSP로 전달
		if(keyword=="") { // 서치 값이 없다면 원래 리스트 setAttribute // 값이 있을경우는 밑에 참조
		NumberListDao dao = new NumberListDaoImpl();
		List<NumberListVo> list = dao.getList();
		//요청에 list를 추가
		req.setAttribute("list", list);
		// 홈페이지로 포워딩	
		}
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/home.jsp");
		rd.forward(req, resp);
		
		}
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String actionName = req.getParameter("a");
		
		if("delete".equals(actionName)){
			Long no = Long.valueOf(req.getParameter("no"));
			NumberListDao dao = new NumberListDaoImpl();
			int deletedCount = dao.delete(no);
			resp.sendRedirect(req.getContextPath());
		}else if("add".equals(actionName)) {
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			NumberListVo vo = new NumberListVo();
			vo.setNumberName(name);
			vo.setNumberHp(hp);
			vo.setNumberTel(tel);
			
			NumberListDao dao = new NumberListDaoImpl();
			
			int insertedCount=dao.insert(vo);
			//처리 후 list 페이지로 리다이렉트
			
			resp.sendRedirect(req.getContextPath());
		}else if("search".equals(actionName))
		{
			String ketword = req.getParameter("search");
			NumberListDao dao = new NumberListDaoImpl();
			List<NumberListVo> searchlist = dao.getSearch(ketword);
			req.setAttribute("list", searchlist); 	// 같은 list에 반환 해줌으로써 처리함	
			doGet(req, resp);
		}
	}
	
}
