package aed;

import java.util.ArrayList;


public class SistemaSIU {

    private Trie  carreras;
    private Trie  alumnos;

    enum CargoDocente{
        AY2, //3
        AY1, //2
        JTP, //1
        PROF //0
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias) {
        this.carreras = new Trie(); // O(1)
        this.alumnos = new Trie();  // O(1)

        //FALTA CALCULAR LA COMPLEJIDAD DE ESTE.
        for (InfoMateria infoMateria : infoMaterias) { //O(c∈C Σ|c| * |Mc|)
            Materia materia = new Materia(); // O(1)
            for (ParCarreraMateria parCarreraMateria : infoMateria.getParesCarreraMateria()) { // O()
                String nombreCarrera = parCarreraMateria.getCarrera();
                String nombreMateria = parCarreraMateria.getNombreMateria();
                Carrera carreraExistente = (Carrera) carreras.buscar(nombreCarrera);
                
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
        
        for (String alumno : libretasUniversitarias) { // O(E)
            alumnos.insertar(alumno, (int) 0);         // O(1)
        }
        

    }

    public void inscribir(String estudiante, String carrera, String materia) { // O(|c| + |m|)
        Carrera c = (Carrera) carreras.buscar(carrera);                        // O(|c|)
        alumnos.insertar(estudiante, ((int) alumnos.buscar(estudiante)) + 1);  // O(1) tanto buscar como insertar un estudiante lo es porque esta acotado.
        if (c != null) { //hay que revisar si no es requisito que exista la carrera per se para inscribir.
            Materia m = (Materia) c.getMaterias().buscar(materia);
            /*                         ||||||         ||||||||
             *                          O(1)           O(|m|)     */
            if (m != null) {
                m.agregarAlumno(estudiante);                                   // O(1)
            }
        }

    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){ // O(|c| + |m|)
        Materia m = (Materia) ((Carrera) carreras.buscar(carrera)).getMaterias().buscar(materia);
        /*                                        ||||||||            ||||||       ||||||||                                            
         *                                         O(|c|)              O(1)         O(|m|)       */
        if(cargo.equals(CargoDocente.AY2)){m.agregarDocente(3);}           // O(1)
        else if(cargo.equals(CargoDocente.AY1)){m.agregarDocente(2);}      // O(1)
        else if(cargo.equals(CargoDocente.JTP)){m.agregarDocente(1);}      // O(1)
        else {m.agregarDocente(0);}                                        // O(1)
    }

    public int[] plantelDocente(String materia, String carrera){ // O(|c| + |m|)
        return ((Materia) ((Carrera) carreras.buscar(carrera)).getMaterias().buscar(materia)).docentes();
        /*                                    ||||||||            ||||||       ||||||||         ||||||
         *                                     O(|c|)              O(1)         O(|m|)           O(1)    */	        
    }

    public void cerrarMateria(String materia, String carrera){ // O(|c| + |m| + n∈Nm Σ|n| + Em)
        ArrayList<Object[]> asignaturas = ((Materia) (((Carrera) this.carreras.buscar(carrera)).getMaterias()).buscar(materia)).getMaterias(); // O(|c| + |m|)
        /*                                                             ||||||||||                ||||||           ||||||||         ||||||          
         *                                                               O(|c|)                   O(1)             O(|m|)           O(1)       */
        ArrayList<String> alumnado = ((Materia) (((Carrera) this.carreras.buscar(carrera)).getMaterias()).buscar(materia)).getAlumnos();       // O(|c| + |m|)
        /*                                                             ||||||||||             ||||||           ||||||||       ||||||           
         *                                                               O(|c|)                O(1)             O(|m|)         O(1)            */
        for (Object[] asignatura : asignaturas) { // O(n∈Nm Σ|n|) la suma de los distintos nombres.
            Object[] tupla = asignatura;        // O(1)
            Carrera llave = (Carrera) tupla[0]; // O(1)
            String valor = (String) tupla[1];   // O(1)
            llave.getMaterias().eliminar(valor);// getMaterias() = O(1), eliminar = O(|n| = |valor|)
        }
        for (String alumno : alumnado) {                                // O(Em) la cantidad de alumnos esta acotada por el cupo.
            alumnos.insertar(alumno, (int) alumnos.buscar(alumno) - 1); // O(1)
            }
    }
    
    public int inscriptos(String materia, String carrera) { // O(|c| + |m|)
        Carrera c = (Carrera) carreras.buscar(carrera); // O(|c|)
        if (c != null) {
            Materia m = (Materia) c.getMaterias().buscar(materia);
            /*                        ||||||          ||||||||
             *                         O(1)            O(|m|)     */
            if (m != null) {
                return m.cantidadAlumnos();             // O(1)
            }
        }
        return 0;                                       // O(1)
    }

    public boolean excedeCupo(String materia, String carrera){ // O(|c| + |m|)
        int cantAlumnos = ((Materia) (((Carrera) this.carreras.buscar(carrera)).getMaterias()).buscar(materia)).cantidadAlumnos();
        /*                                                ||||||||||               ||||||          ||||||||         ||||||      
         *                                                  O(|c|)                  O(1)            O(|m|)           O(1)         */
        int[] _docentes = ((Materia) (((Carrera) this.carreras.buscar(carrera)).getMaterias()).buscar(materia)).docentes();
        /*                                                ||||||||||               ||||||          ||||||||       ||||||      
         *                                                  O(|c|)                  O(1)            O(|m|)         O(1)           */ 
        int cupoPROF = _docentes[0]*250, cupoJTP = _docentes[1]*100, cupoAY1 = _docentes[2]*20, cupoAY2 = _docentes[3]*30; // O(1)
        return cantAlumnos > cupoPROF || cantAlumnos > cupoJTP || cantAlumnos > cupoAY1 || cantAlumnos > cupoAY2 ;         // O(1)
    }

    public String[] carreras(){          // O(c∈C Σ|c|)
        return this.carreras.getClaves();// O(c∈C Σ|c|) 
    }

    public String[] materias(String carrera){ // O(|c| + mc∈Mc Σ|mc|)
        return ((Carrera) this.carreras.buscar(carrera)).getMaterias().getClaves();
        //                      ||||||||||                  ||||||        |||
        //                        O(|c|)                     O(1)      O(mc∈Mc Σ|mc|)
    }

    public int materiasInscriptas(String estudiante){ // O(1)
        return (int) this.alumnos.buscar(estudiante); // O(1) porque estudiante esta acotado 
    }
}
