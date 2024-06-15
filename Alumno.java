package aed;

import java.util.List;

public class Alumno {

    private String lu;
    private List<Materia> materiasInscriptas; 

    public Alumno(String lu) {
        this.lu = lu;
    }

    public void anotarEn(Materia materia){
        this.materiasInscriptas.add(materia);
    }
    

}
