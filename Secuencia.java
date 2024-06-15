package aed;

interface Secuencia<T> {

    /**
     * Devuelve el largo de la secuencia.
     * 
     */
    public int longitud();

    /**
     * Agrega un elemento al principio de la secuencia.
     * 
     */
    public void agregarAdelante(T elem);

    /**
     * Elimina el elemento en la i-esima posicion de la secuencia.
     * 
     */
    public void eliminar(int indice);
}