package _58_roomSche.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;

import _11_room.model.RoomDAO;
import _11_room.model.RoomVO;
import hibernate.util.HibernateUtil;

@WebServlet(urlPatterns = { "/test" })
public class test extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		RoomDAO roomDAO = new RoomDAO();
		System.out.println(roomDAO);
		List<RoomVO> roomlist = roomDAO.getAll();
		
		//接受跨站點的任何請求
		resp.setHeader("Access-Control-Allow-Origin", "*");
		resp.setCharacterEncoding("UTF-8");
		
		//去除空白
		for(RoomVO roomVO:roomlist){
			roomVO.setRoom_type(roomVO.getRoom_type().trim());
			roomVO.setrContext(roomVO.getrContext().trim());
		}
		
		//使用函式庫傳回JSON
		
//		String xString = JSONValue.toJSONString(roomlist);
//		String xString = JSONValue.toJSONString(roomMap);
//		PrintWriter out = resp.getWriter();
//		out.println(xString);

		// R回傳
		// req.getRequestDispatcher("/bRoom/bRoom.jsp").forward(req, resp);
		
		String path =req.getContextPath();
		HttpSession hSession=req.getSession();
		hSession.setAttribute("rooms", roomlist);
		hSession.setAttribute("ha", "ha");
		resp.sendRedirect(path+"/_58_roomSche/roomSche.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
