package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PuestoTest {

    private Puesto puesto;
    private Vehiculo vehiculo;
    private Propietario propietario;

    @BeforeEach
    void setUp() {
        propietario = new Propietario("John Doe");
        vehiculo = new Carro(TipoCarro.CARRO, "ABC123", 2021, propietario);
        puesto = new Puesto(1, 1, vehiculo, 3, 3); // 3x3 grid, valid position
    }

    @Test
    void testConstructor() {
        assertEquals(1, puesto.getPosicionI());
        assertEquals(1, puesto.getPosicionJ());
        assertEquals(vehiculo, puesto.getVehiculo());
    }

    @Test
    void testSetPosicionI() {
        puesto.setPosicionI(2);
        assertEquals(2, puesto.getPosicionI());
    }

    @Test
    void testSetPosicionJ() {
        puesto.setPosicionJ(2);
        assertEquals(2, puesto.getPosicionJ());
    }

    @Test
    void testEstaOcupado() {
        assertTrue(puesto.estaOcupado());
        puesto.liberarPuesto();
        assertFalse(puesto.estaOcupado());
    }

    @Test
    void testOcuparPuesto() {
        Vehiculo nuevoVehiculo = new Carro(TipoCarro.CAMIONETA, "DEF456", 2022, propietario);
        puesto.ocuparPuesto(nuevoVehiculo);
        assertEquals(nuevoVehiculo, puesto.getVehiculo());
        assertTrue(puesto.estaOcupado());
    }

    @Test
    void testLiberarPuesto() {
        puesto.liberarPuesto();
        assertNull(puesto.getVehiculo());
        assertFalse(puesto.estaOcupado());
    }

    @Test
    void testConstructorInvalidPosition() {
        assertThrows(AssertionError.class, () -> new Puesto(-1, 1, vehiculo, 3, 3));
        assertThrows(AssertionError.class, () -> new Puesto(1, -1, vehiculo, 3, 3));
        assertThrows(AssertionError.class, () -> new Puesto(3, 1, vehiculo, 3, 3));
        assertThrows(AssertionError.class, () -> new Puesto(1, 3, vehiculo, 3, 3));
    }
}
