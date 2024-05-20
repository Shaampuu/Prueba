package co.edu.uniquindio.poo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VehiculoTest {

    @Test
    public void testCrearVehiculo() {
        Propietario propietario = new Propietario("Juan");
        Vehiculo vehiculo = new Carro("ABC123", 2022, propietario, TipoCarro.CAMIONETA);
        assertNotNull(vehiculo);
        assertEquals("ABC123", vehiculo.getPlaca());
        assertEquals(2022, vehiculo.getModelo());
        assertEquals(propietario, vehiculo.getPropietario());
    }

    @Test
    public void testSetPlaca() {
        Vehiculo vehiculo = new Carro("ABC123", 2022, new Propietario("Juan"), TipoCarro.CAMIONETA);
        vehiculo.setPlaca("XYZ789");
        assertEquals("XYZ789", vehiculo.getPlaca());
    }

    @Test
    public void testSetModelo() {
        Vehiculo vehiculo = new Carro("ABC123", 2022, new Propietario("Juan"), TipoCarro.CAMIONETA);
        vehiculo.setModelo(2023);
        assertEquals(2023, vehiculo.getModelo());
    }

    @Test
    public void testSetPropietario() {
        Propietario propietario1 = new Propietario("Juan");
        Propietario propietario2 = new Propietario("Maria");
        Vehiculo vehiculo = new Carro("ABC123", 2022, propietario1, TipoCarro.CAMIONETA);
        vehiculo.setPropietario(propietario2);
        assertEquals(propietario2, vehiculo.getPropietario());
    }
}

