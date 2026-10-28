package company.models;

import com.company.models.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;

public class EmpleadoAsalariadoTest {

    @Test
    public void testEmpleadoEsPermanente() {
        List<Empleado> empleados = List.of(
                new EmpleadoAsalariado("Juan", "123456789", 20, new BigDecimal(2_000_000)),
                new EmpleadoPorHoras("Maria", "987654321", 25, 40, true, new BigDecimal(10_000)),
                new EmpleadoPorComision("Ronald", "123456789", 2, new BigDecimal(1_000_000), 0.1, new BigDecimal("10000000")),
                new EmpleadoTemporal("Pedro", "987654321", 10, new BigDecimal(5_000_000), 10)
        );

        // Asalariado y Comision deben ser true
        assertTrue(empleados.get(0).esEmpleadoPermanente());
        assertFalse(empleados.get(1).esEmpleadoPermanente());
        assertTrue(empleados.get(2).esEmpleadoPermanente());
        assertFalse(empleados.get(3).esEmpleadoPermanente());
    }

}
