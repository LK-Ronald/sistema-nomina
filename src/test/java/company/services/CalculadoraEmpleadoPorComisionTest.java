package company.services;

import com.company.models.EmpleadoPorComision;
import com.company.services.CalculadoraEmpleadoPorComision;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraEmpleadoPorComisionTest {

    private CalculadoraEmpleadoPorComision calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraEmpleadoPorComision();
    }

    @Test
    public void testEmpleadoPorComisionVentasMenoresIgualesUmbral() {
        // Salario Base: 1.000.000
        // Porcentaje comision: 5% (0.05)
        // Ventas: 10.000.000 (<= 20.000.000) -> Bono Ventas = 0
        // Comision = 10.000.000 * 0.05 = 500.000
        // Salario Bruto = 1.000.000 + 500.000 = 1.500.000
        // Beneficio Permanente (Bono Alimentación): 1.000.000
        // Deducciones: 1.500.000 * (0.04 + 0.00522) = 60.000 + 7.830 = 67.830
        // Neto = 1.500.000 + 0 + 1.000.000 - 67.830 = 2.432.170,00
        EmpleadoPorComision emp = new EmpleadoPorComision("Ronald", "123", 2, new BigDecimal("1000000"), 0.05, new BigDecimal("10000000"));

        BigDecimal bruto = calculadora.calcularSalarioBruto(emp);
        assertEquals(0, new BigDecimal("1500000").compareTo(bruto));

        BigDecimal bonos = calculadora.calcularBonos(emp);
        assertEquals(BigDecimal.ZERO, bonos);

        BigDecimal beneficios = calculadora.calcularBeneficios(emp);
        assertEquals(new BigDecimal("1000000"), beneficios);

        BigDecimal deducciones = calculadora.calcularDeducciones(emp);
        assertEquals(0, new BigDecimal("67830.00").compareTo(deducciones));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("2432170.00"), neto);
    }

    @Test
    public void testEmpleadoPorComisionVentasSuperioresUmbral() {
        // Salario Base: 1.500.000
        // Porcentaje comision: 5% (0.05)
        // Ventas: 30.000.000 (> 20.000.000)
        // Comision: 30.000.000 * 0.05 = 1.500.000
        // Salario Bruto: 1.500.000 + 1.500.000 = 3.000.000
        // Bono adicional ventas (3% de ventas): 30.000.000 * 0.03 = 900.000
        // Beneficio Permanente (Bono Alimentación): 1.000.000
        // Deducciones: 3.000.000 * 0.04522 = 135.660
        // Neto = 3.000.000 + 900.000 + 1.000.000 - 135.660 = 4.764.340,00
        EmpleadoPorComision emp = new EmpleadoPorComision("Ronald", "123", 2, new BigDecimal("1500000"), 0.05, new BigDecimal("30000000"));

        BigDecimal bonos = calculadora.calcularBonos(emp);
        assertEquals(0, new BigDecimal("900000").compareTo(bonos));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("4764340.00"), neto);
    }
}
