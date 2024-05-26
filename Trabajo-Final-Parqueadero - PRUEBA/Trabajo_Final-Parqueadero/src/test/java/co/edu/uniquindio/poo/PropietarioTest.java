package co.edu.uniquindio.poo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PropietarioTest {

    private Propietario propietario;

    @BeforeEach
    void setUp() {
        propietario = new Propietario("John Doe");
    }

    @Test
    void testConstructor() {
        assertEquals("John Doe", propietario.getNombre());
    }

    @Test
    void testSetNombre() {
        propietario.setNombre("Jane Doe");
        assertEquals("Jane Doe", propietario.getNombre());
    }

    @Test
    void testToString() {
        assertEquals("John Doe", propietario.toString());
        propietario.setNombre("Jane Doe");
        assertEquals("Jane Doe", propietario.toString());
    }

    @Test
    void testValidarNombre() {
        assertThrows(AssertionError.class, () -> new Propietario(null));
        assertThrows(AssertionError.class, () -> new Propietario(""));
        assertThrows(AssertionError.class, () -> new Propietario("  "));
    }
}
