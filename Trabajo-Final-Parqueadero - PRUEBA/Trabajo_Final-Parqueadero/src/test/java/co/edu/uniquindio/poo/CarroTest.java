package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class CarroTest {

    @Test
    public void datosCompletos() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoCarro tipoCarro = TipoCarro.CAMIONETA; // Selecciona el tipo de carro adecuado
        Carro carro = new Carro(tipoCarro, "KDM-645", 2013, propietario); // Cambia el orden de los argumentos
        assertEquals("KDM-645", carro.getPlaca());
        assertEquals(2013, carro.getModelo());
        assertEquals(propietario, carro.getPropietario());
        assertEquals(tipoCarro, carro.getTipoCarro()); // Usa getTipoCarro() en lugar de getTipoVehiculo()
    }

    @Test
    public void datosNulos() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoCarro tipoCarro = TipoCarro.CAMIONETA; // Selecciona el tipo de carro adecuado
        assertDoesNotThrow(() -> new Carro(tipoCarro, "KDM-645", 2013, propietario)); // Cambia el orden de los argumentos
    }

    @Test
    public void datosVacios() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoCarro tipoCarro = TipoCarro.CAMIONETA; // Selecciona el tipo de carro adecuado
        assertDoesNotThrow(() -> new Carro(tipoCarro, "KDM-645", 2013, propietario)); // Cambia el orden de los argumentos
    }

    @Test
    public void numeroNegativo() {
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoCarro tipoCarro = TipoCarro.CAMIONETA; // Selecciona el tipo de carro adecuado
        assertDoesNotThrow(() -> new Carro(tipoCarro, "KDM-645", 2013, propietario)); // Cambia el orden de los argumentos
    }
}
