package aed;

public class Carrera {
    private String _nombre;
    /*
    Contiene la lista de materias asociadas, cuyas claves se buscan
    en el trie de materias del SIU para acceder siempre a la misma instancia
    */
    private TrieII  _listaMaterias;

    public Carrera(String nombreCarrera){
        this._nombre = nombreCarrera;
        this._listaMaterias = new TrieII();
    }

    public void agregarMateria(String nombreMateria, Materia materia){
        if (!this._listaMaterias.pertenece(nombreMateria)){
            this._listaMaterias.insertar(nombreMateria, materia);       
        }
    }

    public String nombre(){
        return this._nombre;
    }

    public Object obtenerMateria(String materia){
        return this._listaMaterias.obtenerValor(materia);
    }

    public String[] listaDeMaterias(){
        return this._listaMaterias.listaDeClaves();
    }

    public int cantidadDeMaterias(){
        return this._listaMaterias.longitud();
    }

    public void borrarMateria(String materia){
        this._listaMaterias.borrar(materia);
    }
}
