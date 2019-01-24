package tecolotl.web.control;

import tecolotl.web.modelo.administracion.PaginacionModeloDato;

import javax.faces.component.html.HtmlDataTable;
import java.util.List;

public abstract class PaginadorControlador<T> {

    private HtmlDataTable htmlDataTable;
    private PaginacionModeloDato<T> paginacionModeloDato;
    private int filasEnPagina;
    private int totalFilas = 0;
    private boolean maximo;

    public void vistaAnterior() {
        if (maximo) {
            htmlDataTable.setFirst(0);
        } else {
            htmlDataTable.setFirst(htmlDataTable.getFirst() - htmlDataTable.getRows());
        }
        cargaDatos(htmlDataTable.getFirst());
    }

    public void vistaSiguiente() {
        if (maximo) {
            int totalRows = htmlDataTable.getRowCount();
            int displayRows = htmlDataTable.getRows();
            int full = totalRows / displayRows;
            int modulo = htmlDataTable.getRowCount() % htmlDataTable.getRows();
            if (modulo <= displayRows && modulo > 0) {
                htmlDataTable.setFirst(full * displayRows);
            } else {
                htmlDataTable.setFirst(modulo + (full - 1) * displayRows);
            }
        } else {
            htmlDataTable.setFirst(htmlDataTable.getFirst() + htmlDataTable.getRows());
        }
        cargaDatos(htmlDataTable.getFirst());
    }

    public void cargaDatos(int primero) {
        paginacionModeloDato = new PaginacionModeloDato(getDatos().subList(primero, primero + filasEnPagina > getDatos().size() ? getDatos().size() : primero + filasEnPagina), totalFilas, filasEnPagina);
    }

    protected abstract List<T> getDatos();

    public HtmlDataTable getHtmlDataTable() {
        return htmlDataTable;
    }

    public void setHtmlDataTable(HtmlDataTable htmlDataTable) {
        this.htmlDataTable = htmlDataTable;
    }

    public PaginacionModeloDato<T> getPaginacionModeloDato() {
        return paginacionModeloDato;
    }

    public void setPaginacionModeloDato(PaginacionModeloDato<T> paginacionModeloDato) {
        this.paginacionModeloDato = paginacionModeloDato;
    }

    public int getFilasEnPagina() {
        return filasEnPagina;
    }

    public void setFilasEnPagina(int filasEnPagina) {
        this.filasEnPagina = filasEnPagina;
    }

    public int getTotalFilas() {
        return totalFilas;
    }

    public void setTotalFilas(int totalFilas) {
        this.totalFilas = totalFilas;
    }

    public boolean isMaximo() {
        return maximo;
    }

    public void setMaximo(boolean maximo) {
        this.maximo = maximo;
    }
}
