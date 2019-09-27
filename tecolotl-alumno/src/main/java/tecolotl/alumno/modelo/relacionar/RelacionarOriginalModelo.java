package tecolotl.alumno.modelo.relacionar;

import tecolotl.alumno.entidad.relacionar.RelacionarEntidad;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class RelacionarOriginalModelo {

    private String codigo;
    private String palabra;
    private byte[] imagen;

    public RelacionarOriginalModelo() {
    }

    public RelacionarOriginalModelo(String codigo) {
        this.codigo = codigo;
    }

    public RelacionarOriginalModelo(RelacionarEntidad relacionarEntidad){
        this.codigo = relacionarEntidad.getCodigo();
        this.palabra = relacionarEntidad.getPalabra();
        this.imagen = relacionarEntidad.getImagen();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getPalabra() {
        return palabra;
    }

    public void setPalabra(String palabra) {
        this.palabra = palabra;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        RelacionarOriginalModelo that = (RelacionarOriginalModelo) o;
        return codigo.equals(that.codigo) &&
                palabra.equals(that.palabra) &&
                Arrays.equals(imagen, that.imagen);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(codigo, palabra);
        result = 31 * result + Arrays.hashCode(imagen);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", RelacionarOriginalModelo.class.getSimpleName() + "[", "]")
                .add("codigo='" + codigo + "'")
                .add("palabra='" + palabra + "'")
                .add("imagen=" + Arrays.toString(imagen))
                .toString();
    }
}
