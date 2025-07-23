package com.cear.showcase.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cear.showcase.dto.SearchResult;
import com.cear.showcase.service.SearchService;

/**
 * Controller para funcionalidade de busca ativa usando Datastar
 * Demonstra busca em tempo real com debounce e fragmentos HTML
 */
@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    /**
     * Página principal de busca
     */
    @GetMapping
    public String searchPage(Model model) {
        // Estatísticas iniciais para mostrar na página
        model.addAttribute("totalPeople", 100); // Valor mock
        model.addAttribute("totalDepartments", searchService.getAvailableDepartments().size());
        model.addAttribute("totalPositions", searchService.getAvailablePositions().size());
        model.addAttribute("activeRoute", "search");
        model.addAttribute("searchStats", searchService.getSearchStats());
        
        return "pages/search";
    }

    /**
     * Endpoint para busca em tempo real - retorna fragmento HTML
     * Usado pelo Datastar com data-on-input e debounce
     */
    @GetMapping("/results")
    public String searchResults(
            @RequestParam(value = "q", defaultValue = "") String query,
            @RequestParam(value = "department", required = false) String department,
            @RequestParam(value = "position", required = false) String position,
            @RequestParam(value = "limit", defaultValue = "10") int limit,
            Model model) {
        
        // Simular pequeno delay para mostrar loading state (removível em produção)
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        List<SearchResult> results;
        
        if (query.trim().isEmpty() && department == null && position == null) {
            // Se não há filtros, retorna algumas pessoas aleatórias como exemplo
            results = searchService.search("", limit);
            model.addAttribute("showingExample", true);
        } else {
            // Busca com filtros
            results = searchService.searchWithFilters(query, department, position, limit);
            model.addAttribute("showingExample", false);
        }
        
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        model.addAttribute("resultCount", results.size());
        model.addAttribute("hasMore", results.size() >= limit);
        
        return "fragments/search-results";
    }

    /**
     * Endpoint para sugestões de busca - retorna JSON
     * Usado para autocomplete
     */
    @GetMapping("/suggestions")
    @ResponseBody
    public ResponseEntity<List<String>> searchSuggestions(
            @RequestParam(value = "q", defaultValue = "") String query) {
        
        if (query.trim().length() < 2) {
            return ResponseEntity.ok(List.of());
        }
        
        List<String> suggestions = searchService.getSuggestions(query, 5);
        return ResponseEntity.ok(suggestions);
    }

    /**
     * Endpoint para filtros disponíveis - retorna JSON
     * Usado para popular dropdowns dinamicamente
     */
    @GetMapping("/filters")
    @ResponseBody
    public ResponseEntity<Map<String, List<String>>> getFilters() {
        Map<String, List<String>> filters = Map.of(
            "departments", searchService.getAvailableDepartments().stream().toList(),
            "positions", searchService.getAvailablePositions().stream().toList()
        );
        return ResponseEntity.ok(filters);
    }

    /**
     * Endpoint para detalhes de uma pessoa específica
     * Pode ser usado em modals ou páginas de detalhe
     */
    @GetMapping("/person/{id}")
    public String personDetails(@PathVariable Long id, Model model) {
        var person = searchService.findById(id);
        if (person != null) {
            model.addAttribute("person", person);
            return "fragments/person-details";
        } else {
            model.addAttribute("error", "Pessoa não encontrada");
            return "fragments/error";
        }
    }

    /**
     * Endpoint para busca avançada com múltiplos filtros
     * Demonstra capacidades mais complexas do Datastar
     */
    @PostMapping("/advanced")
    public String advancedSearch(
            @RequestParam Map<String, String> params,
            Model model) {
        
        String query = params.getOrDefault("query", "");
        String department = params.get("department");
        String position = params.get("position");
        int limit = Integer.parseInt(params.getOrDefault("limit", "20"));
        
        List<SearchResult> results = searchService.searchWithFilters(query, department, position, limit);
        
        model.addAttribute("results", results);
        model.addAttribute("query", query);
        model.addAttribute("department", department);
        model.addAttribute("position", position);
        model.addAttribute("resultCount", results.size());
        model.addAttribute("isAdvancedSearch", true);
        
        return "fragments/search-results";
    }

    /**
     * Endpoint para busca rápida - versão otimizada
     */
    @GetMapping("/quick")
    @ResponseBody
    public ResponseEntity<List<SearchResult>> quickSearch(
            @RequestParam(value = "q", defaultValue = "") String query) {
        
        List<SearchResult> results = searchService.quickSearch(query);
        return ResponseEntity.ok(results);
    }
} 