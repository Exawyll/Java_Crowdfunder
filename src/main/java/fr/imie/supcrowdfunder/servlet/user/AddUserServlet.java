package fr.imie.supcrowdfunder.servlet.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.imie.supcrowdfunder.entity.User;
import fr.imie.supcrowdfunder.forms.PersistUserForm;

@WebServlet( urlPatterns = "/register" )
public class AddUserServlet extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String ATT_USER         = "user";
    public static final String ATT_FORM         = "form";
    public static final String ATT_SESSION_USER = "userSession";
    public static final String VIEW             = "/addUser.jsp";
    public static final String VIEW_SUCCESS     = "/index.jsp";
    public static final String ACTION           = "create";

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher( VIEW );
        rd.forward( req, resp );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        PersistUserForm form = new PersistUserForm();

        User user;

        user = form.persistUser( req, ACTION );

        req.setAttribute( ATT_FORM, form );
        req.setAttribute( ATT_USER, user );

        if ( form.getErrors().isEmpty() ) {
            HttpSession session = req.getSession();

            session.setAttribute( ATT_SESSION_USER, user );

            this.getServletContext().getRequestDispatcher( VIEW_SUCCESS ).forward( req, resp );
        } else {
            doGet( req, resp );
        }
    }
}
