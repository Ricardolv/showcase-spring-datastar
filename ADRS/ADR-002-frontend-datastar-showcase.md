# ADR-SHOWCASE-002: Frontend Hipermídia Showcase - Datastar + Tailwind v4

## Status
Aceito

## Contexto
O frontend do CEAR Showcase precisa **demonstrar de forma impactante** as capacidades do **Datastar** como framework hipermídia e a **paleta visual CEAR** implementada com Tailwind CSS v4:

- **Interatividade sem JavaScript complexo**: Demonstrar reatividade pura via hipermídia
- **Paleta visual marcante**: Evidenciar branco, preto e laranjado em componentes reais  
- **Exemplos práticos**: Reproduzir patterns do [data-star.dev](https://data-star.dev/examples/active_search)
- **Performance visual**: Carregamento rápido e animações fluidas
- **Didático e inspirador**: Código que serve de referência para o sistema real

## Decisão
Utilizaremos **Datastar** como framework principal com **Tailwind CSS v4** personalizado, criando exemplos **práticos e visuais** que demonstrem todo o potencial da stack.

### Tecnologias Escolhidas:
- **Datastar**: Framework hipermídia reativo (10.54 KiB) via CDN
- **Tailwind CSS v4**: Framework CSS utilitário com paleta CEAR
- **JTE**: Engine de templates Java type-safe para server-side rendering
- **CSS Puro**: Animações e transições sem JavaScript adicional

## Arquitetura Frontend Showcase

```
┌─────────────────────────────────────────────────────────────┐
│                    Frontend Architecture                    │
│                                                             │
│  ┌───────────────────┐         ┌───────────────────┐        │
│  │   JTE Templates   │         │   Tailwind v4     │        │
│  │   (Server-Side)   │◄───────►│   (Build-Time)    │        │
│  │                   │         │                   │        │
│  │ • main.jte        │         │ • Paleta CEAR     │        │
│  │ • search.jte      │         │ • Componentes     │        │
│  │ • dashboard.jte   │         │ • Utilities       │        │
│  │ • forms.jte       │         │ • Animations      │        │
│  └───────────────────┘         └───────────────────┘        │
│           │                             │                   │
│           ▼                             ▼                   │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                  Datastar Layer                     │   │
│  │  • data-on-* (eventos)                              │   │
│  │  • data-bind-* (data binding)                       │   │
│  │  • data-target (DOM updates)                        │   │
│  │  • @get/@post (HTTP requests)                       │   │
│  │  • Server-Sent Events                               │   │
│  └─────────────────────────────────────────────────────┘   │
│           │                                                 │
│           ▼                                                 │
│  ┌─────────────────────────────────────────────────────┐   │
│  │                Spring Controllers                   │   │
│  │  • HTML Fragments Response                          │   │
│  │  • JSON Data Response                               │   │
│  │  • SSE Endpoints                                    │   │
│  └─────────────────────────────────────────────────────┘   │
└─────────────────────────────────────────────────────────────┘
```

## Exemplos Datastar para Showcase

### 1. **Active Search** (baseado no data-star.dev)

```html
<!-- Template: search.jte -->
<div class="max-w-4xl mx-auto p-6">
    
    <!-- Input de busca com Datastar -->
    <div class="mb-8">
        <input type="text" 
               placeholder="Digite para buscar contatos..."
               class="w-full px-4 py-3 text-lg border-2 border-secondary-300 rounded-lg
                      focus:ring-2 focus:ring-primary-500 focus:border-primary-500
                      transition-all duration-200"
               data-bind-search
               data-on-input__debounce.300ms="@get('/search/results')">
    </div>
    
    <!-- Resultados dinâmicos -->
    <div id="search-results" 
         class="grid gap-4 md:grid-cols-2 lg:grid-cols-3">
        <!-- Resultados carregados via Datastar -->
    </div>
    
    <!-- Loading state -->
    <div data-show="loading" 
         class="text-center py-12">
        <div class="inline-block w-8 h-8 border-4 border-primary-500 border-t-transparent rounded-full animate-spin"></div>
        <p class="mt-4 text-secondary-600">Buscando...</p>
    </div>
    
</div>
```

### 2. **Dashboard Widgets Reativos**

```html
<!-- Template: dashboard.jte -->
<div class="grid gap-6 md:grid-cols-2 lg:grid-cols-4">
    
    <!-- Widget de estatística que auto-atualiza -->
    <div class="bg-white rounded-lg shadow-orange-md border border-secondary-100 p-6"
         data-refresh="@get('/dashboard/stats')"
         data-interval="5s">
        
        <div class="flex items-center justify-between">
            <div>
                <p class="text-sm font-medium text-secondary-600">Usuários Online</p>
                <p class="text-2xl font-bold text-primary-600" 
                   data-text="onlineUsers">--</p>
            </div>
            <div class="w-12 h-12 bg-primary-100 rounded-lg flex items-center justify-center">
                <svg class="w-6 h-6 text-primary-600" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M9 12l2 2 4-4m6 2a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
            </div>
        </div>
        
        <!-- Indicador de mudança -->
        <div class="mt-4 flex items-center text-sm"
             data-class-if-gt="changePercent > 0:text-green-600"
             data-class-if-lt="changePercent < 0:text-red-600">
            <span data-text="changePercent"></span>% desde ontem
        </div>
        
    </div>
    
    <!-- Mais widgets... -->
    
</div>
```

### 3. **Formulários com Validação Reativa**

```html
<!-- Template: forms.jte -->
<form class="bg-white rounded-lg shadow-orange p-8 border border-secondary-100"
      data-on-submit="@post('/forms/contact')"
      data-target="#form-response">
    
    <h2 class="text-2xl font-bold text-secondary-900 mb-6">Contato</h2>
    
    <!-- Campo Email com validação -->
    <div class="mb-6">
        <label class="block text-sm font-medium text-secondary-700 mb-2">
            Email
        </label>
        <input type="email" 
               name="email"
               class="w-full px-3 py-2 border border-secondary-300 rounded-md
                      focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
               data-on-blur="@post('/forms/validate-email')"
               data-target="#email-feedback">
        
        <!-- Feedback de validação -->
        <div id="email-feedback" class="mt-2">
            <!-- Feedback carregado via Datastar -->
        </div>
    </div>
    
    <!-- Campo Mensagem -->
    <div class="mb-6">
        <label class="block text-sm font-medium text-secondary-700 mb-2">
            Mensagem
        </label>
        <textarea name="message" rows="4"
                  class="w-full px-3 py-2 border border-secondary-300 rounded-md
                         focus:ring-2 focus:ring-primary-500 focus:border-primary-500"
                  data-on-input="@post('/forms/count-chars')"
                  data-target="#char-count"></textarea>
        
        <!-- Contador de caracteres -->
        <div id="char-count" class="mt-1 text-sm text-secondary-500">
            0 caracteres
        </div>
    </div>
    
    <!-- Botão Submit -->
    <button type="submit" 
            class="w-full bg-gradient-cear hover:from-primary-600 hover:to-primary-700 
                   text-white font-medium py-3 px-4 rounded-md transition-all duration-200
                   disabled:opacity-50 disabled:cursor-not-allowed"
            data-loading-text="Enviando..."
            data-loading-class="opacity-50 cursor-not-allowed">
        Enviar Mensagem
    </button>
    
    <!-- Resposta do formulário -->
    <div id="form-response" class="mt-6">
        <!-- Resposta carregada via Datastar -->
    </div>
    
</form>
```

## Tailwind v4 - Paleta CEAR Showcase

### Configuração Completa

```javascript
// tailwind.config.js
module.exports = {
  content: ["./src/main/resources/jte/**/*.jte"],
  theme: {
    extend: {
      colors: {
        // Paleta principal: Branco, Preto, Laranjado
        primary: {
          50: '#fff7ed',    // Laranja muito claro
          100: '#ffedd5',   // Laranja claro  
          200: '#fed7aa',   // Laranja suave
          300: '#fdba74',   // Laranja médio claro
          400: '#fb923c',   // Laranja médio
          500: '#f97316',   // Laranja principal CEAR ⭐
          600: '#ea580c',   // Laranja escuro
          700: '#c2410c',   // Laranja muito escuro
          800: '#9a3412',   // Laranja profundo
          900: '#7c2d12',   // Laranja máximo
          950: '#431407'    // Laranja quase preto
        },
        
        secondary: {
          50: '#f9fafb',    // Branco suave
          100: '#f3f4f6',   // Cinza muito claro
          200: '#e5e7eb',   // Cinza claro
          300: '#d1d5db',   // Cinza médio claro  
          400: '#9ca3af',   // Cinza médio
          500: '#6b7280',   // Cinza
          600: '#4b5563',   // Cinza escuro
          700: '#374151',   // Cinza muito escuro
          800: '#1f2937',   // Preto suave
          900: '#111827',   // Preto ⭐
          950: '#030712'    // Preto profundo
        }
      },
      
      // Animações customizadas para showcase
      animation: {
        'bounce-in': 'bounceIn 0.6s ease-out',
        'slide-up': 'slideUp 0.4s ease-out',
        'fade-in': 'fadeIn 0.3s ease-out',
        'pulse-slow': 'pulse 3s ease-in-out infinite'
      },
      
      // Sombras com laranja
      boxShadow: {
        'orange': '0 1px 3px 0 rgba(249, 115, 22, 0.1), 0 1px 2px 0 rgba(249, 115, 22, 0.06)',
        'orange-md': '0 4px 6px -1px rgba(249, 115, 22, 0.1), 0 2px 4px -1px rgba(249, 115, 22, 0.06)',
        'orange-lg': '0 10px 15px -3px rgba(249, 115, 22, 0.1), 0 4px 6px -2px rgba(249, 115, 22, 0.05)'
      }
    }
  },
  
  plugins: [
    // Plugin para gradientes CEAR
    function({ addUtilities }) {
      const newUtilities = {
        '.bg-gradient-cear': {
          background: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)'
        },
        '.text-gradient-cear': {
          background: 'linear-gradient(135deg, #f97316 0%, #ea580c 100%)',
          '-webkit-background-clip': 'text',
          '-webkit-text-fill-color': 'transparent'
        }
      }
      addUtilities(newUtilities)
    }
  ]
}
```

### CSS Base Customizado

```css
/* src/input.css */
@tailwind base;
@tailwind components;
@tailwind utilities;

/* Estilos base do showcase */
@layer base {
  html {
    font-family: 'Inter', system-ui, -apple-system, sans-serif;
    @apply text-secondary-900 bg-secondary-50;
  }
  
  /* Headers com gradiente */
  h1, h2, h3 { 
    @apply font-bold text-secondary-900; 
  }
  
  h1 { @apply text-4xl md:text-5xl; }
  h2 { @apply text-3xl md:text-4xl; }
  h3 { @apply text-2xl md:text-3xl; }
}

@layer components {
  /* Componentes do showcase */
  .showcase-card {
    @apply bg-white rounded-lg shadow-orange-md border border-secondary-100 p-6
           hover:shadow-orange-lg transition-all duration-300;
  }
  
  .showcase-button {
    @apply bg-primary-500 hover:bg-primary-600 active:bg-primary-700 
           text-white font-medium px-6 py-3 rounded-lg
           transition-all duration-200 shadow-orange;
  }
  
  .showcase-input {
    @apply w-full px-4 py-3 border-2 border-secondary-300 rounded-lg
           focus:ring-2 focus:ring-primary-500 focus:border-primary-500
           transition-all duration-200;
  }
  
  .showcase-badge {
    @apply inline-flex items-center px-3 py-1 rounded-full text-sm font-medium
           bg-primary-100 text-primary-800;
  }
}

@layer utilities {
  /* Animações do showcase */
  @keyframes bounceIn {
    0% { transform: scale(0.3); opacity: 0; }
    50% { transform: scale(1.05); }
    70% { transform: scale(0.9); }
    100% { transform: scale(1); opacity: 1; }
  }
  
  @keyframes slideUp {
    from { transform: translateY(20px); opacity: 0; }
    to { transform: translateY(0); opacity: 1; }
  }
  
  @keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
  }
}
```

## Estrutura de Templates JTE

```
src/main/resources/jte/
├── layout/
│   ├── main.jte                    # Layout principal com Datastar
│   ├── navigation.jte              # Navegação entre exemplos
│   └── footer.jte                  # Footer com links
├── pages/
│   ├── home.jte                    # Página inicial do showcase
│   ├── search.jte                  # Active Search demo
│   ├── dashboard.jte               # Dashboard interativo
│   ├── forms.jte                   # Formulários reativos
│   ├── components.jte              # Galeria de componentes
│   └── about.jte                   # Sobre o showcase
├── components/
│   ├── search-result.jte           # Componente de resultado de busca
│   ├── dashboard-widget.jte        # Widget do dashboard
│   ├── form-feedback.jte           # Feedback de formulários
│   └── code-example.jte            # Exibição de código
└── fragments/
    ├── search-results.jte          # Fragmento de resultados
    ├── widget-stats.jte            # Fragmento de estatísticas
    └── form-validation.jte         # Fragmento de validação
```

## Demonstrações Interativas

### 1. **Search Results Fragment**
```html
<!-- Fragment retornado pelo controller -->
@for(SearchResult result : results)
    <div class="showcase-card animate-fade-in">
        <div class="flex items-center space-x-4">
            <div class="w-12 h-12 bg-gradient-cear rounded-full flex items-center justify-center text-white font-bold">
                ${result.firstName.substring(0, 1)}
            </div>
            <div>
                <h3 class="font-semibold text-secondary-900">${result.fullName}</h3>
                <p class="text-secondary-600">${result.email}</p>
                <span class="showcase-badge">${result.department}</span>
            </div>
        </div>
    </div>
@endfor
```

### 2. **Live Stats Widget**
```html
<!-- Widget que atualiza automaticamente -->
<div class="text-center">
    <div class="text-3xl font-bold text-gradient-cear mb-2">
        ${stats.value}
    </div>
    <div class="text-sm text-secondary-600 mb-4">
        ${stats.label}
    </div>
    <div class="w-full bg-secondary-200 rounded-full h-2">
        <div class="bg-gradient-cear h-2 rounded-full transition-all duration-500" 
             style="width: ${stats.percentage}%"></div>
    </div>
</div>
```

## Princípios de UX/UI para Showcase

### ✅ **Visual Excellence:**
- **Paleta consistente**: Laranja como destaque, cinzas para hierarquia
- **Transições suaves**: Todas as interações com feedback visual
- **Tipografia clara**: Inter font em tamanhos hierárquicos
- **Espaçamento respirável**: Grid system bem definido

### ✅ **Interatividade Fluida:**
- **Feedback imediato**: Estados de loading, hover, focus
- **Animações significativas**: Revelar conteúdo, confirmar ações
- **Responsividade**: Mobile-first design
- **Acessibilidade**: Contraste WCAG AA, navegação por teclado

### ✅ **Performance Visual:**
- **CSS otimizado**: Apenas utilities necessárias
- **Animações performáticas**: Transform/opacity apenas
- **Lazy loading**: Componentes carregados conforme necessário
- **Caching inteligente**: Assets estáticos cacheados

## Critérios de Sucesso

- [ ] **Datastar Examples**: Todos os patterns de interação funcionam
- [ ] **Paleta CEAR**: Cores evidenciadas em todos os componentes
- [ ] **Responsividade**: Funciona perfeitamente em mobile/desktop
- [ ] **Performance**: Primeira renderização < 300ms
- [ ] **Interatividade**: Todas as ações têm feedback visual
- [ ] **Acessibilidade**: Navegação por teclado e screen readers
- [ ] **Code Quality**: Templates limpos e bem documentados

---
**Data**: Janeiro 2025  
**Autor**: Equipe CEAR  
**Revisores**: Frontend, UX/UI, Arquitetura  
**Próxima Revisão**: Março 2025 