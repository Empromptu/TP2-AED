package aed;

import java.util.ArrayList;

public class TrieII {
    private NodoTrie _raiz;
    private Integer _cantidadDePalabras;
    
    public TrieII(){ // Constructor
        this._raiz = new NodoTrie();
        this._cantidadDePalabras = 0; 
    }
    
    private class NodoTrie{
        private Object[] _hijos;
        private boolean _esPalabra;
        /* 
        Object nos permite almacenar valores de tipos distintos,
        pero cuando los recuperamos para utilizarlos hay que hacerles
        el casting del tipo que "deberían" ser, es decir, del tipo esperado
        por eso hay casting por todos lados          
        */
        private Object _valorAsociado;

        public NodoTrie(){ // Constructor
            // Evita preprocesar el texto de entrada. 256 posiciones en null
            this._hijos = new Object[256]; 
            this._esPalabra = false;
            // Aca pueden ir objetos de tipo Carrera, Materia, Estudiante, ParCarreraMateria, etc
            this._valorAsociado = null; 
        }
    }    
    
    /* 
    La sobrecarga del método permite que los nodos 
    almacenen diferentes valores (o ninguno)  
    */
    public void insertar(String palabra){
        insertar(palabra, null);
    }

    public void insertar(String palabra, Object valor){
        // Para iterar
        NodoTrie nodoActual = this._raiz; 
        
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            // Con esto se obtiene el valor ASCII del caracter
            Integer posicion = (int) caracter;
            if (nodoActual._hijos[posicion] == null){
                nodoActual._hijos[posicion] = new NodoTrie();
            }
            // Casting para convertir de Object a NodoTrie
            NodoTrie aux = (NodoTrie) nodoActual._hijos[posicion];
            nodoActual = aux;
        }

        if (!nodoActual._esPalabra){
            nodoActual._esPalabra = true;
            this._cantidadDePalabras += 1;
        }
        nodoActual._valorAsociado = valor;
    }

    // Igual que buscar, pero retorna un booleano
    public boolean pertenece(String palabra){
        NodoTrie nodoActual = this._raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            Integer posicion = (int) caracter;
            if (nodoActual._hijos[posicion] == null){
                return false;
            }
            // Casting para convertir de Object a NodoTrie
            NodoTrie aux = (NodoTrie) nodoActual._hijos[posicion];
            nodoActual = aux;
        }
        return nodoActual._esPalabra;        
    }

    // Si la palabra está definida, devuelve el nodo
    public NodoTrie buscar(String palabra){
        NodoTrie nodoActual = this._raiz;
        for (int i = 0; i < palabra.length(); i++) {
            char caracter = palabra.charAt(i);
            Integer posicion = (int) caracter;
            if (nodoActual._hijos[posicion] == null){
                return null;
            }
            // Casting para convertir de Object a NodoTrie
            NodoTrie aux = (NodoTrie) nodoActual._hijos[posicion];
            nodoActual = aux;        
        }
        return nodoActual;
    }

    // Al resultado esperado hay que hacerle casting
    // porque puede ser cualquier cosa
    public Object obtenerValor(String palabra){
        NodoTrie nodo = buscar(palabra);
        if (!(nodo == null) && nodo._esPalabra){
            return nodo._valorAsociado;
        } else {
            return null;
        }
    }

    public Integer longitud(){
        return this._cantidadDePalabras;
    }

    public void borrar(String palabra){
        if (pertenece(palabra)){
            // Almacena los nodos y posiciones recorridas
            ArrayList<Object[]> nodosRecorridos = new ArrayList<>();

            NodoTrie nodoActual = this._raiz;
            for (int i = 0; i < palabra.length(); i++) {
                char caracter = palabra.charAt(i);
                Integer posicion = (int) caracter;
                // A medida que avanza agrega solo los que se deberán borrar
                if (!nodoActual._esPalabra && !tieneHijos(nodoActual)){
                    nodosRecorridos.add(new Object[]{nodoActual, posicion});
                }                
                // Casting para convertir de Object a NodoTrie
                NodoTrie aux = (NodoTrie) nodoActual._hijos[posicion];
                nodoActual = aux;        
            }
            // Llegue a la clave buscada y la borra
            nodoActual._esPalabra = false;
            nodoActual._valorAsociado = null;
            this._cantidadDePalabras -= 1;

            // Elimina los nodos que ya no sirven
            // agregados mientras buscaba la plabra
            for (int i= 0; i < nodosRecorridos.size(); i++) {
                Object[] tuplaAux = nodosRecorridos.get(i);
                //Casting para obtener la instancia y posicion del nodo
                NodoTrie nodo = (NodoTrie) tuplaAux[0];
                Integer posicion = (int) tuplaAux[1];           
                // Solo vacía los que no son palabras y no tienen hijos
                nodo._hijos[posicion] = null;
            }
        }
    }

    // Método auxiliar a borrar 
    private boolean tieneHijos(NodoTrie nodo){
        boolean res = false;        
        for (int i = 0; i < nodo._hijos.length; i++){
            if (nodo._hijos[i] != null){
                return true;
            }
        }
        return res;
    }    

    public String[] listaDeClaves() {
        String[] claves = new String[_cantidadDePalabras];
        // No funciona la recursion con un entero
        int[] posicion = {0}; 
        armarLista(this._raiz, "", claves, posicion);
        return claves;
    }

    private void armarLista(NodoTrie nodo, String cadena, String[] claves, int[] posicion) {
        // Caso base
        if (nodo == null){
            return;
        } 
        // Si es clave la agrega a la lista e incrementa el contador
        if (nodo._esPalabra) {
            claves[posicion[0]++] = cadena;
        }
        // Recorre el trie
        for (int i = 0; i < 256; i++) {
            if (nodo._hijos[i] != null) {
                char caracter = (char) i;
                NodoTrie nodoAux = (NodoTrie) nodo._hijos[i];
                armarLista(nodoAux, cadena + caracter, claves, posicion);
            }
        }
    }

}
