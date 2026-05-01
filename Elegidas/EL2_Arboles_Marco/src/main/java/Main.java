import Grafo.Grafo;
import Grafo.Camino;
import Grafo.Nodo;
import Grafo.Arista;
import ListasPracticaAnterior.Interfaces.Iterador;
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


        ArbolBinarioDeBusquedaEnteros arbolSuma = new ArbolBinarioDeBusquedaEnteros();

        // 1. Insertar números de 0 a 128 en orden
        for (int i = 0; i <= 128; i++) {
            arbolSuma.add(i);
        }

        // 2. Calcular suma con getSuma()
        int suma = arbolSuma.getSuma();
        System.out.println("Suma total del árbol = " + suma);

        // 3. Verificar suma con los 3 recorridos
        int sumaIn = sumarLista(arbolSuma.getListaOrdenCentral());
        int sumaPre = sumarLista(arbolSuma.getListaPreOrden());
        int sumaPost = sumarLista(arbolSuma.getListaPostOrden());

        System.out.println("Suma en inOrden = " + sumaIn);
        System.out.println("Suma en preOrden = " + sumaPre);
        System.out.println("Suma en postOrden = " + sumaPost);

        // 4. Verificar suma con subárboles
        int sumaIz = arbolSuma.getSubArbolIzquierda().getSuma();
        int sumaDr = arbolSuma.getSubArbolDerecha().getSuma();

        System.out.println("Suma izquierda + derecha = " + (sumaIz + sumaDr));

        // 5. Altura del árbol
        System.out.println("Altura del árbol = " + arbolSuma.getAltura());

        // 6. Camino hasta 110
        ListaSE<Integer> caminoArbol = arbolSuma.getCamino(110);
        System.out.println("Longitud del camino = " + (caminoArbol.getSize() - 1));
        System.out.print("Camino hasta 110 = ");
        imprimirLista(caminoArbol);
        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL =====");



        System.out.println("\n===== FIN DEL PROBADOR DEL ÁRBOL =====");

        // Crear grafo
        Grafo<String> g = new Grafo<String>();

        // Añadir nodos y aristas (como triples RDF)
        g.addTriple("A", "rel", "B");
        g.addTriple("A", "rel", "C");
        g.addTriple("B", "rel", "D");
        g.addTriple("C", "rel", "D");

        // Mostrar grafo (si tienes método)
        // g.mostrarGrafo();

        // Ejecutar Dijkstra desde A hasta D
        Camino<String> camino = g.dijkstra("A", "D");

        if (camino == null) {
            System.out.println("No existe camino entre A y D");
        } else {
            System.out.println("Camino mínimo desde A hasta D:");
            System.out.println(camino);
        }

        System.out.println("\n===== FIN DEL PROBADOR DE CAMINOS =====");

        // 1. Cargar JSON
        RDFData data = GsonUtilEjemploModificado.cargarObjetoDesdeArchivo(
                "data/grafo_nobel.json", //Para probar el grafo disjunto cambiar "conectado" por "disjunto".
                RDFData.class
        );

        if (data == null) {
            System.out.println("ERROR: No se pudo cargar el JSON");
            return;
        }

        // 2. Crear grafo
        Grafo<String> gr = new Grafo<String>();

        // 3. Añadir tripletas
        for (RDFData.Tripleta t : data.tripletas) {
            gr.addTriple(t.s, t.p, t.o);
        }

        // 4. Mostrar nodos cargados (para depurar)
        System.out.println("Nodos cargados:");
        Iterador<Nodo<String>> it = gr.getNodos().getIterador();
        while (it.hasNext()) {
            System.out.println(" - " + it.next().getDato());
        }

        // 5. Probar camino mínimo
        Camino<String> c = gr.dijkstra("persona:A", "persona:C");

        System.out.println("Resultado Dijkstra:");
        System.out.println(c);


        System.out.println("\n===== FIN DEL PROBADOR GRAFOS CONECTADOS/DISJUNTOS =====");

        //Que físico vive en la mismca ciudad que Einstein:
        //0. Si se quiere probar esto, hay que cambiar rutaArchivo del ejercicio anterior a grafo_nobel.
        // 1. Obtener el nodo Einstein
        Nodo<String> einstein = gr.buscarNodo("persona:Einstein");
        if (einstein == null) {
            System.out.println("Einstein no está en el grafo");
            return;
        }

        // 2. Obtener su lugar de nacimiento
        String ciudadEinstein = null;

        Iterador<Arista<String>> it1 = einstein.getAristasSalida().getIterador();
        while (it1.hasNext()) {
            Arista<String> a = it1.next();
            if (a.getInformacion().equals("nace_en")) {
                ciudadEinstein = a.getDestino().getDato();
                break;
            }
        }

        if (ciudadEinstein == null) {
            System.out.println("Einstein no tiene lugar de nacimiento en el grafo");
            return;
        }

        System.out.println("Einstein nació en: " + ciudadEinstein);

        // 3. Buscar personas que nacieron en esa misma ciudad
        System.out.println("Físicos nacidos en la misma ciudad:");

        Iterador<Nodo<String>> it2 = gr.getNodos().getIterador();
        while (it2.hasNext()) {
            Nodo<String> persona = it2.next();

            // Solo personas
            if (!persona.getDato().startsWith("persona:")) continue;

            boolean naceEnMismaCiudad = false;
            boolean esFisico = false;

            Iterador<Arista<String>> it3 = persona.getAristasSalida().getIterador();
            while (it3.hasNext()) {
                Arista<String> a = it3.next();

                if (a.getInformacion().equals("nace_en") &&
                        a.getDestino().getDato().equals(ciudadEinstein)) {
                    naceEnMismaCiudad = true;
                }

                if (a.getInformacion().equals("profesion") &&
                        a.getDestino().getDato().equals("profesion:fisico")) {
                    esFisico = true;
                }
            }

            if (naceEnMismaCiudad && esFisico && !persona.equals(einstein)) {
                System.out.println(" - " + persona.getDato());
            }
        }

        System.out.println("\n===== FIN LUGARES DE NACIMIENTO EINSTEIN =====");

        gr.addTriple("persona:Antonio", "nace_en", "lugar:Villarrubia_de_los_Caballeros");
        //gr.addTriple("persona:Antonio","premio:Nobel_Fisica", "2024"); Si añadimos esta linea, Antonio también aparece en la lista.

        System.out.println("Lugares de nacimiento de los premios Nobel:");

        Iterador<Nodo<String>> it4 = gr.getNodos().getIterador();
        while (it4.hasNext()) {
            Nodo<String> persona = it4.next();

            if (!persona.getDato().startsWith("persona:")) continue;

            boolean esNobel = false;
            String lugarNacimiento = null;

            Iterador<Arista<String>> itA = persona.getAristasSalida().getIterador();
            while (itA.hasNext()) {
                Arista<String> a = itA.next();

                // Detectar si es Nobel
                if (a.getInformacion().startsWith("premio:"))
                    esNobel = true;

                // Detectar lugar de nacimiento
                if (a.getInformacion().equals("nace_en"))
                    lugarNacimiento = a.getDestino().getDato();
            }

            if (esNobel && lugarNacimiento != null) {
                System.out.println(" - " + persona.getDato() + " nació en " + lugarNacimiento);
            }
        }
        System.out.println("\n===== FIN PREMIOS NOBEL =====");
        }


    // Función auxiliar para sumar una ListaSE
    private static int sumarLista(ListaSE<Integer> lista) {
        int suma = 0;
        for (int i = 0; i < lista.getSize(); i++) {
            suma += lista.get(i);
        }
        return suma;
    }

    // Función auxiliar para imprimir una ListaSE
    private static void imprimirLista(ListaSE<Integer> lista) {
        for (int i = 0; i < lista.getSize(); i++) {
            System.out.print(lista.get(i));
            if (i < lista.getSize() - 1) System.out.print(" -> ");
        }
        System.out.println();
    }
    }
