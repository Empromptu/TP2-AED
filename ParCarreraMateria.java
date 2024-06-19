package aed;

public class ParCarreraMateria {
    String carrera;                                                  // O(1)
    String nombreMateria;                                            // O(1)

    public ParCarreraMateria(String carrera, String nombreMateria) { // O(1)
        this.carrera = carrera;                                      // O(1)
        this.nombreMateria = nombreMateria;                          // O(1)
    }

    public String getNombreMateria() {                               // O(1)
        return this.nombreMateria;                                   // O(1)
    }

    public String getCarrera() {                                     // O(1)
        return this.carrera;                                         // O(1)
    }
}
