package com.cear.showcase.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para o DTO SearchResult.
 */
class SearchResultTest {

    @Test
    void testGetFullName() {
        // Teste com nome completo
        SearchResult result = SearchResult.builder()
                .firstName("João")
                .lastName("Silva")
                .build();
        assertEquals("João Silva", result.getFullName());

        // Teste com apenas primeiro nome
        result = SearchResult.builder()
                .firstName("João")
                .build();
        assertEquals("João", result.getFullName());

        // Teste com apenas sobrenome
        result = SearchResult.builder()
                .lastName("Silva")
                .build();
        assertEquals("Silva", result.getFullName());

        // Teste com nomes nulos
        result = new SearchResult();
        assertEquals("", result.getFullName());
    }

    @Test
    void testGetInitials() {
        SearchResult result = SearchResult.builder()
                .firstName("João")
                .lastName("Silva")
                .build();
        assertEquals("JS", result.getInitials());

        // Teste com apenas primeiro nome
        result = SearchResult.builder()
                .firstName("João")
                .build();
        assertEquals("J", result.getInitials());

        // Teste com nomes vazios
        result = SearchResult.builder()
                .firstName("")
                .lastName("")
                .build();
        assertEquals("", result.getInitials());
    }

    @Test
    void testHasAvatar() {
        // Teste com avatar
        SearchResult result = SearchResult.builder()
                .avatar("https://example.com/avatar.jpg")
                .build();
        assertTrue(result.hasAvatar());

        // Teste sem avatar
        result = SearchResult.builder()
                .avatar(null)
                .build();
        assertFalse(result.hasAvatar());

        // Teste com avatar vazio
        result = SearchResult.builder()
                .avatar("   ")
                .build();
        assertFalse(result.hasAvatar());
    }

    @Test
    void testGetAvatarUrl() {
        // Teste com avatar personalizado
        SearchResult result = SearchResult.builder()
                .firstName("João")
                .lastName("Silva")
                .avatar("https://example.com/avatar.jpg")
                .build();
        assertEquals("https://example.com/avatar.jpg", result.getAvatarUrl());

        // Teste com avatar padrão baseado em iniciais
        result = SearchResult.builder()
                .firstName("João")
                .lastName("Silva")
                .build();
        String expectedUrl = "https://ui-avatars.com/api/?name=JS&background=f97316&color=fff";
        assertEquals(expectedUrl, result.getAvatarUrl());
    }

    @Test
    void testBuilder() {
        SearchResult result = SearchResult.builder()
                .id(1L)
                .firstName("Ana")
                .lastName("Santos")
                .email("ana.santos@cear.ufpb.br")
                .department("Tecnologia")
                .position("Desenvolvedora")
                .build();

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Ana", result.getFirstName());
        assertEquals("Santos", result.getLastName());
        assertEquals("ana.santos@cear.ufpb.br", result.getEmail());
        assertEquals("Tecnologia", result.getDepartment());
        assertEquals("Desenvolvedora", result.getPosition());
        assertEquals("Ana Santos", result.getFullName());
        assertEquals("AS", result.getInitials());
    }
} 