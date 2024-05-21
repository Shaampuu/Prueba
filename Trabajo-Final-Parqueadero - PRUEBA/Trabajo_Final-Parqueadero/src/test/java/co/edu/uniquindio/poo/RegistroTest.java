package co.edu.uniquindio.poo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class RegistroTest {
    private Registro registro;

    @BeforeEach
    public void setUp() {
        Vehiculo vehiculo = new Carro("ABC123", 2022, new Propietario("Juan"), TipoCarro.CAMIONETA);
        LocalDateTime fechaEntrada = LocalDateTime.of(2024, 5, 20, 8, 0); // Ejemplo de fecha de entrada
        LocalDateTime fechaSalida = LocalDateTime.of(2024, 5, 20, 12, 0); // Ejemplo de fecha de salida
        registro = new Registro(vehiculo, fechaEntrada, fechaSalida);
    }

    @Test
    public void testCalcularCosto() {
        // Se espera que el costo sea 4 * tarifaPorHora
        double tarifaPorHora = registro.getVehiculo().getTarifaPorHora();
        double costoEsperado = 4 * tarifaPorHora;
        Assertions.assertEquals(costoEsperado, registro.calcularCosto());
    }
}
