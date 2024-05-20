package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import co.edu.uniquindio.poo.*;

import java.time.LocalDate;
import java.util.Map;

public class TestParqueadero {

    private Parqueadero parqueadero;
    private Propietario propietario1;
    private Propietario propietario2;
    private Carro carro;
    private Moto moto;

    @BeforeEach
    public void setUp() {
        parqueadero = new Parqueadero(3, 3); // Asegúrate de que las dimensiones sean válidas
        propietario1 = new Propietario("Juan");
        propietario2 = new Propietario("Maria");
        carro = new Carro("ABC123", 2020, propietario1, TipoVehiculo.CARRO);
        moto = new Moto("XYZ789", 2021, propietario2, TipoVehiculo.MOTO_CLASICA, 120);
    }

    @Test
    public void testAgregarCarro() {
        parqueadero.agregarCarro(carro);
        assertNotNull(parqueadero.getCarro("ABC123"));
    }

    @Test
    public void testAgregarMoto() {
        parqueadero.agregarMoto(moto);
        assertNotNull(parqueadero.getMoto("XYZ789"));
    }

    @Test
    public void testBuscarYParquearCarro() {
        parqueadero.agregarCarro(carro);
        boolean parqueado = parqueadero.buscarYParquearVehiculo(carro, 10.0);
        assertTrue(parqueado);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testBuscarYParquearMoto() {
        parqueadero.agregarMoto(moto);
        boolean parqueado = parqueadero.buscarYParquearVehiculo(moto, 5.0);
        assertTrue(parqueado);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testLiberarPuesto() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro, 10.0);
        parqueadero.liberarPuesto(0, 0);
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testObtenerPropietario() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro, 10.0);
        String nombrePropietario = parqueadero.obtenerPropietario(0, 0);
        assertEquals("Juan", nombrePropietario);
    }

    @Test
    public void testGenerarReporteDiario() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro, 10.0);
        parqueadero.liberarPuesto(0, 0);

        Map<TipoVehiculo, Double> reporteDiario = parqueadero.generarRepoteDiario(LocalDate.now());
        assertTrue(reporteDiario.get(TipoVehiculo.CARRO) > 0);
    }

    @Test
    public void testGenerarReporteMensual() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro, 10.0);
        parqueadero.liberarPuesto(0, 0);

        Map<TipoVehiculo, Double> reporteMensual = parqueadero.generarReporteMensual(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        assertTrue(reporteMensual.get(TipoVehiculo.CARRO) > 0);
    }

    @Test
    public void testVerificarDisponibilidad() {
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testOcuparPuestos() {
        parqueadero.agregarCarro(carro);
        parqueadero.ocuparPuestos(1, 1, carro, 10.0);
        assertFalse(parqueadero.verificarDisponibilidad(1, 1));
    }

    @Test
    public void testHistorialRegistros() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro, 10.0);
        parqueadero.liberarPuesto(0, 0);

        assertFalse(parqueadero.getHistorialRegistros().isEmpty());
    }
}
