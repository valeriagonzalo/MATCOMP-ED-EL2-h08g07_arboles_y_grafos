package main.java.Grafo;

public class RDFData { //Clase que permite que podamos leer los RDF.json
    public String[] tipos;
    public Tripleta[] tripletas;

    public static class Tripleta {
        public String s;
        public String p;
        public String o;
    }
}
