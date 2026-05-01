package Grafos;

class GrafoTest {
    void main() {
        /*
        Grafo grafazo = new  Grafo();
        System.out.println(grafazo);
        grafazo.addVertice("lugar","casa");
        System.out.println(grafazo);
        grafazo.addVertice("animal","perro");
        grafazo.addVertice("objeto","chancla");
        System.out.println(grafazo);
        grafazo.addVertice("animal","perro");
        grafazo.addVertice("","chancla");
        System.out.println(grafazo);

        grafazo.addArista("animal","perro","lugar", "casa", "vive en");
        System.out.println(grafazo);

         */

        //Grafo newGrafo = new Grafo();

        //newGrafo.cargarGrafoDesdeJson("c:\\tmp\\datos.json");
        //System.out.println(newGrafo);

        /*
        Grafo aunmasnuevo = new Grafo();
        aunmasnuevo.cargarGrafoDesdeJson("c:\\tmp\\ejemploplo.json");
        System.out.println(aunmasnuevo);

        System.out.println("Las conexiones con Albert Einstein son: "+ aunmasnuevo.imprimeConexiones(1));


         */
        // 1. Creamos el Grafo
        Grafo g = new Grafo();

        // 2. Añadimos algunos vértices (Simulando tu JSON)
        g.addVertice("persona", "Einstein");  // ID 1
        g.addVertice("lugar", "Ulm");          // ID 2
        g.addVertice("lugar", "Alemania");     // ID 3
        g.addVertice("premio", "Nobel");       // ID 4

        // 3. Añadimos aristas (Conexiones)
        // Einstein -> nace_en -> Ulm
        g.addArista("persona", "Einstein", "lugar", "Ulm", "nace_en");
        // Ulm -> esta_en -> Alemania
        g.addArista("lugar", "Ulm", "lugar", "Alemania", "esta_en");
        // Einstein -> gano -> Nobel
        g.addArista("persona", "Einstein", "premio", "Nobel", "gano");

        System.out.println("--- Estructura del Grafo ---");
        System.out.println(g.toString());

        // 4. Probamos el Algoritmo de Dijkstra
        // Queremos ir de Einstein a Alemania (Camino: Einstein -> Ulm -> Alemania)
        CaminoMinimo dijkstra = new CaminoMinimo(g);

        System.out.println("\n--- Buscando Camino Mínimo desde Einstein ---");
        dijkstra.ejecutarDijkstra("persona", "Einstein");

        String resultado = dijkstra.obtenerCamino("lugar", "Alemania");
        System.out.println("Resultado: " + resultado);

        // 5. Probamos un camino inexistente
        String fallido = dijkstra.obtenerCamino("premio", "Nobel");
        System.out.println("Resultado a Nobel: " + fallido);


        Grafo newGrafo = new Grafo();

        newGrafo.cargarGrafoDesdeJson("c:\\tmp\\fisico_nacimiento_einstein.json");
        System.out.println(newGrafo);

        // 3. Mostramos el grafo para verificar que se cargó bien (opcional)
        System.out.println("\n--- Estado del Grafo ---");
        System.out.println(newGrafo.toString());

        // 4. Instanciamos nuestra clase de búsqueda lógica
        BuscadorGrafo buscador = new BuscadorGrafo(newGrafo);

        // 5. Ejecutamos la consulta específica:
        // "¿Qué físico nació en la misma ciudad que Einstein?"
        System.out.println("--- Ejecutando Consulta ---");
        buscador.ejecutarConsulta();

        System.out.println("\nProceso finalizado.");


        Grafo withAntonio = new Grafo();
        withAntonio.cargarGrafoDesdeJson("c:\\tmp\\fisico_nacimiento_einstein_con_Antonio.json");

    }
}

