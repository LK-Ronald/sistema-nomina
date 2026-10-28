package company.services;

import com.company.models.EmpleadoPorHoras;
import com.company.services.CalculadoraEmpleadoPorHoras;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraEmpleadoPorHorasTest {

    private CalculadoraEmpleadoPorHoras calculadora;

    @BeforeEach
    public void setUp() {
        calculadora = new CalculadoraEmpleadoPorHoras();
    }

    @Test
    public void testSalarioSinHorasExtras() {
        // 40 horas a 10.000 = 400.000 bruto
        // Antigüedad 1 año (<= 1) -> No fondo de ahorro
        // Deducciones: 400.000 * (0.04 + 0.00522) = 16.000 + 2.088 = 18.088
        // Neto = 400.000 - 18.088 = 381.912,00
        EmpleadoPorHoras emp = new EmpleadoPorHoras("Carlos", "123", 1, 40, true, new BigDecimal("10000"));

        BigDecimal bruto = calculadora.calcularSalarioBruto(emp);
        assertEquals(new BigDecimal("400000"), bruto);

        BigDecimal bonos = calculadora.calcularBonos(emp);
        assertEquals(BigDecimal.ZERO, bonos);

        BigDecimal beneficios = calculadora.calcularBeneficios(emp);
        assertEquals(BigDecimal.ZERO, beneficios);

        BigDecimal deducciones = calculadora.calcularDeducciones(emp);
        assertEquals(0, new BigDecimal("18088.00").compareTo(deducciones));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("381912.00"), neto);
    }

    @Test
    public void testSalarioConHorasExtras() {
        // 45 horas a 10.000
        // Normales: 40 * 10.000 = 400.000
        // Extras: 5 * 10.000 * 1.5 = 75.000
        // Total Bruto = 475.000
        // Antigüedad 2 años (> 1) y acepta fondo -> Fondo Ahorro = 475.000 * 0.02 = 9.500
        // Deducciones: 475.000 * (0.04 + 0.00522) = 19.000 + 2.479,5 = 21.479,5
        // Neto = 475.000 + 9.500 - 21.479,5 = 463.020,50
        EmpleadoPorHoras emp = new EmpleadoPorHoras("Maria", "456", 2, 45, true, new BigDecimal("10000"));

        BigDecimal bruto = calculadora.calcularSalarioBruto(emp);
        assertEquals(0, new BigDecimal("475000").compareTo(bruto));

        BigDecimal beneficios = calculadora.calcularBeneficios(emp);
        assertEquals(0, new BigDecimal("9500").compareTo(beneficios));

        BigDecimal neto = calculadora.calcularNomina(emp);
        assertEquals(new BigDecimal("463020.50"), neto);
    }

    @Test
    public void testEmpleadoPorHorasNoAceptaFondoAhorro() {
        // Antigüedad > 1 pero no acepta fondo
        EmpleadoPorHoras emp = new EmpleadoPorHoras("Pedro", "789", 3, 40, false, new BigDecimal("10000"));
        BigDecimal beneficios = calculadora.calcularBeneficios(emp);
        assertEquals(BigDecimal.ZERO, beneficios);
    }
}
