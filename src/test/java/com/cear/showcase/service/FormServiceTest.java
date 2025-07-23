package com.cear.showcase.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cear.showcase.dto.FormResponse;
import com.cear.showcase.dto.ValidationResult;

/**
 * Testes unitários para o FormService.
 */
@ExtendWith(MockitoExtension.class)
class FormServiceTest {

    @Mock
    private MockDataService mockDataService;

    @InjectMocks
    private FormService formService;

    @BeforeEach
    void setUp() {
        // Setup inicial - stubs serão configurados nos testes específicos que precisam
    }

    @Test
    void testProcessFormValid() {
        when(mockDataService.processForm(any())).thenReturn(FormResponse.success("Form processed"));
        
        Map<String, String> formData = new HashMap<>();
        formData.put("name", "João Silva");
        formData.put("email", "joao@example.com");
        formData.put("message", "Esta é uma mensagem de teste com mais de 10 caracteres");

        FormResponse response = formService.processForm(formData, "contact");

        assertNotNull(response);
        assertTrue(response.isSuccess());
    }

    @Test
    void testProcessFormInvalid() {
        Map<String, String> formData = new HashMap<>();
        formData.put("name", ""); // Nome vazio
        formData.put("email", "email-invalido"); // Email inválido

        FormResponse response = formService.processForm(formData, "contact");

        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertEquals(FormResponse.ResponseType.VALIDATION, response.getType());
    }

    @Test
    void testValidateFieldName() {
        ValidationResult result = formService.validateField("name", "João Silva", "contact");

        assertNotNull(result);
        assertTrue(result.isValid());
    }

    @Test
    void testValidateFieldNameInvalid() {
        ValidationResult result = formService.validateField("name", "J", "contact");

        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.hasFieldError("name"));
    }

    @Test
    void testValidateFieldEmail() {
        ValidationResult result = formService.validateField("email", "joao@example.com", "contact");

        assertNotNull(result);
        assertTrue(result.isValid());
    }

    @Test
    void testValidateFieldEmailInvalid() {
        ValidationResult result = formService.validateField("email", "email-invalido", "contact");

        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.hasFieldError("email"));
    }

    @Test
    void testValidateFieldPhone() {
        ValidationResult result = formService.validateField("phone", "(11) 99999-9999", "contact");

        assertNotNull(result);
        assertTrue(result.isValid());
    }

    @Test
    void testValidateFieldPhoneInvalid() {
        ValidationResult result = formService.validateField("phone", "123", "contact");

        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.hasFieldError("phone"));
    }

    @Test
    void testValidateFieldPassword() {
        ValidationResult result = formService.validateField("password", "MinhaSenh@123", "registration");

        assertNotNull(result);
        assertTrue(result.isValid());
    }

    @Test
    void testValidateFieldPasswordWeak() {
        ValidationResult result = formService.validateField("password", "123", "registration");

        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.hasFieldError("password"));
    }

    @Test
    void testValidateFieldAsync() {
        ValidationResult result = formService.validateFieldAsync("email", "test@example.com");

        assertNotNull(result);
        // Pode ser válido ou inválido dependendo da simulação de "já cadastrado"
    }

    @Test
    void testGetFieldSuggestions() {
        List<String> suggestions = formService.getFieldSuggestions("company", "Google");

        assertNotNull(suggestions);
        assertTrue(suggestions.size() <= 5);
    }

    @Test
    void testGetFieldSuggestionsEmpty() {
        List<String> suggestions = formService.getFieldSuggestions("unknownField", "test");

        assertNotNull(suggestions);
        assertTrue(suggestions.isEmpty());
    }

    @Test
    void testValidateFileUpload() {
        ValidationResult result = formService.validateFileUpload("test.pdf", 1024, "application/pdf");

        assertNotNull(result);
        assertTrue(result.isValid());
    }

    @Test
    void testValidateFileUploadTooLarge() {
        ValidationResult result = formService.validateFileUpload("large.pdf", 6 * 1024 * 1024, "application/pdf");

        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.hasFieldError("file"));
    }

    @Test
    void testValidateFileUploadInvalidType() {
        ValidationResult result = formService.validateFileUpload("script.exe", 1024, "application/exe");

        assertNotNull(result);
        assertFalse(result.isValid());
        assertTrue(result.hasFieldError("file"));
    }

    @Test
    void testGetValidationConfig() {
        Map<String, Object> config = formService.getValidationConfig("contact");

        assertNotNull(config);
        assertTrue(config.containsKey("realTimeValidation"));
        assertTrue(config.containsKey("requiredFields"));
        assertTrue(config.containsKey("maxLengths"));
        assertTrue(config.containsKey("patterns"));
    }

    @Test
    void testValidateFormRegistration() {
        when(mockDataService.processForm(any())).thenReturn(FormResponse.success("Form processed"));
        
        Map<String, String> formData = new HashMap<>();
        formData.put("name", "João Silva");
        formData.put("email", "joao@example.com");
        formData.put("password", "MinhaSenh@123");
        formData.put("confirmPassword", "MinhaSenh@123");

        FormResponse response = formService.processForm(formData, "registration");

        assertNotNull(response);
        assertTrue(response.isSuccess());
    }

    @Test
    void testValidateFormRegistrationPasswordMismatch() {
        Map<String, String> formData = new HashMap<>();
        formData.put("name", "João Silva");
        formData.put("email", "joao@example.com");
        formData.put("password", "MinhaSenh@123");
        formData.put("confirmPassword", "SenhasDiferentes");

        FormResponse response = formService.processForm(formData, "registration");

        assertNotNull(response);
        assertFalse(response.isSuccess());
        assertTrue(response.hasValidationErrors());
    }
} 