# 🚀 CEAR Showcase: Datastar + Tailwind v4 + Spring Boot

## 📋 Sobre o Showcase

Este projeto demonstra a integração das tecnologias principais do sistema CEAR de forma simplificada:

- **🌟 Datastar**: Framework hipermídia reativo (10.54 KiB)
- **🎨 Tailwind CSS v4**: Framework CSS utilitário com paleta personalizada
- **☕ Spring Boot**: Backend monolítico com templates server-side
- **📝 JTE**: Engine de templates Java type-safe

## 📚 ADRs - Decisões Arquiteturais

### [ADR-SHOWCASE-001: Arquitetura Geral de Demonstração](ADR-SHOWCASE-001-arquitetura-geral-demonstracao.md)
**Status**: ✅ Aceito  
**Resumo**: Define a arquitetura monolítica simplificada para demonstração, sem banco de dados e sem autenticação, focando na experiência educativa e na demonstração das capacidades do Datastar.

**Decisões principais:**
- Arquitetura monolítica sem complexidades
- Dados mock em memória
- Controllers didáticos e bem documentados
- Hot reload para desenvolvimento ágil

### [ADR-SHOWCASE-002: Frontend Hipermídia Showcase](ADR-SHOWCASE-002-frontend-datastar-showcase.md)
**Status**: ✅ Aceito  
**Resumo**: Especifica o uso do Datastar como framework hipermídia principal, integrado com Tailwind CSS v4 e paleta visual CEAR personalizada.

**Decisões principais:**
- Datastar para reatividade sem JavaScript complexo
- Paleta CEAR: branco, preto, laranjado
- JTE para templates type-safe
- Exemplos práticos inspirados em data-star.dev

**Funcionalidades demonstradas:**
- 🔍 **Active Search**: Busca dinâmica com debounce
- 📊 **Dashboard Interativo**: Widgets com Server-Sent Events
- 📝 **Formulários Reativos**: Validação em tempo real
- 🎨 **Galeria de Componentes**: Showcase da paleta visual

### [ADR-SHOWCASE-003: Backend Spring Boot Simplificado](ADR-SHOWCASE-003-backend-spring-boot-simplificado.md)
**Status**: ✅ Aceito  
**Resumo**: Define a implementação do backend Spring Boot minimalista, focado em servir fragmentos HTML e dados mock para demonstração das capacidades do Datastar.

**Decisões principais:**
- Spring Boot 3.x sem módulos desnecessários
- Services com dados mock bem estruturados
- Controllers educativos para cada funcionalidade
- DTOs bem definidos para separação de responsabilidades

**Estrutura dos Controllers:**
- `SearchController`: Active Search demo
- `DashboardController`: Widgets e SSE
- `FormController`: Validação reativa
- `ComponentController`: Galeria de componentes

### [ADR-SHOWCASE-004: Gerenciamento de Dependências e Build](ADR-SHOWCASE-004-dependencias-build-showcase.md)
**Status**: ✅ Aceito  
**Resumo**: Configura Maven com integração do Tailwind CSS via Node.js, mantendo dependências mínimas e build otimizado para máxima simplicidade.

**Decisões principais:**
- Maven como build principal
- Frontend Maven Plugin para integração Node.js
- JTE Maven Plugin para precompilação
- Scripts de desenvolvimento e produção

**Stack de Build:**
- Maven 3.9+ para dependency management
- Node.js 18+ para Tailwind CSS build
- Spring Boot DevTools para hot reload
- Profiles de desenvolvimento e produção

## 🎯 Funcionalidades Demonstradas

### 1. **Active Search** (inspirado no data-star.dev)
```
📍 /search
- Busca dinâmica em tempo real
- Debounce automático
- Atualização via Datastar
```

### 2. **Dashboard Interativo**
```
📍 /dashboard  
- Cards reativos
- Widgets dinâmicos
- Server-Sent Events
```

### 3. **Formulários Reativos**
```
📍 /forms
- Validação em tempo real
- Fragmentos HTML
- Feedback visual
```

### 4. **Paleta CEAR Tailwind v4**
```
🎨 /components
- Cores: Branco, Preto, Laranjado
- Primary: #f97316 (laranja)
- Secondary: escalas de cinza
- Estados: success, error, warning, info
```

## 🚀 Como Executar

### Pré-requisitos
- Java 21+
- Node.js 18+
- Maven 3.9+

### 1. Instalar dependências do Tailwind
```bash
npm install
```

### 2. Build CSS (desenvolvimento)
```bash
npm run css:dev
```

### 3. Executar Spring Boot
```bash
mvn spring-boot:run
```

### 4. Acessar o showcase
```
🌐 http://localhost:8080
```

## 📁 Estrutura do Projeto

```
ADRS_SHOWCASE/
├── src/main/java/com/cear/showcase/
│   ├── ShowcaseApplication.java          # Main Spring Boot
│   ├── config/
│   │   ├── WebConfig.java               # Configuração MVC/JTE
│   │   └── DatastarConfig.java          # Configurações Datastar
│   ├── controller/
│   │   ├── HomeController.java          # Página inicial
│   │   ├── SearchController.java        # Active Search demo
│   │   ├── DashboardController.java     # Dashboard interativo
│   │   ├── FormController.java          # Formulários reativos
│   │   └── ComponentController.java     # Showcase de componentes
│   ├── dto/
│   │   ├── SearchResult.java            # DTOs para demo
│   │   ├── DashboardWidget.java
│   │   └── FormData.java
│   └── service/
│       ├── MockDataService.java         # Dados mock (sem DB)
│       ├── SearchService.java           # Serviço de busca
│       └── DashboardService.java        # Dados do dashboard
├── src/main/resources/
│   ├── static/
│   │   ├── css/
│   │   │   └── styles.css               # CSS gerado pelo Tailwind
│   │   └── js/
│   │       └── datastar.js              # Datastar CDN local
│   └── jte/
│       ├── layout/
│       │   └── main.jte                 # Layout principal
│       ├── pages/
│       │   ├── home.jte                 # Página inicial
│       │   ├── search.jte               # Active Search
│       │   ├── dashboard.jte            # Dashboard
│       │   └── forms.jte                # Formulários
│       └── components/
│           ├── navbar.jte               # Navegação
│           ├── card.jte                 # Cards reutilizáveis
│           └── button.jte               # Botões estilizados
├── package.json                         # Dependencies Tailwind
├── tailwind.config.js                   # Config Tailwind v4
├── src/input.css                        # CSS source
└── pom.xml                              # Maven dependencies
```

## 🎨 Paleta de Cores CEAR

### Demonstração Visual das Cores

| Cor | Hex | Uso | Preview |
|-----|-----|-----|---------|
| **Primary 500** | `#f97316` | Botões principais, links | 🟠 |
| **Primary 600** | `#ea580c` | Hover states | 🟠 |
| **Secondary 100** | `#f3f4f6` | Backgrounds suaves | ⬜ |
| **Secondary 900** | `#111827` | Textos principais | ⬛ |

### Componentes Estilizados

```css
/* Botão Primary */
.btn-primary {
  @apply bg-primary-500 hover:bg-primary-600 text-white px-4 py-2 rounded-lg;
}

/* Card padrão */
.card {
  @apply bg-white rounded-lg shadow-orange border border-secondary-100 p-6;
}

/* Input com foco laranja */
.input {
  @apply w-full px-3 py-2 border border-secondary-300 rounded-md 
         focus:ring-2 focus:ring-primary-500 focus:border-primary-500;
}
```

## 🌟 Exemplos Datastar

### 1. Active Search
```html
<input data-on-input="@get('/search/results')" 
       data-debounce="300ms"
       placeholder="Digite para buscar...">

<div data-target="#search-results">
  <!-- Resultados carregados dinamicamente -->
</div>
```

### 2. Widget Dinâmico
```html
<div data-target="#widget-stats"
     data-refresh="@get('/dashboard/stats')"
     data-interval="5s">
  <!-- Atualização automática a cada 5s -->
</div>
```

### 3. Formulário Reativo
```html
<form data-on-submit="@post('/forms/validate')"
      data-target="#form-feedback">
  <input name="email" data-on-blur="@post('/forms/validate-email')">
  <!-- Validação em tempo real -->
</form>
```

## 📚 Referências

- [Datastar Examples](https://data-star.dev/examples/) - Inspiração para os exemplos implementados
- [Spring Boot Docs](https://docs.spring.io/spring-boot/index.html) - Documentação oficial do Spring Boot
- [Tailwind Showcase](https://tailwindcss.com/showcase) - Inspiração visual
- [JTE Documentation](https://jte.gg/) - Engine de templates Java

## 🎯 Objetivos do Showcase

✅ **Demonstrar integração perfeita** entre Datastar, Tailwind v4 e Spring Boot  
✅ **Mostrar capacidades reativas** sem JavaScript complexo  
✅ **Evidenciar paleta visual CEAR** com componentes modernos  
✅ **Apresentar templates type-safe** com JTE  
✅ **Ilustrar arquitetura monolítica** simplificada  

## 🔧 Comandos de Desenvolvimento

### **Setup Inicial:**
```bash
# 1. Clone e entre no diretório
git clone <repository>
cd ADRS_SHOWCASE

# 2. Instalar dependências Node.js
npm install

# 3. Executar aplicação
mvn spring-boot:run
```

### **Desenvolvimento Ativo:**
```bash
# Terminal 1: CSS watch mode
npm run css:dev

# Terminal 2: Spring Boot com hot reload
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

### **Build para Produção:**
```bash
# Build completo
./scripts/build.sh

# Executar JAR
java -jar target/showcase-1.0.0.jar
```

## 🏗️ Arquitetura Técnica

O showcase implementa uma **arquitetura monolítica hipermídia** que demonstra:

### **Frontend (Datastar + Tailwind)**
- Interatividade sem JavaScript complexo
- Paleta visual CEAR personalizada
- Fragmentos HTML dinâmicos
- Server-Sent Events para tempo real

### **Backend (Spring Boot + JTE)**
- Controllers educativos e bem documentados
- Services com dados mock estruturados
- Templates type-safe compilados
- Hot reload para desenvolvimento

### **Build (Maven + Node.js)**
- Integração seamless entre Java e CSS
- Build otimizado para produção
- Scripts de desenvolvimento simplificados
- JAR único auto-executável

---

**🏢 Sistema CEAR - Showcase Tecnológico**  
*Demonstrando o poder da stack hipermídia moderna*

**Documentação**: Os ADRs neste diretório detalham todas as decisões arquiteturais  
**Status**: Pronto para implementação  
**Objetivo**: Base técnica para o sistema CEAR real 