package main.java.Grafo;

import ListasPracticaAnterior.ListaSimplementeEnlazada.ListaSE;

public class Camino <T> {
    //Atributos:
    private ListaSE<Nodo<T>> camino;
    private double coste;

    //Constructor:
    public Camino(ListaSE<Nodo<T>> cm, double ct){
        this.camino = cm;
        this.coste = ct;
    }

    //Getters:
    public ListaSE<Nodo<T>> getCamino(){
        return camino;
    }

    public double getCoste(){
        return coste;
    }

    //Setters:
    public void setCamino(ListaSE<Nodo<T>> newCamino){
        this.camino = newCamino;
    }

    public void setCoste(double newCoste){
        this.coste = newCoste;
    }

    @Override
    public String toString() {
        return "Camino: " + camino.toString() + " | Coste: " + coste;
    }
}