package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class VideoEntidadPK implements Serializable {

    private int id;
    private LenguajeEntidad IdLenguaje;
    private NivelLenguajeEntidad IdNivelLenguaje;

    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @ManyToOne
    @JoinColumn(name = "id_lenguaje")
    public LenguajeEntidad getIdLenguaje() {
        return IdLenguaje;
    }

    public void setIdLenguaje(LenguajeEntidad idLenguaje) {
        IdLenguaje = idLenguaje;
    }

    @ManyToOne
    @JoinColumn(name = "id_nivel_lenguaje")
    public NivelLenguajeEntidad getIdNivelLenguaje() {
        return IdNivelLenguaje;
    }

    public void setIdNivelLenguaje(NivelLenguajeEntidad idNivelLenguaje) {
        IdNivelLenguaje = idNivelLenguaje;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoEntidadPK that = (VideoEntidadPK) o;
        return id == that.id &&
                IdLenguaje.equals(that.IdLenguaje) &&
                IdNivelLenguaje.equals(that.IdNivelLenguaje);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, IdLenguaje, IdNivelLenguaje);
    }
}
