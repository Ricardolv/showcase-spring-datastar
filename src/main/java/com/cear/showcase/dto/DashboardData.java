package com.cear.showcase.dto;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representando dados do dashboard com métricas e estatísticas.
 * Utilizado na funcionalidade Interactive Dashboard para demonstrar
 * atualizações reativas em tempo real com Datastar.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardData {
    
    private Long totalUsers;
    private Long activeUsers;
    private BigDecimal totalRevenue;
    private BigDecimal monthlyRevenue;
    private Double conversionRate;
    private Integer newSignups;
    private Integer pendingOrders;
    private Double avgResponseTime;
    private LocalDateTime lastUpdated;
    private List<MetricPoint> chartData;
    
    /**
     * Obtém a receita total formatada para exibição.
     * 
     * @return Receita formatada (ex: "R$ 125.430,50")
     */
    @JsonIgnore
    public String getFormattedTotalRevenue() {
        return formatCurrency(totalRevenue);
    }
    
    /**
     * Obtém a receita mensal formatada para exibição.
     * 
     * @return Receita mensal formatada (ex: "R$ 23.150,00")
     */
    @JsonIgnore
    public String getFormattedMonthlyRevenue() {
        return formatCurrency(monthlyRevenue);
    }
    
    /**
     * Obtém a taxa de conversão formatada como percentual.
     * 
     * @return Taxa formatada (ex: "12,5%")
     */
    public String getFormattedConversionRate() {
        if (conversionRate == null) {
            return "0%";
        }
        DecimalFormat df = new DecimalFormat("#0.0", new java.text.DecimalFormatSymbols(new Locale("pt", "BR")));
        return df.format(conversionRate) + "%";
    }
    
    /**
     * Obtém o tempo de resposta médio formatado.
     * 
     * @return Tempo formatado (ex: "1,2s")
     */
    public String getFormattedAvgResponseTime() {
        if (avgResponseTime == null) {
            return "0s";
        }
        DecimalFormat df = new DecimalFormat("#0.0", new java.text.DecimalFormatSymbols(new Locale("pt", "BR")));
        return df.format(avgResponseTime) + "s";
    }
    
    /**
     * Obtém a data/hora da última atualização formatada.
     * 
     * @return Data formatada (ex: "14/01/2024 15:30")
     */
    @JsonIgnore
    public String getFormattedLastUpdated() {
        if (lastUpdated == null) {
            return "Nunca";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return lastUpdated.format(formatter);
    }
    
    /**
     * Calcula a percentual de usuários ativos.
     * 
     * @return Percentual formatado (ex: "85,2%")
     */
    public String getActiveUsersPercentage() {
        if (totalUsers == null || totalUsers == 0 || activeUsers == null) {
            return "0%";
        }
        double percentage = (activeUsers.doubleValue() / totalUsers.doubleValue()) * 100;
        DecimalFormat df = new DecimalFormat("#0.0", new java.text.DecimalFormatSymbols(new Locale("pt", "BR")));
        return df.format(percentage) + "%";
    }
    
    /**
     * Verifica se há dados de gráfico disponíveis.
     * 
     * @return true se há dados para exibir gráficos
     */
    @JsonIgnore
    public boolean hasChartData() {
        return chartData != null && !chartData.isEmpty();
    }
    
    /**
     * Obtém a tendência da receita (baseado nos últimos pontos do gráfico).
     * 
     * @return "up", "down" ou "stable"
     */
    @JsonIgnore
    public String getRevenueTrend() {
        if (!hasChartData() || chartData.size() < 2) {
            return "stable";
        }
        
        MetricPoint current = chartData.get(chartData.size() - 1);
        MetricPoint previous = chartData.get(chartData.size() - 2);
        
        if (current.getValue().compareTo(previous.getValue()) > 0) {
            return "up";
        } else if (current.getValue().compareTo(previous.getValue()) < 0) {
            return "down";
        }
        return "stable";
    }
    
    private String formatCurrency(BigDecimal value) {
        if (value == null) {
            return "R$ 0,00";
        }
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(value);
    }
    
    /**
     * DTO representando um ponto de métrica no gráfico.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MetricPoint {
        private String label;
        private BigDecimal value;
        private LocalDateTime timestamp;
        
        @JsonIgnore
        public String getFormattedValue() {
            NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            return formatter.format(value);
        }
    }
} 