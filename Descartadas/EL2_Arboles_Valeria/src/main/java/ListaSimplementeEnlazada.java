public class ListaSimplementeEnlazada<T extends Comparable<T>> {
    protected ElementoSE<T> primero;
    protected int tamaño = 0;

    public void add(T dato) {
        ElementoSE<T> nuevo = new ElementoSE<>(dato);
        if (primero == null) {  //El primero empieza sien
            primero = nuevo;
        } else {
            ElementoSE<T> aux = primero;
            while (aux.siguiente != null) {
                aux = aux.siguiente;
            }
            aux.siguiente = nuevo;
        }
        tamaño++;
    }

    public T del(T dato) {
        ElementoSE<T> act = primero, ant = null;
        while (act != null) {
            if (act.dato.compareTo(dato) == 0) {
                if (ant == null) primero = act.siguiente;
                else ant.siguiente = act.siguiente;
                tamaño--;
                return act.dato;
            }
            ant = act;
            act = act.siguiente;
        }
        return null;
    }
}