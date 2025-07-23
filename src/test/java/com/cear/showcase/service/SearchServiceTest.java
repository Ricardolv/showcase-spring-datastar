package com.cear.showcase.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cear.showcase.dto.SearchResult;

/**
 * Testes unitários para o SearchService.
 */
@ExtendWith(MockitoExtension.class)
class SearchServiceTest {

    @Mock
    private MockDataService mockDataService;

    @InjectMocks
    private SearchService searchService;

    private List<SearchResult> mockResults;

    @BeforeEach
    void setUp() {
        mockResults = List.of(
                SearchResult.builder()
                        .id(1L)
                        .firstName("João")
                        .lastName("Silva")
                        .email("joao.silva@cear.ufpb.br")
                        .department("Tecnologia")
                        .position("Desenvolvedor")
                        .build(),
                SearchResult.builder()
                        .id(2L)
                        .firstName("Maria")
                        .lastName("Santos")
                        .email("maria.santos@cear.ufpb.br")
                        .department("Marketing")
                        .position("Analista")
                        .build()
        );
    }

    @Test
    void testSearch() {
        when(mockDataService.generateSearchResults(anyString(), anyInt()))
                .thenReturn(mockResults);

        List<SearchResult> results = searchService.search("João", 10);

        assertNotNull(results);
        assertEquals(2, results.size());
        assertEquals("João", results.get(0).getFirstName());
    }

    @Test
    void testSearchWithFilters() {
        when(mockDataService.generateSearchResults(anyString(), anyInt()))
                .thenReturn(mockResults);

        List<SearchResult> results = searchService.searchWithFilters("", "Tecnologia", null, 10);

        assertNotNull(results);
        assertFalse(results.isEmpty());
        // Verifica se pelo menos um resultado contém o departamento filtrado
        assertTrue(results.stream().anyMatch(r -> r.getDepartment().contains("Tecnologia")));
    }

    @Test
    void testGetSuggestions() {
        List<String> suggestions = searchService.getSuggestions("João", 5);

        assertNotNull(suggestions);
        assertFalse(suggestions.isEmpty());
        assertTrue(suggestions.size() <= 5);
    }

    @Test
    void testGetSuggestionsEmpty() {
        List<String> suggestions = searchService.getSuggestions("", 5);

        assertNotNull(suggestions);
        assertEquals(5, suggestions.size());
    }

    @Test
    void testGetAvailableDepartments() {
        Set<String> departments = searchService.getAvailableDepartments();

        assertNotNull(departments);
        assertFalse(departments.isEmpty());
        assertTrue(departments.contains("Tecnologia"));
        assertTrue(departments.contains("Marketing"));
    }

    @Test
    void testGetAvailablePositions() {
        Set<String> positions = searchService.getAvailablePositions();

        assertNotNull(positions);
        assertFalse(positions.isEmpty());
        assertTrue(positions.contains("Desenvolvedor"));
        assertTrue(positions.contains("Analista"));
    }

    @Test
    void testQuickSearch() {
        when(mockDataService.generateSearchResults(anyString(), eq(5)))
                .thenReturn(mockResults.subList(0, 1));

        List<SearchResult> results = searchService.quickSearch("João");

        assertNotNull(results);
        assertTrue(results.size() <= 5);
    }

    @Test
    void testFindById() {
        when(mockDataService.generateSearchResults(eq(""), eq(50)))
                .thenReturn(mockResults);

        SearchResult result = searchService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("João", result.getFirstName());
    }

    @Test
    void testFindByIdNotFound() {
        when(mockDataService.generateSearchResults(eq(""), eq(50)))
                .thenReturn(mockResults);

        SearchResult result = searchService.findById(999L);

        assertNull(result);
    }

    @Test
    void testGetSearchStats() {
        String stats = searchService.getSearchStats();

        assertNotNull(stats);
        assertTrue(stats.contains("departamentos"));
        assertTrue(stats.contains("posições"));
    }
} 