import ListaDoblementeEnlazada.ListaDE;
import Interfaces.Iterador;

public class ProgramaPruebaArboles {

    // Método auxiliar que usa tu IteradorLDE para sumar los elementos de una ListaDE
    public static int sumarListaDE(ListaDE<Integer> lista) {
        int suma = 0;
        Iterador<Integer> iterador = lista.getIterador();
        while (iterador.hasNext()) {
            suma += iterador.next();
        }
        return suma;
    }

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println("       PRUEBA 1: INSERCIÓN ORDENADA      ");
        System.out.println("=========================================");
        ArbolBinarioDeBusquedaEnteros arbolOrdenado = new ArbolBinarioDeBusquedaEnteros();
        for (int i = 0; i <= 128; i++) {
            arbolOrdenado.add(i);
        }
        
        System.out.println("Altura del árbol: " + arbolOrdenado.getAltura());
        System.out.println("Suma total (getSuma()): " + arbolOrdenado.getSuma());
        
        // Verificación con tus listas y tu iterador
        System.out.println("Suma en PreOrden: " + sumarListaDE(arbolOrdenado.getListaPreOrden()));
        System.out.println("Suma en PostOrden: " + sumarListaDE(arbolOrdenado.getListaPostOrden()));
        System.out.println("Suma en Orden Central: " + sumarListaDE(arbolOrdenado.getListaOrdenCentral()));
        
        System.out.println("Longitud del camino al 110: " + (arbolOrdenado.getCamino(110).getSize() - 1));


        System.out.println("\n=========================================");
        System.out.println("       PRUEBA 2: INSERCIÓN ALEATORIA     ");
        System.out.println("=========================================");
        ArbolBinarioDeBusquedaEnteros arbolAzar = new ArbolBinarioDeBusquedaEnteros();
        
        // Generamos array y lo mezclamos
        int[] nums = new int[129];
        for (int i = 0; i <= 128; i++) nums[i] = i;
        for (int i = 0; i < nums.length; i++) {
            int randIdx = (int)(Math.random() * nums.length);
            int temp = nums[randIdx];
            nums[randIdx] = nums[i];
            nums[i] = temp;
        }
        
        for (int n : nums) {
            arbolAzar.add(n);
        }

        System.out.println("Altura del árbol: " + arbolAzar.getAltura());
        System.out.println("Suma total (getSuma()): " + arbolAzar.getSuma());
        
        // Verificación con tus listas y tu iterador
        System.out.println("Suma en PreOrden: " + sumarListaDE(arbolAzar.getListaPreOrden()));
        System.out.println("Suma en PostOrden: " + sumarListaDE(arbolAzar.getListaPostOrden()));
        System.out.println("Suma en Orden Central: " + sumarListaDE(arbolAzar.getListaOrdenCentral()));
        
        System.out.println("Longitud del camino al 110: " + (arbolAzar.getCamino(110).getSize() - 1));
    }
}