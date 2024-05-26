package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RegistroTest {

    private Registro registro;
    private Carro carro;
    private Propietario propietario;

    @BeforeEach
    void setUp() {
        propietario = new Propietario("John Doe");
        carro = new Carro(TipoCarro.CARRO, "ABC123", 2021, propietario);
        LocalDateTime fechaEntrada = LocalDateTime.now().minusHours(2);
        registro = new Registro(carro, fechaEntrada);
    }

    @Test
    void testConstructorWithFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.now().minusHours(2);
        LocalDateTime fechaSalida = LocalDateTime.now();
        Registro registroConSalida = new Registro(carro, fechaEntrada, fechaSalida);
        assertEquals(carro, registroConSalida.getVehiculo());
        assertEquals(fechaEntrada, registroConSalida.getFechaEntrada());
        assertEquals(fechaSalida, registroConSalida.getFechaSalida());
    }

    @Test
    void testConstructorWithoutFechaSalida() {
        LocalDateTime fechaEntrada = LocalDateTime.now().minusHours(2);
        Registro registroSinSalida = new Registro(carro, fechaEntrada);
        assertEquals(carro, registroSinSalida.getVehiculo());
        assertEquals(fechaEntrada, registroSinSalida.getFechaEntrada());
        assertEquals(null, registroSinSalida.getFechaSalida());
    }

    @Test
    void testSetFechaSalida() {
        LocalDateTime fechaSalida = LocalDateTime.now();
        registro.setFechaSalida(fechaSalida);
        assertEquals(fechaSalida, registro.getFechaSalida());
    }

    @Test
    void testCalcularCosto() {
        LocalDateTime fechaSalida = LocalDateTime.now();
        registro.setFechaSalida(fechaSalida);
        double costo = registro.calcularCosto();
        assertNotNull(costo);
    }

    @Test
    void testCalcularCostoWithoutFechaSalida() {
        assertThrows(AssertionError.class, () -> registro.calcularCosto());
    }
}
