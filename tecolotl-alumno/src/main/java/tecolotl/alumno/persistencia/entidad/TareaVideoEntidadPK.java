package tecolotl.alumno.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

public class TareaVideoEntidadPK implements Serializable {
    private int idAlumno;
    private short idNivelLenguajeVideo;
    private short idLenguajeVideo;
    private int idVideo;

    @Column(name = "id_alumno")
    @Id
    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    @Column(name = "id_nivel_lenguaje_video")
    @Id
    public short getIdNivelLenguajeVideo() {
        return idNivelLenguajeVideo;
    }

    public void setIdNivelLenguajeVideo(short idNivelLenguajeVideo) {
        this.idNivelLenguajeVideo = idNivelLenguajeVideo;
    }

    @Column(name = "id_lenguaje_video")
    @Id
    public short getIdLenguajeVideo() {
        return idLenguajeVideo;
    }

    public void setIdLenguajeVideo(short idLenguajeVideo) {
        this.idLenguajeVideo = idLenguajeVideo;
    }

    @Column(name = "id_video")
    @Id
    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TareaVideoEntidadPK that = (TareaVideoEntidadPK) o;

        if (idAlumno != that.idAlumno) return false;
        if (idNivelLenguajeVideo != that.idNivelLenguajeVideo) return false;
        if (idLenguajeVideo != that.idLenguajeVideo) return false;
        if (idVideo != that.idVideo) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAlumno;
        result = 31 * result + (int) idNivelLenguajeVideo;
        result = 31 * result + (int) idLenguajeVideo;
        result = 31 * result + idVideo;
        return result;
    }
}
