package ArbolBinario;

import ListaSimple.ListaSimple;

class ArbolBinarioDeBusquedaTest {
    public static void main(String[] args) {
        ArbolBinarioDeBusqueda arbol = new ArbolBinarioDeBusqueda();

        for (int i = 0; i <= 128; i++) {
            arbol.add(i);
        }
        int sumaTotal = arbol.getSuma();
        System.out.println("La suma calculada por el árbol es: " + sumaTotal);

        //CAMBIAR ESTO DE LIST POR UNA LISTA SIMPLEEE
        // 2. Obtenemos las tres listas usando tus métodos
        ListaSimple<Integer> listaPre = arbol.getListaPreOrden();
        ListaSimple<Integer> listaPost = arbol.getListaPostOrden();
        ListaSimple<Integer> listaCentral = arbol.getListaOrdenCentral();

        // 3. Calculamos las sumas recorriendo las listas
        int sumaPre = 0;
        int sumaPost = 0;
        int sumaCentral = 0;
        for (int i = 0; i < listaPre.getSize(); i++) {
            sumaPre += listaPre.get(i);
        }
        for (int i = 0; i < listaPost.getSize(); i++) {
            sumaPost += listaPost.get(i);
        }

        for (int i = 0; i < listaCentral.getSize(); i++) {
            sumaCentral += listaCentral.get(i);
        }

        // 4. Verificación por consola
        System.out.println("Resultados de la verificación:");
        System.out.println("-------------------------------");
        System.out.println("Suma desde Lista PreOrden:      " + sumaPre);
        System.out.println("Suma desde Lista PostOrden:     " + sumaPost);
        System.out.println("Suma desde Lista Orden Central: " + sumaCentral);

        // 2. Obtener los subárboles
        // Nota: Como insertaste en orden (0, 1, 2...), el subárbol izquierdo será null
        // y el derecho contendrá casi todos los elementos.
        ArbolBinarioDeBusqueda subIzq = (ArbolBinarioDeBusqueda) arbol.getSubArbolIzquierda();
        ArbolBinarioDeBusqueda subDer = (ArbolBinarioDeBusqueda) arbol.getSubArbolDerecha();

        // 3. Sumar las partes (con cuidado de los nulos)
        int sumaPartes = 0;  //Así se puede hacer un poco sucio porque la raíz es 0

        if (subIzq != null) sumaPartes += subIzq.getSuma();
        if (subDer != null) sumaPartes += subDer.getSuma();

        // 4. Comparación
        System.out.println("Suma Total Directa: " + sumaTotal);
        System.out.println("Suma (Raíz + SubIzq + SubDer): " + sumaPartes);

        if (sumaTotal == sumaPartes) {
            System.out.println("\nVERIFICACIÓN PASADA: La estructura jerárquica es correcta.");
        }

        // 5. ¿Cuál es la altura del árbol?
        //MIRAR PQ TENGO Q PONER UN 2 INICIAL Q NO ENTIENDO ????
        System.out.println("La altura del árbol es: "+arbol.getAltura());

        // 6. ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
        ListaSimple <Integer> camino110 = arbol.getCamino(110);
        System.out.println("El camino para llegar al valor 110 es: "+ camino110);
        System.out.println("La longitud del camino es: " + camino110.getSize());

    }
}