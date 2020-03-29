package tecolotl.nucleo.herramienta;

//TODO Dudas existenciales con Enum
public enum CorreoEnum {
    ASUNTO("Cuenta de sQuadron creada exitosamente"),
    REMITENTE("Jesus Reyes"),
    MENSAJE_BIENVENIDA("Bienvenido a Squadreon vaquero espacial"),
    MENSAJE("Del equipo de sQuadron te damos la bienvenida a la plataforma");

    private final String bienvenido;

    CorreoEnum(String bienvenida){
        this.bienvenido = bienvenida;
    }

    private String getDato(){
        return bienvenido;
    }
}
