package com.cear.showcase.dto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

/**
 * Testes unitários para o DTO ValidationResult.
 */
class ValidationResultTest {

    @Test
    void testAddFieldError() {
        ValidationResult result = new ValidationResult();
        assertTrue(result.isValid());
        assertFalse(result.hasErrors());

        result.addFieldError("email", "Email é obrigatório");
        
        assertFalse(result.isValid());
        assertTrue(result.hasErrors());
        assertTrue(result.hasFieldError("email"));
        assertEquals("Email é obrigatório", result.getFirstFieldError("email"));
    }

    @Test
    void testAddMultipleFieldErrors() {
        ValidationResult result = new ValidationResult();
        
        result.addFieldError("email", "Email é obrigatório");
        result.addFieldError("email", "Email deve ter formato válido");
        
        assertEquals(2, result.getFieldErrors("email").size());
        assertEquals("Email é obrigatório", result.getFirstFieldError("email"));
    }

    @Test
    void testAddFieldErrors() {
        ValidationResult result = new ValidationResult();
        
        result.addFieldErrors("name", Arrays.asList("Nome é obrigatório", "Nome muito curto"));
        
        assertTrue(result.hasFieldError("name"));
        assertEquals(2, result.getFieldErrors("name").size());
    }

    @Test
    void testAddGlobalError() {
        ValidationResult result = new ValidationResult();
        
        result.addGlobalError("Erro geral do sistema");
        
        assertFalse(result.isValid());
        assertTrue(result.hasGlobalErrors());
        assertEquals(1, result.getGlobalErrors().size());
    }

    @Test
    void testAddWarning() {
        ValidationResult result = new ValidationResult();
        
        result.addWarning("Aviso importante");
        
        assertTrue(result.isValid()); // Warnings não invalidam
        assertTrue(result.hasWarnings());
        assertEquals(1, result.getWarnings().size());
    }

    @Test
    void testGetTotalErrorCount() {
        ValidationResult result = new ValidationResult();
        
        result.addFieldError("email", "Erro 1");
        result.addFieldError("name", "Erro 2");
        result.addGlobalError("Erro global");
        
        assertEquals(3, result.getTotalErrorCount());
    }

    @Test
    void testGetFieldsWithErrors() {
        ValidationResult result = new ValidationResult();
        
        result.addFieldError("email", "Erro");
        result.addFieldError("name", "Erro");
        
        assertEquals(2, result.getFieldsWithErrors().size());
        assertTrue(result.getFieldsWithErrors().contains("email"));
        assertTrue(result.getFieldsWithErrors().contains("name"));
    }

    @Test
    void testClearErrors() {
        ValidationResult result = new ValidationResult();
        
        result.addFieldError("email", "Erro");
        result.addGlobalError("Erro global");
        
        assertFalse(result.isValid());
        
        result.clearErrors();
        
        assertTrue(result.isValid());
        assertFalse(result.hasErrors());
    }

    @Test
    void testClearFieldErrors() {
        ValidationResult result = new ValidationResult();
        
        result.addFieldError("email", "Erro");
        result.addFieldError("name", "Erro");
        
        result.clearFieldErrors("email");
        
        assertFalse(result.hasFieldError("email"));
        assertTrue(result.hasFieldError("name"));
    }

    @Test
    void testGetFieldStatusClass() {
        ValidationResult result = new ValidationResult();
        
        // Campo sem erro
        String normalClass = result.getFieldStatusClass("email");
        assertTrue(normalClass.contains("border-gray-300"));
        
        // Campo com erro
        result.addFieldError("email", "Erro");
        String errorClass = result.getFieldStatusClass("email");
        assertTrue(errorClass.contains("border-red-500"));
    }

    @Test
    void testStaticFactoryMethods() {
        // Valid result
        ValidationResult valid = ValidationResult.valid();
        assertTrue(valid.isValid());
        assertFalse(valid.hasErrors());
        
        // Field error result
        ValidationResult fieldError = ValidationResult.withFieldError("email", "Erro");
        assertFalse(fieldError.isValid());
        assertTrue(fieldError.hasFieldError("email"));
        
        // Global error result
        ValidationResult globalError = ValidationResult.withGlobalError("Erro global");
        assertFalse(globalError.isValid());
        assertTrue(globalError.hasGlobalErrors());
    }

    @Test
    void testFieldWithoutErrors() {
        ValidationResult result = new ValidationResult();
        
        assertFalse(result.hasFieldError("inexistente"));
        assertTrue(result.getFieldErrors("inexistente").isEmpty());
        assertNull(result.getFirstFieldError("inexistente"));
    }
} 