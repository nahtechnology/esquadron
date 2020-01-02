package tecolotl.nucleo.persistencia.entidad;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public class CatalagoEntidad {

    private Short clave;
    private String valor;

    public CatalagoEntidad() {
    }

    public CatalagoEntidad(Short clave) {
        this.clave = clave;
    }

    @Id
    @Column(name = "clave")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "generador_defecto")
    public Short getClave() {
        return clave;
    }

    public void setClave(Short clave) {
        this.clave = clave;
    }

    @Basic
    @Column(name = "valor", unique = true)
    @NotNull
    @Size(min = 2, max = 30)
    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}
