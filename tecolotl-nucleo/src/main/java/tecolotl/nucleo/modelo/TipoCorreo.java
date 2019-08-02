package tecolotl.nucleo.modelo;

public enum TipoCorreo {

    COORDINADOR_CONFIRMACION("Bienvenido a Esquadron", "<body style=\"margin: 0; padding: 200; background: cornsilk\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\"><tr><td align=\"center\" bgcolor=\"#fff8dc\" style=\"padding: 30px 0 30px 0;\"><img src=\"http://www.e-squadron.com.mx/javax.faces.resource/esquadron.svg.xhtml?ln=img\" width=\"100px\" height=\"100px\"></td><td></td></tr><tr><td></td><td align=\"center\" bgcolor=\"#f0f8ff\" style=\"padding: 10px 0 0px 0;\"><link href=\"https://fonts.googleapis.com/css?family=Didact+Gothic&display=swap\" rel=\"stylesheet\"><h1 style=\"font-family: Didact Gothic; color: blue;\">Bienvenido a e-squadron</h1><div><link href=\"https://fonts.googleapis.com/css?family=Nunito+Sans&display=swap\" rel=\"stylesheet\"><table><tr style=\"color: saddlebrown; font-family: Nunito Sans;\"><td>Nombre: </td><td>${nombre}</td></tr><tr style=\"color: saddlebrown; font-family: Nunito Sans;\"><td>Apodo: </td><td>${apodo}</td></tr><tr style=\"color: saddlebrown; font-family: Nunito Sans;\"><td>Contraseña: </td><td>${contrasenia}</td></tr></table></div></td></tr><div><tr><td></td><td align=\"center\" style=\"padding: 20px 0px 20px 0px;\"><a href=\"http://www.e-squadron.com.mx\" style=\"color: saddlebrown; font-family: Nunito Sans;\">Para ver tu perfil haz click aquí.</a></td></tr></div></table></body>");

    private String cabecera;
    private String cuerpo;

    TipoCorreo(String cabecera, String cuerpo) {
        this.cabecera = cabecera;
        this.cuerpo = cuerpo;
    }

    public String getCabecera() {
        return cabecera;
    }

    public String getCuerpo() {
        return cuerpo;
    }
}
