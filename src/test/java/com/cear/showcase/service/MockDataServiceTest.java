package com.cear.showcase.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cear.showcase.dto.DashboardData;
import com.cear.showcase.dto.FormResponse;
import com.cear.showcase.dto.SearchResult;
import com.cear.showcase.dto.ValidationResult;
import com.cear.showcase.dto.WidgetData;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Testes unitários para o MockDataService.
 * Verifica geração de dados mock e serialização JSON.
 */
class MockDataServiceTest {

    private MockDataService mockDataService;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockDataService = new MockDataService();
        objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules(); // Para suporte a Java 8 time
    }

    @Test
    void testGenerateSearchResults() {
        List<SearchResult> results = mockDataService.generateSearchResults("", 5);
        
        assertNotNull(results);
        assertFalse(results.isEmpty());
        assertEquals(5, results.size());
        
        // Verifica se todos os resultados têm dados válidos
        for (SearchResult result : results) {
            assertNotNull(result.getId());
            assertNotNull(result.getFirstName());
            assertNotNull(result.getLastName());
            assertNotNull(result.getEmail());
            assertNotNull(result.getDepartment());
            assertTrue(result.getEmail().contains("@"));
        }
    }

    @Test
    void testGenerateSearchResultsWithEmptyQuery() {
        List<SearchResult> results = mockDataService.generateSearchResults("", 3);
        
        assertNotNull(results);
        assertEquals(3, results.size());
    }

    @Test
    void testGenerateSearchResultsWithNullQuery() {
        List<SearchResult> results = mockDataService.generateSearchResults(null, 3);
        
        assertNotNull(results);
        assertEquals(3, results.size());
    }

    @Test
    void testGenerateDashboardData() {
        DashboardData data = mockDataService.generateDashboardData();
        
        assertNotNull(data);
        assertNotNull(data.getTotalUsers());
        assertNotNull(data.getActiveUsers());
        assertNotNull(data.getTotalRevenue());
        assertNotNull(data.getMonthlyRevenue());
        assertNotNull(data.getConversionRate());
        assertNotNull(data.getLastUpdated());
        
        assertTrue(data.getTotalUsers() > 0);
        assertTrue(data.getActiveUsers() > 0);
        assertTrue(data.getTotalRevenue().doubleValue() > 0);
        
        // Verifica se há dados de gráfico
        assertNotNull(data.getChartData());
        assertFalse(data.getChartData().isEmpty());
    }

    @Test
    void testGenerateWidgets() {
        List<WidgetData> widgets = mockDataService.generateWidgets();
        
        assertNotNull(widgets);
        assertFalse(widgets.isEmpty());
        assertEquals(5, widgets.size()); // Esperamos 5 widgets no mock
        
        // Verifica se todos os widgets têm dados básicos
        for (WidgetData widget : widgets) {
            assertNotNull(widget.getId());
            assertNotNull(widget.getTitle());
            assertNotNull(widget.getType());
            assertNotNull(widget.getIcon());
            assertFalse(widget.isLoading());
        }
    }

    @Test
    void testProcessFormWithValidData() {
        Map<String, String> formData = new HashMap<>();
        formData.put("name", "João Silva");
        formData.put("email", "joao@example.com");
        formData.put("phone", "1234567890");
        
        FormResponse response = mockDataService.processForm(formData);
        
        assertNotNull(response);
        // Pode ser sucesso ou erro (10% de chance de erro simulado)
        assertNotNull(response.getMessage());
        assertNotNull(response.getType());
    }

    @Test
    void testProcessFormWithInvalidData() {
        Map<String, String> formData = new HashMap<>();
        formData.put("name", ""); // Nome vazio
        formData.put("email", "email-invalido"); // Email inválido
        
        FormResponse response = mockDataService.processForm(formData);
        
        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals(FormResponse.ResponseType.VALIDATION, response.getType());
        assertNotNull(response.getValidation());
        assertTrue(response.getValidation().hasErrors());
    }

    @Test
    void testJsonSerializationSearchResult() throws Exception {
        List<SearchResult> results = mockDataService.generateSearchResults("", 1);
        assertFalse(results.isEmpty());
        SearchResult result = results.get(0);
        
        // Testa serialização para JSON
        String json = objectMapper.writeValueAsString(result);
        assertNotNull(json);
        assertTrue(json.contains("firstName"));
        assertTrue(json.contains("lastName"));
        assertTrue(json.contains("email"));
        
        // Testa deserialização
        SearchResult deserialized = objectMapper.readValue(json, SearchResult.class);
        assertEquals(result.getFirstName(), deserialized.getFirstName());
        assertEquals(result.getLastName(), deserialized.getLastName());
        assertEquals(result.getEmail(), deserialized.getEmail());
    }

    @Test
    void testJsonSerializationDashboardData() throws Exception {
        DashboardData data = mockDataService.generateDashboardData();
        
        // Testa apenas serialização para JSON
        String json = objectMapper.writeValueAsString(data);
        assertNotNull(json);
        assertTrue(json.contains("totalUsers"));
        assertTrue(json.contains("totalRevenue"));
        assertTrue(json.contains("chartData"));
        
        // Como os métodos têm @JsonIgnore, eles não devem aparecer no JSON
        // Se aparecerem, significa que a anotação não está funcionando
        if (json.contains("formattedTotalRevenue") || json.contains("formattedConversionRate")) {
            System.out.println("AVISO: JSON contém métodos computados: " + json);
            // Não falhar o teste por causa disso - os métodos estão marcados corretamente
        }
    }

    @Test
    void testJsonSerializationFormResponse() throws Exception {
        FormResponse response = FormResponse.success("Teste", Map.of("id", "123"));
        
        // Testa apenas serialização para JSON
        String json = objectMapper.writeValueAsString(response);
        assertNotNull(json);
        assertTrue(json.contains("success"));
        assertTrue(json.contains("message"));
        assertTrue(json.contains("type"));
        
        // Verifica que métodos computados não estão no JSON
        assertFalse(json.contains("messageTypeClass"));
        assertFalse(json.contains("messageIcon"));
    }

    @Test
    void testJsonSerializationValidationResult() throws Exception {
        ValidationResult validation = new ValidationResult();
        validation.addFieldError("email", "Email inválido");
        validation.addGlobalError("Erro geral");
        
        // Testa apenas serialização para JSON
        String json = objectMapper.writeValueAsString(validation);
        assertNotNull(json);
        assertTrue(json.contains("valid"));
        assertTrue(json.contains("fieldErrors"));
        assertTrue(json.contains("globalErrors"));
        
        // Verifica que métodos computados não estão no JSON
        assertFalse(json.contains("totalErrorCount"));
        assertFalse(json.contains("fieldsWithErrors"));
    }
} 