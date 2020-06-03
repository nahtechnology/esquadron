package tecolotl.alumno.modelo;

import tecolotl.alumno.entidad.AlumnoControlSesionEntidad;

import java.util.Date;

public class AlumnoControlSesionModelo {

    private Date momento;
    private String tipo;

    public AlumnoControlSesionModelo() {
    }

    public AlumnoControlSesionModelo(AlumnoControlSesionEntidad entidad) {
        momento = entidad.getTiempo();
        tipo = entidad.getTipoRegistroEntidad().getValor();
    }

    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
