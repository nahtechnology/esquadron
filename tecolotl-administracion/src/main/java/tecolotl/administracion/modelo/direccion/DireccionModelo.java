package tecolotl.administracion.modelo.direccion;

import java.util.List;

public class DireccionModelo {

    private List<ColoniaModelo> coloniaModeloLista;
    private String municipio;
    private String estado;

    public List<ColoniaModelo> getColoniaModeloLista() {
        return coloniaModeloLista;
    }

    public void setColoniaModeloLista(List<ColoniaModelo> coloniaModeloLista) {
        this.coloniaModeloLista = coloniaModeloLista;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DireccionModelo{");
        sb.append("coloniaModeloCollection=").append(coloniaModeloLista);
        sb.append(", municipio='").append(municipio).append('\'');
        sb.append(", estado='").append(estado).append('\'');
        sb.append('}');
        return sb.toString();
    }

}