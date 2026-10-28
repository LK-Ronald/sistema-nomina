package com.company.util;

import java.math.BigDecimal;

public final class FinanceConstants {
    private FinanceConstants() {
    }

    public static final double BONO_MENSUAL = 0.1;
    public static final int ANIOS_PARA_BONO_ASALARIADO = 5;

    public static final int HORAS_SIN_RECARGO = 40;
    public static final double FACTOR_HORA_EXTRA = 1.5;

    public static final int UMBRAL_VENTAS_BONO = 20_000_000;
    public static final double PORCENTAJE_BONO_VENTAS = 0.03;

    public static final double PORCENTAJE_SEGURO_SOCIAL_PENSION = 0.04;
    public static final double PORCENTAJE_ARL_RIESGO_I = 0.00522;

    public static final int MONTO_BONO_ALIMENTACION = 1_000_000;
    public static final BigDecimal VALOR_BONO_ALIMENTACION = BigDecimal.valueOf(MONTO_BONO_ALIMENTACION);

    public static final int ANIOS_PARA_FONDO_AHORRO = 1;
    public static final double PORCENTAJE_FONDO_AHORRO = 0.02;
}
