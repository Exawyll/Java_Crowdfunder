package fr.imie.supcrowdfunder.servlet.project;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.List;

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
 * Servlet implementation class ListProjectsServlet
 */
@WebServlet( urlPatterns = "/listProjects" )
public class ListProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void service( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        // Get all project from DB
        Collection<Object> objectList = DaoFactory.getBaseDao( Project.class ).findAll();

        List<Project> projectList = new ArrayList<Project>();

        for ( Object object : objectList ) {
            Project project = (Project) object;
            String picture = getFile( req, resp, project.getPicture() );
            project.setPicture( picture );
            projectList.add( project );
        }

        req.setAttribute( "projects", projectList );

        RequestDispatcher rd = req.getRequestDispatcher( "listProjects.jsp" );
        rd.forward( req, resp );

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
