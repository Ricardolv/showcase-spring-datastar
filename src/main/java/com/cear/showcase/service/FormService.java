package com.cear.showcase.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.cear.showcase.dto.FormResponse;
import com.cear.showcase.dto.ValidationResult;

import lombok.RequiredArgsConstructor;

/**
 * Serviço responsável pelo processamento de formulários.
 * Implementa validação em tempo real, processamento assíncrono e feedback
 * reativo para demonstrar as capacidades do Datastar com formulários.
 */
@Service
@RequiredArgsConstructor
public class FormService {
    
    private final MockDataService mockDataService;
    
    // Padrões de validação comuns
    private static final Pattern EMAIL_PATTERN = 
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    private static final Pattern PHONE_PATTERN = 
            Pattern.compile("^\\(?\\d{2}\\)?[\\s-]?\\d{4,5}-?\\d{4}$");
    private static final Pattern CPF_PATTERN = 
            Pattern.compile("^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$");
    
    /**
     * Processa um formulário completo com validação.
     * 
     * @param formData Dados do formulário
     * @param formType Tipo do formulário (contact, registration, etc.)
     * @return FormResponse com resultado do processamento
     */
    public FormResponse processForm(Map<String, String> formData, String formType) {
        // Validação baseada no tipo de formulário
        ValidationResult validation = validateForm(formData, formType);
        
        if (validation.hasErrors()) {
            return FormResponse.validationError(validation);
        }
        
        // Delega para o MockDataService para processamento real
        return mockDataService.processForm(formData);
    }
    
    /**
     * Validação em tempo real de campo individual.
     * Usado para feedback imediato no frontend.
     * 
     * @param fieldName Nome do campo
     * @param value Valor do campo
     * @param formType Tipo do formulário
     * @return ValidationResult apenas para este campo
     */
    public ValidationResult validateField(String fieldName, String value, String formType) {
        ValidationResult result = new ValidationResult();
        
        if (value == null) {
            value = "";
        }
        
        switch (fieldName.toLowerCase()) {
            case "name", "firstname", "lastname":
                validateNameField(fieldName, value, result);
                break;
            case "email":
                validateEmailField(value, result);
                break;
            case "phone", "telefone":
                validatePhoneField(value, result);
                break;
            case "cpf":
                validateCpfField(value, result);
                break;
            case "password", "senha":
                validatePasswordField(value, result, formType);
                break;
            case "confirmpassword", "confirmarsenha":
                // Para confirmação de senha, precisamos do valor da senha original
                // Em um caso real, isso viria do contexto do formulário
                break;
            case "message", "mensagem":
                validateMessageField(value, result);
                break;
            case "company", "empresa":
                validateCompanyField(value, result);
                break;
            default:
                // Validação genérica para campos não especificados
                validateGenericField(fieldName, value, result);
                break;
        }
        
        return result;
    }
    
    /**
     * Validação assíncrona que simula verificações em banco de dados.
     * 
     * @param fieldName Nome do campo
     * @param value Valor para verificar
     * @return ValidationResult com resultado da verificação
     */
    public ValidationResult validateFieldAsync(String fieldName, String value) {
        ValidationResult result = new ValidationResult();
        
        // Simula delay de verificação no servidor
        try {
            Thread.sleep(ThreadLocalRandom.current().nextInt(100, 500));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        switch (fieldName.toLowerCase()) {
            case "email":
                // Simula verificação de email já cadastrado
                if (isEmailAlreadyRegistered(value)) {
                    result.addFieldError("email", "Este email já está cadastrado no sistema");
                }
                break;
            case "username":
                // Simula verificação de username disponível
                if (isUsernameUnavailable(value)) {
                    result.addFieldError("username", "Este nome de usuário não está disponível");
                }
                break;
            case "cpf":
                // Simula verificação de CPF já cadastrado
                if (isCpfAlreadyRegistered(value)) {
                    result.addFieldError("cpf", "Este CPF já está cadastrado no sistema");
                }
                break;
        }
        
        return result;
    }
    
    /**
     * Obtém sugestões para preenchimento automático.
     * 
     * @param fieldName Nome do campo
     * @param partialValue Valor parcial digitado
     * @return Lista de sugestões
     */
    public List<String> getFieldSuggestions(String fieldName, String partialValue) {
        switch (fieldName.toLowerCase()) {
            case "company", "empresa":
                return getCompanySuggestions(partialValue);
            case "city", "cidade":
                return getCitySuggestions(partialValue);
            case "state", "estado":
                return getStateSuggestions(partialValue);
            case "country", "pais":
                return getCountrySuggestions(partialValue);
            default:
                return List.of();
        }
    }
    
    /**
     * Simula upload de arquivo com validação.
     * 
     * @param fileName Nome do arquivo
     * @param fileSize Tamanho do arquivo em bytes
     * @param mimeType Tipo MIME do arquivo
     * @return ValidationResult com resultado da validação
     */
    public ValidationResult validateFileUpload(String fileName, long fileSize, String mimeType) {
        ValidationResult result = new ValidationResult();
        
        // Validação de tamanho (máximo 5MB)
        if (fileSize > 5 * 1024 * 1024) {
            result.addFieldError("file", "Arquivo muito grande. Tamanho máximo: 5MB");
        }
        
        // Validação de tipo
        List<String> allowedTypes = List.of(
                "image/jpeg", "image/png", "image/gif",
                "application/pdf", "text/plain",
                "application/msword", "application/vnd.openxmlformats-officedocument.wordprocessingml.document"
        );
        
        if (!allowedTypes.contains(mimeType)) {
            result.addFieldError("file", "Tipo de arquivo não permitido");
        }
        
        // Validação de nome do arquivo
        if (fileName == null || fileName.trim().isEmpty()) {
            result.addFieldError("file", "Nome do arquivo é obrigatório");
        } else if (fileName.length() > 255) {
            result.addFieldError("file", "Nome do arquivo muito longo");
        }
        
        return result;
    }
    
    /**
     * Obtém configurações de validação para um tipo de formulário.
     * 
     * @param formType Tipo do formulário
     * @return Map com configurações de validação
     */
    public Map<String, Object> getValidationConfig(String formType) {
        return Map.of(
                "realTimeValidation", true,
                "asyncValidation", List.of("email", "username", "cpf"),
                "requiredFields", getRequiredFields(formType),
                "maxLengths", Map.of(
                        "name", 100,
                        "email", 255,
                        "phone", 20,
                        "message", 1000
                ),
                "patterns", Map.of(
                        "email", EMAIL_PATTERN.pattern(),
                        "phone", PHONE_PATTERN.pattern(),
                        "cpf", CPF_PATTERN.pattern()
                )
        );
    }
    
    // Métodos de validação específicos
    
    private ValidationResult validateForm(Map<String, String> formData, String formType) {
        ValidationResult result = new ValidationResult();
        
        List<String> requiredFields = getRequiredFields(formType);
        
        // Validação de campos obrigatórios
        for (String field : requiredFields) {
            String value = formData.get(field);
            if (value == null || value.trim().isEmpty()) {
                result.addFieldError(field, "Campo obrigatório");
            } else {
                // Validação específica do campo
                ValidationResult fieldResult = validateField(field, value, formType);
                if (fieldResult.hasFieldError(field)) {
                    result.addFieldErrors(field, fieldResult.getFieldErrors(field));
                }
            }
        }
        
        // Validações globais específicas do tipo de formulário
        if ("registration".equals(formType)) {
            validatePasswordConfirmation(formData, result);
        }
        
        return result;
    }
    
    private void validateNameField(String fieldName, String value, ValidationResult result) {
        if (value.length() < 2) {
            result.addFieldError(fieldName, "Nome deve ter pelo menos 2 caracteres");
        } else if (value.length() > 100) {
            result.addFieldError(fieldName, "Nome não pode ter mais de 100 caracteres");
        } else if (!value.matches("^[A-Za-zÀ-ÿ\\s]+$")) {
            result.addFieldError(fieldName, "Nome deve conter apenas letras e espaços");
        }
    }
    
    private void validateEmailField(String value, ValidationResult result) {
        if (!EMAIL_PATTERN.matcher(value).matches()) {
            result.addFieldError("email", "Email deve ter formato válido");
        } else if (value.length() > 255) {
            result.addFieldError("email", "Email muito longo");
        }
    }
    
    private void validatePhoneField(String value, ValidationResult result) {
        if (!PHONE_PATTERN.matcher(value).matches()) {
            result.addFieldError("phone", "Telefone deve ter formato válido (ex: (11) 99999-9999)");
        }
    }
    
    private void validateCpfField(String value, ValidationResult result) {
        if (!CPF_PATTERN.matcher(value).matches()) {
            result.addFieldError("cpf", "CPF deve ter formato válido (ex: 123.456.789-00)");
        } else if (!isValidCpf(value)) {
            result.addFieldError("cpf", "CPF inválido");
        }
    }
    
    private void validatePasswordField(String value, ValidationResult result, String formType) {
        if ("registration".equals(formType)) {
            if (value.length() < 8) {
                result.addFieldError("password", "Senha deve ter pelo menos 8 caracteres");
            }
            if (!value.matches(".*[A-Z].*")) {
                result.addFieldError("password", "Senha deve conter pelo menos uma letra maiúscula");
            }
            if (!value.matches(".*[a-z].*")) {
                result.addFieldError("password", "Senha deve conter pelo menos uma letra minúscula");
            }
            if (!value.matches(".*\\d.*")) {
                result.addFieldError("password", "Senha deve conter pelo menos um número");
            }
        } else {
            if (value.length() < 6) {
                result.addFieldError("password", "Senha deve ter pelo menos 6 caracteres");
            }
        }
    }
    
    private void validateMessageField(String value, ValidationResult result) {
        if (value.length() < 10) {
            result.addFieldError("message", "Mensagem deve ter pelo menos 10 caracteres");
        } else if (value.length() > 1000) {
            result.addFieldError("message", "Mensagem não pode ter mais de 1000 caracteres");
        }
    }
    
    private void validateCompanyField(String value, ValidationResult result) {
        if (value.length() > 100) {
            result.addFieldError("company", "Nome da empresa muito longo");
        }
    }
    
    private void validateGenericField(String fieldName, String value, ValidationResult result) {
        if (value.length() > 255) {
            result.addFieldError(fieldName, "Valor muito longo");
        }
    }
    
    private void validatePasswordConfirmation(Map<String, String> formData, ValidationResult result) {
        String password = formData.get("password");
        String confirmPassword = formData.get("confirmPassword");
        
        if (password != null && confirmPassword != null && !password.equals(confirmPassword)) {
            result.addFieldError("confirmPassword", "Senhas não coincidem");
        }
    }
    
    // Métodos auxiliares
    
    private List<String> getRequiredFields(String formType) {
        return switch (formType) {
            case "contact" -> List.of("name", "email", "message");
            case "registration" -> List.of("name", "email", "password", "confirmPassword");
            case "profile" -> List.of("name", "email");
            case "newsletter" -> List.of("email");
            default -> List.of("name", "email");
        };
    }
    
    private boolean isEmailAlreadyRegistered(String email) {
        // Simula verificação no banco - 20% dos emails são "já cadastrados"
        return ThreadLocalRandom.current().nextDouble() < 0.2;
    }
    
    private boolean isUsernameUnavailable(String username) {
        // Simula verificação no banco - 30% dos usernames são "indisponíveis"
        return ThreadLocalRandom.current().nextDouble() < 0.3;
    }
    
    private boolean isCpfAlreadyRegistered(String cpf) {
        // Simula verificação no banco - 15% dos CPFs são "já cadastrados"
        return ThreadLocalRandom.current().nextDouble() < 0.15;
    }
    
    private boolean isValidCpf(String cpf) {
        // Simplificação: aceita qualquer CPF com formato correto
        // Em produção, implementaria o algoritmo completo de validação de CPF
        return true;
    }
    
    private List<String> getCompanySuggestions(String partialValue) {
        List<String> companies = List.of(
                "Google Brasil", "Microsoft Brasil", "Amazon Brasil", "Facebook Brasil",
                "IBM Brasil", "Oracle Brasil", "SAP Brasil", "Salesforce Brasil",
                "Petrobras", "Vale", "Itaú Unibanco", "Banco do Brasil"
        );
        
        if (partialValue == null || partialValue.trim().isEmpty()) {
            return companies.subList(0, Math.min(5, companies.size()));
        }
        
        String lower = partialValue.toLowerCase();
        return companies.stream()
                .filter(company -> company.toLowerCase().contains(lower))
                .limit(5)
                .toList();
    }
    
    private List<String> getCitySuggestions(String partialValue) {
        List<String> cities = List.of(
                "São Paulo", "Rio de Janeiro", "Brasília", "Salvador", "Fortaleza",
                "Belo Horizonte", "Manaus", "Curitiba", "Recife", "Porto Alegre",
                "Belém", "Goiânia", "Guarulhos", "Campinas", "São Luís"
        );
        
        if (partialValue == null || partialValue.trim().isEmpty()) {
            return cities.subList(0, Math.min(5, cities.size()));
        }
        
        String lower = partialValue.toLowerCase();
        return cities.stream()
                .filter(city -> city.toLowerCase().contains(lower))
                .limit(5)
                .toList();
    }
    
    private List<String> getStateSuggestions(String partialValue) {
        List<String> states = List.of(
                "São Paulo", "Rio de Janeiro", "Minas Gerais", "Bahia", "Paraná",
                "Rio Grande do Sul", "Pernambuco", "Ceará", "Pará", "Santa Catarina",
                "Maranhão", "Goiás", "Paraíba", "Espírito Santo", "Alagoas"
        );
        
        if (partialValue == null || partialValue.trim().isEmpty()) {
            return states.subList(0, Math.min(5, states.size()));
        }
        
        String lower = partialValue.toLowerCase();
        return states.stream()
                .filter(state -> state.toLowerCase().contains(lower))
                .limit(5)
                .toList();
    }
    
    private List<String> getCountrySuggestions(String partialValue) {
        return List.of("Brasil", "Argentina", "Chile", "Uruguai", "Paraguai");
    }
} 