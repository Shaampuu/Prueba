package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Map;

class ParqueaderoTest {

    private Parqueadero parqueadero;
    private Propietario propietario1;
    private Propietario propietario2;
    private Carro carro;
    private Moto moto;

    @BeforeEach
    void setUp() {
        parqueadero = new Parqueadero(2, 2); // 2x2 grid
        propietario1 = new Propietario("John Doe");
        propietario2 = new Propietario("Jane Doe");
        carro = new Carro(TipoCarro.CARRO, "ABC123", 2021, propietario1);
        moto = new Moto("XYZ123", 2022, propietario2, 120, TipoMoto.MOTO_HIBRIDA);
    }

    @Test
    void testAgregarCarro() {
        parqueadero.agregarCarro(carro);
        assertEquals(1, parqueadero.getCarros().size());
        assertEquals(carro, parqueadero.getCarro("ABC123"));
    }

    @Test
    void testAgregarMoto() {
        parqueadero.agregarMoto(moto);
        assertEquals(1, parqueadero.getMotos().size());
        assertEquals(moto, parqueadero.getMoto("XYZ123"));
    }

    @Test
    void testBuscarYParquearVehiculo() {
        parqueadero.agregarCarro(carro);
        boolean parked = parqueadero.buscarYParquearVehiculo(carro);
        assertTrue(parked);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    void testLiberarPuesto() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    void testObtenerPropietario() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        assertEquals("John Doe", parqueadero.obtenerPropietario(0, 0));
    }

    @Test
    void testGenerarReporteDiario() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);
        Map<TipoVehiculo, Double> reporteDiario = parqueadero.generarReporteDiario(LocalDate.now());
        assertEquals(1, reporteDiario.size());
        assertTrue(reporteDiario.containsKey(TipoVehiculo.CARRO));
    }

    @Test
    void testGenerarReporteMensual() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);
        Map<TipoVehiculo, Double> reporteMensual = parqueadero.generarReporteMensual(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
        assertEquals(1, reporteMensual.size());
        assertTrue(reporteMensual.containsKey(TipoVehiculo.CARRO));
    }

    @Test
    void testVerificarDisponibilidad() {
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
    }
}
