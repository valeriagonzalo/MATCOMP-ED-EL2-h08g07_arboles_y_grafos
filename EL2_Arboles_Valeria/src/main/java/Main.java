import ListaDoblementeEnlazada.LDEOrdenada;
import ListaDoblementeEnlazada.ListaDE;
import Pila.Pila;

    public class Main {

        public static void main(String[] args) {
            System.out.println("=========================================");
            System.out.println(" INICIANDO DEMOSTRACIÓN DE ESTRUCTURAS ");
            System.out.println("=========================================\n");

            // 1. Creación de datos de prueba (Payloads)
            Payload color1 = new Payload("Rojo", "FF0000");
            Payload color2 = new Payload("Azul", "0000FF");
            Payload color3 = new Payload("Verde", "00FF00");
            Payload color4 = new Payload("Amarillo", "FFFF00");

            // Objeto de búsqueda (nuestro compareTo solo evalúa el nombreColor)
            Payload buscarAzul = new Payload("Azul", "");
            Payload buscarAmarillo = new Payload("Amarillo", "");

            // ---------------------------------------------------------
            // DEMOSTRACIÓN: Lista Simplemente Enlazada
            // ---------------------------------------------------------
            System.out.println("--- 1. Lista Simplemente Enlazada ---");
            ListaSimplementeEnlazada<Payload> lse = new ListaSimplementeEnlazada<>();
            lse.add(color1);
            lse.add(color2);
            lse.add(color3);
            System.out.println("Se han añadido Rojo, Azul y Verde.");

            Payload borradoLSE = lse.del(buscarAzul);
            System.out.println("Elemento borrado mediante del(): " + borradoLSE);
            System.out.println();


            // ---------------------------------------------------------
            // DEMOSTRACIÓN: Lista Circular
            // ---------------------------------------------------------
            System.out.println("--- 2. Lista Circular ---");
            ListaCircular<Payload> listaCircular = new ListaCircular<>();
            listaCircular.add(color1);
            listaCircular.add(color2);
            listaCircular.add(color3);

            System.out.println("Se han añadido Rojo, Azul y Verde.");
            System.out.println("Buscando Azul con get(): " + listaCircular.get(buscarAzul));
            System.out.println("Borrando Verde con del(): " + listaCircular.del(new Payload("Verde", "")));
            System.out.println();


            // ---------------------------------------------------------
            // DEMOSTRACIÓN: Cola (FIFO: First In, First Out)
            // ---------------------------------------------------------
            System.out.println("--- 3. Cola ---");
            Cola<Payload> cola = new Cola<>();
            cola.enqueue(color1); // Primero en entrar
            cola.enqueue(color2);
            cola.enqueue(color3); // Último en entrar

            System.out.println("Se encolaron: Rojo, Azul, Verde.");
            System.out.println("Desencolando (Debería ser Rojo): " + cola.dequeue());
            System.out.println("Desencolando (Debería ser Azul): " + cola.dequeue());
            System.out.println();


            // ---------------------------------------------------------
            // DEMOSTRACIÓN: Pila (LIFO: Last In, First Out)
            // ---------------------------------------------------------
            System.out.println("--- 4. Pila ---");
            Pila<Payload> pila = new Pila<Payload>();
            pila.push(color1); // Queda al fondo
            pila.push(color2);
            pila.push(color3); // Queda en la cima (top)

            System.out.println("Se apilaron: Rojo, Azul, Verde.");
            System.out.println("Consultando la cima (Top): " + pila.top());
            System.out.println("Desapilando (Debería ser Verde): " + pila.pop());
            System.out.println("Desapilando (Debería ser Azul): " + pila.pop());
            System.out.println();


            // ---------------------------------------------------------
            // DEMOSTRACIÓN: Lista Doblemente Enlazada (Normal)
            // ---------------------------------------------------------
            System.out.println("--- 5. Lista Doblemente Enlazada (LDE) ---");
            ListaDE<Payload> lde = new ListaDE<>();
            lde.add(color1);         // Añade al final (Rojo)
            lde.addInicio(color4);   // Añade al principio (Amarillo)
            lde.add(color2);         // Añade al final (Azul)

            System.out.println("Se añadieron elementos. Tamaño actual: " + lde.getSize());
            System.out.println("Buscando Azul en la lista: " + lde.get(buscarAzul));
            System.out.println("Buscando Amarillo en la lista: " + lde.get(buscarAmarillo));
            System.out.println("Borrando Rojo: " + lde.del(new Payload("Rojo", "")));
            System.out.println("Nuevo tamaño de la lista: " + lde.getSize());
            System.out.println();


            // ---------------------------------------------------------
            // DEMOSTRACIÓN: Lista Doblemente Enlazada Ordenada
            // ---------------------------------------------------------
            System.out.println("--- 6. Lista Doblemente Enlazada Ordenada ---");
            LDEOrdenada<Payload> ldeOrdenada = new LDEOrdenada<>();

            // Los insertamos en orden aleatorio
            ldeOrdenada.add(color3); // Verde
            ldeOrdenada.add(color2); // Azul
            ldeOrdenada.add(color1); // Rojo
            ldeOrdenada.add(color4); // Amarillo

            System.out.println("Se añadieron (en desorden): Verde, Azul, Rojo, Amarillo.");
            System.out.println("Tamaño de la LDE Ordenada: " + ldeOrdenada.getSize());

            // Verificamos que todos los elementos están accesibles y se pueden recuperar
            System.out.println("Recuperando elementos individualmente para verificar existencia:");
            System.out.println(" -> " + ldeOrdenada.get(new Payload("Amarillo", "")));
            System.out.println(" -> " + ldeOrdenada.get(new Payload("Azul", "")));
            System.out.println(" -> " + ldeOrdenada.get(new Payload("Rojo", "")));
            System.out.println(" -> " + ldeOrdenada.get(new Payload("Verde", "")));

            System.out.println("Vaciando la lista ordenada (clear)...");
            ldeOrdenada.clear();
            System.out.println("¿Está vacía la lista ordenada?: " + ldeOrdenada.isEmpty());
            System.out.println();

            System.out.println("=========================================");
            System.out.println("           FIN DE LA EJECUCIÓN           ");
            System.out.println("=========================================");
        }
    }
