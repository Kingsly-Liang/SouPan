package com.sou.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Servlet implementation class SearchServlet
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3573.0 Safari/537.36";
		int i = 1;
		String needSearch = request.getParameter("needSearch");
		String url = "http://pan.here325.com/s?q="+ needSearch + "&p=" + i;
		
		Document doc = Jsoup.connect(url).userAgent(userAgent).get();
		Elements name = doc.select("div.data-item div a");
		String titles[] = new String[name.size()];   //需传递
		String outLinks[] = new String[name.size()];
		String innerUrls[] = new String[name.size()];   //需传递
		int size = name.size();      //需传递
		
		int j = 0;
		for(Element e : name){
			outLinks[j] = e.attr("href");
			titles[j] = e.text();
			j++;
		}
		
		for(int k = 0; k < name.size(); k++){
			Document doc1 = Jsoup.connect(outLinks[k]).userAgent(userAgent).get();
			Elements innerUrl = doc1.select("body div div iframe");
			innerUrls[k] = innerUrl.attr("src");
		}
		
		request.setAttribute("links",innerUrls);
        request.setAttribute("titles",titles);
        request.setAttribute("size",size);
        request.getRequestDispatcher("result.jsp").forward(request, response);
	}

}
