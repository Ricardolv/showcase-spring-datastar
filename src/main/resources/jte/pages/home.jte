@param String title = "CEAR Showcase"
@param String activeRoute = "home"

@template.layout.main(
    title = title,
    content = @`
        <div class="container-showcase py-12">
            <div class="text-center mb-12">
                <h1 class="text-4xl font-bold text-gray-900 mb-4">
                    🚀 CEAR Showcase
                </h1>
                <p class="text-xl text-gray-600 mb-8">
                    Demonstração de Datastar + Tailwind CSS + Spring Boot
                </p>
                
                <div class="flex justify-center space-x-4">
                    <a href="/search" class="showcase-button-primary">
                        🔍 Active Search
                    </a>
                    <a href="/dashboard" class="showcase-button-secondary">
                        📊 Dashboard
                    </a>
                    <a href="/forms" class="showcase-button-outline">
                        📝 Formulários
                    </a>
                </div>
            </div>
            
            <div class="grid md:grid-cols-3 gap-8">
                <div class="showcase-card text-center">
                    <div class="text-3xl mb-4">⚡</div>
                    <h3 class="text-lg font-semibold mb-2">Datastar</h3>
                    <p class="text-gray-600">Framework hipermídia para interatividade reativa sem JavaScript complexo</p>
                </div>
                
                <div class="showcase-card text-center">
                    <div class="text-3xl mb-4">🎨</div>
                    <h3 class="text-lg font-semibold mb-2">Tailwind CSS</h3>
                    <p class="text-gray-600">Framework CSS utility-first com paleta CEAR personalizada</p>
                </div>
                
                <div class="showcase-card text-center">
                    <div class="text-3xl mb-4">🍃</div>
                    <h3 class="text-lg font-semibold mb-2">Spring Boot</h3>
                    <p class="text-gray-600">Backend Java minimalista com templates JTE type-safe</p>
                </div>
            </div>
            
            <div class="mt-12 text-center">
                <p class="text-gray-500">
                    <strong>Teste de funcionalidade:</strong> 
                    <a href="/test" class="text-orange-600 hover:text-orange-700">Controller Test</a> | 
                    <a href="/health" class="text-orange-600 hover:text-orange-700">Health Check</a>
                </p>
            </div>
        </div>
    `
) 