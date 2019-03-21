package tecolotl.administracion.modelo.direccion;

import tecolotl.administracion.modelo.direccion.ColoniaModelo;

import java.util.Collection;

public class DireccionModelo {

    private Collection<ColoniaModelo> coloniaModeloCollection;
    private String municipio;
    private String estado;

    public Collection<ColoniaModelo> getColoniaModeloCollection() {
        return coloniaModeloCollection;
    }

    public void setColoniaModeloCollection(Collection<ColoniaModelo> coloniaModeloCollection) {
        this.coloniaModeloCollection = coloniaModeloCollection;
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
        sb.append("coloniaModeloCollection=").append(coloniaModeloCollection);
        sb.append(", municipio='").append(municipio).append('\'');
        sb.append(", estado='").append(estado).append('\'');
        sb.append('}');
        return sb.toString();
    }
}