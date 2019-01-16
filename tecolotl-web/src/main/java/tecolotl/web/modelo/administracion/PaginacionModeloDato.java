package tecolotl.web.modelo.administracion;

import javax.faces.model.CollectionDataModel;
import java.util.List;

public class PaginacionModeloDato<T> extends CollectionDataModel<T> {

    private int indiceFila = -1;
    private int totalFilas;
    private int tamanioPagina;
    private List<T> datosLista;

    public PaginacionModeloDato() {
        super();
    }

    public PaginacionModeloDato(List<T> datosLista, int totalFilas, int tamanioPagina) {
        this.datosLista = datosLista;
        this.totalFilas = totalFilas;
        this.tamanioPagina = tamanioPagina;
    }

    @Override
    public boolean isRowAvailable() {
        int indiceFila = getRowIndex();
        return datosLista == null ? false : (indiceFila >= 0 && indiceFila < datosLista.size());
    }

    @Override
    public int getRowCount() {
        return totalFilas;
    }

    @Override
    public T getRowData() {
        if (datosLista == null) {
            return null;
        } else {
            if (!isRowAvailable()) {
                throw new IllegalArgumentException();
            } else {
                return datosLista.get(getRowIndex());
            }
        }
    }

    @Override
    public int getRowIndex() {
        return indiceFila % tamanioPagina;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        indiceFila = rowIndex;
    }

    @Override
    public Object getWrappedData() {
        return datosLista;
    }

    @Override
    public void setWrappedData(Object data) {
        datosLista = (List<T>)data;
    }
}
