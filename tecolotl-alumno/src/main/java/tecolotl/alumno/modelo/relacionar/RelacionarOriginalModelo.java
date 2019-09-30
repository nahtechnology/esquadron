package tecolotl.alumno.modelo.relacionar;

import tecolotl.alumno.entidad.relacionar.RelacionarEntidad;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class RelacionarOriginalModelo {

    private String codigo;
    private byte[] imagen;

    public RelacionarOriginalModelo() {
    }

    public RelacionarOriginalModelo(String codigo) {
        this.codigo = codigo;
    }

    public RelacionarOriginalModelo(RelacionarEntidad relacionarEntidad){
        this.codigo = relacionarEntidad.getCodigo();
        this.imagen = relacionarEntidad.getImagen();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RelacionarOriginalModelo that = (RelacionarOriginalModelo) o;
        return codigo.equals(that.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
