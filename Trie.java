package aed;

public class Trie<T> {
    private TrieNode<T> root;

    
    // Nodo del Trie
    private class TrieNode<T> {
        private TrieNode<T>[] hijos;
        private T _valor;
        
        @SuppressWarnings("unchecked")  // Usamos el supress para generar el array de tipo genérico
        TrieNode() {
            // ajuste a 27 que es el abecedario = " abcdefghijklmnopqrstuvwxyz" con esas posiciones
            TrieNode<T>[] _hijos = new TrieNode<T>[27]; //estaba medio raro escrito antes, no entendia
            for(i = 0; i < 27; i++){ // O(1) acotado <27
                _hijos[i] = null;
            }
            this.hijos = _hijos; //TrieNode<T>[] new TrieNode[27]; ?? esto crea un array?
            this._valor = null;
        }
    }
    
    // Constructor
    public Trie() {
        root = new TrieNode();
    }
    
    private void reemplazar(char ch){ //private porque es una funcion que solo se usa dentro de la class Trie 
        if     (ch == 'á'){ch = 'a';} // O(1) por estar acotada 
        else if(ch == 'é'){ch = 'e';}
        else if(ch == 'í'){ch = 'i';}
        else if(ch == 'ó'){ch = 'o';}
        else if(ch == 'ú'){ch = 'u';}
    }

    private int obtenerPosicion(char ch) {
        String abecedario = " abcdefghijklmnopqrstuvwxyz";
        String numeros = "0123456789/";
        int res;
        res = abecedario.indexOf(ch);
        if(res == -1){res = numeros.indexOf(ch);} //res = -1 significa que no pertenece a abecedario y estamos
        return res;                               //hablando de un numero entonces.
    }
    // Método para insertar una palabra en el Trie
    public void insertar(String palabra, T valor) {
        TrieNode<T> actual = root;
        palabra.toLowerCase(); // Hasta aca ya tengo la palabra en lower
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int posicion; //esta va a ser la posicion del indice que quiero acceder.
            if(ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó' || ch == 'ú'){reemplazar(ch);}
            // HASTA ACA YA TENGO ASEGURADISIMO QUE SON SOLO CARACTERES DEL ABECEDARIO NORMAL
            posicion = obtenerPosicion(ch);
            if(actual.hijos[posicion] == null){actual.hijos[posicion] = new TrieNode();}
            actual = actual.hijos[posicion];
        }
        actual._valor = valor;
    }

    // Método para buscar una palabra en el Trie
    public T buscar(String palabra) {
        TrieNode<T> actual = root;
        palabra.toLowerCase();
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int posicion; //esta va a ser la posicion del indice que quiero acceder.
            if(ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó' || ch == 'ú'){reemplazar(ch);}
            // HASTA ACA YA TENGO ASEGURADISIMO QUE SON SOLO CARACTERES DEL ABECEDARIO NORMAL
            posicion = obtenerPosicion(ch);
            if(actual.hijos[posicion] == null) {return null;}
            else{actual = actual.hijos[posicion];}}
        return actual._valor;
    }
}