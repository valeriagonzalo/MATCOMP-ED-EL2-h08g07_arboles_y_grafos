package ArbolBinario;

import ListaSimple.ListaSimple;

import java.util.Random;

class ArbolBinarioDeBusquedaOtroTest {
    public static void main(String[] args) {
        ArbolBinarioDeBusqueda arbol = new ArbolBinarioDeBusqueda();

        Random rnd = new Random();

        int cantidadObjetivo = 129; // Queremos los números del 1 al 128
        int contador = 0;
        int sumaPartes = 0;

        while (contador < cantidadObjetivo) {
            int num = rnd.nextInt(129); //genero un número aleatorio
            if (contador == 0) {
                sumaPartes = num;
            }
            if (!arbol.contiene(num)) { //Si NO está en el árbol
                arbol.add(num); //lo añado a mi árbol
                contador += 1; //aumento el contador en 1, y así hasta llegar a los 129 números queridos
            }
        }
        int sumaTotal = arbol.getSuma();
        System.out.println("La suma calculada por el árbol es: " + sumaTotal);

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

        ArbolBinarioDeBusqueda subIzq = (ArbolBinarioDeBusqueda) arbol.getSubArbolIzquierda();
        ArbolBinarioDeBusqueda subDer = (ArbolBinarioDeBusqueda) arbol.getSubArbolDerecha();


        if (subIzq != null) sumaPartes += subIzq.getSuma();
        if (subDer != null) sumaPartes += subDer.getSuma();

        // 4. Comparación
        System.out.println("Suma Total Directa: " + sumaTotal);
        System.out.println("Suma (Raíz + SubIzq + SubDer): " + sumaPartes);

        // 5. ¿Cuál es la altura del árbol?
        System.out.println("La altura del árbol es: "+arbol.getAltura());
        //La altura varía dependiendo de en qué orden se introducen los números

        // 6. ¿Cuál es el camino para llegar al valor 110? ¿Cuál es su longitud de camino?
        ListaSimple <Integer> camino110 = arbol.getCamino(110);
        System.out.println("El camino para llegar al valor 110 es: "+ camino110);
        System.out.println("La longitud del camino es: " + camino110.getSize());
        // Ejecuciones adicionales para asegurarnos de que todos los métodos funcionan
        System.out.println("Los datos en el nivel 3 son: "+ arbol.getListaDatosNivel(3));
    }
}