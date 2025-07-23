package com.cear.showcase.config;

import java.time.Duration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Configuração Web para o CEAR Showcase
 * 
 * Responsabilidades:
 * - Configuração de CORS para desenvolvimento local
 * - Gestão de recursos estáticos com cache apropriado
 * - Headers necessários para funcionamento do Datastar
 * - Interceptors para configurações específicas
 * 
 * Esta configuração é simplificada e focada na demonstração,
 * sem implementar complexidades de segurança encontradas
 * em sistemas de produção.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configuração de CORS para desenvolvimento
     * 
     * Permite requisições do frontend local durante desenvolvimento.
     * Em produção, estas configurações devem ser mais restritivas.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * Configuração de recursos estáticos
     * 
     * Define cache apropriado para CSS, JS, imagens e outros assets.
     * - Desenvolvimento: cache de 30 minutos para permitir mudanças rápidas
     * - Produção: cache mais longo (configurado via application-prod.yml)
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(Duration.ofMinutes(30).toSecondsPart());
        
        // Mapeamento específico para CSS gerado pelo Tailwind
        registry.addResourceHandler("/css/**")
                .addResourceLocations("classpath:/static/css/")
                .setCachePeriod(Duration.ofMinutes(30).toSecondsPart());
        
        // Mapeamento para JavaScript (incluindo Datastar CDN local)
        registry.addResourceHandler("/js/**")
                .addResourceLocations("classpath:/static/js/")
                .setCachePeriod(Duration.ofMinutes(30).toSecondsPart());
        
        // Mapeamento para imagens
        registry.addResourceHandler("/images/**")
                .addResourceLocations("classpath:/static/images/")
                .setCachePeriod(Duration.ofDays(7).toSecondsPart());
    }

    /**
     * Configuração de interceptors
     * 
     * Adiciona headers necessários para o funcionamento correto do Datastar
     * e outras configurações específicas do showcase.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new DatastarHeaderInterceptor());
    }

    /**
     * Interceptor para configuração de headers específicos do Datastar
     * 
     * O Datastar requer certos headers HTTP para funcionar corretamente,
     * especialmente para fragmentos HTML e Server-Sent Events.
     */
    public static class DatastarHeaderInterceptor implements HandlerInterceptor {
        
        @Override
        public boolean preHandle(HttpServletRequest request, 
                               HttpServletResponse response, 
                               Object handler) {
            
            // Headers necessários para Datastar
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("X-Content-Type-Options", "nosniff");
            
            // Header para permitir Server-Sent Events
            if (request.getRequestURI().contains("/live-updates")) {
                response.setHeader("Content-Type", "text/event-stream");
                response.setHeader("Connection", "keep-alive");
            }
            
            // Headers para fragmentos HTML (requisições AJAX do Datastar)
            if (isDatastarRequest(request)) {
                response.setHeader("X-Powered-By", "Datastar");
                response.setHeader("Vary", "Accept");
            }
            
            return true;
        }
        
        /**
         * Detecta se a requisição foi feita pelo Datastar
         * baseado em headers ou padrões de URL
         */
        private boolean isDatastarRequest(HttpServletRequest request) {
            // Datastar adiciona headers específicos em suas requisições
            String accept = request.getHeader("Accept");
            String requestedWith = request.getHeader("X-Requested-With");
            
            return (accept != null && accept.contains("text/html")) ||
                   "XMLHttpRequest".equals(requestedWith) ||
                   request.getRequestURI().contains("/fragments/") ||
                   request.getRequestURI().contains("/results") ||
                   request.getRequestURI().contains("/validate") ||
                   request.getRequestURI().contains("/stats");
        }
    }
} 