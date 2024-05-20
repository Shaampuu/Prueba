package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CarroTest {

    @Test
    public void datosCompletos() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoVehiculo tipoVehiculo = TipoVehiculo.CARRO;
        Carro carro = new Carro("KDM-645", 2013, propietario, tipoVehiculo);
        assertEquals("KDM-645", carro.getPlaca());
        assertEquals(2013, carro.getModelo());
        assertEquals(propietario, carro.getPropietario());
        assertEquals(tipoVehiculo, carro.getTipoVehiculo());
    }

    @Test
    public void datosNulos() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoVehiculo tipoVehiculo = TipoVehiculo.CARRO;
        assertDoesNotThrow(() -> new Carro("KDM-645", 2013, propietario, tipoVehiculo));
    }

    @Test
    public void datosVacios() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoVehiculo tipoVehiculo = TipoVehiculo.CARRO;
        assertDoesNotThrow(() -> new Carro("KDM-645", 2013, propietario, tipoVehiculo));
    }

    @Test
    public void numeroNegativo() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoVehiculo tipoVehiculo = TipoVehiculo.CARRO;
        assertDoesNotThrow(() -> new Carro("KDM-645", 2013, propietario, tipoVehiculo));
    }

}
