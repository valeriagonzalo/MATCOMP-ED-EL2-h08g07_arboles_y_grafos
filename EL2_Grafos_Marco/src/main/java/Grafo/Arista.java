package main.java.Grafo;

public class Arista<T> implements Comparable<Arista<T>> {
    //Atributos:
    private String informacion;
    private Nodo<T> origen;
    private Nodo<T> destino;
    private double coste; //El coste es la "longitud" de la arista.

    //Constructor:
    public Arista(String i, Nodo<T> o, Nodo<T> d, double c){
        this.informacion = i;
        this.origen = o;
        this.destino = d;
        this.coste = c;
    }

    //Getters:
    public String getInformacion(){
        return informacion;
    }

    public Nodo<T> getOrigen(){
        return origen;
    }

    public Nodo<T> getDestino(){
        return destino;
    }

    public double getCoste(){
        return coste;
    }

    //Setters:
    public void setInformacion(String newInformacion){
        this.informacion = newInformacion;
    }

    public void setOrigen(Nodo<T> newOrigen){
        this.origen = newOrigen;
    }

    public void setDestino(Nodo<T> newDestino){
        this.destino = newDestino;
    }

    public void setCoste(double newCoste){
        this.coste = newCoste;
    }

    @Override
    public int compareTo(Arista<T> otra) { //Método necesario para poder meter aristas en la listaSE.
        return Double.compare(this.coste, otra.coste);
    }
}
