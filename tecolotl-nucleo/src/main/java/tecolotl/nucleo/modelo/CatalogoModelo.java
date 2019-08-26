package tecolotl.nucleo.modelo;

import tecolotl.nucleo.persistencia.entidad.CatalagoEntidad;
import tecolotl.nucleo.validacion.CatalogoLlavePrimariaValidacion;
import tecolotl.nucleo.validacion.CatalogoNuevoValidacion;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class CatalogoModelo implements Comparable<CatalogoModelo>{

    @NotNull(groups = {CatalogoLlavePrimariaValidacion.class})
    @Min(value = -1, groups = {CatalogoLlavePrimariaValidacion.class})
    private Short clave;

    @NotNull(groups = {CatalogoNuevoValidacion.class})
    @Size(min = 4, max = 30, groups = {CatalogoNuevoValidacion.class})
    private String valor;

    public CatalogoModelo() {
    }

    public CatalogoModelo(CatalagoEntidad catalagoEntidad) {
        clave = catalagoEntidad.getClave();
        valor = catalagoEntidad.getValor();
    }

    public CatalogoModelo(Short clave) {
        this.clave = clave;
    }

    public CatalogoModelo(Short clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public Short getClave() {
        return clave;
    }

    public void setClave(Short clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public int compareTo(CatalogoModelo o) {
        return clave.compareTo(o.clave);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CatalogoModelo{");
        sb.append("clave=").append(clave);
        sb.append(", valor='").append(valor).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CatalogoModelo that = (CatalogoModelo) o;
        return clave.equals(that.clave);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clave);
    }

}
