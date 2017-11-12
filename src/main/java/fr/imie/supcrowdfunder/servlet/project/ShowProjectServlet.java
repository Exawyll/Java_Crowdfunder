package fr.imie.supcrowdfunder.servlet.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Project;

/**
 * Servlet implementation class ShowProjectServlet
 */
@WebServlet( urlPatterns = "/viewProject" )
public class ShowProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProjectServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {
        Long id = Long.parseLong( req.getParameter( "id" ) );
        Project p = (Project) DaoFactory.getBaseDao( Project.class ).findById( id );

        if ( p != null ) {
            String picture = getFile( req, resp, p.getPicture() );
            p.setPicture( picture );

            req.setAttribute( "p", p );

            RequestDispatcher rd = req.getRequestDispatcher( "/viewProject.jsp" );
            rd.forward( req, resp );
        } else {
            resp.getWriter().println( "No project with id " + id );
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        doGet( request, response );
    }

    private String getFile( HttpServletRequest request, HttpServletResponse response, String fileName )
            throws ServletException, IOException {
        File file = new File(
                request.getServletContext().getAttribute( "FILES_DIR" ) + File.separator + "pictures" + File.separator
                        + fileName );
        if ( !file.exists() ) {
            throw new ServletException( "File doesn't exists on server." );
        }
        System.out.println( "File location on server::" + file.getAbsolutePath() );
        InputStream fis = new FileInputStream( file );
        byte[] bufferData = IOUtils.toByteArray( fis );
        byte[] encodeBase64 = Base64.getEncoder().encode( bufferData );
        String base64Encoded = new String( encodeBase64, "UTF-8" );
        fis.close();

        return base64Encoded;
    }

}
