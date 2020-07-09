package tecolotl.alumno.entidad.vista;

import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Immutable
@Table(schema = "alumno", name = "busca_actividades")
@NamedQuery(
        name = "BuscaActividadesVistaEntidad.buscaAlumno",
        query = "SELECT ba FROM BuscaActividadesVistaEntidad ba WHERE ba.idAlumno = :idAlumno"
)
public class BuscaActividadesVistaEntidad {

    private UUID idAlumno;
    private String idActvidad;
    private String preguntaDetonadora;
    private String tema;
    private String nivelLenguaje;
    private Short idNivelLenguaje;
    private String mapaMental;

    @Column(name = "id_alumno")
    public UUID getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(UUID idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Id
    @Column(name = "id_actividad")
    public String getIdActvidad() {
        return idActvidad;
    }

    public void setIdActvidad(String idActvidad) {
        this.idActvidad = idActvidad;
    }

    @Column(name = "pregunta_detonadora")
    public String getPreguntaDetonadora() {
        return preguntaDetonadora;
    }

    public void setPreguntaDetonadora(String preguntaDetonadora) {
        this.preguntaDetonadora = preguntaDetonadora;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    @Column(name = "nivel_lenguaje")
    public String getNivelLenguaje() {
        return nivelLenguaje;
    }

    public void setNivelLenguaje(String nivelLenguaje) {
        this.nivelLenguaje = nivelLenguaje;
    }

    @Column(name = "codigo_nivel_lenguaje")
    public Short getIdNivelLenguaje() {
        return idNivelLenguaje;
    }

    public void setIdNivelLenguaje(Short idNivelLenguaje) {
        this.idNivelLenguaje = idNivelLenguaje;
    }

    @Column(name = "mapa_mental")
    public String getMapaMental() {
        return mapaMental;
    }

    public void setMapaMental(String mapaMental) {
        this.mapaMental = mapaMental;
    }

    @Override
    public String toString() {
        return "BuscaActividadesVistaEntidad{" +
                "idAlumno=" + idAlumno +
                ", idActvidad='" + idActvidad + '\'' +
                ", preguntaDetonadora='" + preguntaDetonadora + '\'' +
                ", tema='" + tema + '\'' +
                ", nivelLenguaje='" + nivelLenguaje + '\'' +
                ", idNivelLenguaje=" + idNivelLenguaje +
                ", mapaMental='" + mapaMental + '\'' +
                '}';
    }
}
