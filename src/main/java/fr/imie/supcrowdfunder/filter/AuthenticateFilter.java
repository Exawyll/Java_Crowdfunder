/**
 * 
 */
package fr.imie.supcrowdfunder.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jerome
 */
@WebFilter( urlPatterns = { "/auth/*" } )
public class AuthenticateFilter implements Filter {
    public static final String ATT_SESSION_USER = "userSession";

    public void doFilter( ServletRequest req, ServletResponse resp, FilterChain fChain )
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        HttpSession session = request.getSession();

        if ( session.getAttribute( ATT_SESSION_USER ) != null ) {
            fChain.doFilter( request, response );
        } else {
            HttpServletResponse httpResp = (HttpServletResponse) resp;
            httpResp.sendRedirect( "/SupCrowdfunder/login.jsp" );
        }
    }

    public void destroy() {
    }

    public void init( FilterConfig arg0 ) throws ServletException {
    }
}
