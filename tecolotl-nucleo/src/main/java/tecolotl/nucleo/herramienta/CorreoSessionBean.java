package tecolotl.nucleo.herramienta;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

    //TODO Insertar el JNDI para poder hacer pruebas.
    @Resource()
    private Session session;

    private Logger logger;

    private String destinatario;

    private String remitente;

    private String asunto;

    private String mensaje;

    //TODO Verificar como se insertarán las url's de los archivos.
    private String urlArchivo = "C:/Users/jesus/Documents/TecoltlProy/Reglamento.pdf";

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getUrlArchivo() {
        return urlArchivo;
    }

    public void setUrlArchivo(String urlArchivo) {
        this.urlArchivo = urlArchivo;
    }

    /**
     * Metodo para enciar el correo
     * @throws Exception
     */
    public void enviar(){
        try{
            BodyPart texto = new MimeBodyPart();
            texto.setText(mensaje);
            BodyPart archivo = new MimeBodyPart();
            DataSource source = new FileDataSource(urlArchivo);
            archivo.setDataHandler(new DataHandler(source));
            archivo.setFileName(urlArchivo);
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(texto);
            multipart.addBodyPart(archivo);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            Address toAddress = new InternetAddress(destinatario);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(asunto);
            message.setContent(multipart);
            Transport.send(message);
        }catch(Exception e){
            logger.log(Level.SEVERE,"Error al enviar el correo: ",e);
        }
    }
}
