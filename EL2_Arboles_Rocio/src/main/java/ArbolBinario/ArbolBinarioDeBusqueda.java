package ArbolBinario;
import ListaSimple.ListaSimple;

public class ArbolBinarioDeBusqueda<T  extends Comparable<T>> {
    private int grado = 0;
    private int altura = 0;
    private Nodo<T> raiz; //este sería el nodo que inicia el árbol

    public ArbolBinarioDeBusqueda() { //para inicializar un árbol vacío
        raiz = null;
    } //creamos un árbol sin raíz provenida

    public ArbolBinarioDeBusqueda(Nodo<T> raiz) {
        this.raiz = raiz;
    } //creamos un árbol con raíz de tipo nodo dado

    public ArbolBinarioDeBusqueda(T dato){
        Nodo<T> raiz = new  Nodo<>(dato);
    } //creamos un árbol con un dato normal dado que hay que pasar a tipo nodo

    public int getGrado() {
        return gradoRecursivo(raiz);
    }
    private int gradoRecursivo(Nodo<T> raiz) { //privado para que el usuario no pueda acceder a ello y que quede más limpio
        int gradoDefIzq = 0; //los defino al principio porque se utilizan al final de la función
        int gradoDefDer = 0;
        if(raiz == null) {
            return -1; //si no hay raíz, su grado es -1
        }
        else {
            int hijos = 0; //en principio, si tengo una raíz, voy a asumir que no tiene hijos
            if (raiz.izq == null || raiz.der == null) { //ahora si tiene algún hijo, sé que mínimo tiene grado 1
                return 1;
            }
            if(raiz.izq != null) { //ahora, si el hijo es por la izquierda, aumento el número de hijos y miro el grado de ese hijo
                hijos += 1;
                int gradoHijo = this.gradoRecursivo(raiz.izq);
                gradoDefIzq = Math.max(hijos, gradoHijo); //puede ser que mi hijo sea una hoja, o que este tenga más hijos y entonces el grado aumenta, entonces con el máximo elegimos el mayor de los 2
            }
            if(raiz.der != null) { //hacemos exactamente lo mismo con la izquierda
                hijos += 1;
                int gradoHijo = this.gradoRecursivo(raiz.der);
                gradoDefDer = Math.max(hijos, gradoHijo);
            }
        }
        return Math.max(gradoDefIzq,gradoDefDer); //finalmente, escogemos el mayor grado de las 2 ramas principales
    }

    public int getAltura() {
        return alturaRecursiva(raiz);
    }
    private int alturaRecursiva(Nodo<T> raiz) { //al igual que en getGrado, añado que haya que proporcionarle la raíz para así hacerlo recursivo
        int alturaIzq = 0; //porque si tiene hijos estos están en el nivel 1?
        int alturaDer = 0;
        if (raiz == null) {
            return -1; //si el árbol está vacío, la altura es ne
        }
        else {
            if (raiz.izq == null && raiz.der == null) { //si no tiene hijos, no se le añade ningún nivel de altura
                return 0;
            }
            if(raiz.izq != null) { //si tiene hijo, mínimo se le suma la altura a la que está el hijo y se mira si su hijo también tiene
               alturaIzq = 1 + alturaRecursiva(raiz.izq);
            }
            if(raiz.der != null) {
                alturaDer = 1 + alturaRecursiva(raiz.der);
            }
            return Math.max(alturaIzq,alturaDer);
        }
    }

    public ListaSimple<T> getListaDatosNivel(int nivel) {//quiero obtener una lista de los datos que estén a equis nivel
        ListaSimple<T> resultado = new ListaSimple<T>(); //primero inicializo la lista vacía
        nivelRecursivo(raiz, nivel, 0, resultado);
        return resultado;
    }

    private void nivelRecursivo(Nodo<T> raiz, int nivelObj, int nivelActual, ListaSimple<T> resultado) {
        if (raiz != null) {
            if (nivelActual == nivelObj) {
                resultado.add(raiz.dato); //si he llegado al nivel que quiero, añado el dato que esté en ese nivel
            } else { //si no es así, recorro hacia la izquierda y hacia la derecha, e indicamos manualmente que es un nivel inferior
                nivelRecursivo(raiz.izq, nivelObj, nivelActual + 1, resultado);
                nivelRecursivo(raiz.der, nivelObj, nivelActual + 1, resultado);
            }
        }
    }

    public Boolean isArbolHomogeneo() {
        int gradoMax = getGrado();
        if (gradoMax == 0) { //Si el árbol solo tiene la raíz, entonces es homogéneo.
            return true;
        }
        else {
            return homogeneidadRecursiva(raiz);
        }
    }

    private Boolean homogeneidadRecursiva(Nodo<T> nodo) {
        if (nodo == null) {
            return true; //si no hay árbol, es homogéneo
        }
        int hijos = 0; //volvemos a asumir que el número de hijos es 0.
        if (nodo.izq != null) {
            hijos += 1;
        }
        if (nodo.der != null) {
            hijos += 1;
        }
        if (hijos != 0 && hijos != 2) { //Excluímos hijos == 0 pq sería hojas. Además, ponemos 2 porque los árboles binarios como mucho tienen grado 2
            return false;
        }
        Boolean homogeneoIzq = homogeneidadRecursiva(nodo.izq); //ahora vemos si por la izquierda conserva la homogeneidad
        Boolean homogeneoDer = homogeneidadRecursiva(nodo.der); // también miramos que la conserve por la derecha
        return  homogeneoIzq && homogeneoDer;

    }

    public Boolean isArbolCompleto() {
        if (raiz == null) {
            return true;
        }
        int alturaTotal = getAltura(); // Primero obtenemos la altura para saber dónde deberían estar todas las hojas
        return completoRecursivo(raiz, 1, alturaTotal);
    }

    private Boolean completoRecursivo(Nodo<T> nodo, int nivelActual, int alturaTotal) {
        int hijos = 0; //Vemos si es hoja dependiendo de si tiene hijos o no
        if (nodo.izq != null) hijos++;
        if (nodo.der != null) hijos++;

        if (hijos == 0) { //si es hoja se mira su nivel con la altura total
            return nivelActual == alturaTotal;
        }

        if (hijos != 2) { //todo nodo debe tener exactamente 2 hijos por ser binario
            return false;
        }

        return completoRecursivo(nodo.izq, nivelActual + 1, alturaTotal) &&
                completoRecursivo(nodo.der, nivelActual + 1, alturaTotal);
    }

    public Boolean isArbolCasiCompleto() { //NO ESCOGER
        if (raiz == null) return true;

        int alturaMax = getAltura(raiz);


        // Usamos un array de un solo elemento para rastrear si ya encontramos el final de los nodos en el último nivel.
        boolean[] finalNivelProfundoAlcanzado = {false};

        return validarCasiCompleto(raiz, 1, alturaMax, finalNivelProfundoAlcanzado);
    }

    private Boolean validarCasiCompleto(Nodo<T> nodo, int nivel, int alturaMax, boolean[] alcanzadoFin) {
        if (nodo == null) return true;

        // Caso: Es una hoja
        if (nodo.izq == null && nodo.der == null) {
            if (nivel == alturaMax) {
                // Si estamos en el nivel más profundo y ya habíamos visto un "hueco" antes
                if (alcanzadoFin[0]) {return false;}
                else {return true;}
            } else if (nivel == alturaMax - 1) {
                // Si es una hoja en el penúltimo nivel, a partir de aquí no puede haber más nodos en el nivel alturaMax.
                alcanzadoFin[0] = true;
                return true;
            } else {
                // Hoja en un nivel demasiado alto (ej: nivel 1 cuando la altura es 3)
                return false;
            }
        }

        // Caso: ArbolBinario.Nodo con hijos (debe estar en nivel < alturaMax)
        if (nivel >= alturaMax) {return false;}

        // Recorrido Pre-Order (Izquierda -> Derecha) para asegurar contigüidad
        if (!validarCasiCompleto(nodo.izq, nivel + 1, alturaMax, alcanzadoFin)) {return false;}

        // Si el hijo izquierdo era nulo, pero el derecho no, no es contiguo desde la izquierda
        if (nodo.izq == null && nodo.der != null) {return false;}

        // Si el hijo izquierdo no llenó el nivel alturaMax, el derecho tampoco puede
        if (nodo.izq == null && nivel + 1 == alturaMax) {alcanzadoFin[0] = true;}

        return validarCasiCompleto(nodo.der, nivel + 1, alturaMax, alcanzadoFin);
    }


    private int getAltura(Nodo<T> nodo) { //esto es un getAltura específico para el tema de el árbol casi completo
        if (nodo == null) return 0;
        return 1 + Math.max(getAltura(nodo.izq), getAltura(nodo.der));
    }

    public ListaSimple<T> getCamino(T objetivo) { //se necesita introducir un parámetro para saber hasta donde quiero buscar el camino
        return buscarCaminoRecursivo(raiz, objetivo);
    }

    private ListaSimple<T> buscarCaminoRecursivo(Nodo<T> nodo, T objetivo) {
        // Caso base 1: El árbol está vacío
        if (nodo == null) {
            return null;
        }

        // Caso base 2: Si la raíz es justo el elemento que busco
        if (nodo.dato.equals(objetivo)) {
            ListaSimple<T> listaFinal = new ListaSimple<T>(); // Usamos LinkedList por ser más ligera para esto MIRAR SI ME VA A ARRUINAR LA VIDA
            listaFinal.add(nodo.dato);
            return listaFinal;
        }

        // Lógica de búsqueda (BST)
        ListaSimple<T> subCamino = null;
        if (objetivo.compareTo(nodo.dato) < 0) {
            subCamino = buscarCaminoRecursivo(nodo.izq, objetivo);
        } else {
            subCamino = buscarCaminoRecursivo(nodo.der, objetivo);
        }

        // Si el subcamino no es nulo, significa que el objetivo está por esta rama
        if (subCamino != null) {
            // Añadimos el nodo actual AL PRINCIPIO del camino que viene de abajo
            subCamino.add(0, nodo.dato);
            return subCamino;
        }

        return null; // Si llegamos aquí, el elemento no está en este subárbol
    }



    //OPERACIONES

    public void add(T dato) {
        raiz = addRecursivo(dato, raiz);
    }
    public Nodo<T> addRecursivo(T dato, Nodo<T> actual) { //para añadir necesito saber lo que quiero añadir y donde
        Nodo<T> newNodo = new Nodo<>(dato);
        if (actual == null) { //si esa posición está vacía, la rellenamos
            actual = newNodo;
        } else { //comparamos los datos de los nodos, y vemos si el que queremos introducir es más grande o más pequeño
            if (dato.compareTo(actual.dato) < 0) {
                actual.izq = addRecursivo(dato, actual.izq); //recursivamente, nos metemos en el nodo de la izquierda para comprobar
            } else if (dato.compareTo(actual.dato) > 0) {
                actual.der = addRecursivo(dato, actual.der);
            }
        }
        //no añadimos el caso = 0 para evitar repetidos
        return actual;
    }

    public ArbolBinarioDeBusqueda<T> getSubArbolIzquierda() {
        if (raiz == null || raiz.izq == null) {//si tanto mi raíz como mi raíz por la izquierda está vacío
            return null; //devuelvo nulo porque no hay
        }
        else {
            return new ArbolBinarioDeBusqueda<>(raiz.izq); //si hay raíz, creo un nuevo árbol desde la izquierda de la raíz.
        }

    }

    public ArbolBinarioDeBusqueda<T> getSubArbolDerecha() {
        if (raiz == null || raiz.der == null) {//si tanto mi raíz como mi raíz por la derecha está vacío
            return null; //devuelvo nulo porque no hay
        }
        else {
            return new ArbolBinarioDeBusqueda<>(raiz.der); //si hay raíz, creo un nuevo árbol desde la derecha de la raíz.
        }

    }

    //GENERACIÓN DE LISTAS - Contienen los datos de los elementos del árbol organizados según pidamos

    public ListaSimple<T> getListaPreOrden() { //EXPLICAR
        ListaSimple<T> lista = new ListaSimple<T>();
        listaPreOrdenRecursivo(raiz, lista);
        return lista;
    }

    public void listaPreOrdenRecursivo(Nodo<T> nodo, ListaSimple<T> lista) {
        if (nodo != null) {
            lista.add(nodo.dato);
            listaPreOrdenRecursivo(nodo.izq, lista);
            listaPreOrdenRecursivo(nodo.der, lista);
        }
    }

    public ListaSimple<T> getListaPostOrden() {
        ListaSimple<T> lista = new ListaSimple<T>();
        listaPostOrdenRecursivo(raiz, lista);
        return lista;
    }

    public void listaPostOrdenRecursivo(Nodo<T> nodo, ListaSimple<T> lista) {
        if (nodo != null) {
            listaPostOrdenRecursivo(nodo.izq, lista);
            listaPostOrdenRecursivo(nodo.der, lista);
            lista.add(nodo.dato);
        }
    }

    public ListaSimple<T> getListaOrdenCentral() {
        ListaSimple<T> lista = new ListaSimple<T>();
        listaOrdenCentralRecursivo(raiz, lista);
        return lista;
    }

    public void listaOrdenCentralRecursivo(Nodo<T> nodo, ListaSimple<T> lista) {
        if (nodo != null) {
            listaOrdenCentralRecursivo(nodo.izq, lista);
            lista.add(nodo.dato);
            listaOrdenCentralRecursivo(nodo.der, lista);
        }
    }

    //ADICIONALES
    public int getSuma() {
        //FALTA COMPROBAR PASARLO A ENTERO ???
        return sumaRecursiva((Nodo<Integer>) raiz); //me aseguro de que sea de entero
    }

    public int sumaRecursiva(Nodo<Integer> nodo) { //el enunciado dice que deben ser enteros, entonces nos encargamos de que lo sean
        if (nodo == null) {
            return 0;
        }
        else {
            int sumaIzq = sumaRecursiva(nodo.izq);
            int sumaDer = sumaRecursiva(nodo.der);
            return nodo.dato + sumaIzq + sumaDer;
        }
    }
    //Adicionales pero necesarios
    public boolean contiene(T dato) {
        return contieneRecursivo(raiz, dato);
    }

    private boolean contieneRecursivo(Nodo<T> nodo, T dato) {
        if (nodo == null) {return false;}
        else {
            if (dato.compareTo(nodo.dato) < 0) {
                return contieneRecursivo(nodo.izq, dato);
            }
            if (dato.compareTo(nodo.dato) > 0) {
                return contieneRecursivo(nodo.der, dato);
            }
        }
        //Solo queda si el dato es igual al que hay en el nodo, por lo que sí lo contiene y devuelve true
        return true;
    }
}



