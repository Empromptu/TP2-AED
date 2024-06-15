package aed;

public class Trie {
    private TrieNode root;

    // Constructor
    public Trie() {
        root = new TrieNode();
    }

    // Nodo del Trie
    private class TrieNode<T> {
        private TrieNode[] hijos;
        private T valor;

        public TrieNode() {
            this.hijos = new TrieNode[28];
            this.valor = null;
        }
    }

    // Método para insertar una palabra en el Trie
    public void insertar(String palabra, T valor) {
        TrieNode actual = root;
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int index = ch - 'a';
            if (actual.hijos[index] == null) {
                actual.hijos[index] = new TrieNode();
            }
            actual = actual.hijos[index];
        }
        actual.valor = valor;
    }

    // Método para buscar una palabra en el Trie
    public T buscar(String palabra) {
        TrieNode actual = root;
        for (int i = 0; i < palabra.length(); i++) {
            char ch = palabra.charAt(i);
            int index = ch - 'a';
            if (actual.hijos[index] == null) {
                return null;
            }
            actual = actual.hijos[index];
        }
        return actual.valor;
    }
}
