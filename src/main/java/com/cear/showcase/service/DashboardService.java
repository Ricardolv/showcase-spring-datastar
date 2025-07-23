package com.cear.showcase.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Service;

import com.cear.showcase.dto.DashboardData;
import com.cear.showcase.dto.WidgetData;

import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pelos dados do dashboard.
 * Fornece métricas em tempo real, widgets e dados para Server-Sent Events (SSE)
 * para demonstrar a capacidade reativa do Datastar.
 */
@Service
@RequiredArgsConstructor
public class DashboardService {
    
    private final MockDataService mockDataService;
    
    // Cache simples para dados do dashboard (simula estado em memória)
    private final Map<String, Object> dashboardCache = new ConcurrentHashMap<>();
    private LocalDateTime lastCacheUpdate = LocalDateTime.now();
    
    // TTL do cache em segundos
    private static final int CACHE_TTL_SECONDS = 30;
    
    /**
     * Obtém dados completos do dashboard.
     * 
     * @param useCache Se deve usar cache ou gerar dados frescos
     * @return DashboardData com métricas atualizadas
     */
    public DashboardData getDashboardData(boolean useCache) {
        if (useCache && isCacheValid()) {
            return (DashboardData) dashboardCache.get("dashboardData");
        }
        
        DashboardData data = mockDataService.generateDashboardData();
        
        // Adiciona variação temporal para simular dados reais
        data = addTemporalVariation(data);
        
        // Atualiza cache
        dashboardCache.put("dashboardData", data);
        lastCacheUpdate = LocalDateTime.now();
        
        return data;
    }
    
    /**
     * Obtém dados do dashboard sempre frescos (para SSE).
     * 
     * @return DashboardData com dados em tempo real
     */
    public DashboardData getRealtimeDashboardData() {
        return getDashboardData(false);
    }
    
    /**
     * Obtém widgets específicos do dashboard.
     * 
     * @param widgetIds IDs dos widgets a buscar (opcional, null = todos)
     * @param useCache Se deve usar cache
     * @return Lista de widgets
     */
    public List<WidgetData> getWidgets(List<String> widgetIds, boolean useCache) {
        if (useCache && isCacheValid()) {
            @SuppressWarnings("unchecked")
            List<WidgetData> cachedWidgets = (List<WidgetData>) dashboardCache.get("widgets");
            if (cachedWidgets != null) {
                if (widgetIds == null || widgetIds.isEmpty()) {
                    return cachedWidgets;
                }
                return cachedWidgets.stream()
                        .filter(widget -> widgetIds.contains(widget.getId()))
                        .toList();
            }
        }
        
        List<WidgetData> widgets = mockDataService.generateWidgets();
        
        // Adiciona estado de loading simulado ocasionalmente
        widgets = addLoadingStates(widgets);
        
        // Atualiza cache
        dashboardCache.put("widgets", widgets);
        lastCacheUpdate = LocalDateTime.now();
        
        if (widgetIds == null || widgetIds.isEmpty()) {
            return widgets;
        }
        
        return widgets.stream()
                .filter(widget -> widgetIds.contains(widget.getId()))
                .toList();
    }
    
    /**
     * Obtém um widget específico por ID.
     * 
     * @param widgetId ID do widget
     * @return WidgetData ou null se não encontrado
     */
    public WidgetData getWidget(String widgetId) {
        List<WidgetData> widgets = getWidgets(List.of(widgetId), true);
        return widgets.isEmpty() ? null : widgets.get(0);
    }
    
    /**
     * Simula atualização de widget individual.
     * 
     * @param widgetId ID do widget a atualizar
     * @return WidgetData atualizado
     */
    public WidgetData refreshWidget(String widgetId) {
        List<WidgetData> allWidgets = mockDataService.generateWidgets();
        
        return allWidgets.stream()
                .filter(widget -> widget.getId().equals(widgetId))
                .findFirst()
                .map(this::addRandomVariation)
                .orElse(null);
    }
    
    /**
     * Obtém dados para Server-Sent Events.
     * Retorna dados formatados para streaming em tempo real.
     * 
     * @return Map com dados para SSE
     */
    public Map<String, Object> getSSEData() {
        DashboardData dashboard = getRealtimeDashboardData();
        List<WidgetData> widgets = getWidgets(null, false);
        
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "dashboard", dashboard,
                "widgets", widgets,
                "stats", Map.of(
                        "activeConnections", ThreadLocalRandom.current().nextInt(10, 50),
                        "serverLoad", ThreadLocalRandom.current().nextDouble(0.1, 0.9),
                        "memoryUsage", ThreadLocalRandom.current().nextDouble(0.3, 0.8)
                )
        );
    }
    
    /**
     * Simula alertas em tempo real para o dashboard.
     * 
     * @return Lista de alertas ativos
     */
    public List<Map<String, Object>> getActiveAlerts() {
        List<String> alertTypes = List.of("info", "warning", "error", "success");
        List<String> messages = List.of(
                "Sistema funcionando normalmente",
                "Alta utilização de CPU detectada",
                "Backup concluído com sucesso",
                "Novo usuário registrado",
                "Manutenção programada em 2 horas"
        );
        
        int alertCount = ThreadLocalRandom.current().nextInt(0, 3);
        
        return java.util.stream.IntStream.range(0, alertCount)
                .mapToObj(i -> Map.<String, Object>of(
                        "id", "alert-" + i,
                        "type", alertTypes.get(ThreadLocalRandom.current().nextInt(alertTypes.size())),
                        "message", messages.get(ThreadLocalRandom.current().nextInt(messages.size())),
                        "timestamp", LocalDateTime.now().minusMinutes(ThreadLocalRandom.current().nextInt(1, 60))
                ))
                .toList();
    }
    
    /**
     * Obtém configurações do dashboard do usuário.
     * 
     * @param userId ID do usuário (simulado)
     * @return Configurações do dashboard
     */
    public Map<String, Object> getDashboardSettings(String userId) {
        return Map.of(
                "refreshInterval", 5, // segundos
                "enableNotifications", true,
                "theme", "light",
                "favoriteWidgets", List.of("active-users", "revenue", "conversion"),
                "layout", "grid",
                "autoRefresh", true
        );
    }
    
    /**
     * Limpa o cache do dashboard.
     */
    public void clearCache() {
        dashboardCache.clear();
        lastCacheUpdate = LocalDateTime.now();
    }
    
    /**
     * Obtém informações sobre o cache.
     * 
     * @return Status do cache
     */
    public Map<String, Object> getCacheInfo() {
        return Map.of(
                "size", dashboardCache.size(),
                "lastUpdate", lastCacheUpdate,
                "isValid", isCacheValid(),
                "ttlSeconds", CACHE_TTL_SECONDS
        );
    }
    
    // Métodos auxiliares privados
    
    private boolean isCacheValid() {
        return lastCacheUpdate.plusSeconds(CACHE_TTL_SECONDS).isAfter(LocalDateTime.now());
    }
    
    private DashboardData addTemporalVariation(DashboardData data) {
        // Adiciona pequenas variações baseadas no horário para simular dados reais
        int hour = LocalDateTime.now().getHour();
        double factor = 1.0 + (Math.sin(hour * Math.PI / 12.0) * 0.1); // Variação senoidal baseada na hora
        
        if (data.getTotalUsers() != null) {
            long newTotal = Math.round(data.getTotalUsers() * factor);
            data.setTotalUsers(newTotal);
        }
        
        if (data.getActiveUsers() != null) {
            long newActive = Math.round(data.getActiveUsers() * factor);
            data.setActiveUsers(newActive);
        }
        
        return data;
    }
    
    private List<WidgetData> addLoadingStates(List<WidgetData> widgets) {
        return widgets.stream()
                .map(widget -> {
                    // 5% chance de mostrar estado de loading
                    if (ThreadLocalRandom.current().nextDouble() < 0.05) {
                        widget.setLoading(true);
                    }
                    return widget;
                })
                .toList();
    }
    
    private WidgetData addRandomVariation(WidgetData widget) {
        // Adiciona variação de ±10% aos valores
        if (widget.getValue() != null) {
            double variation = 1.0 + (ThreadLocalRandom.current().nextDouble(-0.1, 0.1));
            widget.setValue(widget.getValue().multiply(java.math.BigDecimal.valueOf(variation)));
        }
        
        if (widget.getTrendPercentage() != null) {
            double variation = ThreadLocalRandom.current().nextDouble(-2.0, 2.0);
            widget.setTrendPercentage(widget.getTrendPercentage() + variation);
        }
        
        widget.setLastUpdated(LocalDateTime.now());
        return widget;
    }
} 