package tecolotl.web.modelo.administracion;

import tecolotl.administracion.dto.MotivoBloqueoDto;

import javax.faces.model.CollectionDataModel;
import java.util.List;

public class MotivoBloqueoModeloDatos extends CollectionDataModel<MotivoBloqueoDto> {

    private int rowIndex = -1;
    private int allRowsCount;
    private int pageSize;
    private List<MotivoBloqueoDto> motivoBloqueoDtoLista;

    public MotivoBloqueoModeloDatos() {
        super();
    }

    public MotivoBloqueoModeloDatos(List<MotivoBloqueoDto> motivoBloqueoDtoLista, int allRowsCount, int pageSize) {
        this.motivoBloqueoDtoLista = motivoBloqueoDtoLista;
        this.allRowsCount = allRowsCount;
        this.pageSize = pageSize;
    }

    @Override
    public boolean isRowAvailable() {
        if (motivoBloqueoDtoLista == null) {
            return false;
        }
        int indiceFila = getRowIndex();
        return indiceFila >= 0 && indiceFila < motivoBloqueoDtoLista.size();
    }

    @Override
    public int getRowCount() {
        return allRowsCount;
    }

    @Override
    public MotivoBloqueoDto getRowData() {
        if (motivoBloqueoDtoLista == null) {
            return null;
        } else {
            if (!isRowAvailable()) {
                throw new IllegalArgumentException();
            } else {
                return motivoBloqueoDtoLista.get(getRowIndex());
            }
        }
    }

    @Override
    public int getRowIndex() {
        return rowIndex % pageSize;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public Object getWrappedData() {
        return motivoBloqueoDtoLista;
    }

    @Override
    public void setWrappedData(Object data) {
        motivoBloqueoDtoLista = (List<MotivoBloqueoDto>)data;
    }

}
