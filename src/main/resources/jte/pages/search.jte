@param Integer totalPeople = 0
@param Integer totalDepartments = 0  
@param Integer totalPositions = 0

@template.layout.main(
    title = "Active Search",
    content = @`
        <div class="container-showcase py-8">
            <!-- Header -->
            <div class="text-center mb-8">
                <h1 class="text-3xl font-bold text-gray-900 mb-4">
                    🔍 Active Search
                </h1>
                <p class="text-lg text-gray-600 max-w-2xl mx-auto">
                    Busca em tempo real usando Datastar. Digite para ver a mágica do hypermedia acontecendo!
                </p>
            </div>

            <!-- Stats Cards -->
            <div class="grid grid-cols-1 md:grid-cols-3 gap-6 mb-8">
                @template.components.card(
                    title = "Pessoas",
                    subtitle = "Total no sistema",
                    content = @`
                        <div class="text-3xl font-bold text-orange-600">${totalPeople}</div>
                    `
                )
                @template.components.card(
                    title = "Departamentos",
                    subtitle = "Áreas disponíveis",
                    content = @`
                        <div class="text-3xl font-bold text-blue-600">${totalDepartments}</div>
                    `
                )
                @template.components.card(
                    title = "Cargos",
                    subtitle = "Posições diferentes",
                    content = @`
                        <div class="text-3xl font-bold text-green-600">${totalPositions}</div>
                    `
                )
            </div>

            <!-- Search Form -->
            <div class="bg-white rounded-xl border border-gray-200 shadow-sm p-6 mb-8">
                <form data-store='{"searchQuery": "", "selectedDepartment": "", "selectedPosition": ""}'>
                    <div class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-4">
                        <!-- Search Input -->
                        <div class="md:col-span-1">
                            <label for="search-input" class="block text-sm font-medium text-gray-700 mb-2">
                                Buscar pessoas
                            </label>
                            <input 
                                type="text" 
                                id="search-input"
                                name="q"
                                placeholder="Nome, email, telefone..."
                                data-model="searchQuery"
                                data-on-input.debounce.300ms="/search/results"
                                data-target="#search-results"
                                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent">
                        </div>

                        <!-- Department Filter -->
                        <div>
                            <label for="department-select" class="block text-sm font-medium text-gray-700 mb-2">
                                Departamento
                            </label>
                            <select 
                                id="department-select"
                                name="department"
                                data-model="selectedDepartment"
                                data-on-change="/search/results"
                                data-target="#search-results"
                                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500">
                                <option value="">Todos os departamentos</option>
                                <option value="Engenharia">Engenharia</option>
                                <option value="Pesquisa">Pesquisa</option>
                                <option value="Administração">Administração</option>
                                <option value="Projetos">Projetos</option>
                                <option value="Qualidade">Qualidade</option>
                                <option value="Inovação">Inovação</option>
                                <option value="Sustentabilidade">Sustentabilidade</option>
                                <option value="Energia Solar">Energia Solar</option>
                                <option value="Energia Eólica">Energia Eólica</option>
                                <option value="Biomassa">Biomassa</option>
                            </select>
                        </div>

                        <!-- Position Filter -->
                        <div>
                            <label for="position-select" class="block text-sm font-medium text-gray-700 mb-2">
                                Cargo
                            </label>
                            <select 
                                id="position-select"
                                name="position"
                                data-model="selectedPosition"
                                data-on-change="/search/results"
                                data-target="#search-results"
                                class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500">
                                <option value="">Todos os cargos</option>
                                <option value="Engenheiro">Engenheiro</option>
                                <option value="Pesquisador">Pesquisador</option>
                                <option value="Analista">Analista</option>
                                <option value="Coordenador">Coordenador</option>
                                <option value="Gerente">Gerente</option>
                                <option value="Diretor">Diretor</option>
                                <option value="Técnico">Técnico</option>
                                <option value="Assistente">Assistente</option>
                                <option value="Especialista">Especialista</option>
                                <option value="Consultor">Consultor</option>
                            </select>
                        </div>
                    </div>

                    <!-- Help Text -->
                    <p class="text-sm text-gray-500">
                        💡 A busca acontece em tempo real conforme você digita. Use os filtros para refinar os resultados.
                    </p>
                </form>
            </div>

            <!-- Search Results Container -->
            <div id="search-results" class="min-h-96">
                <!-- Results will be loaded here by Datastar -->
                <div class="text-center py-12 text-gray-500">
                    <div class="text-4xl mb-4">🔍</div>
                    <p class="text-lg">Digite algo na busca para ver os resultados aparecerem!</p>
                    <p class="text-sm mt-2">Ou use os filtros para explorar por departamento ou cargo.</p>
                </div>
            </div>

            <!-- Loading Overlay (shown automatically by Datastar) -->
            <div id="loading-overlay" class="hidden fixed inset-0 bg-black bg-opacity-25 flex items-center justify-center z-50" data-loading>
                <div class="bg-white rounded-lg p-6 flex items-center space-x-3">
                    <div class="animate-spin rounded-full h-6 w-6 border-b-2 border-orange-600"></div>
                    <span class="text-gray-700">Buscando...</span>
                </div>
            </div>
        </div>

        <!-- Custom CSS for this page -->
        <style>
            /* Loading state styling */
            [data-loading] {
                opacity: 0.7;
                pointer-events: none;
            }
            
            /* Smooth transitions */
            #search-results {
                transition: opacity 0.2s ease-in-out;
            }
            
            /* Custom focus styles */
            input:focus, select:focus {
                box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.1);
            }
        </style>

        <!-- Custom JavaScript for enhanced UX -->
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                // Auto-focus on search input
                const searchInput = document.getElementById('search-input');
                if (searchInput) {
                    searchInput.focus();
                }
                
                // Show initial results when page loads
                fetch('/search/results?limit=6')
                    .then(response => response.text())
                    .then(html => {
                        document.getElementById('search-results').innerHTML = html;
                    })
                    .catch(error => {
                        console.error('Error loading initial results:', error);
                    });
                
                // Add keyboard shortcuts
                document.addEventListener('keydown', function(e) {
                    // Ctrl/Cmd + K to focus search
                    if ((e.ctrlKey || e.metaKey) && e.key === 'k') {
                        e.preventDefault();
                        searchInput.focus();
                    }
                    
                    // Escape to clear search
                    if (e.key === 'Escape' && document.activeElement === searchInput) {
                        searchInput.value = '';
                        searchInput.dispatchEvent(new Event('input'));
                    }
                });
            });
        </script>
    `
) 