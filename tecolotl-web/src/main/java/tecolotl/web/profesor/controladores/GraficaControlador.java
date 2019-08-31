package tecolotl.web.profesor.controladores;

import tecolotl.web.profesor.ColorModelo;
import tecolotl.web.profesor.GraficaModelo;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class GraficaControlador {
    private List<GraficaModelo> graficaModelos;
    private List<ColorModelo> colorModelos;

    @PostConstruct
    public void init(){
        colorModelos = new ArrayList<>();
        graficaModelos = new ArrayList<>();

        graficaModelos.add(new GraficaModelo("A",1,"rgba(51,153,255,0.6)",8.5f,10.0f,9.3f,8.6f,7.8f));
        graficaModelos.add(new GraficaModelo("B",1,"rgba(36,113,163,0.6)",7.5f,9.0f,8.7f,7.7f,10.0f));
        graficaModelos.add(new GraficaModelo("C",1,"rgba(20,143,119,0.6)",9.5f,8.0f,2.3f,8.6f,9.8f));
        graficaModelos.add(new GraficaModelo("D",1,"rgba(183,149,11,0.6)",10.0f,9.0f,8.3f,9.6f,8.8f));

        colorModelos.add(new ColorModelo("rgba(51,153,255,0.6)"));
        colorModelos.add(new ColorModelo("rgba(36,113,163,0.6)"));
        colorModelos.add(new ColorModelo("rgba(20,143,119,0.6)"));
        colorModelos.add(new ColorModelo("rgba(183,149,11,0.6)"));
    }

    public List<GraficaModelo> getGraficaModelos() {
        return graficaModelos;
    }

    public void setGraficaModelos(List<GraficaModelo> graficaModelos) {
        this.graficaModelos = graficaModelos;
    }

    public List<ColorModelo> getColorModelos() {
        return colorModelos;
    }

    public void setColorModelos(List<ColorModelo> colorModelos) {
        this.colorModelos = colorModelos;
    }
}
