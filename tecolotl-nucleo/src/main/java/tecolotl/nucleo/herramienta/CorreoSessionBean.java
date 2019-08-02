package tecolotl.nucleo.herramienta;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.*;
import java.net.URL;
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
    private String urlArchivo = "D:/Proyectos/Tecolotl/tecolotl/tecolotl-administracion/src/main/resources/ConfirmacionNuevoCoordinador.html";


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
            texto.setContent(mensaje, "text/html");
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

    //Cuando se resuelva el problema de NullPointerException este es el códifo para leer el recurso y la modificación para su uso según sea el caso,
   /* public String getCuerpoMail(Class coordinador){
        try {
            Class clase = coordinador;
            System.out.println(coordinador);
            InputStream inputStream = clase.getClassLoader().getResourceAsStream("ConfirmacionNuevoCoordinador.html");
            System.out.println(inputStream);
            String cuerpo = this.readFromInputStream(inputStream);
            inputStream.close();
            return cuerpo;
        }catch(Exception io){
            logger.log(Level.SEVERE, "Ocurrió un error al obtener el archivo: ", io);
            return null;
        }
    }
    private String readFromInputStream(InputStream inputStream){
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            String aux;
            while ((line = br.readLine()) != null) {
                if(line.matches("(.*)NombreCompleto(.*)")){
                    aux = line.replaceFirst("NombreCompleto","Jesus Reyes Reyes");
                    resultStringBuilder.append(aux).append("\n");
                }else if(line.matches("(.*)ApodoA(.*)")){
                    aux = line.replaceFirst("ApodoA","Juanito");
                    resultStringBuilder.append(aux).append("\n");
                }else if(line.matches("(.*)correo@correo(.*)")){
                    aux = line.replaceFirst("correo@correo","solucionesT67@gmail.com");
                    resultStringBuilder.append(aux).append("\n");
                }else if(line.matches("(.*)contrasenia(.*)")){
                    aux = line.replaceFirst("contrasenia","123456");
                    resultStringBuilder.append(aux).append("\n");
                }else {
                    resultStringBuilder.append(line).append("\n");
                }
            }
        }catch(IOException io){
            logger.log(Level.SEVERE, "Ocurrio un error al leer el archivo: ",io);
        }
        return resultStringBuilder.toString();
    }*/

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
