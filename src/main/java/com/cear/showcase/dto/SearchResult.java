package com.cear.showcase.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO representando um resultado de busca de funcionário.
 * Utilizado na funcionalidade Active Search para demonstrar 
 * interações reativas com Datastar.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchResult {
    
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private String position;
    private String avatar;
    
    /**
     * Método computado para obter o nome completo.
     * Útil para uso em templates JTE.
     * 
     * @return Nome completo formatado
     */
    @JsonIgnore
    public String getFullName() {
        if (firstName == null && lastName == null) {
            return "";
        }
        if (firstName == null) {
            return lastName;
        }
        if (lastName == null) {
            return firstName;
        }
        return firstName + " " + lastName;
    }
    
    /**
     * Método computado para obter as iniciais do nome.
     * Útil para avatars quando não há imagem.
     * 
     * @return Iniciais do nome (ex: "JS" para "João Silva")
     */
    @JsonIgnore
    public String getInitials() {
        StringBuilder initials = new StringBuilder();
        
        if (firstName != null && !firstName.isEmpty()) {
            initials.append(firstName.charAt(0));
        }
        
        if (lastName != null && !lastName.isEmpty()) {
            initials.append(lastName.charAt(0));
        }
        
        return initials.toString().toUpperCase();
    }
    
    /**
     * Verifica se o resultado possui avatar.
     * 
     * @return true se há avatar definido
     */
    @JsonIgnore
    public boolean hasAvatar() {
        return avatar != null && !avatar.trim().isEmpty();
    }
    
    /**
     * Obtém a URL do avatar ou uma URL padrão baseada nas iniciais.
     * 
     * @return URL do avatar
     */
    @JsonIgnore
    public String getAvatarUrl() {
        if (hasAvatar()) {
            return avatar;
        }
        // Fallback para um serviço de avatar baseado em iniciais
        return "https://ui-avatars.com/api/?name=" + getInitials() + "&background=f97316&color=fff";
    }
} 