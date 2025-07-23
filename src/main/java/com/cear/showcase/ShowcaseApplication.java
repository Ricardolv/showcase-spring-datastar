package com.cear.showcase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * CEAR Showcase Application
 * 
 * Sistema de demonstração educativo que integra:
 * - Datastar (framework hipermídia reativo)
 * - Tailwind CSS v4 (paleta CEAR personalizada)
 * - Spring Boot (backend com templates JTE)
 * 
 * Objetivo: Demonstrar a integração perfeita entre estas tecnologias
 * através de exemplos práticos e interativos.
 * 
 * Funcionalidades demonstradas:
 * - Active Search (busca dinâmica com debounce)
 * - Dashboard Interativo (widgets reativos com SSE)
 * - Formulários Reativos (validação em tempo real)
 * - Galeria de Componentes (showcase da paleta CEAR)
 * 
 * @author Equipe CEAR
 * @version 1.0.0
 */
@SpringBootApplication
public class ShowcaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShowcaseApplication.class, args);
    }

} 