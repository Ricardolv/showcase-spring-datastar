package com.cear.showcase.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cear.showcase.dto.DashboardData;
import com.cear.showcase.dto.WidgetData;

/**
 * Testes unitários para o DashboardService.
 */
@ExtendWith(MockitoExtension.class)
class DashboardServiceTest {

    @Mock
    private MockDataService mockDataService;

    @InjectMocks
    private DashboardService dashboardService;

    private DashboardData mockDashboardData;
    private List<WidgetData> mockWidgets;

    @BeforeEach
    void setUp() {
        mockDashboardData = DashboardData.builder()
                .totalUsers(1000L)
                .activeUsers(800L)
                .totalRevenue(new BigDecimal("50000"))
                .lastUpdated(LocalDateTime.now())
                .build();

        mockWidgets = List.of(
                WidgetData.builder()
                        .id("widget-1")
                        .title("Users")
                        .type(WidgetData.WidgetType.METRIC)
                        .value(new BigDecimal("100"))
                        .loading(false)
                        .build(),
                WidgetData.builder()
                        .id("widget-2")
                        .title("Revenue")
                        .type(WidgetData.WidgetType.CHART)
                        .value(new BigDecimal("5000"))
                        .loading(false)
                        .build()
        );
    }

    @Test
    void testGetDashboardDataWithCache() {
        when(mockDataService.generateDashboardData()).thenReturn(mockDashboardData);

        // Primeira chamada para popular o cache (cache vazio)
        DashboardData result = dashboardService.getDashboardData(false);
        assertNotNull(result);

        // Segunda chamada usando cache
        DashboardData cachedResult = dashboardService.getDashboardData(true);
        assertNotNull(cachedResult);
        assertNotNull(cachedResult.getTotalUsers());
        assertNotNull(cachedResult.getActiveUsers());
    }

    @Test
    void testGetDashboardDataWithoutCache() {
        when(mockDataService.generateDashboardData()).thenReturn(mockDashboardData);

        DashboardData result = dashboardService.getDashboardData(false);

        assertNotNull(result);
        assertNotNull(result.getTotalUsers());
    }

    @Test
    void testGetRealtimeDashboardData() {
        when(mockDataService.generateDashboardData()).thenReturn(mockDashboardData);

        DashboardData result = dashboardService.getRealtimeDashboardData();

        assertNotNull(result);
        assertNotNull(result.getTotalUsers());
    }

    @Test
    void testGetWidgets() {
        when(mockDataService.generateWidgets()).thenReturn(mockWidgets);

        List<WidgetData> results = dashboardService.getWidgets(null, false);

        assertNotNull(results);
        assertEquals(2, results.size());
    }

    @Test
    void testGetWidgetsFiltered() {
        when(mockDataService.generateWidgets()).thenReturn(mockWidgets);

        List<WidgetData> results = dashboardService.getWidgets(List.of("widget-1"), false);

        assertNotNull(results);
        assertEquals(1, results.size());
        assertEquals("widget-1", results.get(0).getId());
    }

    @Test
    void testGetWidget() {
        when(mockDataService.generateWidgets()).thenReturn(mockWidgets);

        WidgetData result = dashboardService.getWidget("widget-1");

        assertNotNull(result);
        assertEquals("widget-1", result.getId());
    }

    @Test
    void testGetWidgetNotFound() {
        when(mockDataService.generateWidgets()).thenReturn(mockWidgets);

        WidgetData result = dashboardService.getWidget("nonexistent");

        assertNull(result);
    }

    @Test
    void testRefreshWidget() {
        when(mockDataService.generateWidgets()).thenReturn(mockWidgets);

        WidgetData result = dashboardService.refreshWidget("widget-1");

        assertNotNull(result);
        assertEquals("widget-1", result.getId());
        assertNotNull(result.getLastUpdated());
    }

    @Test
    void testGetSSEData() {
        when(mockDataService.generateDashboardData()).thenReturn(mockDashboardData);
        when(mockDataService.generateWidgets()).thenReturn(mockWidgets);

        Map<String, Object> sseData = dashboardService.getSSEData();

        assertNotNull(sseData);
        assertTrue(sseData.containsKey("timestamp"));
        assertTrue(sseData.containsKey("dashboard"));
        assertTrue(sseData.containsKey("widgets"));
        assertTrue(sseData.containsKey("stats"));
    }

    @Test
    void testGetActiveAlerts() {
        List<Map<String, Object>> alerts = dashboardService.getActiveAlerts();

        assertNotNull(alerts);
        // Pode ter 0-2 alertas (baseado no random)
        assertTrue(alerts.size() <= 3);
    }

    @Test
    void testGetDashboardSettings() {
        Map<String, Object> settings = dashboardService.getDashboardSettings("user123");

        assertNotNull(settings);
        assertTrue(settings.containsKey("refreshInterval"));
        assertTrue(settings.containsKey("enableNotifications"));
        assertTrue(settings.containsKey("theme"));
    }

    @Test
    void testClearCache() {
        // Deve executar sem erro
        assertDoesNotThrow(() -> dashboardService.clearCache());
    }

    @Test
    void testGetCacheInfo() {
        Map<String, Object> cacheInfo = dashboardService.getCacheInfo();

        assertNotNull(cacheInfo);
        assertTrue(cacheInfo.containsKey("size"));
        assertTrue(cacheInfo.containsKey("lastUpdate"));
        assertTrue(cacheInfo.containsKey("isValid"));
        assertTrue(cacheInfo.containsKey("ttlSeconds"));
    }
} 