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
public class MotoTest {
    private static final Logger LOG = Logger.getLogger(MotoTest.class.getName());

    @Test
    public void datosCompletos(){
        LOG.info("Iniciando test datosCompletos");
        var propietario = new Propietario ("Veronica Martinez");
        var moto = new Moto("KUK-44F", (int) 2022, propietario, TipoVehiculo.MOTO_CLASICA, (int)120);
        assertEquals("KUK-44F", moto.getPlaca());
        assertEquals((int)2022, moto.getModelo());
        assertEquals("Veronica Martinez", moto.getPropietario());
        assertEquals((int)120, moto.getVelocidadMaxima());
        LOG.info("Finalizando test datosCompletos");
    }

    @Test
    public void datosNulos(){
        LOG.info("Iniciando test datosNulos");
        assertThrows(Throwable.class, () -> new Moto(null, (int) 0, null, null, (int) 0));
        LOG.info("Finalizando test datosNulos");
    }

    @Test
    public void datosVacios(){
        LOG.info("Iniciando test datosVacíos");
        var propietario = new Propietario("Veronica Martinez");
        assertThrows(Throwable.class, () -> new Moto(" ", (int) 0, propietario, TipoVehiculo.MOTO_CLASICA, (int) 0));
        LOG.info("Finalizando test datosVacíos");
    }

    @Test
    public void numeroNegativo(){
        LOG.info("Iniciando test númeroNegativo");
        var propietario = new Propietario("Veronica Martinez");
        assertThrows(Throwable.class, () -> new Moto("TTA-98F", (int) -2023, propietario, TipoVehiculo.MOTO_CLASICA, (int) -130));
        TesteadorDeNumeroNegativo testeador = new TesteadorDeNumeroNegativo();
        assertThrows(Throwable.class, testeador);
        LOG.info("Finalizando test númeroNegativo");
    }

    private static class TesteadorDeNumeroNegativo implements Executable{
        @Override
        public void execute () throws Throwable {
            var propietario = new Propietario("Veronica Martinez");
            new Moto("TTA-98F", (int) -2023, propietario, TipoVehiculo.MOTO_CLASICA, (int) -130);
        }
    }
    
}