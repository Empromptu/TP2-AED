package aed;

public class Carrera {

    private String nombre;
    private Trie<Materia> materias;

    public Carrera(String nombre) {
        this.nombre = nombre;
        this.materias = new Trie<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Trie<Materia> getMaterias() {
        return materias;
    }

    public void setMaterias(Trie<Materia> materias) {
        this.materias = materias;
    }

    public void agregarMateria(String nombreMateria, Materia materia) {
        this.materias.insertar(nombreMateria, materia);
    }
}