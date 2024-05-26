package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MotoTest {

    private Moto moto;
    private Propietario propietario;

    @BeforeEach
    void setUp() {
        propietario = new Propietario("John Doe");
        moto = new Moto("XYZ123", 2022, propietario, 120, TipoMoto.MOTO_HIBRIDA);
    }

    @Test
    void testConstructor() {
        assertEquals("XYZ123", moto.getPlaca());
        assertEquals(2022, moto.getModelo());
        assertEquals(propietario, moto.getPropietario());
        assertEquals(120, moto.getVelocidadMaxima());
        assertEquals(TipoMoto.MOTO_HIBRIDA, moto.getTipoMoto());
    }

    @Test
    void testSetVelocidadMaxima() {
        moto.setVelocidadMaxima(130);
        assertEquals(130, moto.getVelocidadMaxima());
    }

    @Test
    void testSetTipoMoto() {
        moto.setTipoMoto(TipoMoto.MOTO_CLASICA);
        assertEquals(TipoMoto.MOTO_CLASICA, moto.getTipoMoto());
    }

    @Test
    void testCalcularTarifa() {
        moto.setHorasEstacionadas(5);
        moto.setTarifaPorHora(7.0); // Ensuring tarifaPorHora is set correctly
        assertEquals(35.0, moto.calcularTarifa(), 0.01);
    }

    @Test
    void testGetTarifaPorHora() {
        assertEquals(7.0, moto.getTarifaPorHora()); // velocidadMaxima is 120
        moto.setVelocidadMaxima(90);
        assertEquals(5.0, moto.getTarifaPorHora());
        moto.setVelocidadMaxima(160);
        assertEquals(10.0, moto.getTarifaPorHora());
    }

    @Test
    void testValidarPlaca() {
        assertThrows(AssertionError.class, () -> new Moto(null, 2022, propietario, 120, TipoMoto.MOTO_HIBRIDA));
        assertThrows(AssertionError.class, () -> new Moto("", 2022, propietario, 120, TipoMoto.MOTO_HIBRIDA));
    }

    @Test
    void testValidarModelo() {
        assertThrows(AssertionError.class, () -> new Moto("XYZ123", -1, propietario, 120, TipoMoto.MOTO_HIBRIDA));
    }

    @Test
    void testValidarPropietario() {
        assertThrows(AssertionError.class, () -> new Moto("XYZ123", 2022, null, 120, TipoMoto.MOTO_HIBRIDA));
    }

    @Test
    void testValidarVelocidadMaxima() {
        assertThrows(AssertionError.class, () -> new Moto("XYZ123", 2022, propietario, -10, TipoMoto.MOTO_HIBRIDA));
    }
}
