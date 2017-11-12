package fr.imie.supcrowdfunder.servlet.project;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import fr.imie.supcrowdfunder.dao.DaoFactory;
import fr.imie.supcrowdfunder.entity.Category;
import fr.imie.supcrowdfunder.entity.Project;
import fr.imie.supcrowdfunder.entity.User;
import fr.imie.supcrowdfunder.forms.PictureForm;

@WebServlet( urlPatterns = "/auth/addProject" )
@MultipartConfig( fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 ) // 50MB
public class AddProjectServlet extends HttpServlet {

    private static final long  serialVersionUID = 1L;

    public static final String ATT_SESSION_USER = "userSession";
    public static final String VIEW             = "/auth/addProject.jsp";
    public static final String ATT_FORM         = "form";

    @Override
    public void init() throws ServletException {
        DiskFileItemFactory fileFactory = new DiskFileItemFactory();
        File filesDir = (File) getServletContext().getAttribute( "FILES_DIR_FILE" );
        fileFactory.setRepository( filesDir );
        new ServletFileUpload( fileFactory );
    }

    @Override
    protected void doGet( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        Collection<Object> objectList = DaoFactory.getBaseDao( Category.class ).findAll();

        List<Category> categoryList = new ArrayList<Category>();

        for ( Object object : objectList ) {
            categoryList.add( (Category) object );
        }

        req.setAttribute( "categories", categoryList );

        this.getServletContext().getRequestDispatcher( VIEW ).forward( req, resp );
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp ) throws ServletException, IOException {

        PictureForm form = new PictureForm();

        /**
         * Handle file picture
         */
        String picture = form.uploadPicture( req );

        req.setAttribute( ATT_FORM, form );

        if ( form.getErrors().isEmpty() ) {

            Project p = new Project();
            p.setName( req.getParameter( "name" ) );
            p.setDescription( req.getParameter( "description" ) );
            p.setObjective( Float.parseFloat( req.getParameter( "objective" ) ) );
            p.setCreatedAt( new org.joda.time.DateTime() );

            DateTimeFormatter dtf = DateTimeFormat.forPattern( "dd-mm-yy" );
            DateTime jodatime = dtf.parseDateTime( req.getParameter( "endsAt" ) );
            p.setEndsAt( jodatime );

            User user = (User) req.getSession().getAttribute( ATT_SESSION_USER );
            p.setCreator( user );

            Long idCategory = Long.parseLong( req.getParameter( "category" ) );
            Category category = (Category) DaoFactory.getBaseDao( Category.class ).findById( idCategory );
            p.setCategory( category );

            p.setPicture( picture );

            Project thisProject = (Project) DaoFactory.getBaseDao( Project.class ).add( p );

            resp.sendRedirect( "/SupCrowdfunder/viewProject?id=" + thisProject.getId().toString() );
        } else {

            this.getServletContext().getRequestDispatcher( VIEW ).forward( req, resp );
        }

    }

}
