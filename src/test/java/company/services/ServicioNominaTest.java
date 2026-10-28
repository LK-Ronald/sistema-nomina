package company.services;

import com.company.enums.TipoTrabajador;
import com.company.models.*;
import com.company.services.CalculadoraNomina;
import com.company.services.ServicioNomina;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ServicioNominaTest {

    private ServicioNomina servicioNomina;

    @BeforeEach
    public void setUp() {
        servicioNomina = new ServicioNomina();
    }

    @Test
    public void testLiquidarTodosLosTiposDeEmpleado() {
        Empleado asalariado = new EmpleadoAsalariado("Juan", "1001", 6, new BigDecimal("3000000"));
        Empleado horas = new EmpleadoPorHoras("Maria", "2001", 2, 45, true, new BigDecimal("10000"));
        Empleado comision = new EmpleadoPorComision("Ronald", "3001", 2, new BigDecimal("1500000"), 0.05, new BigDecimal("30000000"));
        Empleado temporal = new EmpleadoTemporal("Pedro", "4001", 1, new BigDecimal("2000000"), 6);

        DetalleNomina detAsalariado = servicioNomina.liquidar(asalariado);
        assertNotNull(detAsalariado);
        assertEquals(new BigDecimal("4164340.00"), detAsalariado.getSalarioNeto());
        assertEquals(new BigDecimal("4164340.00"), servicioNomina.calcularNomina(asalariado));

        DetalleNomina detHoras = servicioNomina.liquidar(horas);
        assertNotNull(detHoras);
        assertEquals(new BigDecimal("463020.50"), detHoras.getSalarioNeto());
        assertEquals(new BigDecimal("463020.50"), servicioNomina.calcularNomina(horas));

        DetalleNomina detComision = servicioNomina.liquidar(comision);
        assertNotNull(detComision);
        assertEquals(new BigDecimal("4764340.00"), detComision.getSalarioNeto());
        assertEquals(new BigDecimal("4764340.00"), servicioNomina.calcularNomina(comision));

        DetalleNomina detTemporal = servicioNomina.liquidar(temporal);
        assertNotNull(detTemporal);
        assertEquals(new BigDecimal("1909560.00"), detTemporal.getSalarioNeto());
        assertEquals(new BigDecimal("1909560.00"), servicioNomina.calcularNomina(temporal));
    }

    @Test
    public void testLiquidarEmpleadoNuloLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> servicioNomina.liquidar(null));
        assertThrows(IllegalArgumentException.class, () -> servicioNomina.calcularNomina(null));
    }

    @Test
    public void testEmpleadoNoSoportadoLanzaExcepcion() {
        Empleado dummy = new Empleado("Test", "9999", 0) {
            @Override
            public TipoTrabajador getTipoTrabajador() {
                return null;
            }
        };

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> servicioNomina.liquidar(dummy));
        assertTrue(ex.getMessage().contains("Tipo de empleado no soportado"));
    }

    @Test
    public void testEmpleadoDerivadoUsaCalculadoraDeSuTipoBase() {
        class EmpleadoAsalariadoEspecial extends EmpleadoAsalariado {
            public EmpleadoAsalariadoEspecial() {
                super("Ana", "5555", 1, new BigDecimal("2500000.00"));
            }
        }

        Empleado empleado = new EmpleadoAsalariadoEspecial();

        assertEquals(new BigDecimal("3386950.00"), servicioNomina.calcularNomina(empleado));
        assertNotNull(servicioNomina.liquidar(empleado));
    }

    @Test
    public void testInyeccionDeDependenciasEnConstructor() {
        Map<Class<? extends Empleado>, CalculadoraNomina<?>> map = new HashMap<>();
        CalculadoraNomina<EmpleadoAsalariado> customCalc = new CalculadoraNomina<>() {
            @Override
            public BigDecimal calcularSalarioBruto(EmpleadoAsalariado empleado) {
                return empleado.getSalarioMensual();
            }

            @Override
            public BigDecimal calcularBonos(EmpleadoAsalariado empleado) {
                return BigDecimal.ZERO;
            }

            @Override
            public BigDecimal calcularBeneficios(EmpleadoAsalariado empleado) {
                return BigDecimal.ZERO;
            }

            @Override
            public BigDecimal calcularDeducciones(BigDecimal salarioBruto) {
                return BigDecimal.ZERO;
            }

            @Override
            public BigDecimal calcularNomina(EmpleadoAsalariado empleado) {
                return empleado.getSalarioMensual();
            }
        };

        map.put(EmpleadoAsalariado.class, customCalc);
        ServicioNomina customServicio = new ServicioNomina(map);

        EmpleadoAsalariado asalariado = new EmpleadoAsalariado("Ana", "5555", 1, new BigDecimal("2500000.00"));
        assertEquals(new BigDecimal("2500000.00"), customServicio.calcularNomina(asalariado));
    }
}
