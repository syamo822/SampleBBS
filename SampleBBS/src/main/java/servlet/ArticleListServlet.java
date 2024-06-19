package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Article;
import dao.Dao;

//SampleBBS/ArticleListServletにアクセスすると，このサーブレットが動作
@WebServlet("/ArticleListServlet")
public class ArticleListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//コンストラクタ（省略可能）
    public ArticleListServlet() {
        super();
    }

    //Article編集
    //POSTアクセスされた場合は，doGetに丸投げ
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}
	
	//GETアクセスされた場合に動作
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//DBアクセスのためのクラスをインスタンス化
		Dao dao = new Dao();
		//すべての記事のリストを取得．Article(記事)クラスはbeansパッケージに宣言してある．
		List<Article> articleList = dao.getArticleList();

		//requestに記事リストを格納．
		request.setAttribute("articleList" , articleList);

		//./WEB-INF/jsp/articleList.jspを表示
		RequestDispatcher dispatcher = request.getRequestDispatcher("./WEB-INF/jsp/articleList.jsp");
		dispatcher.forward(request, response);
	}

}
