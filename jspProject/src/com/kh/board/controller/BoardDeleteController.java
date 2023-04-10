package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class BoardDeleteController
 */
@WebServlet("/delete.bo")
public class BoardDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    //04/10
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int boardNo = Integer.parseInt(request.getParameter("bno"));

		int result = new BoardService().deleteBoard(boardNo);
		
		//결과를 받환했을 떄
		if(result>0) {
			
			//알람 띄워주고 목록으로 돌아가기
			request.getSession().setAttribute("alertMsg", "게시글 삭제 완료");
			//작업 끝났으니 rediret	현재 페이지가 1인 페이지로 response.sendRedircet는 작어블 수행하고 지정한 페이지로 이동하고 싶을 떄 사용
									//requset.getContenxtPath는 경로만 얻어오는 거다
			response.sendRedirect(request.getContextPath()+"/list.bo?currentPage=1");
		}else {
			//에레메세지 뜨ㅣ오고  위임 
			request.setAttribute("errorMsg","게시글 삭제 실패");
			request.getRequestDispatcher("views/common/errerPage.jsp").forward(request, response);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
