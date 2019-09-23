package tecolotl.alumno.cache;


import tecolotl.alumno.modelo.glosario.GlosarioModelo;

import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import java.util.List;
import java.util.TreeSet;

@Singleton
public class ImagenesGlosario {
    private TreeSet<List<GlosarioModelo>> glosarioModeloTreeSet = new TreeSet<>();

    @Lock(value = LockType.READ)
    public TreeSet<List<GlosarioModelo>> getGlosarioModeloTreeSet() {
        return glosarioModeloTreeSet;
    }

    @Lock(value = LockType.WRITE)
    public void setGlosarioModeloTreeSet(TreeSet<List<GlosarioModelo>> glosarioModeloTreeSet) {
        this.glosarioModeloTreeSet = glosarioModeloTreeSet;
    }
}
