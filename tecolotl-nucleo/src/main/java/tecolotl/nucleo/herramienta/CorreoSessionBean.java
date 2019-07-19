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
import java.io.*;
import java.util.StringJoiner;
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

    @Resource(mappedName = "java:jboss/mail/MyOtherMail")
    private Session session;

    private Logger logger;

    private String destinatario;

    private String remitente;

    private String asunto;

    private String mensaje;

    //TODO Verificar como se insertarán las url's de los archivos.
    //private String urlArchivo = "C:/Users/jesus/Documents/TecoltlProy/Reglamento.pdf";
    private String urlArchivo = "C:/Users/jesus/IdeaProjects/tecolotl/tecolotl-administracion/src/main/resources/ConfirmacionNuevoCoordinador.html";


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
    //String nombre, String apellidoP, String apellidoM, String apodo, String correo, String contrasenia
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

    public File getCuerpoMail(String direccionCuerpo){
        try {
            InputStream inputStream = ClassLoader.getSystemClassLoader().getResourceAsStream(direccionCuerpo);
            if(inputStream == null){
                return null;
            }
            File archivoTmp = File.createTempFile(String.valueOf(inputStream.hashCode()),".tmp");
            archivoTmp.deleteOnExit();
            try(FileOutputStream out = new FileOutputStream(archivoTmp)){
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1){
                    out.write(buffer, 0, bytesRead);
                }
            }
            return archivoTmp;
        }catch(IOException e){
            logger.fine("Hubo un error en la lectura del archivo: ".concat(e.toString()));
            return null;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", CorreoSessionBean.class.getSimpleName() + "[", "]")
            .add("session=" + session)
            .add("destinatario='" + destinatario + "'")
            .add("remitente='" + remitente + "'")
            .add("asunto='" + asunto + "'")
            .add("mensaje='" + mensaje + "'")
            .add("urlArchivo='" + urlArchivo + "'")
            .toString();
    }
}
