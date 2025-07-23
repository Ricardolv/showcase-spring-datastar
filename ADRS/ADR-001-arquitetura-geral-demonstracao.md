# ADR-SHOWCASE-001: Arquitetura Geral de Demonstração - Monolito Hipermídia

## Status
Aceito

## Contexto
O CEAR Showcase precisa demonstrar a integração das tecnologias principais do sistema CEAR de forma **simplificada e didática**:

- Evidenciar capacidades do **Datastar** como framework hipermídia reativo
- Mostrar a **paleta visual CEAR** com Tailwind CSS v4
- Demonstrar **Spring Boot** com templates server-side
- Ilustrar **arquitetura monolítica** sem complexidades desnecessárias
- **Sem banco de dados** (dados mock em memória)
- **Sem autenticação** (foco na demonstração técnica)

## Decisão
Implementaremos uma **arquitetura monolítica de demonstração** com foco em **hipermídia reativa** e **experiência visual**.

### Tecnologias Escolhidas:
- **Spring Boot 3.x**: Framework principal (sem Spring Security)
- **Datastar**: Framework hipermídia reativo (10.54 KiB)
- **JTE**: Engine de templates Java type-safe
- **Tailwind CSS v4**: Framework CSS com paleta CEAR personalizada
- **Dados Mock**: Sem dependência de banco de dados
- **Maven**: Build e dependency management

## Arquitetura de Demonstração

```
┌────────────────────────────────────────────────────────────┐
│                 CEAR Showcase Application                  │
│                                                            │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │   Controllers   │    │   JTE Templates │                │
│  │   (@Controller) │◄──►│   + Datastar    │                │
│  │                 │    │                 │                │
│  │ • HomeController│    │ • Layout Base   │                │
│  │ • SearchCtrl    │    │ • Active Search │                │
│  │ • DashboardCtrl │    │ • Dashboard     │                │
│  │ • FormController│    │ • Forms Demo    │                │
│  └─────────────────┘    └─────────────────┘                │
│           │                                                │
│           ▼                                                │
│  ┌─────────────────┐    ┌─────────────────┐                │
│  │  Mock Services  │    │   Static DTOs   │                │
│  │  (@Service)     │◄──►│  (Response)     │                │
│  │                 │    │                 │                │
│  │ • MockDataSvc   │    │ • SearchResult  │                │
│  │ • DemoGenerator │    │ • DashboardData │                │
│  │ • StaticContent │    │ • FormResponse  │                │
│  └─────────────────┘    └─────────────────┘                │
│                                                            │
│  ┌─────────────────────────────────────────┐               │
│  │              Tailwind v4 + Datastar     │               │
│  │  • Paleta CEAR (Branco, Preto, Laranja) │               │
│  │  • Componentes Reativos                 │               │
│  │  • Interações Hipermídia                │               │
│  └─────────────────────────────────────────┘               │
└────────────────────────────────────────────────────────────┘
```

## Funcionalidades de Demonstração

### 1. **Active Search** (inspirado no data-star.dev)
```
📍 /search
- Input com debounce automático
- Busca dinâmica em lista mock
- Atualização via fragmentos HTML
- Demonstra: data-on-input, data-target
```

### 2. **Dashboard Interativo**
```
📍 /dashboard
- Cards reativos com estatísticas mock
- Widgets que atualizam via SSE simulado
- Gráficos em CSS puro
- Demonstra: data-bind, data-show, intervals
```

### 3. **Formulários Reativos**
```
📍 /forms
- Validação em tempo real
- Feedback visual instantâneo
- Múltiplos tipos de input
- Demonstra: data-on-submit, data-on-blur
```

### 4. **Paleta Visual CEAR**
```
🎨 /components
- Showcase de todos os componentes
- Botões, cards, inputs, alerts
- Demonstração da paleta completa
- Guia de estilo interativo
```

## Estrutura de Diretórios

```
src/main/java/com/cear/showcase/
├── ShowcaseApplication.java          # Main Spring Boot
├── config/
│   ├── WebConfig.java               # Configuração MVC/JTE
│   └── DatastarConfig.java          # Headers e configurações Datastar
├── controller/
│   ├── HomeController.java          # Página inicial e navegação
│   ├── SearchController.java        # Active Search demo
│   ├── DashboardController.java     # Dashboard interativo
│   ├── FormController.java          # Formulários reativos
│   └── ComponentController.java     # Showcase de componentes
├── dto/
│   ├── SearchResult.java            # DTOs para demonstração
│   ├── DashboardWidget.java         # Dados de widgets
│   ├── FormData.java               # Dados de formulários
│   └── ComponentExample.java       # Exemplos de componentes
└── service/
    ├── MockDataService.java         # Gerador de dados mock
    ├── SearchService.java           # Serviço de busca simulada
    └── DashboardService.java        # Dados do dashboard
```

## Princípios do Showcase

### ✅ **Foco na Demonstração:**
- **Simplicidade**: Código limpo e didático
- **Interatividade**: Exemplos práticos de Datastar
- **Visual**: Paleta CEAR bem evidenciada
- **Performance**: Carregamento rápido e responsivo
- **Educativo**: Código comentado e explicativo

### ❌ **Sem Complexidades Desnecessárias:**
- **Sem banco de dados**: Apenas dados mock
- **Sem autenticação**: Foco na demonstração técnica
- **Sem validações complexas**: Validações simples para demo
- **Sem testes extensivos**: Testes básicos apenas

### 🎯 **Objetivos de Demonstração:**

1. **Datastar Capabilities:**
   - Reatividade sem JavaScript complexo
   - Fragmentos HTML dinâmicos
   - Server-Sent Events simulados
   - Debounce e outros modificadores

2. **Tailwind v4 Integration:**
   - Paleta personalizada CEAR
   - Componentes responsivos
   - Dark mode support
   - Animações CSS puras

3. **Spring Boot Simplicity:**
   - Controllers limpos e focados
   - Templates type-safe com JTE
   - DTOs bem estruturados
   - Hot reload para desenvolvimento

## Consequências

### Positivas:
- **Demonstração efetiva**: Foco nas tecnologias principais
- **Simplicidade**: Fácil de entender e modificar
- **Performance**: Sem overhead de banco ou segurança
- **Portabilidade**: Executa em qualquer ambiente Java 21+
- **Didático**: Código serve como referência para implementação real

### Negativas:
- **Limitações funcionais**: Não demonstra persistência real
- **Dados temporários**: Reinicia a cada execução
- **Simplicidade excessiva**: Pode não mostrar complexidades reais
- **Escopo reduzido**: Foca apenas no frontend/interação

## Critérios de Aceitação

- [ ] **Execução**: `mvn spring-boot:run` funciona sem configuração adicional
- [ ] **Navegação**: Todas as páginas demo são acessíveis
- [ ] **Datastar**: Todos os exemplos de interatividade funcionam
- [ ] **Tailwind**: Paleta CEAR é evidenciada em todos os componentes
- [ ] **JTE**: Templates compilam e renderizam corretamente
- [ ] **Performance**: Primeira renderização < 300ms
- [ ] **Responsivo**: Funciona em desktop, tablet e mobile
- [ ] **Documentação**: README.md explica como executar e navegar

---
**Data**: Janeiro 2025  
**Autor**: Equipe CEAR  
**Revisores**: Frontend, Arquitetura  
**Próxima Revisão**: Março 2025 