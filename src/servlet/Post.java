package servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import dao.BoardDao;

/**
 * Servlet implementation class Post
 */
@WebServlet("/Post")
@MultipartConfig(maxFileSize=1048576)
public class Post extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Post() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String content = request.getParameter("content");
		//日時の取得
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());

		//添付ファイルの取得
		Part part = request.getPart("file");
		String filename = this.getFilename(part);

		if(filename.length() == 0){
			//添付ファイルがない場合
			BoardDao.addDao(name, email, content, timestamp);
		}else{
			//添付ファイルがある場合
			part.write("C:\\pleiades\\workspace\\board\\WebContent\\upload\\" + filename);
			BoardDao.fileaddDao(name, email, content,filename,timestamp);
		}

		String view = "/WEB-INF/view/PostResult.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(view);
		dispatcher.forward(request, response);
	}

	private String getFilename(Part part) {
		String filename = null;
		for(String dispotion : part.getHeader("Content-Disposition").split(";")){
			if(dispotion.trim().startsWith("filename")){
				filename = dispotion.substring(dispotion.indexOf("=")+1).replace("\"","" ).trim();
				filename = filename.substring(filename.lastIndexOf("\\")+1);
				break;
			}
		}
		return filename;
	}

}
