package company.util;

import com.company.exceptions.HorasInvalidasException;
import com.company.exceptions.SalarioInvalidoException;
import com.company.exceptions.VentaInvalidaException;
import com.company.util.Validaciones;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class ValidacionesTest {

    @Test
    public void testValidarHorasValidas() {
        assertEquals(0, Validaciones.validarHoras(0));
        assertEquals(40, Validaciones.validarHoras(40));
        assertEquals(50, Validaciones.validarHoras(50));
    }

    @Test
    public void testValidarHorasNegativasLanzaExcepcion() {
        assertThrows(HorasInvalidasException.class, () -> Validaciones.validarHoras(-1));
        assertThrows(HorasInvalidasException.class, () -> Validaciones.validarHoras(-40));
    }

    @Test
    public void testValidarVentasValidas() {
        assertEquals(0, Validaciones.validarCantidad(0));
        assertEquals(10, Validaciones.validarCantidad(10));
        assertEquals(BigDecimal.ZERO, Validaciones.validarVentas(BigDecimal.ZERO));
        assertEquals(new BigDecimal("1000000"), Validaciones.validarVentas(new BigDecimal("1000000")));
    }

    @Test
    public void testValidarVentasNegativasLanzaExcepcion() {
        assertThrows(VentaInvalidaException.class, () -> Validaciones.validarCantidad(-1));
        assertThrows(VentaInvalidaException.class, () -> Validaciones.validarVentas(new BigDecimal("-0.01")));
        assertThrows(VentaInvalidaException.class, () -> Validaciones.validarVentas(null));
    }

    @Test
    public void testValidarSalarioValido() {
        BigDecimal salario = new BigDecimal("1000000");
        assertEquals(salario, Validaciones.validarSalarioNeto(salario));
    }

    @Test
    public void testValidarSalarioInvalidoLanzaExcepcion() {
        assertThrows(SalarioInvalidoException.class, () -> Validaciones.validarSalarioNeto(BigDecimal.ZERO));
        assertThrows(SalarioInvalidoException.class, () -> Validaciones.validarSalarioNeto(new BigDecimal("-1000")));
        assertThrows(SalarioInvalidoException.class, () -> Validaciones.validarSalarioNeto(null));
    }

    @Test
    public void testValidarSalarioNetoCalculadoNegativoLanzaExcepcion() {
        assertThrows(SalarioInvalidoException.class, () -> Validaciones.validarSalarioNetoCalculado(new BigDecimal("-1")));
        assertThrows(SalarioInvalidoException.class, () -> Validaciones.validarSalarioNetoCalculado(null));
        assertDoesNotThrow(() -> Validaciones.validarSalarioNetoCalculado(BigDecimal.ZERO));
        assertDoesNotThrow(() -> Validaciones.validarSalarioNetoCalculado(new BigDecimal("100")));
    }
}
