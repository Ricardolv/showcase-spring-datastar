# ADR-SHOWCASE-004: Gerenciamento de Dependências e Build - Showcase

## Status
Aceito

## Contexto
O CEAR Showcase precisa de uma configuração de build **simples e eficiente** que permita:

- **Execução rápida**: `mvn spring-boot:run` deve funcionar imediatamente
- **Desenvolvimento ágil**: Hot reload para Java e CSS
- **Dependências mínimas**: Apenas o essencial para demonstração
- **Build otimizado**: Tailwind CSS integrado ao Maven
- **Distribuição simples**: JAR único auto-executável
- **Documentação clara**: Setup em 3 comandos

## Decisão
Utilizaremos **Maven** com configuração simplificada, integrando **Tailwind CSS v4** via Node.js e mantendo **dependências mínimas** para máxima simplicidade.

### Stack de Build:
- **Maven 3.9+**: Build principal e dependency management
- **Node.js 18+**: Build do Tailwind CSS
- **Spring Boot Maven Plugin**: Packaging e execução
- **JTE Maven Plugin**: Precompilação de templates
- **Frontend Maven Plugin**: Integração Node.js + Maven

## Configuração Maven Completa

### 1. **POM.xml Principal**

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 
         http://maven.apache.org/xsd/maven-4.0.0.xsd">
    
    <modelVersion>4.0.0</modelVersion>
    
    <!-- Project Information -->
    <groupId>com.cear</groupId>
    <artifactId>showcase</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>
    
    <name>CEAR Showcase</name>
    <description>Showcase: Datastar + Tailwind v4 + Spring Boot</description>
    
    <!-- Properties -->
    <properties>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        
        <!-- Spring Boot -->
        <spring-boot.version>3.2.1</spring-boot.version>
        
        <!-- JTE Template Engine -->
        <jte.version>3.1.9</jte.version>
        
        <!-- Lombok -->
        <lombok.version>1.18.30</lombok.version>
        
        <!-- Validation -->
        <jakarta-validation.version>3.0.2</jakarta-validation.version>
        
        <!-- Frontend -->
        <node.version>v20.10.0</node.version>
        <npm.version>10.2.5</npm.version>
    </properties>
    
    <!-- Spring Boot Dependencies Management -->
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-dependencies</artifactId>
                <version>${spring-boot.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>
    
    <!-- Dependencies -->
    <dependencies>
        <!-- Spring Boot Core -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-validation</artifactId>
        </dependency>
        
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        
        <!-- JTE Template Engine -->
        <dependency>
            <groupId>gg.jte</groupId>
            <artifactId>jte-spring-boot-starter-3</artifactId>
            <version>${jte.version}</version>
        </dependency>
        
        <dependency>
            <groupId>gg.jte</groupId>
            <artifactId>jte</artifactId>
            <version>${jte.version}</version>
        </dependency>
        
        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- Jakarta Validation -->
        <dependency>
            <groupId>jakarta.validation</groupId>
            <artifactId>jakarta.validation-api</artifactId>
            <version>${jakarta-validation.version}</version>
        </dependency>
        
        <!-- Development Tools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
        
        <!-- Test Dependencies -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>
    
    <!-- Build Configuration -->
    <build>
        <plugins>
            <!-- Spring Boot Plugin -->
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <version>${spring-boot.version}</version>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>repackage</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            
            <!-- Maven Compiler Plugin -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>21</source>
                    <target>21</target>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                            <version>${lombok.version}</version>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            
            <!-- JTE Maven Plugin -->
            <plugin>
                <groupId>gg.jte</groupId>
                <artifactId>jte-maven-plugin</artifactId>
                <version>${jte.version}</version>
                <configuration>
                    <sourceDirectory>src/main/resources/jte</sourceDirectory>
                    <targetDirectory>target/generated-sources/jte</targetDirectory>
                    <contentType>Html</contentType>
                    <trimControlStructures>true</trimControlStructures>
                </configuration>
                <executions>
                    <execution>
                        <phase>generate-sources</phase>
                        <goals>
                            <goal>precompile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            
            <!-- Frontend Maven Plugin -->
            <plugin>
                <groupId>com.github.eirslett</groupId>
                <artifactId>frontend-maven-plugin</artifactId>
                <version>1.15.0</version>
                <configuration>
                    <nodeVersion>${node.version}</nodeVersion>
                    <npmVersion>${npm.version}</npmVersion>
                </configuration>
                <executions>
                    <!-- Install Node.js and npm -->
                    <execution>
                        <id>install node and npm</id>
                        <goals>
                            <goal>install-node-and-npm</goal>
                        </goals>
                    </execution>
                    
                    <!-- Install npm dependencies -->
                    <execution>
                        <id>npm install</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <configuration>
                            <arguments>install</arguments>
                        </configuration>
                    </execution>
                    
                    <!-- Build CSS for production -->
                    <execution>
                        <id>npm run build</id>
                        <goals>
                            <goal>npm</goal>
                        </goals>
                        <phase>compile</phase>
                        <configuration>
                            <arguments>run css:prod</arguments>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
    
    <!-- Profiles -->
    <profiles>
        <!-- Development Profile -->
        <profile>
            <id>dev</id>
            <activation>
                <activeByDefault>true</activeByDefault>
            </activation>
            <build>
                <plugins>
                    <plugin>
                        <groupId>com.github.eirslett</groupId>
                        <artifactId>frontend-maven-plugin</artifactId>
                        <executions>
                            <execution>
                                <id>npm run dev</id>
                                <goals>
                                    <goal>npm</goal>
                                </goals>
                                <phase>compile</phase>
                                <configuration>
                                    <arguments>run css:dev</arguments>
                                </configuration>
                            </execution>
                        </executions>
                    </plugin>
                </plugins>
            </build>
        </profile>
        
        <!-- Production Profile -->
        <profile>
            <id>prod</id>
            <build>
                <plugins>
                    <plugin>
                        <groupId>gg.jte</groupId>
                        <artifactId>jte-maven-plugin</artifactId>
                        <configuration>
                            <sourceDirectory>src/main/resources/jte</sourceDirectory>
                            <targetDirectory>target/generated-sources/jte</targetDirectory>
                            <contentType>Html</contentType>
                            <trimControlStructures>true</trimControlStructures>
                            <binaryStaticContent>true</binaryStaticContent>
                        </configuration>
                    </plugin>
                </plugins>
            </build>
        </profile>
    </profiles>
</project>
```

## Configuração Frontend (Node.js)

### 2. **package.json**

```json
{
  "name": "cear-showcase",
  "version": "1.0.0",
  "description": "CEAR Showcase Frontend Build",
  "private": true,
  "scripts": {
    "css:dev": "tailwindcss -i ./src/input.css -o ./src/main/resources/static/css/styles.css --watch",
    "css:prod": "tailwindcss -i ./src/input.css -o ./src/main/resources/static/css/styles.css --minify",
    "css:build": "npm run css:prod",
    "dev": "npm run css:dev",
    "build": "npm run css:prod"
  },
  "devDependencies": {
    "tailwindcss": "^4.0.0-alpha.25"
  },
  "engines": {
    "node": ">=18.0.0",
    "npm": ">=9.0.0"
  }
}
```

### 3. **tailwind.config.js**

```javascript
/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/main/resources/jte/**/*.jte",
    "./src/main/resources/static/js/**/*.js"
  ],
  theme: {
    extend: {
      colors: {
        // Paleta CEAR: Branco, Preto, Laranjado
        primary: {
          50: '#fff7ed',    // Laranja muito claro
          100: '#ffedd5',   // Laranja claro
          200: '#fed7aa',   // Laranja suave
          300: '#fdba74',   // Laranja médio claro
          400: '#fb923c',   // Laranja médio
          500: '#f97316',   // Laranja principal CEAR
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
          900: '#111827',   // Preto
          950: '#030712'    // Preto profundo
        }
      },
      
      fontFamily: {
        sans: ['Inter', 'system-ui', '-apple-system', 'sans-serif'],
        mono: ['JetBrains Mono', 'Monaco', 'Consolas', 'monospace']
      },
      
      boxShadow: {
        'orange': '0 1px 3px 0 rgba(249, 115, 22, 0.1), 0 1px 2px 0 rgba(249, 115, 22, 0.06)',
        'orange-md': '0 4px 6px -1px rgba(249, 115, 22, 0.1), 0 2px 4px -1px rgba(249, 115, 22, 0.06)',
        'orange-lg': '0 10px 15px -3px rgba(249, 115, 22, 0.1), 0 4px 6px -2px rgba(249, 115, 22, 0.05)'
      },
      
      animation: {
        'bounce-in': 'bounceIn 0.6s ease-out',
        'slide-up': 'slideUp 0.4s ease-out',
        'fade-in': 'fadeIn 0.3s ease-out'
      }
    }
  },
  
  plugins: [
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

### 4. **src/input.css**

```css
@tailwind base;
@tailwind components;
@tailwind utilities;

/* Base styles para o showcase */
@layer base {
  html {
    font-family: 'Inter', system-ui, -apple-system, sans-serif;
    @apply text-secondary-900 bg-secondary-50;
  }
  
  body {
    @apply antialiased;
  }
  
  h1, h2, h3, h4, h5, h6 { 
    @apply font-bold text-secondary-900; 
  }
  
  h1 { @apply text-4xl md:text-5xl; }
  h2 { @apply text-3xl md:text-4xl; }
  h3 { @apply text-2xl md:text-3xl; }
  h4 { @apply text-xl md:text-2xl; }
  
  a {
    @apply text-primary-600 hover:text-primary-700 transition-colors;
  }
  
  *:focus {
    @apply outline-none ring-2 ring-primary-500 ring-opacity-50;
  }
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
  
  .showcase-navbar {
    @apply bg-white shadow-orange border-b border-secondary-200;
  }
  
  .showcase-footer {
    @apply bg-secondary-900 text-secondary-100;
  }
}

@layer utilities {
  /* Animações customizadas */
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
  
  /* Utilities customizadas */
  .text-gradient-cear {
    background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
  }
  
  .bg-gradient-cear {
    background: linear-gradient(135deg, #f97316 0%, #ea580c 100%);
  }
}
```

## Scripts de Build e Desenvolvimento

### 5. **Scripts Maven**

```bash
#!/bin/bash
# scripts/dev.sh - Desenvolvimento
echo "🚀 Iniciando CEAR Showcase em modo desenvolvimento..."

# Build CSS em watch mode (background)
npm run css:dev &

# Iniciar Spring Boot com DevTools
mvn spring-boot:run -Dspring-boot.run.profiles=dev

# Cleanup
trap 'kill $(jobs -p)' EXIT
```

```bash
#!/bin/bash
# scripts/build.sh - Build para produção
echo "🏗️ Building CEAR Showcase para produção..."

# Instalar dependências
npm install

# Build CSS otimizado
npm run css:prod

# Build Java
mvn clean compile -Pprod

# Package
mvn package -Pprod

echo "✅ Build completo! JAR disponível em target/showcase-1.0.0.jar"
```

```bash
#!/bin/bash
# scripts/run.sh - Execução rápida
echo "⚡ Execução rápida do CEAR Showcase..."

# Build CSS se não existir
if [ ! -f "src/main/resources/static/css/styles.css" ]; then
    echo "📦 Gerando CSS inicial..."
    npm run css:prod
fi

# Executar aplicação
mvn spring-boot:run
```

## Configuração Spring Boot

### 6. **application.yml**

```yaml
# Configuração base
server:
  port: 8080
  compression:
    enabled: true
    mime-types: text/html,text/css,application/javascript

spring:
  application:
    name: cear-showcase
  
  # JTE Configuration
  jte:
    development-mode: true
    use-precompiled-templates: false
    template-location: classpath:jte/
  
  # Web Configuration
  web:
    resources:
      static-locations: classpath:/static/
      cache:
        period: PT30M # 30 minutos para desenvolvimento
  
  # DevTools
  devtools:
    restart:
      enabled: true
      additional-paths: src/main/resources/jte,src/main/resources/static
    livereload:
      enabled: true
      port: 35729

# Actuator
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

# Logging
logging:
  level:
    com.cear.showcase: DEBUG
    org.springframework.web: INFO
    gg.jte: INFO
  pattern:
    console: "%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n"
```

### 7. **application-prod.yml**

```yaml
# Configuração de produção
spring:
  jte:
    development-mode: false
    use-precompiled-templates: true
  
  web:
    resources:
      cache:
        period: P30D # 30 dias para produção

# Logging otimizado
logging:
  level:
    com.cear.showcase: INFO
    org.springframework.web: WARN
    gg.jte: WARN
```

## Estrutura de Diretórios Final

```
ADRS_SHOWCASE/
├── src/
│   ├── main/
│   │   ├── java/com/cear/showcase/
│   │   │   ├── ShowcaseApplication.java
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   └── service/
│   │   └── resources/
│   │       ├── jte/                    # Templates JTE
│   │       │   ├── layout/
│   │       │   ├── pages/
│   │       │   ├── components/
│   │       │   └── fragments/
│   │       ├── static/                 # Assets estáticos
│   │       │   ├── css/
│   │       │   │   └── styles.css      # CSS gerado pelo Tailwind
│   │       │   ├── js/
│   │       │   │   └── datastar.js     # Datastar CDN
│   │       │   └── images/
│   │       ├── application.yml
│   │       └── application-prod.yml
│   ├── test/java/                      # Testes
│   └── input.css                       # CSS source do Tailwind
├── scripts/                            # Scripts de build
│   ├── dev.sh
│   ├── build.sh
│   └── run.sh
├── target/                             # Build output
├── node_modules/                       # Dependencies Node.js
├── pom.xml                            # Maven configuration
├── package.json                       # Node.js configuration
├── tailwind.config.js                 # Tailwind configuration
└── README.md                          # Documentação
```

## Comandos de Desenvolvimento

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

## Vantagens da Configuração

### ✅ **Simplicidade de Setup:**
- **3 comandos**: npm install → mvn spring-boot:run → pronto
- **Zero configuração**: Funciona out-of-the-box
- **Hot reload**: CSS e Java recarregam automaticamente
- **Build rápido**: < 30 segundos para build completo

### ✅ **Desenvolvimento Produtivo:**
- **CSS watch**: Tailwind reconstrói automaticamente
- **Spring DevTools**: Classes recompilam sem restart
- **JTE templates**: Hot reload de templates
- **Logs claros**: Debug informativo

### ✅ **Build Otimizado:**
- **CSS minificado**: Tailwind produz CSS otimizado
- **Templates precompilados**: JTE compila para produção
- **JAR único**: Execução simples em qualquer ambiente
- **Assets cacheados**: Performance otimizada

## Critérios de Sucesso

- [ ] **Setup rápido**: `npm install && mvn spring-boot:run` funciona
- [ ] **Hot reload**: Mudanças refletem sem restart manual
- [ ] **CSS build**: Tailwind CSS é gerado corretamente
- [ ] **Templates**: JTE compila e renderiza sem erros
- [ ] **Build otimizado**: JAR de produção funciona standalone
- [ ] **Performance**: Build completo < 60 segundos
- [ ] **Documentação**: README explica todos os comandos

---
**Data**: Janeiro 2025  
**Autor**: Equipe CEAR  
**Revisores**: DevOps, Frontend, Backend  
**Próxima Revisão**: Março 2025 