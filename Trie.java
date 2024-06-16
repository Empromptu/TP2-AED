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
    // Método para insertar una palabra en el Trie
    public void insertar(String palabra, T valor) {
        TrieNode<T> actual = root;
        palabra.toLowerCase(); // Hasta aca ya tengo la palabra en lower
        for (int i = 0; i < palabra.length(); i++) {
            String abecedario = " abcdefghijklmnopqrstuvwxyz";
            char ch = palabra.charAt(i);
            int posicion; //esta va a ser la posicion del indice que quiero acceder.
            if(ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó' || ch == 'ú'){reemplazar(ch);}
            // HASTA ACA YA TENGO ASEGURADISIMO QUE SON SOLO CARACTERES DEL ABECEDARIO NORMAL
            for(i = 0; i < abecedario.length(); i++){
                if(ch = abecedario.charAt(i)){
                    posicion = i; //con esto ya tengo el index que quiero ver
                }
            }
            if(i = palabra.length() - 1){ //ultimo elemento, tenemos que cambiar el valor a true o que se yo.
                if(actual.hijos[posicion] == null){
                    actual.hijos[posicion] = new TrieNode();
                    actual = actual.hijos[posicion]; //ahora estoy en la ultima letra
                    actual.hijos[posicion] = new TrieNode(); //creo nuevo TrieNode() desp de la letra
                    actual._valor = valor;
                }
                else{
                    actual = actual.hijos[posicion]; //estoy en el nodo de la ultima letra
                    if(actual.hijos[posicion] == null){
                        actual.hijos[posicion] = new TrieNode(); //creo nuevo TrieNode() desp de la letra
                        actual = actual.hijos[posicion];
                        actual._valor = valor;
                    }
                    else{
                        actual = actual.hijos[posicion];
                        actual._valor = valor; 
                    }
                }
            }
            //int index = (int) ch; // Convertimos el carácter a su valor ASCII ¿lo necesito en ASCII?
            //if(index == 225 || index == 233 || index == 237 || index == 243 || index == 250){ reemplazar(index);}
            //ya para este punto deberia tener todos los index en letras normales
            else{
                if (actual.hijos[posicion] == null) {
                    actual.hijos[posicion] = new TrieNode();
                    actual = actual.hijos[posicion];
                }
                else{ //si no es null, tengo que revisar si la letra que estoy viendo es null o no.
                    actual = actual.hijos[posicion];
                }
            }
        }
        //actual.valor = valor; //esto se esta insertando en TODOS y no en el ultimo que vendria siendo la palabra
        //ademas no se que mierda es valor
    }
    // Método para buscar una palabra en el Trie
    public T buscar(String palabra) {
        TrieNode<T> actual = root;
        palabra.toLowerCase();
        for (int i = 0; i < palabra.length(); i++) {
            String abecedario = " abcdefghijklmnopqrstuvwxyz";
            char ch = palabra.charAt(i);
            int posicion; //esta va a ser la posicion del indice que quiero acceder.
            if(ch == 'á' || ch == 'é' || ch == 'í' || ch == 'ó' || ch == 'ú'){reemplazar(ch);}
            // HASTA ACA YA TENGO ASEGURADISIMO QUE SON SOLO CARACTERES DEL ABECEDARIO NORMAL
            for(i = 0; i < abecedario.length(); i++){
                if(ch = abecedario.charAt(i)){
                    posicion = i; //con esto ya tengo el index que quiero ver
                }
            }
            if (actual.hijos[posicion] == null) {
                return null;
            }
            else{
                actual = actual.hijos[posicion];
            }
        }
        return actual._valor;
    }
}