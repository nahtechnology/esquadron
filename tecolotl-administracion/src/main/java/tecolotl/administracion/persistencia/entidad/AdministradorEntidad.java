package tecolotl.administracion.persistencia.entidad;

import tecolotl.nucleo.persistencia.entidad.PersonaEntidad;

import javax.persistence.*;


@Entity
@Table(name = "admnistrador", schema = "administracion")
@NamedQueries(value = {
        @NamedQuery(
                name = "AdministradorEntidad.buscaApodo",
                query = "SELECT a FROM AdministradorEntidad a WHERE a.apodo = :apodo")
})
@AttributeOverride(name = "apodo", column = @Column(name = "apodo"))
public class AdministradorEntidad extends PersonaEntidad {

    @Id
    private Integer identificador;

    public Integer getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Integer identificador) {
        this.identificador = identificador;
    }
}
