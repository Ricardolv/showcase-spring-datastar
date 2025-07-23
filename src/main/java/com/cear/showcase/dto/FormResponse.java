package com.cear.showcase.dto;

import java.time.LocalDateTime;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representando uma resposta de formulário.
 * Utilizado na funcionalidade Reactive Forms para demonstrar
 * validação e feedback em tempo real com Datastar.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FormResponse {
    
    private boolean success;
    private String message;
    private Map<String, Object> data;
    private ValidationResult validation;
    private String redirectUrl;
    private LocalDateTime processedAt;
    private String requestId;
    private ResponseType type;
    
    /**
     * Tipos de resposta do formulário.
     */
    public enum ResponseType {
        SUCCESS,      // Sucesso na submissão
        VALIDATION,   // Erro de validação
        ERROR,        // Erro de processamento
        WARNING,      // Aviso/atenção
        INFO          // Informação
    }
    
    /**
     * Cria uma resposta de sucesso.
     * 
     * @param message Mensagem de sucesso
     * @return FormResponse de sucesso
     */
    public static FormResponse success(String message) {
        return FormResponse.builder()
                .success(true)
                .message(message)
                .type(ResponseType.SUCCESS)
                .processedAt(LocalDateTime.now())
                .build();
    }
    
    /**
     * Cria uma resposta de sucesso com dados.
     * 
     * @param message Mensagem de sucesso
     * @param data Dados da resposta
     * @return FormResponse de sucesso com dados
     */
    public static FormResponse success(String message, Map<String, Object> data) {
        return FormResponse.builder()
                .success(true)
                .message(message)
                .data(data)
                .type(ResponseType.SUCCESS)
                .processedAt(LocalDateTime.now())
                .build();
    }
    
    /**
     * Cria uma resposta de erro de validação.
     * 
     * @param validation Resultado da validação
     * @return FormResponse com erro de validação
     */
    public static FormResponse validationError(ValidationResult validation) {
        return FormResponse.builder()
                .success(false)
                .message("Erro de validação. Verifique os campos abaixo.")
                .validation(validation)
                .type(ResponseType.VALIDATION)
                .processedAt(LocalDateTime.now())
                .build();
    }
    
    /**
     * Cria uma resposta de erro.
     * 
     * @param message Mensagem de erro
     * @return FormResponse de erro
     */
    public static FormResponse error(String message) {
        return FormResponse.builder()
                .success(false)
                .message(message)
                .type(ResponseType.ERROR)
                .processedAt(LocalDateTime.now())
                .build();
    }
    
    /**
     * Cria uma resposta de aviso.
     * 
     * @param message Mensagem de aviso
     * @return FormResponse de aviso
     */
    public static FormResponse warning(String message) {
        return FormResponse.builder()
                .success(true)
                .message(message)
                .type(ResponseType.WARNING)
                .processedAt(LocalDateTime.now())
                .build();
    }
    
    /**
     * Obtém a classe CSS para o tipo da mensagem.
     * 
     * @return Classe CSS baseada no tipo
     */
    @JsonIgnore
    public String getMessageTypeClass() {
        switch (type) {
            case SUCCESS:
                return "bg-green-50 border-green-200 text-green-800";
            case ERROR:
                return "bg-red-50 border-red-200 text-red-800";
            case VALIDATION:
                return "bg-yellow-50 border-yellow-200 text-yellow-800";
            case WARNING:
                return "bg-orange-50 border-orange-200 text-orange-800";
            case INFO:
                return "bg-blue-50 border-blue-200 text-blue-800";
            default:
                return "bg-gray-50 border-gray-200 text-gray-800";
        }
    }
    
    /**
     * Obtém o ícone adequado para o tipo da mensagem.
     * 
     * @return Classe do ícone
     */
    @JsonIgnore
    public String getMessageIcon() {
        switch (type) {
            case SUCCESS:
                return "check-circle";
            case ERROR:
                return "x-circle";
            case VALIDATION:
                return "exclamation-triangle";
            case WARNING:
                return "exclamation-circle";
            case INFO:
                return "information-circle";
            default:
                return "information-circle";
        }
    }
    
    /**
     * Verifica se há dados na resposta.
     * 
     * @return true se há dados
     */
    @JsonIgnore
    public boolean hasData() {
        return data != null && !data.isEmpty();
    }
    
    /**
     * Verifica se há redirecionamento.
     * 
     * @return true se há URL de redirecionamento
     */
    @JsonIgnore
    public boolean hasRedirect() {
        return redirectUrl != null && !redirectUrl.trim().isEmpty();
    }
    
    /**
     * Verifica se há erros de validação.
     * 
     * @return true se há erros de validação
     */
    @JsonIgnore
    public boolean hasValidationErrors() {
        return validation != null && validation.hasErrors();
    }
    
    /**
     * Obtém um valor específico dos dados da resposta.
     * 
     * @param key Chave do dado
     * @return Valor ou null se não encontrado
     */
    @JsonIgnore
    public Object getData(String key) {
        if (data == null) {
            return null;
        }
        return data.get(key);
    }
} 