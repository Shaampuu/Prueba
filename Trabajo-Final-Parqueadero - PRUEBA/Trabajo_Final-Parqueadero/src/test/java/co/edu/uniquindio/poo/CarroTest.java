package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */

/**
 * Unit test for simple App.
 */
public class CarroTest {
    private static final Logger LOG = Logger.getLogger(CarroTest.class.getName());

    @Test
    public void datosCompletos() {
        LOG.info("Iniciando test datosCompletos");
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoVehiculo tipoVehiculo = TipoVehiculo.CARRO;
        var carro = new Carro("KDM-645", 2013, propietario, tipoVehiculo);
        assertEquals("KDM-645", carro.getPlaca());
        assertEquals(2013, carro.getModelo());
        assertEquals(propietario, carro.getPropietario());
        assertEquals(tipoVehiculo, carro.getTipoVehiculo());
    }

    @Test
    public void datosNulos() {
        LOG.info("Iniciando test datosNulos");
        assertThrows(NullPointerException.class, () -> new Carro(null, 0, null, null));
        LOG.info("Finalizando test datosNulos");
    }

    @Test
    public void datosVacios() {
        LOG.info("Iniciando test datosVacíos");
        Propietario propietarioVacio = new Propietario(" ");
        TipoVehiculo tipoVehiculoVacio = TipoVehiculo.CARRO;
        assertThrows(IllegalArgumentException.class, () -> new Carro(" ", 0, propietarioVacio, tipoVehiculoVacio));
        LOG.info("Finalizando test datosVacíos");
    }

    @Test
    public void numeroNegativo() {
        LOG.info("Iniciando test númerosNegativos");
        Propietario propietario = new Propietario("Alexander Buitrago");
        TipoVehiculo tipoVehiculo = TipoVehiculo.CARRO;
        assertThrows(IllegalArgumentException.class, () -> new Carro("KDM-645", -2013, propietario, tipoVehiculo));
        LOG.info("Finalizando test númerosNegativos");
    }
}