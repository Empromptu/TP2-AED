package aed;

public class Materia {

    private String nombre;
    private int[] _docentes;
    private ListaEnlazada<String> _alumnos;
    private ListaEnlazada<ParCarreraMateria> _carreras;

    enum CargoDocente {
        AY2, AY1, JTP, PROF
    }

    public Materia(String nombre) {
        this.nombre = nombre;
        this._docentes = new int[4];
        for (int i = 0; i < 4; i++) {
            _docentes[i] = 0;
        }
        _alumnos = new ListaEnlazada<>();
        _carreras = new ListaEnlazada<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void agregarCarrera(String carrera) {
        // Verificar si la carrera ya existe para esta materia
        boolean existe = false;
        for (ParCarreraMateria par : _carreras) {
            if (par.getCarrera().equals(carrera)) {
                existe = true;
                break;
            }
        }
        if (!existe) {
            _carreras.agregarAdelante(new ParCarreraMateria(carrera, this.nombre));
        }
    }

    public void agregarDocente(CargoDocente docente) {
        int posicion = docente.ordinal();
        _docentes[posicion] = _docentes[posicion] + 1;
    }

    public void agregarAlumno(String lu) {
        _alumnos.agregarAdelante(lu);
    }

    public int cantidadAlumnos() {
        return _alumnos.longitud();
    }

    public int[] docentes() {
        return _docentes;
    }

    public ListaEnlazada<ParCarreraMateria> getCarreras() {
        return _carreras;
    }
}

