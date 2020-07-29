package tecolotl.profesor.entidad;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(schema = "profesor", name = "notificacion")
@NamedQueries(value = {
        @NamedQuery(
                name = "NotificacionEntidad.buscaProfesor",
                query = "SELECT n FROM NotificacionEntidad n JOIN FETCH n.notificacionEntidadPK.alumnoEntidad a JOIN FETCH a.nivelLenguajeEntidad " +
                        "WHERE n.notificacionEntidadPK.profesorEntidad.id = :idProfesor"),
        @NamedQuery(
                name = "NotificacionEntidad.leidos",
                query = "UPDATE NotificacionEntidad AS n SET n.leido = TRUE WHERE n.notificacionEntidadPK = :notificacionPK"
        ),
        @NamedQuery(
                name = "NotificacionEntidad.elimina",
                query = "DELETE FROM NotificacionEntidad n WHERE n.notificacionEntidadPK = :notificacionPK"
        ),
        @NamedQuery(
                name = "NotificacionEntidad.cuentaProfesor",
                query = "SELECT count (n) FROM NotificacionEntidad n WHERE n.notificacionEntidadPK.profesorEntidad.id = :idProfesor"
        )
})
public class NotificacionEntidad {

    private NotificacionEntidadPK notificacionEntidadPK;
    private Date momento;
    private boolean leido;

    @EmbeddedId
    public NotificacionEntidadPK getNotificacionEntidadPK() {
        return notificacionEntidadPK;
    }

    public void setNotificacionEntidadPK(NotificacionEntidadPK notificacionEntidadPK) {
        this.notificacionEntidadPK = notificacionEntidadPK;
    }

    @Basic
    @Column(name = "momento", insertable = false)
    @Temporal(TemporalType.DATE)
    public Date getMomento() {
        return momento;
    }

    public void setMomento(Date momento) {
        this.momento = momento;
    }

    @Basic
    @Column(name = "leido", insertable = false)
    public boolean isLeido() {
        return leido;
    }

    public void setLeido(boolean leido) {
        this.leido = leido;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotificacionEntidad that = (NotificacionEntidad) o;
        return notificacionEntidadPK.equals(that.notificacionEntidadPK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(notificacionEntidadPK);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("NotificacionEntidad{");
        sb.append("notificacionEntidadPK=").append(notificacionEntidadPK);
        sb.append(", momento=").append(momento);
        sb.append(", leido=").append(leido);
        sb.append('}');
        return sb.toString();
    }

}
