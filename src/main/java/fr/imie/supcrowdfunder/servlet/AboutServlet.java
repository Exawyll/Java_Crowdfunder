package fr.imie.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AboutServlet
 */
@WebServlet( urlPatterns = "/about" )
public class AboutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AboutServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.http.HttpServlet#service(javax.servlet.http.
     * HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher( "about.jsp" );
        rd.forward( req, resp );
    }
}
