package aed;

public class Trie<T> {
    private TrieNode<T> root;

    // Constructor
    public Trie() {
        root = new TrieNode<>();
    }

    // Nodo del Trie
    private class TrieNode<T> {
        private TrieNode<T>[] hijos;
        private T valor;

        @SuppressWarnings("unchecked")  // Usamos el supress para generar el array de tipo genérico
        public TrieNode() {
            // Ajustamos el tamaño del array para soportar todos los caracteres ASCII (0-255)
            this.hijos = (TrieNode<T>[]) new TrieNode[256];
            this.valor = null;
        }
    }

    // Método para insertar una palabra en el Trie
    public void insertar(String palabra, T valor) {
        TrieNode<T> actual = root;
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int index = (int) ch; // Convertimos el carácter a su valor ASCII
            if (actual.hijos[index] == null) {
                actual.hijos[index] = new TrieNode<>();
            }
            actual = actual.hijos[index];
        }
        actual.valor = valor;
    }

    // Método para buscar una palabra en el Trie
    public T buscar(String palabra) {
        TrieNode<T> actual = root;
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int index = (int) ch; // Convertimos el carácter a su valor ASCII
            if (actual.hijos[index] == null) {
                return null;
            }
            actual = actual.hijos[index];
        }
        return actual.valor;
    }
}