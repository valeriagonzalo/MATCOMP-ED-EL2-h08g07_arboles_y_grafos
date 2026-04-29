public class ListaCircular<T extends  Comparable<T>> {
    protected ElementoSE<T> ultimo;
    protected int tamanno = 0;

    public void add(T dato) {
        ElementoSE<T> annadir = new ElementoSE<>(dato);
        if (ultimo == null) {
            // Si está vacía, el elemento añadido es el último y tmb apunta a sí mismo
            ultimo = annadir;
            ultimo.siguiente = ultimo;
        } else {
            // El nuevo elemento apunta al primero (que es el siguiente del último)
            annadir.siguiente = ultimo.siguiente;
            // El antiguo último ahora tiene que apuntar al aladido
            ultimo.siguiente = annadir;
            // Y ahora añadir es el nuevo último
            ultimo = annadir;
        }
        tamanno += 1;
    }

    public T get(T dato) {
        if (ultimo == null) {
            return null; // lista vacía
        }

        ElementoSE<T> actual = ultimo.siguiente; // primer elemento

        do {
            if (actual.dato.compareTo(dato) == 0) {
                return actual.dato; // encontrado
            }
            actual = actual.siguiente;
        }while (actual != ultimo.siguiente);

        return null; // no encontrado
    }

    public T del(T dato) { //borramos un elemento de la lista
        if (ultimo == null) {
            return null; // lista vacía
        }

        ElementoSE<T> actual = ultimo.siguiente; // primero
        ElementoSE<T> anterior = ultimo;
        do {
            if (actual.dato.compareTo(dato) == 0) {
                T eliminado = actual.dato;

                if (actual == ultimo && actual.siguiente == ultimo) { //si solo hay un elemento en la lista
                    ultimo = null;
                }

                else if (actual == ultimo) { //si lo que eliminamos es el último de la lista
                    anterior.siguiente = actual.siguiente;
                    ultimo = anterior;
                }
                else { //si está en cualquier otra posición
                    anterior.siguiente = actual.siguiente;
                }

                tamanno -= 1;
                return eliminado;
            }

            anterior = actual;
            actual = actual.siguiente;

        }while (actual != ultimo.siguiente);
        return null; // no encontrado
        }
}
