package ArbolBinario;

public class Nodo<T> {
    protected T dato;
    protected Nodo<T> izq;
    protected Nodo<T> der;
    /*
    como estamos trabajando con un árbol binario
    podemos ponerle solamente dos nodos por debajo
    que serían izq y der, los cuales estarían vacíos
    a la hora de crear un nuevo nodo
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }
}
