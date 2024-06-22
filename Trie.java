package aed;

import java.util.ArrayList;

public class Trie {
    private TrieNode root; // O(1)

    private class TrieNode {
        private TrieNode[] hijos; // O(1)
        private Object valor; // O(1)

        TrieNode() { // O(1)
            hijos = new TrieNode[256]; // O(1)
            for (int i = 0; i < 256; i++) { // O(1)
                hijos[i] = null; // O(1)
            }
            this.valor = null; // O(1)
        }
    }

    // Constructor
    public Trie() {
        root = new TrieNode(); // O(1)
    }

    // revisa si esta vacio el TrieNode
    private boolean estaVacio(TrieNode root) { // O(1)
        for (int i = 0; i < 256; i++) { // O(1)
            if (root.hijos[i] != null) {
                return false;
            } // O(1)
        }
        return true; // O(1)
    }

    // inserta en el TrieNode
    public void insertar(String palabra, Object valor) {// va a depender del Trie, abajo estan especificados los casos.
        TrieNode actual = root; // O(1)
        for (int i = 0; i < palabra.length(); i++) { // (λ)
            /*
             * Caso Alumnos: Caso Carreras/Materias:
             * (λ) va a ser O(1), (λ) va ser O(|c|)
             * puesto que estamos hablando de que (λ) va ser O(|m|)
             * Los estudiantes son identificados con un string
             * (su libreta universitaria, o LU) de longitud acotada
             */
            int index = (int) (palabra.charAt(i)); // O(1)
            if (actual.hijos[index] == null) { // O(1)
                actual.hijos[index] = new TrieNode(); // O(1)
            }
            actual = actual.hijos[index]; // O(1)
        }
        actual.valor = valor; // O(1)
    }

    // busca en el TrieNode y devuelve el valor.
    public Object buscar(String palabra) { // pasa lo mismo que en insertar, depende de lo que estemos buscando.
        TrieNode actual = root; // O(1)

        for (int i = 0; i < palabra.length(); i++) { // (λ)
            /*
             * Caso Alumnos: Caso Carreras/Materias:
             * (λ) va a ser O(1), (λ) va ser O(|c|)
             * puesto que estamos hablando de que (λ) va ser O(|m|)
             * Los estudiantes son identificados con un string
             * (su libreta universitaria, o LU) de longitud acotada
             */
            int index = (int) (palabra.charAt(i)); // O(1)
            if (actual.hijos[index] == null) { // O(1)
                return null; // O(1)
            }
            actual = actual.hijos[index]; // O(1)
        }
        return actual.valor; // O(1)
    }

    // devuelve las claves del TrieNode.
    public String[] getClaves() { // O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|)
        ArrayList<String> claves = this.obtenerClaves(); // O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|)
        String[] respuesta = new String[claves.size()]; // O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|), claves.size() = O(1)
        for (int i = 0; i < claves.size(); i++) { // O(1)
            respuesta[i] = claves.get(i); // O(1)
        }
        return respuesta; // O(1)
    }

    // obtiene las claves de manera privada, es una funcion auxiliar de getClaves
    private ArrayList<String> obtenerClaves() { // O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|)
        ArrayList<String> claves = new ArrayList<>(); // O(1)
        obtenerClavesRec(root, "", claves); // O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|)
        return claves; // O(1)
    }

    /*
     * Lo que hace este metodo es basicamente ir recorriendo el trie de manera
     * lexicografica [0,1,...,255,256]
     * ingresa con un prefijo "" que es un String vacio, y lo va a ir llenando con
     * el caracter correspondiente
     * a su posicion en el Trie. Una vez terminado, vuelve al primer trie que
     * tuviese mas de un hijo y continua
     * con ese, hasta terminar con todo el Trie, para luego volver al anterior y asi
     * sucesivamente hasta que
     * llegue al ultimo elemento del Trie root. Es recursivo.
     * Para terminar, su complejidad va a depender de que estemos buscando, si en el
     * Trie de Carreras o en el
     * Trie de materias, es por eso que esta anotado: O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|)
     */
    private void obtenerClavesRec(TrieNode nodo, String prefijo, ArrayList<String> claves) { // O(c∈C Σ|c|) || O(mc∈Mc
                                                                                             // Σ|mc|)
        if (nodo == null) {
            return;
        } // O(1)
        if (nodo.valor != null) {
            claves.add(prefijo);
        } // O(1)
        for (int i = 0; i < nodo.hijos.length; i++) { // O(1) porque es un array de 256.
            if (nodo.hijos[i] != null) { // O(1) // O(1)
                obtenerClavesRec(nodo.hijos[i], prefijo + ((char) i), claves); // O(c∈C Σ|c|) || O(mc∈Mc Σ|mc|)
            }
        }
    }

    public void eliminar(String palabra) { // O(|m|)
        eliminarRec(root, palabra, 0); // O(|m|)
    }

    /*
     * Casi lo mismo que obtenerClavesRec pero solo vamos a ir a una palabra y a la
     * vuelta le vamos a ir sacando
     * losTrieNode hasta que se encuentre con uno que tiene mas de una palabra
     * root
     * / | \
     * p d (otras letras)
     * / \
     * a (otras letras)
     * / \
     * t r
     * /
     * a
     * eliminar "pata" iria hasta la ultima letra y al volver recursivamente
     * eliminaria el nodo a, t y se pararia
     * en el nodo de la letra a que indica que hay otra palabra, en este caso "par".
     * Como esta funcion va a ser unicamente utilizada para cerrarMaterias, su
     * complejidad toma unicamente
     * |m| = nombre de la materia.
     */
    private void eliminarRec(TrieNode nodo, String palabra, int index) { // O(|m|)
        if (nodo == null) {
            return;
        } // O(1)

        if (index == palabra.length()) { // O(1)
            if (nodo.valor != null) {
                nodo.valor = null;
            } // O(1)
            return; // O(1)
        }

        int posicion = (int) (palabra.charAt(index)); // O(1)
        eliminarRec(nodo.hijos[posicion], palabra, index + 1); // O(|m|)

        // Después de eliminar el nodo hijo, revisa si se debe eliminar este nodo
        if (nodo.hijos[posicion] != null && estaVacio(nodo.hijos[posicion]) && nodo.hijos[posicion].valor == null) {// O(1)
            nodo.hijos[posicion] = null; // O(1)
        }
    }

}

///////////////////////////////////////////////////////
// Invariante de Representacion del Trie
/*
 * - El nodo root debe existir y tener un conjunto de 256 hijos.
 * - Cada nodo, excepto la raiz, representa un caracter.
 * - No hay dos hijos de un mismo nodo con el mismo caracter.
 * - No hay ciclos.
 */
// Invariante de Representacion del TrieNode
/*
 * - El nodo tiene 256 hijos.
 * - Cada nodo tiene un "valor" para indicar la terminacion de una palabra.
 */