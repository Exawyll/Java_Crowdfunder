package fr.imie.supcrowdfunder.forms;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class PictureForm {

    /**
     * Name of the directory where uploaded files will be saved, relative to the
     * web application directory.
     */
    private static final String SAVE_DIR    = "pictures";
    private static final String PICTURE     = "picture";
    private static final String ERROR_FIELD = "error";

    private static String       result;

    private Map<String, String> errors      = new HashMap<String, String>();

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public String uploadPicture( HttpServletRequest req ) throws ServletException {
        // gets absolute path of the web application
        // String appPath = req.getServletContext().getRealPath( "" );
        String appPath = (String) req.getServletContext().getAttribute( "FILES_DIR" );
        String savePath = null;
        String fileName = null;

        try {
            savePath = createDirectory( appPath );
        } catch ( Exception e ) {
            errors.put( ERROR_FIELD, "An error occured while creating the directory to upload the picture" );
        }

        try {
            Part part = req.getPart( PICTURE );
            fileName = writeFile( part, savePath );
        } catch ( IOException e ) {
            errors.put( ERROR_FIELD, e.getMessage() );
        }

        if ( errors.isEmpty() ) {
            result = "Picture uploaded with success.";
        } else {
            result = "Failure while uploading the picture.";
        }

        return fileName;
    }

    /**
     * Extracts file name from HTTP header content-disposition
     */
    private String extractFileName( Part part ) {
        String contentDisp = part.getHeader( "content-disposition" );
        String[] items = contentDisp.split( ";" );
        for ( String s : items ) {
            if ( s.trim().startsWith( "filename" ) ) {
                return s.substring( s.indexOf( "=" ) + 2, s.length() - 1 );
            }
        }
        return "";
    }

    /**
     * creates the save directory if it does not exists.
     */
    private String createDirectory( String appPath ) {
        String savePath = appPath + File.separator + SAVE_DIR;

        File fileSaveDir = new File( savePath );
        if ( !fileSaveDir.exists() ) {
            fileSaveDir.mkdir();
        }

        return savePath;
    }

    /**
     * Write the file in the defined directory.
     * 
     * @param part
     * @param fileName
     * @param savePath
     * @return String
     * @throws IOException
     */
    private String writeFile( Part part, String savePath ) throws IOException {
        String fileName = extractFileName( part );
        // refines the fileName in case it is an absolute path
        fileName = new File( fileName ).getName();
        part.write( savePath + File.separator + fileName );

        return fileName;
    }

}
