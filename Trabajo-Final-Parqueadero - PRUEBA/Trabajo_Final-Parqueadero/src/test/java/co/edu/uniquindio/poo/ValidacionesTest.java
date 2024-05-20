package co.edu.uniquindio.poo;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidacionesTest {

    @Test
    public void testValidarPlacaValida() {
        String placa = "ABC123";
        assertDoesNotThrow(() -> {
            Validaciones.validarPlaca(placa);
        });
    }

    @Test
    public void testValidarPlacaNula() {
        assertThrows(IllegalArgumentException.class, () -> {
            Validaciones.validarPlaca(null);
        });
    }

    @Test
    public void testValidarPlacaVacia() {
        assertThrows(IllegalArgumentException.class, () -> {
            Validaciones.validarPlaca("");
        });
    }

    @Test
    public void testValidarModeloValido() {
        int modelo = 2022;
        assertDoesNotThrow(() -> {
            Validaciones.validarModelo(modelo);
        });
    }

    @Test
    public void testValidarModeloNegativo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Validaciones.validarModelo(-2022);
        });
    }

    @Test
    public void testValidarPropietarioValido() {
        Propietario propietario = new Propietario("Juan");
        assertDoesNotThrow(() -> {
            Validaciones.validarPropietario(propietario);
        });
    }

    @Test
    public void testValidarPropietarioNulo() {
        assertThrows(IllegalArgumentException.class, () -> {
            Validaciones.validarPropietario(null);
        });
    }
}

