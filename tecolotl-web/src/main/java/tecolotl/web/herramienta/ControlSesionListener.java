package tecolotl.web.herramienta;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.security.Principal;
import java.util.Enumeration;
import java.util.logging.Logger;

//@WebListener
public class ControlSesionListener implements HttpSessionListener {

    @Inject
    private Logger logger;

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        logger.info("sesion iniciada:" + httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        HttpSession httpSession = httpSessionEvent.getSession();
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            logger.info(attributeNames.nextElement());
        }
    }

}
