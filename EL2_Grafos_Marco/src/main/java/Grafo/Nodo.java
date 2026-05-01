package main.java.Grafo;

import ListasPracticaAnterior.ListaSimplementeEnlazada.ListaSE;

public class Nodo<T> implements Comparable<Nodo<T>>{
    //Atributos:
    private T dato;
    private ListaSE<Arista<T>> aristasEntrada;
    private ListaSE<Arista<T>> aristasSalida;

    //Constructor
    public Nodo(T d){
        this.dato = d;
        this.aristasEntrada = new ListaSE<>();
        this.aristasSalida = new ListaSE<>();
    }

    //Getters:
    public T getDato(){
        return dato;
    }

    public ListaSE<Arista<T>> getAristasEntrada(){
        return aristasEntrada;
    }

    public ListaSE<Arista<T>> getAristasSalida(){
        return aristasSalida;
    }

    //Setters:
    public void setDato(T newDato){
        this.dato = newDato;
    }

    public void setAristasEntrada(ListaSE<Arista<T>> newAristasEntrada){
        this.aristasEntrada = newAristasEntrada;
    }

    public void setAristasSalida(ListaSE<Arista<T>> newAristasSalida){
        this.aristasSalida = newAristasSalida;
    }

    //ToString:
    @Override
    public String toString() {
        return dato.toString();
    }

    //Otros métodos:
    public void addAristaEntrada(Arista<T> arista){
        this.aristasEntrada.add(arista);
    }

    public void addAristaSalida(Arista<T> arista){
        this.aristasSalida.add(arista);
    }

    @Override
    public int compareTo(Nodo<T> otro) { //Método necesario para poder meter nodos en la listaSE.
        return 0;
    }
}
