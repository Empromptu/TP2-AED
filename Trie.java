package aed;

public class Trie<T> {
    private TrieNode<T> root;

    // Nodo del Trie
    private class TrieNode<T> {
        private TrieNode<T>[] hijos;
        private T valor;

        @SuppressWarnings("unchecked")
        TrieNode() {
            hijos = (TrieNode<T>[]) new TrieNode[38];
            for (int i = 0; i < 38; i++) {
                hijos[i] = null;
            }
            this.valor = null;
        }
    }

    // Constructor
    public Trie() {
        root = new TrieNode<>();
    }

    private char reemplazar(char ch) {
        switch (ch) {
            case 'á': return 'a';
            case 'é': return 'e';
            case 'í': return 'i';
            case 'ó': return 'o';
            case 'ú': return 'u';
            case 'ñ': return 'n';
            default: return ch;
        }
    }

    private int obtenerPosicion(char ch) {
        String abecedario = " abcdefghijklmnopqrstuvwxyz0123456789/";
        return abecedario.indexOf(ch);
    }

    // Método para insertar una palabra en el Trie
    public void insertar(String palabra, T valor) {
        TrieNode<T> actual = root;
        palabra = palabra.toLowerCase();
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            ch = reemplazar(ch); // Usar el carácter reemplazado
            int posicion = obtenerPosicion(ch);
            if (actual.hijos[posicion] == null) {
                actual.hijos[posicion] = new TrieNode<>();
            }
            actual = actual.hijos[posicion];
        }
        actual.valor = valor;
    }

    // Método para buscar una palabra en el Trie
    public T buscar(String palabra) {
        TrieNode<T> actual = root;
        palabra = palabra.toLowerCase();
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            ch = reemplazar(ch); // Usar el carácter reemplazado
            int posicion = obtenerPosicion(ch);
            if (actual.hijos[posicion] == null) {
                return null;
            }
            actual = actual.hijos[posicion];
        }
        return actual.valor;
    }
}