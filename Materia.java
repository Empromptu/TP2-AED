package aed;


public class Materia {

    private Integer cantAlumnos;
    private Integer[] docentes;

    public Materia(String materia){
        this.cantAlumnos = 0;
        this.docentes = new Integer[4];
    }

    enum CargoDocente{
        AY2,
        AY1,
        JTP,
        PROF
    }

    public void agregarDocente(CargoDocente docente) {
        Integer posicion = docente.ordinal();
        this.docentes[posicion] = this.docentes[posicion] +1;
    }
    
    public void agregarAlumno(String lu) {
        this.cantAlumnos =+1;
    }

    public int CantidadAlumnos() {
        return cantAlumnos;
    }
}
