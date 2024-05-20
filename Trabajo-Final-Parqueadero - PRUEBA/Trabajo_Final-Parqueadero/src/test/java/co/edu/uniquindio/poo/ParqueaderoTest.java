package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.Map;

public class ParqueaderoTest {

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
        carro = new Carro(TipoCarro.CAMIONETA, "ABC123", 2020, propietario1); // Modifica para usar el enum TipoCarro
        moto = new Moto("XYZ789", 2021, propietario2, 120); // Ajusta el constructor de Moto
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
        boolean parqueado = parqueadero.buscarYParquearVehiculo(carro);
        assertTrue(parqueado);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testBuscarYParquearMoto() {
        parqueadero.agregarMoto(moto);
        boolean parqueado = parqueadero.buscarYParquearVehiculo(moto);
        assertTrue(parqueado);
        assertFalse(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testLiberarPuesto() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testObtenerPropietario() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        String nombrePropietario = parqueadero.obtenerPropietario(0, 0);
        assertEquals("Juan", nombrePropietario);
    }

    @Test
    public void testGenerarReporteDiario() {
        // Agregar carro y registrarlo en el parqueadero
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);

        // Generar el reporte diario para la fecha actual
        Map<TipoVehiculo, Double> reporteDiario = parqueadero.generarReporteDiario(LocalDate.now());

        // Validar que el reporte no sea nulo
        assertNotNull(reporteDiario);

        // Validar que el reporte no esté vacío
        assertFalse(reporteDiario.isEmpty(), "El reporte diario no debe ser vacío");

        // Validar que el reporte contenga el tipo de vehículo del carro
        assertTrue(reporteDiario.containsKey(carro.getTipoCarro().getTipoVehiculo()));

        // Validar que el valor del reporte para el tipo de vehículo del carro sea mayor
        // que 0
        assertTrue(reporteDiario.get(carro.getTipoCarro().getTipoVehiculo()) >= 0);
    }

    @Test
    public void testGenerarReporteMensual() {
        // Agregar carro y registrarlo en el parqueadero
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);
    
        // Generar el reporte mensual para el mes y año actuales
        Map<TipoVehiculo, Double> reporteMensual = parqueadero.generarReporteMensual(LocalDate.now().getMonthValue(), LocalDate.now().getYear());
    
        // Validar que el reporte no sea nulo
        assertNotNull(reporteMensual);
    
        // Validar que el reporte no esté vacío
        assertFalse(reporteMensual.isEmpty(), "El reporte mensual no debe ser vacío");
    
        // Validar que el reporte contenga el tipo de vehículo del carro
        assertTrue(reporteMensual.containsKey(carro.getTipoCarro().getTipoVehiculo()));
    
        // Validar que el valor del reporte para el tipo de vehículo del carro sea mayor que 0
        assertTrue(reporteMensual.get(carro.getTipoCarro().getTipoVehiculo()) >= 0);
    }
    

    @Test
    public void testVerificarDisponibilidad() {
        assertTrue(parqueadero.verificarDisponibilidad(0, 0));
    }

    @Test
    public void testOcuparPuestos() {
        parqueadero.agregarCarro(carro);
        parqueadero.ocuparPuestos(1, 1, carro);
        assertFalse(parqueadero.verificarDisponibilidad(1, 1));
    }

    @Test
    public void testHistorialRegistros() {
        parqueadero.agregarCarro(carro);
        parqueadero.buscarYParquearVehiculo(carro);
        parqueadero.liberarPuesto(0, 0);

        assertFalse(parqueadero.getHistorialRegistros().isEmpty());
    }
}
