package com.cear.showcase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representando dados de um widget do dashboard.
 * Suporta diferentes tipos de widgets (metric, chart, list, etc.)
 * para demonstrar a flexibilidade do Datastar.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WidgetData {
    
    private String id;
    private String title;
    private String subtitle;
    private WidgetType type;
    private BigDecimal value;
    private String unit;
    private String trend; // "up", "down", "stable"
    private Double trendPercentage;
    private String color; // CSS class or hex color
    private String icon; // Icon class or name
    private List<DataPoint> chartData;
    private List<ListItem> listData;
    private Map<String, Object> customData;
    private LocalDateTime lastUpdated;
    private boolean loading;
    private String errorMessage;
    
    /**
     * Tipos de widget suportados.
     */
    public enum WidgetType {
        METRIC,      // Widget de métrica simples
        CHART,       // Widget com gráfico
        LIST,        // Widget com lista de itens
        PROGRESS,    // Widget de progresso
        ALERT,       // Widget de alerta/notificação
        CUSTOM       // Widget customizado
    }
    
    /**
     * Obtém o valor formatado do widget.
     * 
     * @return Valor formatado com unidade
     */
    @JsonIgnore
    public String getFormattedValue() {
        if (value == null) {
            return "N/A";
        }
        
        String formattedValue;
        if (value.scale() == 0) {
            formattedValue = value.toBigInteger().toString();
        } else {
            formattedValue = value.toString();
        }
        
        if (unit != null && !unit.trim().isEmpty()) {
            return formattedValue + " " + unit;
        }
        
        return formattedValue;
    }
    
    /**
     * Obtém a tendência formatada como percentual.
     * 
     * @return Tendência formatada (ex: "+12,5%")
     */
    @JsonIgnore
    public String getFormattedTrend() {
        if (trendPercentage == null) {
            return "";
        }
        
        String sign = trendPercentage >= 0 ? "+" : "";
        return String.format("%s%.1f%%", sign, trendPercentage);
    }
    
    /**
     * Obtém a classe CSS para o ícone da tendência.
     * 
     * @return Classe CSS do ícone
     */
    @JsonIgnore
    public String getTrendIconClass() {
        if ("up".equals(trend)) {
            return "trending-up text-green-500";
        } else if ("down".equals(trend)) {
            return "trending-down text-red-500";
        }
        return "trending-flat text-gray-500";
    }
    
    /**
     * Obtém a classe CSS para a cor da tendência.
     * 
     * @return Classe CSS de cor
     */
    @JsonIgnore
    public String getTrendColorClass() {
        if ("up".equals(trend)) {
            return "text-green-600";
        } else if ("down".equals(trend)) {
            return "text-red-600";
        }
        return "text-gray-600";
    }
    
    /**
     * Verifica se o widget tem dados de gráfico.
     * 
     * @return true se há dados de gráfico
     */
    @JsonIgnore
    public boolean hasChartData() {
        return chartData != null && !chartData.isEmpty();
    }
    
    /**
     * Verifica se o widget tem dados de lista.
     * 
     * @return true se há dados de lista
     */
    @JsonIgnore
    public boolean hasListData() {
        return listData != null && !listData.isEmpty();
    }
    
    /**
     * Verifica se o widget está em estado de erro.
     * 
     * @return true se há erro
     */
    @JsonIgnore
    public boolean hasError() {
        return errorMessage != null && !errorMessage.trim().isEmpty();
    }
    
    /**
     * Obtém a classe CSS base do widget baseada no tipo.
     * 
     * @return Classe CSS do widget
     */
    @JsonIgnore
    public String getWidgetTypeClass() {
        return "widget-" + type.name().toLowerCase();
    }
    
    /**
     * DTO representando um ponto de dados para gráficos.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DataPoint {
        private String label;
        private BigDecimal value;
        private String color;
        private LocalDateTime timestamp;
    }
    
    /**
     * DTO representando um item de lista.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ListItem {
        private String id;
        private String title;
        private String subtitle;
        private String value;
        private String status;
        private String icon;
        private String color;
        private Map<String, Object> metadata;
    }
} 