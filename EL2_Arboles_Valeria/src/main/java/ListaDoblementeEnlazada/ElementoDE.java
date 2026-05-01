package ListaDoblementeEnlazada;

public class ElementoDE <T> {
    //Variables:
    private T dato;
    private ElementoDE<T> siguiente;
    private ElementoDE<T> anterior;

    //Constructores:
    public ElementoDE(T d){
        this.dato = d;
        this.siguiente = null;
        this.anterior = null;
    }

    public ElementoDE(T d, ElementoDE<T> s, ElementoDE<T> a){
        this.dato = d;
        this.siguiente = s;
        this.anterior = a;
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public ElementoDE<T> getSiguiente(){
        return siguiente;
    }

    public ElementoDE<T> getAnterior(){
        return anterior;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setSiguiente(ElementoDE<T> newSiguiente){
        this.siguiente = newSiguiente;
    }

    public void setAnterior(ElementoDE<T> newAnterior){
        this.anterior = newAnterior;
    }
}

