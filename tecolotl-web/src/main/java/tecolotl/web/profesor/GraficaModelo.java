package tecolotl.web.profesor;

public class GraficaModelo {
    private String grupo;
    private  int grado;
    private  String color;
    private float dato1;
    private float dato2;
    private float dato3;
    private float dato4;
    private float dato5;

    public GraficaModelo(String grupo, int grado, String color, float dato1, float dato2, float dato3, float dato4, float dato5) {
        this.grupo = grupo;
        this.grado = grado;
        this.color = color;
        this.dato1 = dato1;
        this.dato2 = dato2;
        this.dato3 = dato3;
        this.dato4 = dato4;
        this.dato5 = dato5;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public int getGrado() {
        return grado;
    }

    public void setGrado(int grado) {
        this.grado = grado;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getDato1() {
        return dato1;
    }

    public void setDato1(float dato1) {
        this.dato1 = dato1;
    }

    public float getDato2() {
        return dato2;
    }

    public void setDato2(float dato2) {
        this.dato2 = dato2;
    }

    public float getDato3() {
        return dato3;
    }

    public void setDato3(float dato3) {
        this.dato3 = dato3;
    }

    public float getDato4() {
        return dato4;
    }

    public void setDato4(float dato4) {
        this.dato4 = dato4;
    }

    public float getDato5() {
        return dato5;
    }

    public void setDato5(float dato5) {
        this.dato5 = dato5;
    }
}
