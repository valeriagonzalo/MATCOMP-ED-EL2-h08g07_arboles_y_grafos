import ListaDoblementeEnlazada.ListaDE;

public class ArbolBinarioDeBusqueda<T extends Comparable<T>> {
    protected T dato;
    protected ArbolBinarioDeBusqueda<T> izq, der;

    public ArbolBinarioDeBusqueda() {
        this.dato = null;
    }

    public void add(T nuevoDato) {
        if (this.dato == null) {
            this.dato = nuevoDato;
        } else if (nuevoDato.compareTo(this.dato) < 0) {
            if (izq == null) izq = new ArbolBinarioDeBusqueda<>();
            izq.add(nuevoDato);
        } else if (nuevoDato.compareTo(this.dato) > 0) {
            if (der == null) der = new ArbolBinarioDeBusqueda<>();
            der.add(nuevoDato);
        }
    }

    public int getGrado() {
        if (this.dato == null) return 0;
        int g = 0;
        if (izq != null) g++;
        if (der != null) g++;
        int gIzq = (izq != null) ? izq.getGrado() : 0;
        int gDer = (der != null) ? der.getGrado() : 0;
        return Math.max(g, Math.max(gIzq, gDer));
    }

    public int getAltura() {
        if (this.dato == null) return -1;
        int altIzq = (izq != null) ? izq.getAltura() : -1;
        int altDer = (der != null) ? der.getAltura() : -1;
        return 1 + Math.max(altIzq, altDer);
    }

    // --- Recorridos integrados con tu ListaDE ---
    public ListaDE<T> getListaOrdenCentral() {
        ListaDE<T> lista = new ListaDE<>();
        ayudaOrdenCentral(this, lista);
        return lista;
    }
    private void ayudaOrdenCentral(ArbolBinarioDeBusqueda<T> nodo, ListaDE<T> lista) {
        if (nodo == null || nodo.dato == null) return;
        ayudaOrdenCentral(nodo.izq, lista);
        lista.add(nodo.dato);
        ayudaOrdenCentral(nodo.der, lista);
    }

    public ListaDE<T> getListaPreOrden() {
        ListaDE<T> lista = new ListaDE<>();
        ayudaPreOrden(this, lista);
        return lista;
    }
    private void ayudaPreOrden(ArbolBinarioDeBusqueda<T> nodo, ListaDE<T> lista) {
        if (nodo == null || nodo.dato == null) return;
        lista.add(nodo.dato);
        ayudaPreOrden(nodo.izq, lista);
        ayudaPreOrden(nodo.der, lista);
    }

    public ListaDE<T> getListaPostOrden() {
        ListaDE<T> lista = new ListaDE<>();
        ayudaPostOrden(this, lista);
        return lista;
    }
    private void ayudaPostOrden(ArbolBinarioDeBusqueda<T> nodo, ListaDE<T> lista) {
        if (nodo == null || nodo.dato == null) return;
        ayudaPostOrden(nodo.izq, lista);
        ayudaPostOrden(nodo.der, lista);
        lista.add(nodo.dato);
    }

    public ListaDE<T> getCamino(T objetivo) {
        ListaDE<T> camino = new ListaDE<>();
        buscarCamino(this, objetivo, camino);
        return camino;
    }
    private boolean buscarCamino(ArbolBinarioDeBusqueda<T> nodo, T obj, ListaDE<T> camino) {
        if (nodo == null || nodo.dato == null) return false;
        camino.add(nodo.dato);
        if (nodo.dato.equals(obj)) return true;
        if (obj.compareTo(nodo.dato) < 0) return buscarCamino(nodo.izq, obj, camino);
        return buscarCamino(nodo.der, obj, camino);
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() { return izq; }
    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() { return der; }
}