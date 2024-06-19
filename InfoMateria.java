package aed;

public class InfoMateria{

    private ParCarreraMateria[] paresCarreraMateria;             // O(1)

    public InfoMateria(ParCarreraMateria[] paresCarreraMateria){ // O(1)
        this.paresCarreraMateria = paresCarreraMateria;          // O(1)
    }

    public ParCarreraMateria[] getParesCarreraMateria() {        // O(1)
        return this.paresCarreraMateria;                         // O(1)
    }
}
