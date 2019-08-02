package tecolotl.nucleo.herramienta;

import tecolotl.nucleo.modelo.CorreoConfirmacionModelo;
import tecolotl.nucleo.modelo.TipoCorreo;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Herramienta para enviar correos.
 * @version 1.0
 * @author Jesus Caballero Luna
 */

@Named
@SessionScoped
public class CorreoSessionBean implements Serializable {

    private Logger logger = Logger.getLogger(getClass().getName());

    @Resource(mappedName = "java:jboss/mail/MyOtherMail")
    private Session session;

    /**
     * Envio de correo electrónico
     */
    //String nombre, String apellidoP, String apellidoM, String apodo, String correo, String contrasenia
    public void enviar(CorreoConfirmacionModelo correoConfirmacionModelo, TipoCorreo tipoCorreo){
        try{
            BodyPart texto = new MimeBodyPart();
            texto.setContent(tipoCorreo.getCuerpo(), "text/html");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("prueba@correo.com"));
            Address toAddress = new InternetAddress(correoConfirmacionModelo.getCorreo());
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(tipoCorreo.getCabecera());
            message.setContent(multipart);
            Transport.send(message);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error al enviar el correo: ".concat(correoConfirmacionModelo.toString()),e);
        }
    }

}
