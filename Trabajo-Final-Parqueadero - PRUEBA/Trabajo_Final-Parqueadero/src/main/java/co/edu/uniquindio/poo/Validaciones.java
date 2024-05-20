package co.edu.uniquindio.poo;

public class Validaciones {
    public static void validarPlaca(String placa) {
        if (placa == null || placa.isEmpty()) {
            throw new IllegalArgumentException("La placa no puede ser nula o vacía");
        }
    }

    public static void validarModelo(int modelo) {
        if (modelo <= 0) {
            throw new IllegalArgumentException("El modelo debe ser un número positivo");
        }
    }

    public static void validarPropietario(Propietario propietario) {
        if (propietario == null) {
            throw new IllegalArgumentException("El propietario no puede ser nulo");
        }
    }
}
