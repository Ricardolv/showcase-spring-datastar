package com.cear.showcase.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representando o resultado de validação de formulário.
 * Utilizado para comunicar erros de validação específicos por campo
 * e mensagens gerais de validação.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidationResult {
    
    @Builder.Default
    private boolean valid = true;
    
    @Builder.Default
    private Map<String, List<String>> fieldErrors = new HashMap<>();
    
    @Builder.Default
    private List<String> globalErrors = new ArrayList<>();
    
    @Builder.Default
    private List<String> warnings = new ArrayList<>();
    
    /**
     * Adiciona um erro para um campo específico.
     * 
     * @param field Nome do campo
     * @param error Mensagem de erro
     */
    public void addFieldError(String field, String error) {
        this.valid = false;
        fieldErrors.computeIfAbsent(field, k -> new ArrayList<>()).add(error);
    }
    
    /**
     * Adiciona múltiplos erros para um campo específico.
     * 
     * @param field Nome do campo
     * @param errors Lista de mensagens de erro
     */
    public void addFieldErrors(String field, List<String> errors) {
        this.valid = false;
        fieldErrors.computeIfAbsent(field, k -> new ArrayList<>()).addAll(errors);
    }
    
    /**
     * Adiciona um erro global (não específico de campo).
     * 
     * @param error Mensagem de erro
     */
    public void addGlobalError(String error) {
        this.valid = false;
        globalErrors.add(error);
    }
    
    /**
     * Adiciona um aviso.
     * 
     * @param warning Mensagem de aviso
     */
    public void addWarning(String warning) {
        warnings.add(warning);
    }
    
    /**
     * Verifica se há erros de validação.
     * 
     * @return true se há erros
     */
    @JsonIgnore
    public boolean hasErrors() {
        return !valid || !fieldErrors.isEmpty() || !globalErrors.isEmpty();
    }
    
    /**
     * Verifica se há erros para um campo específico.
     * 
     * @param field Nome do campo
     * @return true se há erros para o campo
     */
    @JsonIgnore
    public boolean hasFieldError(String field) {
        List<String> errors = fieldErrors.get(field);
        return errors != null && !errors.isEmpty();
    }
    
    /**
     * Obtém os erros de um campo específico.
     * 
     * @param field Nome do campo
     * @return Lista de erros ou lista vazia
     */
    @JsonIgnore
    public List<String> getFieldErrors(String field) {
        return fieldErrors.getOrDefault(field, new ArrayList<>());
    }
    
    /**
     * Obtém o primeiro erro de um campo específico.
     * 
     * @param field Nome do campo
     * @return Primeira mensagem de erro ou null
     */
    @JsonIgnore
    public String getFirstFieldError(String field) {
        List<String> errors = getFieldErrors(field);
        return errors.isEmpty() ? null : errors.get(0);
    }
    
    /**
     * Verifica se há erros globais.
     * 
     * @return true se há erros globais
     */
    @JsonIgnore
    public boolean hasGlobalErrors() {
        return !globalErrors.isEmpty();
    }
    
    /**
     * Verifica se há avisos.
     * 
     * @return true se há avisos
     */
    @JsonIgnore
    public boolean hasWarnings() {
        return !warnings.isEmpty();
    }
    
    /**
     * Obtém o total de erros (campos + globais).
     * 
     * @return Número total de erros
     */
    @JsonIgnore
    public int getTotalErrorCount() {
        int fieldErrorCount = fieldErrors.values().stream()
                .mapToInt(List::size)
                .sum();
        return fieldErrorCount + globalErrors.size();
    }
    
    /**
     * Obtém todos os campos com erro.
     * 
     * @return Set com nomes dos campos com erro
     */
    @JsonIgnore
    public java.util.Set<String> getFieldsWithErrors() {
        return fieldErrors.keySet();
    }
    
    /**
     * Limpa todos os erros de validação.
     */
    public void clearErrors() {
        this.valid = true;
        this.fieldErrors.clear();
        this.globalErrors.clear();
    }
    
    /**
     * Limpa erros de um campo específico.
     * 
     * @param field Nome do campo
     */
    public void clearFieldErrors(String field) {
        fieldErrors.remove(field);
        // Recalcula validade
        this.valid = fieldErrors.isEmpty() && globalErrors.isEmpty();
    }
    
    /**
     * Obtém a classe CSS para um campo baseado no status de validação.
     * 
     * @param field Nome do campo
     * @return Classe CSS apropriada
     */
    @JsonIgnore
    public String getFieldStatusClass(String field) {
        if (hasFieldError(field)) {
            return "border-red-500 text-red-900 placeholder-red-300 focus:border-red-500 focus:ring-red-500";
        }
        return "border-gray-300 focus:border-orange-500 focus:ring-orange-500";
    }
    
    /**
     * Obtém a classe CSS para o ícone de status do campo.
     * 
     * @param field Nome do campo
     * @return Classe CSS do ícone
     */
    @JsonIgnore
    public String getFieldIconClass(String field) {
        if (hasFieldError(field)) {
            return "text-red-500";
        }
        return "text-gray-400";
    }
    
    /**
     * Cria um ValidationResult válido (sem erros).
     * 
     * @return ValidationResult válido
     */
    public static ValidationResult valid() {
        return ValidationResult.builder()
                .valid(true)
                .build();
    }
    
    /**
     * Cria um ValidationResult com erro de campo.
     * 
     * @param field Nome do campo
     * @param error Mensagem de erro
     * @return ValidationResult com erro
     */
    public static ValidationResult withFieldError(String field, String error) {
        ValidationResult result = new ValidationResult();
        result.addFieldError(field, error);
        return result;
    }
    
    /**
     * Cria um ValidationResult com erro global.
     * 
     * @param error Mensagem de erro
     * @return ValidationResult com erro global
     */
    public static ValidationResult withGlobalError(String error) {
        ValidationResult result = new ValidationResult();
        result.addGlobalError(error);
        return result;
    }
} 