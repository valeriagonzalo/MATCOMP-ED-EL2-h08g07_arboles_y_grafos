public class Cola<T> {
    protected ElementoSE<T> cabeza, ultimo;
    protected int tamaño = 0;

    // Encolamos, añadimos al final
    public void enqueue(T dato) {
        ElementoSE<T> annadir = new ElementoSE<>(dato);
        if (ultimo == null) {
            cabeza = ultimo = annadir; // Si está vacía, la cabeza y el último son iguales
        } else {
            ultimo.siguiente = annadir; //si hay elementos, lo ponemos el siguiente al ultimo
            ultimo = annadir; //Le adjudicamos el rol de último en la cola
        }
        tamaño += 1;
    }

    // Desencolar, quitamos la cabeza (FIFO)
    public T dequeue() {
        if (cabeza == null) return null;
        T dato = cabeza.dato; //guardamos la cabeza bajo otra dirección para poder devolverla
        cabeza = cabeza.siguiente; //sustituimos la cabeza, efectivamente eliminandola de la cola

        if (cabeza == null) { //si al decir que cabeza = cabeza.siguiente da null, significa que la lista está vacía
            ultimo = null; //entonces decimos explícitamente que el final es nulo también
        }
        tamaño -= 1;
        return dato;
    }
}
