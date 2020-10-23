package tecolotl.profesor.modelo;

import tecolotl.profesor.entidad.ProfesorControlSesionEntidad;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ProfesorSesionControlModelo {

    private Date momento;
    private String tipo;

    public ProfesorSesionControlModelo() {
    }

    public ProfesorSesionControlModelo(ProfesorControlSesionEntidad entidad) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(entidad.getTiempo());
        calendar.add(Calendar.HOUR,-6);
        momento = calendar.getTime();
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProfesorSesionControlModelo{");
        sb.append("momento=").append(momento);
        sb.append(", tipo='").append(tipo).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
