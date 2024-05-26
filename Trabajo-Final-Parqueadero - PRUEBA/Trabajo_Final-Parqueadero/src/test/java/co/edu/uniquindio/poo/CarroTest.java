package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarroTest {

    private Carro carro;
    private Propietario propietario;

    @BeforeEach
    void setUp() {
        propietario = new Propietario("John Doe");
        carro = new Carro(TipoCarro.CAMIONETA, "ABC123", 2021, propietario);
    }

    @Test
    void testConstructor() {
        assertEquals("ABC123", carro.getPlaca());
        assertEquals(2021, carro.getModelo());
        assertEquals(propietario, carro.getPropietario());
        assertEquals(TipoCarro.CAMIONETA, carro.getTipoCarro());
    }

    @Test
    void testSetTipoCarro() {
        carro.setTipoCarro(TipoCarro.DEPORTIVO);
        assertEquals(TipoCarro.DEPORTIVO, carro.getTipoCarro());
    }

    @Test
    void testCalcularTarifa() {
        carro.setHorasEstacionadas(5);
        carro.setTarifaPorHora(15.0);
        assertEquals(75.0, carro.calcularTarifa(), 0.01);
    }

    @Test
    void testGetTarifaPorHora() {
        assertEquals(15.0, carro.getTarifaPorHora());
        carro.setTipoCarro(TipoCarro.DEPORTIVO);
        assertEquals(20.0, carro.getTarifaPorHora());
    }

    @Test
    void testValidarPlaca() {
        assertThrows(AssertionError.class, () -> new Carro(TipoCarro.CAMIONETA, null, 2021, propietario));
        assertThrows(AssertionError.class, () -> new Carro(TipoCarro.CAMIONETA, "", 2021, propietario));
    }

    @Test
    void testValidarModelo() {
        assertThrows(AssertionError.class, () -> new Carro(TipoCarro.CAMIONETA, "ABC123", -1, propietario));
    }

    @Test
    void testValidarPropietario() {
        assertThrows(AssertionError.class, () -> new Carro(TipoCarro.CAMIONETA, "ABC123", 2021, null));
    }
}
