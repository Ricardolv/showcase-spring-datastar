package com.cear.showcase.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para o DTO DashboardData.
 */
class DashboardDataTest {

    @Test
    void testFormattedRevenues() {
        DashboardData data = DashboardData.builder()
                .totalRevenue(new BigDecimal("125430.50"))
                .monthlyRevenue(new BigDecimal("23150.00"))
                .build();

        // Testa formatação da receita total
        String formattedTotal = data.getFormattedTotalRevenue();
        assertNotNull(formattedTotal);
        assertTrue(formattedTotal.contains("125"));
        assertTrue(formattedTotal.contains("R$") || formattedTotal.contains("BRL"));

        // Testa formatação da receita mensal
        String formattedMonthly = data.getFormattedMonthlyRevenue();
        assertNotNull(formattedMonthly);
        assertTrue(formattedMonthly.contains("23"));
        assertTrue(formattedMonthly.contains("R$") || formattedMonthly.contains("BRL"));
    }

    @Test
    void testFormattedConversionRate() {
        DashboardData data = DashboardData.builder()
                .conversionRate(12.5)
                .build();

        String formatted = data.getFormattedConversionRate();
        assertEquals("12,5%", formatted);

        // Teste com valor nulo
        data = DashboardData.builder().build();
        assertEquals("0%", data.getFormattedConversionRate());
    }

    @Test
    void testFormattedAvgResponseTime() {
        DashboardData data = DashboardData.builder()
                .avgResponseTime(1.2)
                .build();

        String formatted = data.getFormattedAvgResponseTime();
        assertEquals("1,2s", formatted);

        // Teste com valor nulo
        data = DashboardData.builder().build();
        assertEquals("0s", data.getFormattedAvgResponseTime());
    }

    @Test
    void testActiveUsersPercentage() {
        DashboardData data = DashboardData.builder()
                .totalUsers(1000L)
                .activeUsers(850L)
                .build();

        String percentage = data.getActiveUsersPercentage();
        assertEquals("85,0%", percentage);

        // Teste com total zero
        data = DashboardData.builder()
                .totalUsers(0L)
                .activeUsers(100L)
                .build();
        assertEquals("0%", data.getActiveUsersPercentage());
    }

    @Test
    void testHasChartData() {
        // Com dados
        DashboardData.MetricPoint point = DashboardData.MetricPoint.builder()
                .label("Test")
                .value(new BigDecimal("100"))
                .build();

        DashboardData data = DashboardData.builder()
                .chartData(Arrays.asList(point))
                .build();
        assertTrue(data.hasChartData());

        // Sem dados
        data = DashboardData.builder()
                .chartData(Collections.emptyList())
                .build();
        assertFalse(data.hasChartData());

        // Lista nula
        data = DashboardData.builder().build();
        assertFalse(data.hasChartData());
    }

    @Test
    void testRevenueTrend() {
        DashboardData.MetricPoint point1 = DashboardData.MetricPoint.builder()
                .value(new BigDecimal("100"))
                .build();
        DashboardData.MetricPoint point2 = DashboardData.MetricPoint.builder()
                .value(new BigDecimal("150"))
                .build();

        // Tendência crescente
        DashboardData data = DashboardData.builder()
                .chartData(Arrays.asList(point1, point2))
                .build();
        assertEquals("up", data.getRevenueTrend());

        // Tendência decrescente
        data = DashboardData.builder()
                .chartData(Arrays.asList(point2, point1))
                .build();
        assertEquals("down", data.getRevenueTrend());

        // Sem dados suficientes
        data = DashboardData.builder()
                .chartData(Arrays.asList(point1))
                .build();
        assertEquals("stable", data.getRevenueTrend());
    }

    @Test
    void testFormattedLastUpdated() {
        LocalDateTime now = LocalDateTime.of(2024, 1, 15, 14, 30);
        DashboardData data = DashboardData.builder()
                .lastUpdated(now)
                .build();

        String formatted = data.getFormattedLastUpdated();
        assertEquals("15/01/2024 14:30", formatted);

        // Teste com valor nulo
        data = DashboardData.builder().build();
        assertEquals("Nunca", data.getFormattedLastUpdated());
    }

    @Test
    void testMetricPointFormattedValue() {
        DashboardData.MetricPoint point = DashboardData.MetricPoint.builder()
                .value(new BigDecimal("1500.50"))
                .build();

        String formatted = point.getFormattedValue();
        assertNotNull(formatted);
        assertTrue(formatted.contains("1500") || formatted.contains("1.500"));
    }
} 