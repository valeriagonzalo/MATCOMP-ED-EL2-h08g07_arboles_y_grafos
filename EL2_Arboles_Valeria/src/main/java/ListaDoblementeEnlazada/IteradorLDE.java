package ListaDoblementeEnlazada;

import Interfaces.Iterador;

public class IteradorLDE<T> implements Iterador<T> {
    //Variables:
    private ElementoDE<T> actual;

    //Constructor:
    public IteradorLDE(ElementoDE<T> a){
        this.actual = a;
    }

    //Métodos:
    @Override
    public boolean hasNext(){
        return actual != null; //Si actual es null False, de lo contrario True.
    }

    @Override
    public T next(){
        if (hasNext() == false){
            return null;
        }
        T dato = actual.getDato();
        actual = actual.getSiguiente();
        return dato;
    }
}

