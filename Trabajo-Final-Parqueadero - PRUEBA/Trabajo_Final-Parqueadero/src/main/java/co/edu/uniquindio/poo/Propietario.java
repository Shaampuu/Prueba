package co.edu.uniquindio.poo;

public class Propietario {
    private String nombre;

    public Propietario(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del propietario no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre del propietario no puede ser nulo o vacío");
        }
        this.nombre = nombre;
    }
}
