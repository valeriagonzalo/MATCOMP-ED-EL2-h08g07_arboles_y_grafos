public class Payload implements Comparable<Payload>{ //Esto es el producto que se va a meter en las listas
    //yo voy a elegir botes de pintura
    private String nombreColor;
    private String hexadecimal;

    public Payload(String nombreColor, String hexadecimal) { //Con los parámetros que se me dan, creo un objeto
        this.nombreColor = nombreColor;
        this.hexadecimal = hexadecimal;
    }

    public int compareTo(Payload another) { //???
        return this.nombreColor.compareTo(another.nombreColor);
    }
    public String toString() {
        return "Color{"+nombreColor+" #"+hexadecimal+"}";
    }


}
