# 🚀  Showcase - Datastar + Tailwind v4 + Spring Boot

> **Projeto de demonstração educativo** que integra Datastar (framework hipermídia reativo), Tailwind CSS v4 com paleta  personalizada, e Spring Boot para criar aplicações web modernas e interativas.

## 🎯 Objetivo

Demonstrar a **integração perfeita** entre tecnologias modernas de desenvolvimento web através de exemplos práticos e interativos, evidenciando como criar aplicações reativas sem JavaScript complexo.

## 🛠️ Stack Tecnológica

### Frontend
- **[Datastar](https://data-star.dev/)** - Framework hipermídia para interatividade reativa
- **[Tailwind CSS v4](https://tailwindcss.com/)** - Framework CSS com paleta  personalizada
- **[JTE Templates](https://jte.gg/)** - Engine de templates type-safe para Java

### Backend
- **[Spring Boot 3.x](https://spring.io/projects/spring-boot)** - Framework Java minimalista
- **Java 21** - LTS com recursos modernos
- **Arquitetura monolítica** - Sem banco de dados, dados mock estruturados

### Build & Deploy
- **Maven 3.9+** - Gerenciamento de dependências e build
- **Node.js 18+** - Build do Tailwind CSS
- **Frontend Maven Plugin** - Integração seamless Maven + Node.js

## ✨ Funcionalidades Demonstradas

### 🔍 Active Search
- **Busca em tempo real** com debounce automático
- **Filtros dinâmicos** por departamento e cargo
- **Performance < 50ms** com dados mock otimizados
- **Fragmentos HTML** atualizados via Datastar

### 📊 Dashboard Interativo
- **Widgets reativos** com atualização automática
- **Server-Sent Events (SSE)** para dados em tempo real
- **Cache inteligente** com TTL de 30 segundos
- **Cards dinâmicos** com estados de loading

### 📝 Formulários Reativos
- **Validação em tempo real** campo por campo
- **Validação assíncrona** simulando verificações de banco
- **Feedback visual** com estados de erro/sucesso
- **Auto-complete** com sugestões contextuais

### 🎨 Galeria de Componentes 
- **Paleta oficial ** - Laranja (#f97316) como cor primária
- **Componentes reutilizáveis** - Cards, botões, badges, alertas
- **Design responsivo** com foco em acessibilidade
- **Animações suaves** e estados hover

## 🚀 Como Executar

### Pré-requisitos
```bash
# Verificar versões
java -version    # Java 21+
node -v         # Node.js 18+
mvn -version    # Maven 3.9+
```

### 1. Clone e Setup
```bash
# Clone o repositório
git clone <repository-url>
cd showcase-datastar-spring

# Instalar dependências do Tailwind
npm install
```

### 2. Desenvolvimento Rápido
```bash
# Executar aplicação (modo desenvolvimento)
mvn spring-boot:run -Dskip.npm

# Acessar o showcase
open http://localhost:8080
```

### 3. Desenvolvimento com CSS Watch
```bash
# Terminal 1: CSS em modo watch
npm run css:dev

# Terminal 2: Spring Boot com hot reload
mvn spring-boot:run -Dspring.profiles.active=dev
```

### 4. Build para Produção
```bash
# Build completo
mvn clean package

# Executar JAR
java -jar target/showcase-1.0.0.jar
```

## 📁 Estrutura do Projeto

```
showcase-datastar-spring/
├── 📂 src/main/java/com//showcase/
│   ├── ShowcaseApplication.java           # 🚀 Main Spring Boot
│   ├── 📂 controller/
│   │   ├── HomeController.java            # 🏠 Página inicial
│   │   ├── SearchController.java          # 🔍 Active Search
│   │   ├── DashboardController.java       # 📊 Dashboard interativo
│   │   └── FormController.java            # 📝 Formulários reativos
│   ├── 📂 service/
│   │   ├── SearchService.java             # 🔍 Lógica de busca
│   │   ├── DashboardService.java          # 📊 Dados do dashboard
│   │   ├── FormService.java               # 📝 Validação de forms
│   │   └── MockDataService.java           # 🎭 Gerador de dados mock
│   └── 📂 dto/
│       ├── SearchResult.java              # 🔍 Resultado de busca
│       ├── DashboardData.java             # 📊 Dados do dashboard
│       └── ValidationResult.java          # ✅ Resultado de validação
├── 📂 src/main/resources/
│   ├── 📂 static/
│   │   ├── css/styles.css                 # 🎨 CSS gerado pelo Tailwind
│   │   └── js/datastar.js                 # ⚡ Datastar framework
│   ├── 📂 jte/
│   │   ├── 📂 layout/
│   │   │   └── main.jte                   # 🏗️ Layout principal
│   │   ├── 📂 pages/
│   │   │   ├── home.jte                   # 🏠 Página inicial
│   │   │   ├── search.jte                 # 🔍 Busca ativa
│   │   │   ├── dashboard.jte              # 📊 Dashboard
│   │   │   └── forms.jte                  # 📝 Formulários
│   │   ├── 📂 components/
│   │   │   ├── navbar.jte                 # 🧭 Navegação
│   │   │   ├── card.jte                   # 🃏 Cards reutilizáveis
│   │   │   └── button.jte                 # 🔘 Botões estilizados
│   │   └── 📂 fragments/
│   │       ├── search-results.jte         # 🔍 Resultados de busca
│   │       └── person-details.jte         # 👤 Detalhes da pessoa
│   └── application.yml                    # ⚙️ Configuração Spring
├── 📂 ADRS/                              # 📋 Architecture Decision Records
├── src/input.css                         # 🎨 CSS source do Tailwind
├── tailwind.config.js                    # ⚙️ Configuração Tailwind v4
├── package.json                          # 📦 Dependencies Node.js
└── pom.xml                               # 📦 Dependencies Maven
```

## 🎨 Paleta de Cores 

### Cores Principais
| Cor | Hex | Uso | Preview |
|-----|-----|-----|---------|
| **Primary 500** | `#f97316` | Botões principais, links ativos | 🟠 |
| **Primary 600** | `#ea580c` | Hover states, estados pressed | 🟠 |
| **Primary 50** | `#fff7ed` | Backgrounds suaves | 🔶 |

### Cores Secundárias
| Cor | Hex | Uso | Preview |
|-----|-----|-----|---------|
| **Gray 50** | `#f9fafb` | Backgrounds principais | ⬜ |
| **Gray 900** | `#111827` | Textos principais | ⬛ |
| **Gray 600** | `#4b5563` | Textos secundários | ▪️ |

### Exemplos de Uso
```css
/* Botão Primary */
.btn-primary {
  @apply bg-orange-500 hover:bg-orange-600 text-white px-4 py-2 rounded-lg;
}

/* Card  */
.card- {
  @apply bg-white rounded-lg border border-gray-200 shadow-sm hover:shadow-md;
}

/* Input com foco laranja */
.input- {
  @apply border-gray-300 focus:ring-2 focus:ring-orange-500 focus:border-orange-500;
}
```

## 🌟 Exemplos Datastar

### 1. Busca Ativa (Active Search)
```html
<input data-on-input="@get('/search/results')" 
       data-debounce="300ms"
       placeholder="Digite para buscar pessoas...">

<div id="search-results" data-target="#search-results">
  <!-- Resultados carregados dinamicamente via Datastar -->
</div>
```

### 2. Widget com Auto-refresh
```html
<div data-target="#widget-stats"
     data-refresh="@get('/dashboard/stats')"
     data-interval="5s">
  <!-- Atualização automática a cada 5 segundos -->
</div>
```

### 3. Formulário com Validação Reativa
```html
<form data-on-submit="@post('/forms/validate')"
      data-target="#form-feedback">
  
  <input data-on-input="@get('/forms/validate-field')"
         data-debounce="500ms"
         name="email"
         placeholder="seu@email.com">
  
  <!-- Feedback de validação em tempo real -->
</form>
```

## 🧪 Testes

### Executar Testes
```bash
# Todos os testes
mvn test

# Testes específicos
mvn test -Dtest=SearchServiceTest
mvn test -Dtest=DashboardServiceTest
mvn test -Dtest=FormServiceTest
```

### Cobertura de Testes
- ✅ **SearchService** - 11 testes (busca, filtros, sugestões)
- ✅ **DashboardService** - 12 testes (widgets, cache, SSE)
- ✅ **FormService** - 19 testes (validação, async, auto-complete)

## 📊 Performance

### Métricas de Performance
- **Busca ativa**: < 50ms de resposta
- **Cache de dashboard**: TTL de 30 segundos
- **Validação de forms**: < 100ms para campos simples
- **Validação assíncrona**: 100-500ms (simulado)

### Otimizações
- **Debounce** automático em buscas (300ms)
- **Cache inteligente** para dados de dashboard
- **Lazy loading** de componentes pesados
- **Compression** automática de assets

## 🔧 Comandos Úteis

### Desenvolvimento
```bash
# Hot reload CSS + Java
npm run dev

# Limpar cache e reinstalar
mvn clean && npm ci

# Verificar dependências desatualizadas
mvn versions:display-dependency-updates
npm outdated
```

### Debugging
```bash
# Executar com debug ativo
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"

# Logs detalhados
mvn spring-boot:run -Dspring.profiles.active=dev -Dlogging.level.com..showcase=DEBUG
```

### Produção
```bash
# Build otimizado
mvn clean package -Pprod

# Executar com profile de produção
java -jar target/showcase-1.0.0.jar --spring.profiles.active=prod
```

## 🤝 Como Contribuir

### 1. Setup do Ambiente
```bash
git clone <repository-url>
cd showcase-datastar-spring
npm install
mvn compile
```

### 2. Desenvolvimento
- Siga as **[ADRs](ADRS/)** para decisões arquiteturais
- Use **Java 21 features** quando apropriado
- Mantenha **testes atualizados** para novas funcionalidades
- Siga a **paleta ** para novos componentes

### 3. Submissão
- Crie **branch feature** a partir de `main`
- Adicione **testes** para novas funcionalidades
- Atualize **documentação** se necessário
- Submeta **Pull Request** com descrição detalhada

## 📚 Recursos e Referências

### Documentação Oficial
- 🌟 **[Datastar Examples](https://data-star.dev/examples/)** - Inspiração para implementações
- 🍃 **[Spring Boot Docs](https://docs.spring.io/spring-boot/)** - Documentação oficial
- 🎨 **[Tailwind CSS](https://tailwindcss.com/)** - Framework CSS utility-first
- 🏗️ **[JTE Documentation](https://jte.gg/)** - Templates type-safe para Java

### Inspirações
- **[HTMX Examples](https://htmx.org/examples/)** - Padrões de hipermídia
- **[Spring Petclinic](https://github.com/spring-projects/spring-petclinic)** - Estrutura Spring Boot
- **[Tailwind UI](https://tailwindui.com/)** - Componentes profissionais

## 🎓 Conceitos Demonstrados

### Arquitetura Hipermídia
- ✅ **Server-side rendering** com interatividade
- ✅ **Fragmentos HTML** como unidade de atualização
- ✅ **Progressive enhancement** sem JavaScript pesado
- ✅ **Separation of concerns** clara

### Padrões Spring Boot
- ✅ **Controllers educativos** com documentação rica
- ✅ **Services com dados mock** bem estruturados
- ✅ **DTOs type-safe** para transferência de dados
- ✅ **Profiles** para desenvolvimento e produção

### Frontend Moderno
- ✅ **CSS utilitário** com Tailwind v4
- ✅ **Design system** consistente com paleta 
- ✅ **Responsividade** mobile-first
- ✅ **Acessibilidade** com ARIA labels