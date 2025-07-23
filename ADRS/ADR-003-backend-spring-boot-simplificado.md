# ADR-SHOWCASE-003: Backend Spring Boot Simplificado para Demonstração

## Status
Aceito

## Contexto
O backend do CEAR Showcase precisa ser **simples, focado e educativo**, demonstrando as capacidades do Spring Boot sem as complexidades de um sistema real:

- **Sem banco de dados**: Dados mock em memória para simplicidade total
- **Sem Spring Security**: Foco na demonstração de interatividade, não em segurança
- **Controllers limpos**: Exemplos claros de como servir fragmentos HTML para Datastar
- **DTOs bem estruturados**: Demonstrar separação correta entre dados e apresentação
- **Hot reload**: Desenvolvimento ágil com Spring Boot DevTools
- **Didático**: Código serve de referência para implementação real

## Decisão
Implementaremos um **backend Spring Boot minimalista** focado em **servir dados mock** e **fragmentos HTML** para demonstração das capacidades do Datastar.

### Tecnologias Escolhidas:
- **Spring Boot 3.x**: Framework principal sem módulos desnecessários
- **Spring Web**: Apenas MVC para controllers
- **JTE**: Templates server-side type-safe
- **Lombok**: Redução de boilerplate
- **Spring Boot DevTools**: Hot reload para desenvolvimento
- **Dados Mock**: Listas e objetos em memória (sem persistência)

## Arquitetura Backend Simplificada

```
┌────────────────────────────────────────────────────────────┐
│                Spring Boot Showcase Backend               │
│                                                            │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │   Controllers   │    │   JTE Templates │                │
│  │   (@Controller) │◄──►│   (Fragments)   │                │
│  │                 │    │                 │                │
│  │ • HomeController│    │ • search.jte    │                │
│  │ • SearchCtrl    │    │ • dashboard.jte │                │
│  │ • DashboardCtrl │    │ • forms.jte     │                │
│  │ • FormController│    │ • fragments/    │                │
│  └─────────────────┘    └─────────────────┘                │
│           │                                                │
│           ▼                                                │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │  Mock Services  │    │   Response DTOs │                │
│  │  (@Service)     │◄──►│  (Clean Data)   │                │
│  │                 │    │                 │                │
│  │ • MockDataSvc   │    │ • SearchResult  │                │
│  │ • SearchService │    │ • DashboardData │                │
│  │ • StatsService  │    │ • FormResponse  │                │
│  │ • FormService   │    │ • ValidationMsg │                │
│  └─────────────────┘    └─────────────────┘                │
│           │                                                │
│           ▼                                                │
│  ┌─────────────────────────────────────────┐               │
│  │              Mock Data Layer            │               │
│  │  • Lists.of() static data               │               │
│  │  • Random generators                    │               │
│  │  • Faker library (optional)            │               │
│  └─────────────────────────────────────────┘               │
└────────────────────────────────────────────────────────────┘
```

## Controllers para Demonstração

### 1. **Search Controller** (Active Search Demo)

```java
/**
 * Controller para demonstração de Active Search com Datastar
 * Baseado no exemplo: https://data-star.dev/examples/active_search
 */
@Controller
@RequestMapping("/search")
@RequiredArgsConstructor
@Slf4j
public class SearchController {
    
    private final SearchService searchService;
    
    /**
     * Página principal de busca
     */
    @GetMapping
    public String searchPage(Model model) {
        model.addAttribute("title", "Active Search Demo");
        model.addAttribute("description", "Busca dinâmica inspirada no data-star.dev");
        return "pages/search";
    }
    
    /**
     * Endpoint para resultados de busca (fragmento HTML)
     * Retorna apenas o HTML dos resultados, não uma página completa
     */
    @GetMapping("/results")
    public String searchResults(@RequestParam(defaultValue = "") String search,
                               Model model) {
        log.debug("Searching for: '{}'", search);
        
        // Simular delay de rede (opcional para demo)
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Buscar dados mock
        List<SearchResult> results = searchService.searchContacts(search);
        
        // Adicionar dados ao modelo
        model.addAttribute("results", results);
        model.addAttribute("query", search);
        model.addAttribute("totalResults", results.size());
        
        // Retornar apenas o fragmento, não página completa
        return "fragments/search-results";
    }
    
    /**
     * Endpoint para sugestões de busca (JSON)
     */
    @GetMapping("/suggestions")
    @ResponseBody
    public List<String> getSuggestions(@RequestParam String query) {
        return searchService.getSuggestions(query);
    }
}
```

### 2. **Dashboard Controller** (Widgets Reativos)

```java
/**
 * Controller para demonstração de dashboard com widgets reativos
 * Simula dados em tempo real usando Server-Sent Events
 */
@Controller
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
    
    private final DashboardService dashboardService;
    
    /**
     * Página principal do dashboard
     */
    @GetMapping
    public String dashboardPage(Model model) {
        // Dados iniciais do dashboard
        DashboardData initialData = dashboardService.getInitialData();
        
        model.addAttribute("dashboard", initialData);
        model.addAttribute("title", "Dashboard Interativo");
        return "pages/dashboard";
    }
    
    /**
     * Endpoint para atualização de estatísticas (fragmento HTML)
     * Usado pelos widgets para auto-atualização
     */
    @GetMapping("/stats")
    public String getStats(Model model) {
        // Gerar dados mock atualizados
        List<WidgetData> widgets = dashboardService.generateRandomStats();
        
        model.addAttribute("widgets", widgets);
        model.addAttribute("lastUpdate", LocalDateTime.now());
        
        return "fragments/widget-stats";
    }
    
    /**
     * Server-Sent Events para atualizações em tempo real
     * Demonstra capabilities avançadas do Datastar
     */
    @GetMapping("/live-updates")
    public SseEmitter liveUpdates() {
        SseEmitter emitter = new SseEmitter(30000L); // 30s timeout
        
        // Simular dados em tempo real
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        
        executor.scheduleAtFixedRate(() -> {
            try {
                LiveUpdate update = dashboardService.generateLiveUpdate();
                emitter.send(SseEmitter.event()
                    .name("stats-update")
                    .data(update));
            } catch (Exception e) {
                log.error("Erro ao enviar SSE: {}", e.getMessage());
                emitter.completeWithError(e);
            }
        }, 0, 2, TimeUnit.SECONDS);
        
        emitter.onCompletion(() -> executor.shutdown());
        emitter.onError(e -> executor.shutdown());
        
        return emitter;
    }
    
    /**
     * Widget específico (fragmento HTML)
     */
    @GetMapping("/widget/{type}")
    public String getWidget(@PathVariable String type, Model model) {
        WidgetData widget = dashboardService.getWidgetData(type);
        
        model.addAttribute("widget", widget);
        model.addAttribute("type", type);
        
        return "fragments/dashboard-widget";
    }
}
```

### 3. **Form Controller** (Validação Reativa)

```java
/**
 * Controller para demonstração de formulários reativos
 * Validação em tempo real e feedback instantâneo
 */
@Controller
@RequestMapping("/forms")
@RequiredArgsConstructor
@Slf4j
public class FormController {
    
    private final FormService formService;
    
    /**
     * Página de formulários
     */
    @GetMapping
    public String formsPage(Model model) {
        model.addAttribute("form", new ContactFormData());
        model.addAttribute("title", "Formulários Reativos");
        return "pages/forms";
    }
    
    /**
     * Validação de email em tempo real
     */
    @PostMapping("/validate-email")
    public String validateEmail(@RequestParam String email, Model model) {
        ValidationResult result = formService.validateEmail(email);
        
        model.addAttribute("validation", result);
        model.addAttribute("field", "email");
        
        return "fragments/form-validation";
    }
    
    /**
     * Contador de caracteres dinâmico
     */
    @PostMapping("/count-chars")
    public String countCharacters(@RequestParam String message, Model model) {
        CharCountResult result = formService.countCharacters(message);
        
        model.addAttribute("charCount", result);
        
        return "fragments/char-counter";
    }
    
    /**
     * Submissão do formulário completo
     */
    @PostMapping("/contact")
    public String submitContact(@Valid @ModelAttribute ContactFormData form,
                               BindingResult bindingResult,
                               Model model) {
        
        if (bindingResult.hasErrors()) {
            FormResponse response = FormResponse.builder()
                .success(false)
                .message("Por favor, corrija os erros destacados")
                .errors(bindingResult.getAllErrors())
                .build();
            
            model.addAttribute("response", response);
            return "fragments/form-response";
        }
        
        // Simular processamento
        FormResponse response = formService.processContactForm(form);
        
        model.addAttribute("response", response);
        return "fragments/form-response";
    }
}
```

## Services com Dados Mock

### 1. **Search Service**

```java
/**
 * Service para busca simulada
 * Demonstra como implementar busca sem banco de dados
 */
@Service
@Slf4j
public class SearchService {
    
    // Dados mock estáticos
    private static final List<SearchResult> MOCK_CONTACTS = List.of(
        SearchResult.builder()
            .id(1L)
            .firstName("Ana")
            .lastName("Silva")
            .email("ana.silva@cear.com")
            .department("Tecnologia")
            .position("Desenvolvedora")
            .build(),
        SearchResult.builder()
            .id(2L)
            .firstName("Bruno")
            .lastName("Santos")
            .email("bruno.santos@cear.com")
            .department("Design")
            .position("UX Designer")
            .build(),
        SearchResult.builder()
            .id(3L)
            .firstName("Carla")
            .lastName("Oliveira")
            .email("carla.oliveira@cear.com")
            .department("Produto")
            .position("Product Manager")
            .build()
        // ... mais contatos mock
    );
    
    /**
     * Busca contatos por termo
     */
    public List<SearchResult> searchContacts(String query) {
        if (query == null || query.trim().isEmpty()) {
            return Collections.emptyList();
        }
        
        String lowerQuery = query.toLowerCase().trim();
        
        return MOCK_CONTACTS.stream()
            .filter(contact -> matchesQuery(contact, lowerQuery))
            .limit(10) // Limitar resultados para demo
            .collect(Collectors.toList());
    }
    
    /**
     * Gera sugestões de busca
     */
    public List<String> getSuggestions(String query) {
        // Sugestões baseadas nos dados mock
        return MOCK_CONTACTS.stream()
            .flatMap(contact -> Stream.of(
                contact.getFirstName(),
                contact.getLastName(),
                contact.getDepartment()
            ))
            .filter(term -> term.toLowerCase().startsWith(query.toLowerCase()))
            .distinct()
            .limit(5)
            .collect(Collectors.toList());
    }
    
    private boolean matchesQuery(SearchResult contact, String query) {
        return contact.getFirstName().toLowerCase().contains(query) ||
               contact.getLastName().toLowerCase().contains(query) ||
               contact.getEmail().toLowerCase().contains(query) ||
               contact.getDepartment().toLowerCase().contains(query);
    }
}
```

### 2. **Dashboard Service**

```java
/**
 * Service para dados do dashboard
 * Simula estatísticas em tempo real
 */
@Service
@Slf4j
public class DashboardService {
    
    private final Random random = new Random();
    
    /**
     * Dados iniciais do dashboard
     */
    public DashboardData getInitialData() {
        return DashboardData.builder()
            .totalUsers(random.nextInt(1000) + 500)
            .activeUsers(random.nextInt(200) + 50)
            .totalRevenue(BigDecimal.valueOf(random.nextDouble() * 100000))
            .conversionRate(random.nextDouble() * 10)
            .build();
    }
    
    /**
     * Gera estatísticas aleatórias para widgets
     */
    public List<WidgetData> generateRandomStats() {
        return List.of(
            WidgetData.builder()
                .type("users")
                .label("Usuários Online")
                .value(String.valueOf(random.nextInt(50) + 10))
                .percentage(random.nextInt(100))
                .trend(random.nextBoolean() ? "up" : "down")
                .changePercent(random.nextDouble() * 20 - 10) // -10 a +10
                .build(),
            
            WidgetData.builder()
                .type("revenue")
                .label("Receita Hoje")
                .value("R$ " + String.format("%.2f", random.nextDouble() * 10000))
                .percentage(random.nextInt(100))
                .trend("up")
                .changePercent(random.nextDouble() * 5) // 0 a +5
                .build()
            
            // ... mais widgets
        );
    }
    
    /**
     * Dados para Server-Sent Events
     */
    public LiveUpdate generateLiveUpdate() {
        return LiveUpdate.builder()
            .timestamp(LocalDateTime.now())
            .onlineUsers(random.nextInt(50) + 10)
            .newSignups(random.nextInt(5))
            .revenue(BigDecimal.valueOf(random.nextDouble() * 1000))
            .build();
    }
    
    /**
     * Dados de widget específico
     */
    public WidgetData getWidgetData(String type) {
        return switch (type) {
            case "users" -> generateUserWidget();
            case "revenue" -> generateRevenueWidget();
            case "conversion" -> generateConversionWidget();
            default -> generateDefaultWidget();
        };
    }
    
    // ... métodos auxiliares para cada tipo de widget
}
```

## DTOs Bem Estruturados

### Response DTOs

```java
/**
 * DTO para resultados de busca
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
    private String avatarUrl;
    
    // Métodos computed para template
    public String getFullName() {
        return firstName + " " + lastName;
    }
    
    public String getInitials() {
        return (firstName.charAt(0) + "" + lastName.charAt(0)).toUpperCase();
    }
}

/**
 * DTO para dados do dashboard
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardData {
    private Integer totalUsers;
    private Integer activeUsers;
    private BigDecimal totalRevenue;
    private Double conversionRate;
    private LocalDateTime lastUpdate;
    
    // Formatadores para template
    public String getFormattedRevenue() {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formatter.format(totalRevenue);
    }
    
    public String getFormattedConversionRate() {
        return String.format("%.1f%%", conversionRate);
    }
}

/**
 * DTO para widgets
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WidgetData {
    private String type;
    private String label;
    private String value;
    private Integer percentage;
    private String trend; // "up", "down", "stable"
    private Double changePercent;
    private String icon;
    private String color;
    
    // Métodos para template
    public String getTrendIcon() {
        return switch (trend) {
            case "up" -> "↗️";
            case "down" -> "↘️";
            default -> "➡️";
        };
    }
    
    public String getTrendColor() {
        return switch (trend) {
            case "up" -> "text-green-600";
            case "down" -> "text-red-600";
            default -> "text-secondary-600";
        };
    }
}
```

## Configuração Simplificada

### Application Properties

```yaml
# application.yml - Configuração mínima
server:
  port: 8080

spring:
  application:
    name: cear-showcase
  
  # JTE Configuration
  jte:
    development-mode: true
    use-precompiled-templates: false
    template-location: classpath:jte/
  
  # DevTools
  devtools:
    restart:
      enabled: true
    livereload:
      enabled: true

# Logging
logging:
  level:
    com.cear.showcase: DEBUG
    org.springframework.web: INFO
```

### Web Configuration

```java
/**
 * Configuração web simplificada
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {
    
    /**
     * Configuração de CORS para desenvolvimento
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8080")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
    
    /**
     * Recursos estáticos
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(Duration.ofDays(30).toSecondsPart());
    }
    
    /**
     * Headers para Datastar
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, 
                                   HttpServletResponse response, 
                                   Object handler) {
                // Headers necessários para Datastar
                response.setHeader("Cache-Control", "no-cache");
                response.setHeader("X-Content-Type-Options", "nosniff");
                return true;
            }
        });
    }
}
```

## Vantagens da Arquitetura Simplificada

### ✅ **Desenvolvimento Rápido:**
- **Zero configuração**: Executa imediatamente após clone
- **Hot reload**: Mudanças refletem instantaneamente  
- **Dados mock**: Sem dependência de banco de dados
- **Debugging simples**: Stack trace claro e direto

### ✅ **Foco na Demonstração:**
- **Controllers limpos**: Foco na interação Datastar
- **DTOs educativos**: Exemplos de estruturação correta
- **Fragmentos HTML**: Demonstração clara de hipermídia
- **Código didático**: Comentários explicativos extensos

### ✅ **Performance:**
- **Startup rápido**: < 5 segundos para inicializar
- **Memória baixa**: < 200MB de RAM
- **Resposta rápida**: < 50ms para endpoints mock
- **Build simples**: Maven padrão sem complexidade

## Limitações Intencionais

### ❌ **Sem Persistência:**
- Dados reiniciam a cada execução
- Não demonstra transações reais
- Limitado a dados estáticos/mock

### ❌ **Sem Segurança:**
- Não demonstra autenticação
- Endpoints públicos para simplicidade
- Foco apenas na interação frontend

### ❌ **Validações Básicas:**
- Validações simples para demo
- Sem regras de negócio complexas
- Feedback educativo, não robusto

## Critérios de Sucesso

- [ ] **Startup**: Aplicação inicia em < 5 segundos
- [ ] **Endpoints**: Todos os controllers respondem corretamente
- [ ] **Fragmentos**: HTML fragments renderizam via JTE
- [ ] **Mock Data**: Dados simulados funcionam como esperado
- [ ] **Hot Reload**: Mudanças refletem sem restart
- [ ] **Logs**: Debugging claro e informativo
- [ ] **Documentação**: Código bem comentado e explicativo

---
**Data**: Janeiro 2025  
**Autor**: Equipe CEAR  
**Revisores**: Backend, Arquitetura  
**Próxima Revisão**: Março 2025 