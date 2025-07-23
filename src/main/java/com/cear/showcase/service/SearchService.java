package com.cear.showcase.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cear.showcase.dto.SearchResult;

import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pela funcionalidade de busca ativa.
 * Implementa busca em tempo real, filtros e sugestões para demonstrar
 * a capacidade reativa do Datastar.
 */
@Service
@RequiredArgsConstructor
public class SearchService {
    
    private final MockDataService mockDataService;
    
    /**
     * Busca funcionários com base em uma query.
     * 
     * @param query Termo de busca
     * @param limit Número máximo de resultados
     * @return Lista de resultados filtrados
     */
    public List<SearchResult> search(String query, int limit) {
        long startTime = System.currentTimeMillis();
        
        List<SearchResult> results = mockDataService.generateSearchResults(query, limit);
        
        long duration = System.currentTimeMillis() - startTime;
        
        // Log para demonstrar performance (deve ser < 50ms conforme requirement)
        if (duration > 50) {
            System.out.printf("WARN: Busca demorou %dms (objetivo: <50ms)%n", duration);
        }
        
        return results;
    }
    
    /**
     * Busca com filtros avançados.
     * 
     * @param query Termo de busca
     * @param department Filtro por departamento (opcional)
     * @param position Filtro por cargo (opcional)
     * @param limit Número máximo de resultados
     * @return Lista de resultados filtrados
     */
    public List<SearchResult> searchWithFilters(String query, String department, String position, int limit) {
        // Busca base mais ampla para permitir filtragem
        List<SearchResult> baseResults = mockDataService.generateSearchResults(query, limit * 2);
        
        return baseResults.stream()
                .filter(result -> department == null || department.trim().isEmpty() || 
                        result.getDepartment().toLowerCase().contains(department.toLowerCase()))
                .filter(result -> position == null || position.trim().isEmpty() || 
                        result.getPosition().toLowerCase().contains(position.toLowerCase()))
                .limit(limit)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtém sugestões de busca baseadas em termos populares.
     * 
     * @param partialQuery Query parcial
     * @param maxSuggestions Número máximo de sugestões
     * @return Lista de sugestões
     */
    public List<String> getSuggestions(String partialQuery, int maxSuggestions) {
        // Simula sugestões baseadas em dados comuns
        List<String> suggestions = List.of(
                "Ana Silva", "João Santos", "Maria Oliveira", "Carlos Souza", "Fernanda Rodrigues",
                "Pedro Ferreira", "Juliana Alves", "Ricardo Pereira", "Beatriz Lima", "Fernando Gomes",
                "Tecnologia", "Recursos Humanos", "Marketing", "Vendas", "Financeiro",
                "Desenvolvedor", "Analista", "Coordenador", "Gerente", "Diretor"
        );
        
        if (partialQuery == null || partialQuery.trim().isEmpty()) {
            return suggestions.stream().limit(maxSuggestions).collect(Collectors.toList());
        }
        
        String lowerQuery = partialQuery.toLowerCase();
        return suggestions.stream()
                .filter(suggestion -> suggestion.toLowerCase().contains(lowerQuery))
                .limit(maxSuggestions)
                .collect(Collectors.toList());
    }
    
    /**
     * Obtém departamentos únicos para filtros.
     * 
     * @return Set com departamentos disponíveis
     */
    public Set<String> getAvailableDepartments() {
        return Set.of(
                "Tecnologia", "Recursos Humanos", "Marketing", "Vendas", "Financeiro",
                "Operações", "Atendimento", "Jurídico", "Administrativo", "Desenvolvimento"
        );
    }
    
    /**
     * Obtém posições únicas para filtros.
     * 
     * @return Set com posições disponíveis
     */
    public Set<String> getAvailablePositions() {
        return Set.of(
                "Desenvolvedor", "Analista", "Coordenador", "Gerente", "Diretor",
                "Assistente", "Especialista", "Consultor", "Supervisor", "Trainee"
        );
    }
    
    /**
     * Busca rápida com cache simulado para demonstrar performance.
     * Em uma aplicação real, usaria Redis ou cache local.
     * 
     * @param query Termo de busca
     * @return Lista de resultados (limitada a 5 para busca rápida)
     */
    public List<SearchResult> quickSearch(String query) {
        return search(query, 5);
    }
    
    /**
     * Busca por ID específico.
     * 
     * @param id ID do funcionário
     * @return SearchResult ou null se não encontrado
     */
    public SearchResult findById(Long id) {
        // Simula busca por ID
        List<SearchResult> results = mockDataService.generateSearchResults("", 50);
        return results.stream()
                .filter(result -> result.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
    
    /**
     * Obtém estatísticas de busca para debugging.
     * 
     * @return String com estatísticas úteis
     */
    public String getSearchStats() {
        return String.format(
                "Busca ativa: %d departamentos, %d posições disponíveis. Cache simulado ativo.",
                getAvailableDepartments().size(),
                getAvailablePositions().size()
        );
    }
} 