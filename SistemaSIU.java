package aed;

import java.util.ArrayList;


public class SistemaSIU {

    private Trie<Carrera>  carreras;
    private Trie<Integer>  alumnos;

    enum CargoDocente{
        AY2, //3
        AY1, //2
        JTP, //1
        PROF //0
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        this.carreras = new Trie<>();
        this.alumnos = new Trie<>();

        for (InfoMateria infoMateria : infoMaterias) {
            Materia materia = new Materia<>();
            for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()) {
                String nombreCarrera = parCarreraMateria.getCarrera();
                String nombreMateria = parCarreraMateria.getNombreMateria();
                materia.setNombre(nombreMateria);
                Carrera carreraExistente = carreras.buscar(nombreCarrera);
                
                if (carreraExistente == null) {
                    carreraExistente = new Carrera(nombreCarrera);
                    Object[] tupla = {carreraExistente, nombreMateria}; // ojo al leer la informacion
                    materia.agregarMaterias(tupla);                     // (carrera)[0] (String)[1]
                    carreraExistente.setMaterias(nombreMateria, materia);
                    carreras.insertar(nombreCarrera, carreraExistente);
                }
                else{
                    Object[] tupla = {carreraExistente, nombreMateria}; // ojo al leer la informacion
                    materia.agregarMaterias(tupla);                     // (carrera)[0] (String)[1]
                    carreraExistente.setMaterias(nombreMateria, materia);
                }
            }
        }
        
        for (String alumno : libretasUniversitarias) {
            alumnos.insertar(alumno, 0);
        }
        

    }

    public void inscribir(String estudiante, String carrera, String materia) {
        Carrera c = carreras.buscar(carrera);
        int res = alumnos.buscar(estudiante);
        res++;
        alumnos.insertar(estudiante, res);
        if (c != null) {
            Materia m = c.getMaterias().buscar(materia);
            if (m != null) {
                m.agregarAlumno(estudiante);
            }
        }

    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){ // O(1)
        Carrera c = carreras.buscar(carrera);
        Materia m = c.getMaterias().buscar(materia);
        CargoDocente _ay2 = CargoDocente.AY2;
        CargoDocente _ay1 = CargoDocente.AY1;
        CargoDocente _jtp = CargoDocente.JTP;
        if(cargo.equals(_ay2)){m.agregarDocente(3);}
        else if(cargo.equals(_ay1)){m.agregarDocente(2);}
        else if(cargo.equals(_jtp)){m.agregarDocente(1);}
        else {m.agregarDocente(0);}
    }

    public int[] plantelDocente(String materia, String carrera){
            Carrera c = carreras.buscar(carrera);
            Materia m = c.getMaterias().buscar(materia);
            return m.docentes();	        
    }

    public void cerrarMateria(String materia, String carrera){ //the dificultest.
        Materia c = this.carreras.buscar(carrera).getMaterias().buscar(materia);
        ArrayList<Object[]> materias = new ArrayList<>();
        ListaEnlazada<String> alumnado = c.getAlumnos();
        materias = c.getMaterias();
        for (int i = 0; i < materias.size(); i++) {
            Object[] tupla = materias.get(i);
            Carrera career = (Carrera) tupla[0];
            String key = (String) tupla[1];
            Trie<Materia> nodo = career.getMaterias();
            nodo.eliminar(key); 
            // hasta aca ya cerre la materia, me falta eliminar a todos los alumnos de la misma
            for (int j = 0; i < alumnado.longitud(); j++) {
                int res = alumnos.buscar(alumnado.obtener()); // valor
                res--;
                alumnos.insertar(alumnado.obtener(),res);
                alumnado.eliminar(); 
        }
        }
    }
    
    public int inscriptos(String materia, String carrera) {
        Carrera c = carreras.buscar(carrera);
        if (c != null) {
            Materia m = c.getMaterias().buscar(materia);
            if (m != null) {
                int totalAlumnos = m.cantidadAlumnos();
                return totalAlumnos;
            }
        }
        return 0;
    }


    public boolean excedeCupo(String materia, String carrera){ // O(|c| + |m|)
        Carrera c = carreras.buscar(carrera);
        Materia m = c.getMaterias().buscar(materia);
        int[] _docentes = m.docentes();
        int cupoPROF = _docentes[0]*250;
        int cupoJTP = _docentes[1]*100;
        int cupoAY1 = _docentes[2]*20;
        int cupoAY2 = _docentes[3]*30;
        return m.cantidadAlumnos() > cupoPROF || m.cantidadAlumnos() > cupoJTP || m.cantidadAlumnos() > cupoAY1 || m.cantidadAlumnos() > cupoAY2 ; 
    }

    public String[] carreras(){
        return this.carreras.getClaves();
    }

    public String[] materias(String carrera){
        Carrera c = this.carreras.buscar(carrera); // O(|c|)
        Trie<Materia> m = c.getMaterias(); //Trie (donde estan las materias)
        return m.getClaves();
    }

    public int materiasInscriptas(String estudiante){
        return this.alumnos.buscar(estudiante); // O(1) ✅ chequeado por un intelectual de miller  
    }
}
