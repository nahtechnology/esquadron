package tecolotl.profesor.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TareaAlumnoEntidad {

    private int id;
    private Short calificacionMapamental;
    private Short calificacionRelacionarImagen;
    private Short calificacionGramatica;

    @Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "calificacion_mapamental")
    public Short getCalificacionMapamental() {
        return calificacionMapamental;
    }

    public void setCalificacionMapamental(Short calificacionMapamental) {
        this.calificacionMapamental = calificacionMapamental;
    }

    @Column(name = "calificacion_relacionar_imagen")
    public Short getCalificacionRelacionarImagen() {
        return calificacionRelacionarImagen;
    }

    public void setCalificacionRelacionarImagen(Short calificacionRelacionarImagen) {
        this.calificacionRelacionarImagen = calificacionRelacionarImagen;
    }

    @Column(name = "calificacion_gramatica")
    public Short getCalificacionGramatica() {
        return calificacionGramatica;
    }

    public void setCalificacionGramatica(Short calificacionGramatica) {
        this.calificacionGramatica = calificacionGramatica;
    }

}
