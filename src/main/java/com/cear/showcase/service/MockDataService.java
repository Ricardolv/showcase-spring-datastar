package com.cear.showcase.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.cear.showcase.dto.DashboardData;
import com.cear.showcase.dto.FormResponse;
import com.cear.showcase.dto.SearchResult;
import com.cear.showcase.dto.ValidationResult;
import com.cear.showcase.dto.WidgetData;

/**
 * Serviço responsável por gerar dados mock para demonstração das funcionalidades.
 * Simula dados reais sem necessidade de banco de dados.
 */
@Service
public class MockDataService {
    
    private final Random random = new Random();
    
    // Dados mock para pessoas
    private final String[] firstNames = {
        "Ana", "João", "Maria", "Carlos", "Fernanda", "Pedro", "Juliana", "Ricardo",
        "Beatriz", "Fernando", "Camila", "Roberto", "Larissa", "Marcelo", "Patrícia",
        "André", "Isabela", "Rafael", "Vanessa", "Gustavo"
    };
    
    private final String[] lastNames = {
        "Silva", "Santos", "Oliveira", "Souza", "Rodrigues", "Ferreira", "Alves", "Pereira",
        "Lima", "Gomes", "Costa", "Ribeiro", "Martins", "Carvalho", "Almeida", "Lopes",
        "Soares", "Fernandes", "Vieira", "Barbosa"
    };
    
    private final String[] departments = {
        "Tecnologia", "Recursos Humanos", "Marketing", "Vendas", "Financeiro", 
        "Operações", "Atendimento", "Jurídico", "Administrativo", "Desenvolvimento"
    };
    
    private final String[] positions = {
        "Desenvolvedor", "Analista", "Coordenador", "Gerente", "Diretor",
        "Assistente", "Especialista", "Consultor", "Supervisor", "Trainee"
    };
    
    /**
     * Gera uma lista de resultados de busca mock.
     * 
     * @param query Query de busca (pode filtrar resultados)
     * @param limit Número máximo de resultados
     * @return Lista de SearchResult
     */
    public List<SearchResult> generateSearchResults(String query, int limit) {
        List<SearchResult> results = new ArrayList<>();
        
        for (long i = 1; i <= limit; i++) {
            String firstName = firstNames[random.nextInt(firstNames.length)];
            String lastName = lastNames[random.nextInt(lastNames.length)];
            String department = departments[random.nextInt(departments.length)];
            String position = positions[random.nextInt(positions.length)];
            
            // Sempre inclui se query está vazia, senão aplica filtro
            boolean includeResult = query == null || query.trim().isEmpty() || 
                firstName.toLowerCase().contains(query.toLowerCase()) ||
                lastName.toLowerCase().contains(query.toLowerCase()) ||
                department.toLowerCase().contains(query.toLowerCase());
                
            if (includeResult) {
                
                SearchResult result = SearchResult.builder()
                        .id(i)
                        .firstName(firstName)
                        .lastName(lastName)
                        .email(firstName.toLowerCase() + "." + lastName.toLowerCase() + "@cear.ufpb.br")
                        .department(department)
                        .position(position)
                        .avatar(random.nextBoolean() ? null : generateAvatarUrl(firstName, lastName))
                        .build();
                
                results.add(result);
            }
        }
        
        return results;
    }
    
    /**
     * Gera dados mock para o dashboard.
     * 
     * @return DashboardData com métricas simuladas
     */
    public DashboardData generateDashboardData() {
        List<DashboardData.MetricPoint> chartData = generateChartData(7);
        
        return DashboardData.builder()
                .totalUsers(1250L + random.nextInt(500))
                .activeUsers(980L + random.nextInt(200))
                .totalRevenue(new BigDecimal("125430.50").add(new BigDecimal(random.nextInt(50000))))
                .monthlyRevenue(new BigDecimal("23150.00").add(new BigDecimal(random.nextInt(10000))))
                .conversionRate(12.5 + (random.nextGaussian() * 2))
                .newSignups(45 + random.nextInt(20))
                .pendingOrders(8 + random.nextInt(15))
                .avgResponseTime(1.2 + (random.nextGaussian() * 0.3))
                .lastUpdated(LocalDateTime.now().minusMinutes(random.nextInt(30)))
                .chartData(chartData)
                .build();
    }
    
    /**
     * Gera dados mock para widgets do dashboard.
     * 
     * @return Lista de WidgetData
     */
    public List<WidgetData> generateWidgets() {
        List<WidgetData> widgets = new ArrayList<>();
        
        // Widget de usuários ativos
        widgets.add(WidgetData.builder()
                .id("active-users")
                .title("Usuários Ativos")
                .type(WidgetData.WidgetType.METRIC)
                .value(new BigDecimal(980 + random.nextInt(50)))
                .unit("usuários")
                .trend("up")
                .trendPercentage(5.2 + (random.nextGaussian() * 2))
                .color("text-green-600")
                .icon("users")
                .lastUpdated(LocalDateTime.now().minusMinutes(random.nextInt(15)))
                .loading(false)
                .build());
        
        // Widget de receita
        widgets.add(WidgetData.builder()
                .id("revenue")
                .title("Receita Mensal")
                .type(WidgetData.WidgetType.METRIC)
                .value(new BigDecimal("23150.00").add(new BigDecimal(random.nextInt(5000))))
                .unit("R$")
                .trend(random.nextBoolean() ? "up" : "down")
                .trendPercentage(random.nextGaussian() * 10)
                .color("text-blue-600")
                .icon("currency-dollar")
                .lastUpdated(LocalDateTime.now().minusMinutes(random.nextInt(10)))
                .loading(false)
                .build());
        
        // Widget de conversão
        widgets.add(WidgetData.builder()
                .id("conversion")
                .title("Taxa de Conversão")
                .type(WidgetData.WidgetType.PROGRESS)
                .value(new BigDecimal("12.5"))
                .unit("%")
                .trend("stable")
                .trendPercentage(0.1 + (random.nextGaussian() * 0.5))
                .color("text-orange-600")
                .icon("trending-up")
                .lastUpdated(LocalDateTime.now().minusMinutes(random.nextInt(20)))
                .loading(false)
                .build());
        
        // Widget com gráfico
        widgets.add(WidgetData.builder()
                .id("weekly-sales")
                .title("Vendas da Semana")
                .type(WidgetData.WidgetType.CHART)
                .chartData(generateWidgetChartData(7))
                .color("text-purple-600")
                .icon("chart-bar")
                .lastUpdated(LocalDateTime.now().minusMinutes(random.nextInt(5)))
                .loading(false)
                .build());
        
        // Widget de lista
        widgets.add(WidgetData.builder()
                .id("recent-activities")
                .title("Atividades Recentes")
                .type(WidgetData.WidgetType.LIST)
                .listData(generateWidgetListData(5))
                .color("text-gray-600")
                .icon("clipboard-list")
                .lastUpdated(LocalDateTime.now().minusMinutes(random.nextInt(2)))
                .loading(false)
                .build());
        
        return widgets;
    }
    
    /**
     * Simula processamento de formulário com validação.
     * 
     * @param formData Dados do formulário
     * @return FormResponse com resultado do processamento
     */
    public FormResponse processForm(Map<String, String> formData) {
        ValidationResult validation = validateFormData(formData);
        
        if (validation.hasErrors()) {
            return FormResponse.validationError(validation);
        }
        
        // Simula processamento
        try {
            Thread.sleep(random.nextInt(1000) + 500); // 0.5-1.5s delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Simula falha ocasional (10% de chance)
        if (random.nextInt(10) == 0) {
            return FormResponse.error("Erro interno do servidor. Tente novamente.");
        }
        
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("id", UUID.randomUUID().toString());
        responseData.put("processedAt", LocalDateTime.now());
        responseData.putAll(formData);
        
        return FormResponse.success("Formulário processado com sucesso!", responseData);
    }
    
    /**
     * Valida dados do formulário.
     * 
     * @param formData Dados para validar
     * @return ValidationResult com resultado da validação
     */
    private ValidationResult validateFormData(Map<String, String> formData) {
        ValidationResult result = new ValidationResult();
        
        // Validação de nome
        String name = formData.get("name");
        if (name == null || name.trim().isEmpty()) {
            result.addFieldError("name", "Nome é obrigatório");
        } else if (name.length() < 2) {
            result.addFieldError("name", "Nome deve ter pelo menos 2 caracteres");
        }
        
        // Validação de email
        String email = formData.get("email");
        if (email == null || email.trim().isEmpty()) {
            result.addFieldError("email", "Email é obrigatório");
        } else if (!email.contains("@") || !email.contains(".")) {
            result.addFieldError("email", "Email deve ter formato válido");
        }
        
        // Validação de telefone
        String phone = formData.get("phone");
        if (phone != null && !phone.trim().isEmpty()) {
            if (phone.length() < 10) {
                result.addFieldError("phone", "Telefone deve ter pelo menos 10 dígitos");
            }
        }
        
        return result;
    }
    
    private List<DashboardData.MetricPoint> generateChartData(int days) {
        List<DashboardData.MetricPoint> data = new ArrayList<>();
        LocalDateTime start = LocalDateTime.now().minusDays(days);
        
        for (int i = 0; i < days; i++) {
            data.add(DashboardData.MetricPoint.builder()
                    .label("Dia " + (i + 1))
                    .value(new BigDecimal(15000 + random.nextInt(10000)))
                    .timestamp(start.plusDays(i))
                    .build());
        }
        
        return data;
    }
    
    private List<WidgetData.DataPoint> generateWidgetChartData(int points) {
        return IntStream.range(0, points)
                .mapToObj(i -> WidgetData.DataPoint.builder()
                        .label("P" + (i + 1))
                        .value(new BigDecimal(100 + random.nextInt(400)))
                        .color("#f97316")
                        .timestamp(LocalDateTime.now().minusDays(points - i - 1))
                        .build())
                .toList();
    }
    
    private List<WidgetData.ListItem> generateWidgetListData(int items) {
        List<String> activities = Arrays.asList(
                "Usuário criou conta", "Pedido realizado", "Produto visualizado",
                "Email enviado", "Relatório gerado"
        );
        
        return IntStream.range(0, items)
                .mapToObj(i -> WidgetData.ListItem.builder()
                        .id("activity-" + i)
                        .title(activities.get(random.nextInt(activities.size())))
                        .subtitle("há " + (i + 1) + " minutos")
                        .status("active")
                        .icon("bell")
                        .color("text-gray-600")
                        .build())
                .toList();
    }
    
    private String generateAvatarUrl(String firstName, String lastName) {
        return "https://ui-avatars.com/api/?name=" + firstName + "+" + lastName + 
               "&background=f97316&color=fff&size=128";
    }
} 