package aed;

public class Carrera {

    private String nombre; // O(1)
    private Trie materias; // O(1)

    public Carrera(String nombre) { // O(1)
        this.nombre = nombre; // O(1)
        this.materias = new Trie(); // O(1)
    }

    public String getNombre() { // O(1)
        return nombre; // O(1)
    }

    public void setNombre(String nombre) { // O(1)
        this.nombre = nombre; // O(1)
    }

    public Trie getMaterias() { // O(1)
        return materias; // O(1)
    }

    public void setMaterias(String nombre, Materia materia) { // O(|m|)
        this.materias.insertar(nombre, materia); // O(|m|)
    }

}

/////////////////////////////////////////////////////////////////
/* Invariante de Representacion */
/*
 * -La misma materia puede exitir en varias carreras.
 * -Las claves de "carrera.getMaterias()" se encuentran ordenadas
 * lexicograficamente
 * 
 */