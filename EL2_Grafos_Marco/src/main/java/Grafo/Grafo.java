package main.java.Grafo;

import ListasPracticaAnterior.Cola.Cola;
import ListasPracticaAnterior.ListaSimplementeEnlazada.IteradorLSE;
import ListasPracticaAnterior.ListaSimplementeEnlazada.ListaSE;
import ListasPracticaAnterior.Interfaces.Iterador;
import Grafo.Nodo;
import Grafo.Arista;

public class Grafo<T> {
    //Atributos:
    private ListaSE<Nodo<T>> nodos;
    private ListaSE<Arista<T>> aristas;

    //Constructores:
    public Grafo() {
        this.nodos = new ListaSE<>();
        this.aristas = new ListaSE<>();
    }

    public Grafo(ListaSE<Nodo<T>> n, ListaSE<Arista<T>> a) {
        this.nodos = n;
        this.aristas = a;
    }

    //Getters:
    public ListaSE<Nodo<T>> getNodos() {
        return this.nodos;
    }

    public ListaSE<Arista<T>> getAristas() {
        return this.aristas;
    }

    //Métodos:
    public void addNodo(Nodo<T> nodo) {
        this.nodos.add(nodo); //Añadimos el nodo a la lista de nodos.
    }

    public void addArista(Arista<T> arista) {
        this.aristas.add(arista); //Añadimos la arista a la lista de aristas
        arista.getOrigen().addAristaSalida(arista); //Añadimos a la lista de aristas de salida.
        arista.getDestino().addAristaEntrada(arista); //Añadimos a la lista de aristas de entrada.
    }

    public Nodo<T> buscarNodo(T dato) {
        Iterador<Nodo<T>> iterador = nodos.getIterador(); //Utilizamos un iterador para recorrer la lista de nodos.

        while (iterador.hasNext()) { //Mientras haya elementos en la lista continuamos.
            Nodo<T> actual = iterador.next(); //Avanzamos en la lista.
            if (actual.getDato().equals(dato)) {
                return actual; //Si coincide el dato con el que buscamos lo hemos encontrado y paramos.
            }
        }

        return null; //Si no encontramos el dato buscado en la lista no devolvemos nada.
    }

    public Nodo<T> getOrCreateNodo(T dato) { //Método que nos permite buscar o crear nodos.
        Nodo<T> nodoExistente = buscarNodo(dato); //Buscamos si el dato se corresponde con algún nodo de nuestro grafo.
        if (nodoExistente != null) {
            return nodoExistente; //Si el dato se corresponde a un nodo, lo devolvemos
        } else { //Si no se encuentra, lo añadimos al grafo y lo devolvemos.
            Nodo<T> nuevo = new Nodo<>(dato);
            addNodo(nuevo);
            return nuevo;
        }
    }

    public void addTriple(T sujeto, String predicado, T objeto) {
        /*
        Sujeto --> NodoOrigen.
        Predicado --> DatosArista.
        Objeto --> NodoDestino.
         */

        //Buscamos o creamos los nodos origen y destino:
        Nodo<T> nodoOrigen = getOrCreateNodo(sujeto);
        Nodo<T> nodoDestino = getOrCreateNodo(objeto);

        //Creamos la arista que une varios vértices y la añadimos al grafo:
        Arista<T> arista1 = new Arista<T>(predicado, nodoOrigen, nodoDestino, 1.0);
        addArista(arista1);;
        Arista<T> arista2 = new Arista<T>(predicado, nodoDestino, nodoOrigen, 1.0);
        addArista(arista2);

    }

    //Dijkstra (Caminos minimos):
    private class Registro<T> implements Comparable<Registro<T>> { //Clase auxiliar para poder utilizarla en el algoritmo de Dijkstra.
        Nodo<T> nodo;
        double distancia;
        Nodo<T> padre;

        public Registro(Nodo<T> n, double d, Nodo<T> p) {
            this.nodo = n;
            this.distancia = d;
            this.padre = p;
        }

        @Override
        public int compareTo(Registro<T> otro) {
            return Double.compare(this.distancia, otro.distancia);
        }
    }

    public Camino<T> dijkstra(T origenDato, T destinoDato) {

        Nodo<T> origen = buscarNodo(origenDato);
        Nodo<T> destino = buscarNodo(destinoDato);

        if (origen == null || destino == null) return null;

        // 1. Inicialización
        ListaSE<Registro<T>> tabla = dijkstra_init(origen);

        // 2. Lista de no visitados
        ListaSE<Nodo<T>> noVisitados = new ListaSE<>();
        Iterador<Nodo<T>> it = nodos.getIterador();
        while (it.hasNext()) noVisitados.add(it.next());

        // 3. Cálculo
        dijkstra_calcula(tabla, noVisitados);

        // 4. Reconstrucción
        return reconstruirCamino(origen, destino, tabla);
    }

    private Registro<T> buscarRegistro(ListaSE<Registro<T>> tabla, Nodo<T> nodo) {
        Iterador<Registro<T>> it = tabla.getIterador();
        while (it.hasNext()) {
            Registro<T> r = it.next();
            if (r.nodo == nodo) return r;
        }
        return null;
    }


    private ListaSE<Registro<T>> dijkstra_init(Nodo<T> origen) {

        ListaSE<Registro<T>> tabla = new ListaSE<>();

        Iterador<Nodo<T>> it = nodos.getIterador();
        while (it.hasNext()) {
            Nodo<T> n = it.next();

            double dist = (n == origen) ? 0.0 : Double.MAX_VALUE;
            Nodo<T> padre = null;

            tabla.add(new Registro<T>(n, dist, padre));
        }

        return tabla;
    }

    private Nodo<T> extraerMinimo(ListaSE<Registro<T>> tabla, ListaSE<Nodo<T>> noVisitados) {

        Nodo<T> mejor = null;
        double mejorDist = Double.MAX_VALUE;

        Iterador<Nodo<T>> it = noVisitados.getIterador();
        while (it.hasNext()) {
            Nodo<T> n = it.next();
            Registro<T> r = buscarRegistro(tabla, n);

            if (r != null && r.distancia < mejorDist) {
                mejorDist = r.distancia;
                mejor = n;
            }
        }

        // Si no hay ninguno alcanzable, devolvemos null
        if (mejor == null) {
            return null;
        }

        noVisitados.del(mejor);
        return mejor;
    }

    private void dijkstra_calcula(ListaSE<Registro<T>> tabla, ListaSE<Nodo<T>> noVisitados) {

        while (!noVisitados.isEmpty()) {

            Nodo<T> u = extraerMinimo(tabla, noVisitados);
            if (u == null) return; // No quedan nodos alcanzables

            Registro<T> regU = buscarRegistro(tabla, u);

            Iterador<Arista<T>> it = u.getAristasSalida().getIterador();

            while (it.hasNext()) {
                Arista<T> arista = it.next();
                Nodo<T> v = arista.getDestino();

                // ¿v sigue sin visitar?
                if (noVisitados.get(v) != null) {

                    Registro<T> regV = buscarRegistro(tabla, v);

                    double nuevaDist = regU.distancia + arista.getCoste();

                    if (nuevaDist < regV.distancia) {
                        regV.distancia = nuevaDist;
                        regV.padre = u;
                    }
                }
            }
        }
    }

    private Camino<T> reconstruirCamino(Nodo<T> origen, Nodo<T> destino,
                                        ListaSE<Registro<T>> tabla) {

        ListaSE<Nodo<T>> camino = new ListaSE<>();

        Registro<T> reg = buscarRegistro(tabla, destino);

        if (reg.distancia == Double.MAX_VALUE) return null;

        Nodo<T> actual = destino;

        while (actual != null) {
            camino.addInicio(actual);
            actual = buscarRegistro(tabla, actual).padre;
        }

        return new Camino<T>(camino, reg.distancia);
    }















}

