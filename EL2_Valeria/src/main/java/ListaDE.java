import Interfaces.Iterador;
import Interfaces.ListaInterfaz;

public class ListaDE <T extends Comparable<T>> implements ListaInterfaz<T> {

    //Variables:
    protected ElementoDE<T> primero;
    protected ElementoDE<T> ultimo;
    protected int tamaño;

    //Constructores:
    public ListaDE(){
        this.primero = null;
        this.ultimo = null;
        this.tamaño = 0;
    }

    //Métodos:
    @Override
    public void addInicio(T dato){
        ElementoDE<T> nuevo = new ElementoDE<>(dato);
        if (isEmpty()){
            primero = ultimo = nuevo; //Si la lista está vacia el primer y ultimo serán el mismo.
        }
        else{
            nuevo.setSiguiente(primero); //Conviertes el primer elemento en el siguiente.
            primero.setAnterior(nuevo); //El anterior del antiguo primero será el elemento añadir, asi convirtiendosé en el nuevo.
            primero = nuevo;
        }
        tamaño++; //Aumenta el tamaño de la lista en 1.
    }

    @Override
    //Add es añadir al final.
    public void add(T dato){
        ElementoDE<T> nuevo = new ElementoDE<>(dato);
        if (isEmpty()){
            primero = ultimo = nuevo; //Si la lista está vacia el primer y ultimo serán el mismo.
        }
        else{
            ultimo.setSiguiente((nuevo)); // El nuevo queda por detras del último elemento.
            nuevo.setAnterior(ultimo); // el último está antes del nuevo
            ultimo = nuevo;
        }
        tamaño++; //Aumenta el tamaño de la lista en 1.
    }

    @Override
    public T get(T dato){
        ElementoDE<T> puntero = primero;
        while (puntero != null){ //Mientras no llegue al final sigue buscando.
            if(puntero.getDato().compareTo(dato) == 0){
                return puntero.getDato(); //Si coincide devuelve el dato
            }
            puntero = puntero.getSiguiente();
        }
        return null;
    }

    @Override
    public T del(T dato){
        ElementoDE<T> puntero = primero;
        while (puntero != null){ //Mientras no llegue al final sigue buscando.
            if(puntero.getDato().compareTo(dato) == 0){

                if (puntero == primero) { //Si el elemento es el primero.
                    primero = puntero.getSiguiente(); //Conviertes al segundo en el primero
                    if(primero != null){
                        primero.setAnterior(null); //Convierte el elemento anterior al actual primero en un null, es decir elimina el antiguo primero.
                    }
                    else{
                        ultimo = null; //La lista quedaría vacía.
                    }
                }

                else if (puntero == ultimo){ //Si el elemento es el último.
                    ultimo = puntero.getAnterior(); //Conviertes el penúltimo en el último.
                    ultimo.setSiguiente((null)); //Convierte el elemento siguiente al actual último en un null, es decir elimina el antiguo último.
                }

                else{
                    puntero.getAnterior().setSiguiente(puntero.getSiguiente()); //El siguiente del anterior al que borramos es ahora el que estaba delante de él
                    puntero.getSiguiente().setAnterior(puntero.getAnterior()); //El anterior del siguiente al que borramos es ahora el que estaba detrás de él
                }
                tamaño--; //Disminuye el tamaño de la lista en 1.
                return puntero.getDato();
            }
            puntero = puntero.getSiguiente();
        }
        return  null;
    }

    @Override
    public void clear(){ // Vaciamos la lista entera.
        primero = null;
        ultimo = null;
        tamaño = 0;
    }

    @Override
    public boolean isEmpty(){
        return primero == null; //Si no hay primer elemento True, de lo contrario False.
    }

    @Override
    public int getSize(){
        return tamaño; //Devuelve el tamaño de la lista.
    }

    @Override
    public Iterador<T> getIterador(){
        return new IteradorLDE<T>(this.primero);
    }
}
