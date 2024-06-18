package aed;

import java.util.ArrayList;

public class SistemaSIU {

    private TrieII _carreras;
    private TrieII _estudiantes;

    // Se inviritó el orden para cumplir con el enunciado
    enum CargoDocente{
        PROF,
        JTP,
        AY1,
        AY2
    }

    public SistemaSIU(InfoMateria[] infoMaterias, String[] libretasUniversitarias){
        this._carreras = new TrieII();
        this._estudiantes = new TrieII();

        // Registro de materias y carreras
        for (int i=0; i < infoMaterias.length; i++){
            ParCarreraMateria[] paresCM = infoMaterias[i].getParesCarreraMateria();
            
            // Instancia de materia que recibe distintos nombres almacenados en un trie
            Materia instanciaMateria = new Materia();

            for (int j=0; j < paresCM.length; j++){
                // Accede a cada uno de los pares
                ParCarreraMateria parCM = paresCM[j];

                // Cadenas auxiliares para hacer mas legible el código
                String carrera = parCM.getCarrera();
                String materia = parCM.getNombreMateria();

                instanciaMateria.agregarNombre(materia);
                

                // Si la carrera no es clave en el trie principal
                if (!this._carreras.pertenece(carrera)){                  
                    // Instancia el objeto que se almacenará en el nodo que es palabra
                    Carrera instanciaCarrera = new Carrera(carrera);
                    // Agrega la materia a la lista de materias de la carrera
                    instanciaCarrera.agregarMateria(materia, instanciaMateria);
                    // Agrega el parCarreraMateria al trie de la materia 
                    // que contiene a sus carreras asociadas
                    instanciaMateria.agregarCarrera(carrera, parCM); 
                    // Agrega al trie correspondiente la clave con su valor
                    this._carreras.insertar(carrera, instanciaCarrera);

                } else { // La carrera ya está en el trie principal                  
                    // Casting para acceder al valor asociado a una carrera ya definida
                    Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
                    carreraExistente.agregarMateria(materia, instanciaMateria);
                    // Agrega el parCarreraMateria al trie de la materia 
                    // que contiene a sus carreras asociadas
                    instanciaMateria.agregarCarrera(carrera, parCM);                         
                }                
            }
        }

        // Registro de estudiantes
        for (int i=0; i < libretasUniversitarias.length; i++){
            String lu = libretasUniversitarias[i];
            Estudiante estudiante = new Estudiante(lu);
            this._estudiantes.insertar(lu, estudiante);
        }   
    }

    public void inscribir(String estudiante, String carrera, String materia){
        // Casting para acceder a la instancia almacenada en el nodo que es una clave del trie
        Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
        if (carreraExistente!= null){
            // Casting para acceder a la instancia almacenada en el nodo que es una clave del trie
            Materia materiaExistente = (Materia) carreraExistente.obtenerMateria(materia);
            if (materiaExistente != null){
                // Casting para acceder a la instancia almacenada en el nodo que es una clave del trie
                Estudiante instanciaEstudiante = (Estudiante) this._estudiantes.obtenerValor(estudiante);
                ParCarreraMateria datos = new ParCarreraMateria(carrera, materia);
                instanciaEstudiante.anotarEnMateria(materia);
                materiaExistente.inscribirEstudiante(estudiante, datos);
            }  
        }        
    }

    public void agregarDocente(CargoDocente cargo, String carrera, String materia){
        Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
        if (carreraExistente!= null){
            Materia materiaExistente = (Materia) carreraExistente.obtenerMateria(materia);
            if (materiaExistente != null){
                int docente = cargo.ordinal();
                materiaExistente.agregarDocente(docente);
            }
        }
    }

    public int[] plantelDocente(String materia, String carrera){
        int[] res = {0,0,0,0};
        Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
        if (carreraExistente!= null){
            Materia materiaExistente = (Materia) carreraExistente.obtenerMateria(materia);
            if (materiaExistente != null){
                res =  materiaExistente.docentes();
            }
        }
        return res;
    }

    public void cerrarMateria(String materia, String carrera){
        Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
        Materia materiaABorrar = (Materia) carreraExistente.obtenerMateria(materia);
        ArrayList<ParCarreraMateria> pares = materiaABorrar.carrerasAsociadas();
        String[] estudiantesinscriptos = materiaABorrar.listaDeEstudiantes();
        for (ParCarreraMateria par: pares){
            // Borrado del trie
            Carrera carreraAModificar = (Carrera) this._carreras.obtenerValor(par.getCarrera());
            carreraAModificar.borrarMateria(par.getNombreMateria());
        }
        for(String estudiante: estudiantesinscriptos){
            // Borrado del trie
            Estudiante estudianteAModificar = (Estudiante) this._estudiantes.obtenerValor(estudiante);
            ParCarreraMateria datos = materiaABorrar.datosDeEstudiante(estudiante);
            estudianteAModificar.borrarMateria(datos.getNombreMateria());
        }
    }

    public int inscriptos(String materia, String carrera){
        Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
        Materia materiaExistente = (Materia) carreraExistente.obtenerMateria(materia);
        return materiaExistente.cantidadDeEstudiantes();     	    
    }

    public boolean excedeCupo(String materia, String carrera){
        Carrera carreraExistente = (Carrera) this._carreras.obtenerValor(carrera);
        Materia materiaExistente = (Materia) carreraExistente.obtenerMateria(materia);
        int estudiantesInscriptos = materiaExistente.cantidadDeEstudiantes();
        int[] cargosDocentes = materiaExistente.docentes();
        boolean condicion_1 = cargosDocentes[0] * 250 < estudiantesInscriptos; // Profesores
        boolean condicion_2 = cargosDocentes[1] * 100 < estudiantesInscriptos; // JTP
        boolean condicion_3 = cargosDocentes[2] * 20 < estudiantesInscriptos; // AY1
        boolean condicion_4 = cargosDocentes[3] * 30 < estudiantesInscriptos; // AY2
        return (condicion_1 || condicion_2 || condicion_3 || condicion_4);        	    
    }

    public String[] carreras(){
        return this._carreras.listaDeClaves();	    
    }

    public String[] materias(String carrera){
        Carrera carreraAux = (Carrera) this._carreras.obtenerValor(carrera);
        return carreraAux.listaDeMaterias(); 	    
    }

    public int materiasInscriptas(String estudiante){
        Estudiante estudianteExistente = (Estudiante) this._estudiantes.obtenerValor(estudiante);
        return estudianteExistente.cantidadDeMateriasCursadas();	    
    }
}
