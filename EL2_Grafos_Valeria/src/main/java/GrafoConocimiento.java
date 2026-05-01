import ListaDoblementeEnlazada.ListaDE;
import Interfaces.Iterador;

public class GrafoConocimiento {

    // --- ADAPTACIÓN A LAS ESTRUCTURAS PROPORCIONADAS ---
    // Como tu ListaDE requiere que los objetos implementen Comparable, 
    // adaptamos nuestras clases internas para que funcionen con tus listas.

    public static class Tripleta implements Comparable<Tripleta> {
        public String s, p, o;
        public Tripleta(String s, String p, String o) {
            this.s = s; this.p = p; this.o = o;
        }
        @Override
        public int compareTo(Tripleta otra) {
            // Unimos los strings para poder compararlas de forma unívoca en tu ListaDE
            String esta = this.s + this.p + this.o;
            String otraT = otra.s + otra.p + otra.o;
            return esta.compareTo(otraT);
        }
    }

    public static class Arista implements Comparable<Arista> {
        public String destino, relacion;
        public boolean inversa;
        public Arista(String destino, String relacion, boolean inversa) {
            this.destino = destino; this.relacion = relacion; this.inversa = inversa;
        }
        @Override
        public int compareTo(Arista otra) {
            return this.destino.compareTo(otra.destino);
        }
    }

    public static class NodoAdyacencia implements Comparable<NodoAdyacencia> {
        public String nodo;
        public ListaDE<Arista> aristas;
        public NodoAdyacencia(String nodo) {
            this.nodo = nodo;
            this.aristas = new ListaDE<>();
        }
        @Override
        public int compareTo(NodoAdyacencia otro) {
            return this.nodo.compareTo(otro.nodo);
        }
    }

    // Usaremos tu propia Cola para el BFS del Camino Mínimo
    public static class EstadoCamino {
        public String nodoActual;
        public String caminoHastaAqui;
        public EstadoCamino(String nodoActual, String caminoHastaAqui) {
            this.nodoActual = nodoActual;
            this.caminoHastaAqui = caminoHastaAqui;
        }
    }

    // --- ESTRUCTURAS DEL GRAFO (Basadas en tu ListaDE) ---
    private ListaDE<Tripleta> tripletas = new ListaDE<>();
    private ListaDE<String> nodosUnicos = new ListaDE<>();
    private ListaDE<NodoAdyacencia> adj = new ListaDE<>();

    // --- MÉTODOS DEL GRAFO ---

    private NodoAdyacencia getAdyacencia(String nodo) {
        NodoAdyacencia busqueda = new NodoAdyacencia(nodo);
        NodoAdyacencia encontrado = adj.get(busqueda); // Tu método get
        if (encontrado == null) {
            encontrado = new NodoAdyacencia(nodo);
            adj.add(encontrado); // Tu método add
        }
        return encontrado;
    }

    public void anadirTripleta(String s, String p, String o) {
        tripletas.add(new Tripleta(s, p, o));
        
        if (nodosUnicos.get(s) == null) nodosUnicos.add(s);
        if (nodosUnicos.get(o) == null) nodosUnicos.add(o);

        // El grafo es bidireccional para poder rastrear caminos
        getAdyacencia(s).aristas.add(new Arista(o, p, false));
        getAdyacencia(o).aristas.add(new Arista(s, p, true));
    }

    // --- RESPUESTAS A LAS CONSULTAS DE LA PRÁCTICA ---

    // 1. Camino mínimo entre A y B (usando tu Cola)
    public String caminoMinimo(String inicio, String fin) {
        if (nodosUnicos.get(inicio) == null || nodosUnicos.get(fin) == null) return null;

        Cola<EstadoCamino> cola = new Cola<>();
        ListaDE<String> visitados = new ListaDE<>();

        cola.enqueue(new EstadoCamino(inicio, inicio)); // Tu método enqueue
        visitados.add(inicio);

        while (true) {
            EstadoCamino actual = cola.dequeue(); // Tu método dequeue
            if (actual == null) break;

            if (actual.nodoActual.compareTo(fin) == 0) {
                return actual.caminoHastaAqui;
            }

            NodoAdyacencia ady = getAdyacencia(actual.nodoActual);
            Iterador<Arista> it = ady.aristas.getIterador(); // Tu iterador
            
            while (it.hasNext()) {
                Arista arista = it.next();
                if (visitados.get(arista.destino) == null) {
                    visitados.add(arista.destino);
                    String direccion = arista.inversa ? "[inv:" + arista.relacion + "]" : "[" + arista.relacion + "]";
                    cola.enqueue(new EstadoCamino(arista.destino, actual.caminoHastaAqui + " -> " + direccion + " -> " + arista.destino));
                }
            }
        }
        return null;
    }

    // 2. Comprobar si el grafo es disjunto (Usando tu Cola y tu ListaDE)
    public boolean esDisjunto() {
        if (nodosUnicos.getSize() == 0) return false;

        Iterador<String> itNodos = nodosUnicos.getIterador();
        if (!itNodos.hasNext()) return false;
        
        String nodoInicial = itNodos.next();

        ListaDE<String> visitados = new ListaDE<>();
        Cola<String> cola = new Cola<>();

        cola.enqueue(nodoInicial);
        visitados.add(nodoInicial);

        while (true) {
            String actual = cola.dequeue();
            if (actual == null) break;

            NodoAdyacencia ady = getAdyacencia(actual);
            Iterador<Arista> itAristas = ady.aristas.getIterador();
            
            while (itAristas.hasNext()) {
                Arista arista = itAristas.next();
                if (visitados.get(arista.destino) == null) {
                    visitados.add(arista.destino);
                    cola.enqueue(arista.destino);
                }
            }
        }
        // Si no visitamos todos los nodos, hay islas
        return visitados.getSize() < nodosUnicos.getSize();
    }

    // 3. Físico famoso nacido en la misma ciudad que Einstein
    public void buscarFisicoCiudadEinstein() {
        String ciudadEinstein = "";
        
        Iterador<Tripleta> it = tripletas.getIterador();
        while (it.hasNext()) {
            Tripleta t = it.next();
            if (t.s.compareTo("persona:Albert Einstein") == 0 && t.p.compareTo("nace_en") == 0) {
                ciudadEinstein = t.o;
                break;
            }
        }

        it = tripletas.getIterador(); // Reiniciar iterador
        while (it.hasNext()) {
            Tripleta t = it.next();
            if (t.p.compareTo("nace_en") == 0 && t.o.compareTo(ciudadEinstein) == 0 && t.s.compareTo("persona:Albert Einstein") != 0) {
                System.out.println("Físico encontrado: " + t.s);
            }
        }
    }

    // 4. Lugares de nacimiento de los premios Nobel
    public void lugaresNacimientoNobel() {
        ListaDE<String> personasConPremio = new ListaDE<>();

        // Paso A: Identificar quién tiene premio
        Iterador<Tripleta> it = tripletas.getIterador();
        while (it.hasNext()) {
            Tripleta t = it.next();
            if (t.p.startsWith("premio:")) {
                if (personasConPremio.get(t.s) == null) {
                    personasConPremio.add(t.s);
                }
            }
        }

        // Paso B: Dónde nacieron
        it = tripletas.getIterador();
        while (it.hasNext()) {
            Tripleta t = it.next();
            if (t.p.compareTo("nace_en") == 0) {
                if (personasConPremio.get(t.s) != null) {
                    System.out.println(" - " + t.s + " nació en " + t.o);
                }
            }
        }
    }

    // --- MÉTODO MAIN ---
    public static void main(String[] args) {
        GrafoConocimiento grafo = new GrafoConocimiento();

        grafo.anadirTripleta("persona:Albert Einstein", "nace_en", "lugar:Ulm");
        grafo.anadirTripleta("persona:Albert Einstein", "premio:Nobel_Fisica", "1921");
        grafo.anadirTripleta("persona:Fisico Ficticio", "nace_en", "lugar:Ulm");
        grafo.anadirTripleta("persona:Fisico Ficticio", "premio:Nobel_Fisica", "1999");
        grafo.anadirTripleta("persona:Antonio", "nace_en", "lugar:Villarrubia de los Caballeros");

        System.out.println("¿Es disjunto?: " + grafo.esDisjunto());
        
        System.out.println("\nCamino de Einstein a Ulm:");
        System.out.println(grafo.caminoMinimo("persona:Albert Einstein", "lugar:Ulm"));

        System.out.println("\nFísico en la ciudad de Einstein:");
        grafo.buscarFisicoCiudadEinstein();

        System.out.println("\nLugares de nacimiento de premios Nobel (Antonio no sale):");
        grafo.lugaresNacimientoNobel();
    }
}