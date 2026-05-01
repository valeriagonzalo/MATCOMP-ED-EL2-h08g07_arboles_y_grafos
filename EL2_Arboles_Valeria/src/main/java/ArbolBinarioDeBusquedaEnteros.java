public class ArbolBinarioDeBusquedaEnteros extends ArbolBinarioDeBusqueda<Integer> {
    
    public int getSuma() {
        if (this.dato == null) return 0;
        int suma = this.dato;
        if (izq != null) suma += ((ArbolBinarioDeBusquedaEnteros)izq).getSuma();
        if (der != null) suma += ((ArbolBinarioDeBusquedaEnteros)der).getSuma();
        return suma;
    }

    @Override
    public void add(Integer nuevoDato) {
        if (this.dato == null) {
            this.dato = nuevoDato;
        } else if (nuevoDato < this.dato) {
            if (izq == null) izq = new ArbolBinarioDeBusquedaEnteros();
            izq.add(nuevoDato);
        } else if (nuevoDato > this.dato) {
            if (der == null) der = new ArbolBinarioDeBusquedaEnteros();
            der.add(nuevoDato);
        }
    }
}