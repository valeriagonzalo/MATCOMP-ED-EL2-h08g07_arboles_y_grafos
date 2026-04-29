public class ElementoSE<T> {    //Cualquier tipo de elemento
    T dato;
    ElementoSE<T> siguiente;

    ElementoSE(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }
}
