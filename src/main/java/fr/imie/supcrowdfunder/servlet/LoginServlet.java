package fr.imie.supcrowdfunder.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.imie.supcrowdfunder.entity.User;
import fr.imie.supcrowdfunder.forms.LoginForm;

@WebServlet( urlPatterns = "/login" )
public class LoginServlet extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "userSession";
    public static final String VIEW             = "/login.jsp";
    public static final String VIEW_SUCCESS     = "/index.jsp";

    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

        LoginForm form = new LoginForm();

        User user = form.logUser( request );

        HttpSession session = request.getSession();

        if ( form.getErrors().isEmpty() ) {
            session.setAttribute( ATT_SESSION_USER, user );

            request.setAttribute( ATT_FORM, form );

            this.getServletContext().getRequestDispatcher( VIEW_SUCCESS ).forward( request, response );
        } else {
            session.setAttribute( ATT_SESSION_USER, null );

            request.setAttribute( ATT_FORM, form );
            request.setAttribute( ATT_USER, user );

            this.getServletContext().getRequestDispatcher( VIEW ).forward( request, response );
        }

    }

}
