package main.java.ListasPracticaAnterior.ListaDoblementeEnlazada;

public class LDEOrdenada <T extends Comparable<T>> extends ListaDE<T> {

    //Solamente necesitamos cambiar el método add, para que meta los elementos de manera ordenada.
    @Override
    public void add(T dato) {
        ElementoDE<T> nuevo = new ElementoDE<>(dato);
        if (isEmpty()) { //Si la lista está vacía.
            primero = ultimo = nuevo;
            tamaño++; //Incrementamos el tamaño de la lista en 1.
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 1.
        } else if (dato.compareTo(primero.getDato()) < 0) { //Si el dato va antes que el primero.
            nuevo.setSiguiente(primero);
            primero.setAnterior(nuevo);
            primero = nuevo;
            tamaño++;
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 2.
        } else if (dato.compareTo(ultimo.getDato()) > 0) { //Si el dato va despues del último.
            ultimo.setSiguiente(nuevo);
            nuevo.setAnterior(ultimo);
            ultimo = nuevo;
            tamaño++;
            return; //Asi evitamos que el código siga ejecutandose si se diera el caso 3.
        } else { //Si el dato está entre el primero y el último.
            ElementoDE<T> puntero = primero.getSiguiente();
            while (puntero != null && dato.compareTo(puntero.getDato()) > 0) { //Mientras que el puntero no recorra toda la lista y el dato sea mayor que el puntero.
                puntero = puntero.getSiguiente();
            }
            nuevo.setSiguiente(puntero);
            nuevo.setAnterior(puntero.getAnterior());
            puntero.getAnterior().setSiguiente(nuevo);
            puntero.setAnterior(nuevo);
        }
        tamaño++; //Incrementamos el tamaño de la lista en 1.
    }
}
