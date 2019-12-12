package tecolotl.nucleo.herramienta;

public enum AlmacenamientoEnum {

    CARTAS, DESCARGABLES, GUIA, IMAGENES_GLOSARIO, PLATILLA;

    public static void main(String[] args) {
        System.out.println(AlmacenamientoEnum.CARTAS.name().toLowerCase());
    }

}
