package co.edu.uniquindio.poo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PropietarioTest {

    @Test
    public void testConstructorNombreValido() {
        String nombre = "Juan";
        Propietario propietario = new Propietario(nombre);
        assertEquals(nombre, propietario.getNombre());
    }

    @Test
    public void testConstructorNombreNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Propietario(null);
        });
    }

    @Test
    public void testConstructorNombreVacio() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Propietario("");
        });
    }

    @Test
    public void testSetNombreValido() {
        Propietario propietario = new Propietario("Juan");
        String nuevoNombre = "Pedro";
        propietario.setNombre(nuevoNombre);
        assertEquals(nuevoNombre, propietario.getNombre());
    }

    @Test
    public void testSetNombreNulo() {
        Propietario propietario = new Propietario("Juan");
        assertThrows(IllegalArgumentException.class, () -> {
            propietario.setNombre(null);
        });
    }

    @Test
    public void testSetNombreVacio() {
        Propietario propietario = new Propietario("Juan");
        assertThrows(IllegalArgumentException.class, () -> {
            propietario.setNombre("");
        });
    }
}

