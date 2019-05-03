package tecolotl.alumno.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "grado_escolar", schema = "alumno")
@NamedQueries({
        @NamedQuery(name = "GradoEscolarEntidad.busca", query = "SELECT a FROM GradoEscolarEntidad a")
})
public class GradoEscolarEntidad {
    private short id;
    private String nivel;
    private short grado;

    @Id
    public short getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nivel")
    @NotNull
    @Size(min = 2, max = 20)
    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Basic
    @NotNull
    @Column(name = "grado")
    public short getGrado() {
        return grado;
    }

    public void setGrado(short grado) {
        this.grado = grado;
    }
}
