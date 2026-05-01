import Grafo.Grafo;
import Grafo.Camino;
import Grafo.Nodo;
import Grafo.Arista;
import ListasPracticaAnterior.Interfaces.Iterador;
import ListasPracticaAnterior.ListaSimplementeEnlazada.ElementoSE;
import ListasPracticaAnterior.ListaSimplementeEnlazada.ListaSE;
import Árbol.ArbolBinarioDeBusqueda;
import ListasPracticaAnterior.GsonUtilEjemploModificado;
import Grafo.RDFData;
import ListasPracticaAnterior.*;
import Árbol.ArbolBinarioDeBusquedaEnteros;

import static ListasPracticaAnterior.GsonUtilEjemploModificado.cargarObjetoDesdeArchivo;

public class Main {
    public static void main(String[] args) {

        ArbolBinarioDeBusqueda<Integer> arbol = new ArbolBinarioDeBusqueda<>();

        System.out.println("===== INSERTANDO DATOS =====");
        int[] datos = {5, 2, 8, 1, 3, 7, 9, 6};
        for (int d : datos) {
            arbol.add(d);
            System.out.println("Insertado: " + d);
        }

        System.out.println("\n===== RECORRIDOS =====");
        System.out.println("Orden Central (inOrden): " + arbol.getListaOrdenCentral());
        System.out.println("PreOrden: " + arbol.getListaPreOrden());
        System.out.println("PostOrden: " + arbol.getListaPostOrden());

        System.out.println("\n===== ALTURA Y GRADO =====");
        System.out.println("Altura del árbol: " + arbol.getAltura());
        System.out.println("Grado del árbol: " + arbol.getGrado());

        System.out.println("\n===== SUBÁRBOLES =====");
        System.out.println("Subárbol izquierdo (inOrden): " +
                arbol.getSubArbolIzquierda().getListaOrdenCentral());
        System.out.println("Subárbol derecho (inOrden): " +
                arbol.getSubArbolDerecha().getListaOrdenCentral());

        System.out.println("\n===== DATOS POR NIVEL =====");
        for (int nivel = 1; nivel <= arbol.getAltura(); nivel++) {
            System.out.println("Nivel " + nivel + ": " + arbol.getListaDatosNivel(nivel));
        }

        System.out.println("\n===== CAMINO HASTA UN DATO =====");
        int buscado = 6;
        System.out.println("Camino hasta " + buscado + ": " + arbol.getCamino(buscado));

        System.out.println("\n===== PROPIEDADES DEL ÁRBOL =====");
        System.out.println("¿Es homogéneo?: " + arbol.isArbolHomogeneo());
        System.out.println("¿Es completo?: " + arbol.isArbolCompleto());
        System.out.println("¿Es casi completo?: " + arbol.isArbolCasiCompleto());

        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL =====");