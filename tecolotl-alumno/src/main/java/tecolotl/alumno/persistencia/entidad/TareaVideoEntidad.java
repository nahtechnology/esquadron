package tecolotl.alumno.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.VideoEntidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tarea_video", schema = "alumno", catalog = "afjbrhmu")
//@IdClass(TareaVideoEntidadPK.class)
public class TareaVideoEntidad implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AlumnoEntidad alumno;
    private VideoEntidad video;
    private Date asignacion;

    @Id
    @ManyToOne
    @JoinColumn(name = "id_alumno", referencedColumnName = "id")
    public AlumnoEntidad getAlumno() {
        return alumno;
    }

    public void setAlumno(AlumnoEntidad alumno) {
        this.alumno = alumno;
    }

    @Id
    @ManyToOne
    @JoinColumns(value = {
            @JoinColumn(name = "id_video", referencedColumnName = "id"),
            @JoinColumn(name = "id_lenguaje_video", referencedColumnName = "id_lenguaje"),
            @JoinColumn(name = "id_nivel_lenguaje_video", referencedColumnName = "id_nivel_lenguaje")
    })
    public VideoEntidad getVideo() {
        return video;
    }

    public void setVideo(VideoEntidad video) {
        this.video = video;
    }

    public Date getAsignacion() {
        return asignacion;
    }

    public void setAsignacion(Date asignacion) {
        this.asignacion = asignacion;
    }
}
