package tecolotl.alumno.modelo.escribir;

import tecolotl.alumno.entidad.escribir.EscribirEntidad;
import tecolotl.alumno.entidad.escribir.TareaEscribirActividadEntidad;
import tecolotl.alumno.validacion.escribir.EscribirRespuestaValidacion;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;
import java.util.StringJoiner;

public class EscribirModelo extends EscribirBaseModelo {

    private String textoRespuesta;
    private Date fechaRespuesta;

    public EscribirModelo() {
    }

    public EscribirModelo(Integer id) {
        super(id);
    }

    public EscribirModelo(EscribirEntidad escribirEntidad) {
        super(escribirEntidad);
    }

    public EscribirModelo(TareaEscribirActividadEntidad tareaEscribirActividadEntidad) {
        this(tareaEscribirActividadEntidad.getTareaEscribirActividadEntidadPK().getEscribirActividadEntidad().getEscribirEntidad());
        this.textoRespuesta = tareaEscribirActividadEntidad.getTextRespuesta();
        this.fechaRespuesta = tareaEscribirActividadEntidad.getHoraRespuesta();
    }

    @NotNull(groups = {EscribirRespuestaValidacion.class})
    @Size(max = 300, groups = {EscribirRespuestaValidacion.class})
    public String getTextoRespuesta() {
        return textoRespuesta;
    }

    public void setTextoRespuesta(String textoRespuesta) {
        this.textoRespuesta = textoRespuesta;
    }

    public Date getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(Date fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EscribirModelo that = (EscribirModelo) o;
        return textoRespuesta.equals(that.textoRespuesta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(textoRespuesta);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", EscribirModelo.class.getSimpleName() + "[", "]")
                .add("textoRespuesta='" + textoRespuesta + "'")
                .add("fechaRespuesta=" + fechaRespuesta)
                .toString();
    }
}
