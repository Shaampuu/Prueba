package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
/**
 * Clase para probar el funcionamiento del código
 * @author Área de programación UQ
 * @since 2023-08
 * 
 * Licencia GNU/GPL V3.0 (https://raw.githubusercontent.com/grid-uq/poo/main/LICENSE) 
 */

public class ParqueaderoTest {
    private static final Logger LOG = Logger.getLogger(ParqueaderoTest.class.getName());


    @Test
    public void datosCompletos() {
        LOG.info("Iniciando test datosCompletos");
        var parqueadero = new Parqueadero(12,12);
        assertEquals(144, parqueadero.getCantidadPuestos() );
        LOG.info("Finalizando test datosCompletos");
    }

    @Test 
    public void datosVacios(){
        LOG.info("Iniciando test datosVacios");
        assertThrows(Throwable.class, () -> new Parqueadero((int)0, (int)0));
        LOG.info("Finalizando test datosVacios");
    }

    @Test
    public void valoresErroneosDePuestos(){
        LOG.info("Iniciando test valoresErroneosDePuestos");
        var parqueadero = new Parqueadero(12, 12);
        assertNotEquals(parqueadero.getCantidadPuestos(), 24);
        LOG.info("Finalizando test valoresErroneosDePuestos");
    }

    @Test
    public void agregarMoto(){
        LOG.info("Iniciando test agregarMoto");
        var propietario = new Propietario("Veronica Martinez");
        var parqueadero = new Parqueadero(12, 12);
        var moto = new Moto("TTA-98F", (int) 2023, propietario, TipoVehiculo.MOTO_CLASICA, (int) 130);
        parqueadero.agregarMoto(moto);
        assertTrue(parqueadero.getMotos().contains(moto));
        assertEquals(1,parqueadero.getMotos().size());
        LOG.info("Finalizando test agregarMoto");
    }

    @Test 
    public void agregarCarro(){
        LOG.info("Iniciando test agregarMoto");
        var propietario = new Propietario("Alexander Buitrago");
        var parqueadero = new Parqueadero(12, 12);
        var carro = new Carro("KDM-645", (int) 2013, propietario, TipoVehiculo.CARRO);
        parqueadero.agregarCarro(carro);
        assertTrue(parqueadero.getCarros().contains(carro));
        assertEquals(1, parqueadero.getCarros().size());
        LOG.info("Finalizando test agregarMoto");
    }

    @Test
    public void agregarMotoRepetido(){
        LOG.info("Iniciando test agregarMotoRepetida");
        var propietario1 = new Propietario("Veronica Martinez");
        var propietario2 = new Propietario("Nicolas Buitrago");
        var parqueadero = new Parqueadero(12, 12);
        var moto1 = new Moto("KUK-44F", (int) 2022, propietario1, TipoVehiculo.MOTO_CLASICA, (int) 120);
        var moto2 = new Moto("KUK-44F", 2022, propietario2, TipoVehiculo.MOTO_CLASICA, (int) 120);
        parqueadero.agregarMoto(moto1);
        assertThrows(Throwable.class, () -> parqueadero.agregarMoto(moto2));
        LOG.info("Finalizando test agregarMotoRepetida");                                              
    }

    @Test
    public void agregarCarroRepetido(){
        LOG.info("Iniciando test agregarCarroRepetido");
        var propietario1 = new Propietario("Alexander Buitrago");
        var propietario2 = new Propietario("Juan Jose Buitrago");
        var parqueadero = new Parqueadero(12, 12);
        var carro1 = new Carro("KDM-645", (int) 2013, propietario1, TipoVehiculo.CARRO);
        var carro2 = new Carro("KDM-645", (int) 2013, propietario2, TipoVehiculo.CARRO);
        parqueadero.agregarCarro(carro1);
        assertThrows(Throwable.class, () -> parqueadero.agregarCarro(carro2));
        LOG.info("Finalizando test agregarCarroRepetida");
    }

    @Test
    public void ocuparPuesto(){
        LOG.info("Iniciando test ocuparPuesto");
        var parqueadero = new Parqueadero(12, 12);
        var propietario = new Propietario("Veronica Martinez");
        var moto = new Moto("KDM-645", (int) 2013, propietario, TipoVehiculo.MOTO_CLASICA, (int) 120);

        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
        parqueadero.ocuparPuestos(0, 0, moto, 1500.0);
        assertFalse(parqueadero.verificarDisponibilidad(0,0));
        assertEquals(moto, parqueadero.getHistorialRegistros().get(0).getVehiculo());
        LOG.info("Finalizando test ocuparPuesto");
    }

    @Test
    public void buscarYParquearVehiculo(){
        LOG.info("Iniciando test buscarYParquearVehiculo");
        var parqueadero = new Parqueadero(3,3);
        var propietario = new Propietario("Sofia Osorio");
        var moto = new Moto("TTA-98F", 2023, propietario, TipoVehiculo.MOTO_HIBRIDA, 140);

        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
        assertTrue(parqueadero.verificarDisponibilidad(0, 1));
        assertTrue(parqueadero.verificarDisponibilidad(0, 2));
        assertTrue(parqueadero.verificarDisponibilidad(1, 0));
        assertTrue(parqueadero.verificarDisponibilidad(1, 1));
        assertTrue(parqueadero.verificarDisponibilidad(1, 2));
        assertTrue(parqueadero.verificarDisponibilidad(2, 0));
        assertTrue(parqueadero.verificarDisponibilidad(2, 1));
        assertTrue(parqueadero.verificarDisponibilidad(2, 2));

        boolean parqueado = parqueadero.buscarYParquearVehiculo(moto, 2000.0);
        assertTrue(parqueado);

        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
        assertEquals(moto, parqueadero.getHistorialRegistros().get(0).getVehiculo());

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if (parqueadero.verificarDisponibilidad(i, j)){
                    parqueadero.ocuparPuestos(i, j, new Carro("KDM-645", 2013, propietario, TipoVehiculo.CARRO), 2500.0);
                }
            }
        }
        LOG.info("Finalizando test buscarYParquearVehiculo");
    }

    @Test
    public void sinEspacio(){
        LOG.info("Iniciando test sinEspacio");
        var parqueadero = new Parqueadero(3, 3);
        var propietario = new Propietario("Jorge Simon Nieto");
        var moto = new Moto("SOC-19F", 2024, propietario, TipoVehiculo.MOTO_HIBRIDA, 200);
        boolean noParqueado = parqueadero.buscarYParquearVehiculo(moto, 2000.0);
        assertFalse(noParqueado);
        LOG.info("Finalizando test sinEspacio"); 
    }

    @Test 
    public void liberarPuesto(){
        LOG.info("Iniciando test liberarPuesto");
        var parqueadero = new Parqueadero(12, 12);
        var propietario = new Propietario("Alexander Buitrago");
        var carro = new Carro("KDM-645", 2013, propietario, TipoVehiculo.CARRO);

        parqueadero.ocuparPuestos(0, 0, carro, 2500.0);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
        parqueadero.liberarPuesto(0, 0);
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
        assertNotNull(parqueadero.getHistorialRegistros().get(0).getHoraSalida());
        LOG.info("Finalizando test ocuparPuestos");
    }

    @Test
    public void  generarRepoteDiario(){
        LOG.info ("Iniciando test generarReporteDiario");
        Parqueadero parqueadero = new Parqueadero( 12, 12);
        Propietario propietario1 = new Propietario("Sofia Osorio");
        Propietario propietario2 = new Propietario("Nicolas Buitrago");
        Vehiculo carro = new Carro("KDM-645", 2013, propietario1, TipoVehiculo.CARRO);
        Vehiculo moto = new Moto("KUK-44F", 2022, propietario2, TipoVehiculo.MOTO_HIBRIDA, 140);

        parqueadero.ocuparPuestos(0, 0, moto, 1500.0);
        parqueadero.ocuparPuestos(0, 1, carro, 2500.0);
        parqueadero.liberarPuesto(0, 0);
        parqueadero.liberarPuesto(0, 1);

        LocalDate hoy = LocalDate.now();
        Map<TipoVehiculo, Double> reporteDiario = parqueadero.generarRepoteDiario(hoy);

        assertEquals(1500.0, reporteDiario.get(TipoVehiculo.MOTO_HIBRIDA));
        assertEquals(2500.0, reporteDiario.get(TipoVehiculo.CARRO));
        assertEquals(0.0, reporteDiario.get(TipoVehiculo.MOTO_CLASICA));
        LOG.info("Finalizando test generarReporteDiario");
    }

    @Test 
    public void generarReporteMensual(){
        LOG.info("Iniciando test generarReporteMensual");
        Parqueadero parqueadero = new Parqueadero( 12, 12);
        Propietario propietario1 = new Propietario("Sofia Osorio");
        Propietario propietario2 = new Propietario("Nicolas Buitrago");
        Vehiculo carro = new Carro("KDM-645", 2013, propietario1, TipoVehiculo.CARRO);
        Vehiculo moto = new Moto("KUK-44F", 2022, propietario2, TipoVehiculo.MOTO_HIBRIDA, 140);

        parqueadero.ocuparPuestos(0, 0, moto, 1500.0);
        parqueadero.ocuparPuestos(0, 1, carro, 2500.0);
        parqueadero.liberarPuesto(0, 0);
        parqueadero.liberarPuesto(0, 1);

        int mesActual = LocalDate.now().getMonthValue();
        int añoActual = LocalDate.now().getYear();
        Map<TipoVehiculo, Double> reporteMensual = parqueadero.generarReporteMensual(mesActual, añoActual);

        assertEquals(1500.0, reporteMensual.get(TipoVehiculo.MOTO_HIBRIDA));
        assertEquals(2500.0, reporteMensual.get(TipoVehiculo.CARRO));
        assertEquals(0.0, reporteMensual.get(TipoVehiculo.MOTO_CLASICA));
    }
}