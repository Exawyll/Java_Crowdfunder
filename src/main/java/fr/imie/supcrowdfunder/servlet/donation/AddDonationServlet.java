package fr.imie.supcrowdfunder.servlet.donation;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Donation;
import fr.imie.supcrowdfunder.entity.Project;
import fr.imie.supcrowdfunder.entity.User;

/**
 * Servlet implementation class AddDonationServlet
 */
@WebServlet( urlPatterns = "/auth/addDonation" )
public class AddDonationServlet extends HttpServlet {
    private static final long  serialVersionUID = 1L;

    public static final String ATT_SESSION_USER = "userSession";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDonationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Servlet#init(ServletConfig)
     */
    public void init( ServletConfig config ) throws ServletException {
        // TODO Auto-generated method stub
    }

    /**
     * @see Servlet#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // TODO: Create the corresponding view addDonation.jsp
        if ( request.getParameter( "idProject" ) == null ) {
            response.sendRedirect( "/SupCrowdFunder/listProject.jsp" );
        } else {
            Project project = (Project) DaoFactory.getBaseDao( Project.class )
                    .findById( Long.parseLong( request.getParameter( "idProject" ) ) );
            request.setAttribute( "project", project );
            RequestDispatcher rd = request.getRequestDispatcher( "/views/auth/addDonation.jsp" );
            rd.forward( request, response );
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        // Create a new Donation object
        Donation donation = new Donation();
        // Retrieve the form parameters
        // Set the parameters in the object
        // amount
        donation.setAmount( Float.parseFloat( request.getParameter( "amount" ) ) );
        // creation date

        donation.setCreatedAt( new org.joda.time.DateTime() );
        // project
        Project project = (Project) DaoFactory.getBaseDao( Project.class )
                .findById( Long.parseLong( request.getParameter( "idProject" ) ) );

        donation.setProject( project );
        // user

        User user = (User) request.getSession().getAttribute( ATT_SESSION_USER );

        donation.setDonator( user );

        if ( donation.getAmount() > 0 ) {
            DaoFactory.getBaseDao( Donation.class ).add( donation );
        }

        response.sendRedirect( "/SupCrowdfunder/viewProject?id=" + project.getId().toString() );
        // response.sendRedirect("/SupCrowdFunder/showProject/" +
        // project.getId());

    }

}
