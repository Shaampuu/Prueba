package co.edu.uniquindio.poo;

public class Propietario {
    private String nombre;

    public Propietario(String nombre){
        assert nombre != null && nombre.isBlank() : "El nombre debe ser diferente de null";

        this.nombre=nombre;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre=nombre;
    } 

    @Override
    public String toString(){
        return nombre;
    }

}
