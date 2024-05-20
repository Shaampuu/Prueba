package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PuestoTest {

    @Test
    public void testConstructor() {
        Propietario propietario = new Propietario("Juan");
        Carro carro = new Carro(TipoCarro.CAMIONETA, "ABC123", 2020, propietario);
        Puesto puesto = new Puesto(0, 0, carro, 5, 5);
        assertEquals(0, puesto.getPosicionI());
        assertEquals(0, puesto.getPosicionJ());
        assertEquals(carro, puesto.getVehiculo());
    }

    @Test
    public void testEstaOcupado() {
        Puesto puestoVacio = new Puesto(0, 0, null, 5, 5);
        assertFalse(puestoVacio.estaOcupado());

        Propietario propietario = new Propietario("Juan");
        Carro carro = new Carro(TipoCarro.CAMIONETA, "ABC123", 2020, propietario);
        Puesto puestoOcupado = new Puesto(0, 0, carro, 5, 5);
        assertTrue(puestoOcupado.estaOcupado());
    }

    @Test
    public void testOcuparPuesto() {
        Puesto puesto = new Puesto(0, 0, null, 5, 5);
        assertFalse(puesto.estaOcupado());

        Propietario propietario = new Propietario("Juan");
        Carro carro = new Carro(TipoCarro.CAMIONETA, "ABC123", 2020, propietario);
        puesto.ocuparPuesto(carro);
        assertTrue(puesto.estaOcupado());
        assertEquals(carro, puesto.getVehiculo());
    }

    @Test
    public void testLiberarPuesto() {
        Propietario propietario = new Propietario("Juan");
        Carro carro = new Carro(TipoCarro.CAMIONETA, "ABC123", 2020, propietario);
        Puesto puesto = new Puesto(0, 0, carro, 5, 5);
        assertTrue(puesto.estaOcupado());

        puesto.liberarPuesto();
        assertFalse(puesto.estaOcupado());
        assertEquals(null, puesto.getVehiculo());
    }
}
