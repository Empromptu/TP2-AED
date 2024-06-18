package aed;

public class Estudiante {
    private String _lu;
    private TrieII _materiasCursadas;

    public Estudiante(String lu){
        this._lu = lu;
        this._materiasCursadas = new TrieII();
    }

    public void anotarEnMateria(String materia){
        this._materiasCursadas.insertar(materia);
    }

    public String nombre(){
        return this._lu;
    }

    public int cantidadDeMateriasCursadas(){
        return this._materiasCursadas.longitud();
    }

    public void borrarMateria(String materia){
        this._materiasCursadas.borrar(materia);
    }

}
