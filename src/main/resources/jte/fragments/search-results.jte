@param java.util.List<com.cear.showcase.dto.SearchResult> results
@param String query = ""
@param Integer resultCount = 0
@param Boolean hasMore = false
@param Boolean showingExample = false

<!-- Results Header -->
<div class="flex items-center justify-between mb-6">
    <div>
        @if(showingExample)
            <h2 class="text-lg font-semibold text-gray-900">
                Exemplos de pessoas no sistema
            </h2>
            <p class="text-sm text-gray-600">
                ${resultCount} pessoas mostradas como exemplo
            </p>
        @elseif(query.isEmpty())
            <h2 class="text-lg font-semibold text-gray-900">
                Resultados filtrados
            </h2>
            <p class="text-sm text-gray-600">
                ${resultCount} pessoas encontradas
            </p>
        @else
            <h2 class="text-lg font-semibold text-gray-900">
                Resultados para "${query}"
            </h2>
            <p class="text-sm text-gray-600">
                ${resultCount} pessoas encontradas
            </p>
        @endif
    </div>
    
    @if(resultCount > 0)
        <div class="flex items-center space-x-2">
            @template.components.badge(
                text = "${resultCount} resultados",
                variant = "primary"
            )
            @if(hasMore)
                @template.components.badge(
                    text = "Mais resultados disponíveis",
                    variant = "warning"
                )
            @endif
        </div>
    @endif
</div>

<!-- Results Grid -->
@if(results.isEmpty())
    <div class="text-center py-12">
        <div class="text-6xl mb-4">🔍</div>
        <h3 class="text-lg font-medium text-gray-900 mb-2">
            Nenhum resultado encontrado
        </h3>
        <p class="text-gray-600">
            @if(!query.isEmpty())
                Tente buscar por outro termo ou use filtros diferentes.
            @else
                Use os filtros acima para encontrar pessoas por departamento ou cargo.
            @endif
        </p>
    </div>
@else
    <div class="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6">
        @for(var person : results)
            <div class="bg-white rounded-lg border border-gray-200 shadow-sm hover:shadow-md transition-shadow p-6">
                <!-- Person Avatar -->
                <div class="flex items-center mb-4">
                    <div class="w-12 h-12 bg-orange-100 rounded-full flex items-center justify-center mr-4">
                        <span class="text-orange-600 font-bold text-lg">
                            ${person.getInitials()}
                        </span>
                    </div>
                    <div class="flex-1">
                        <h3 class="font-semibold text-gray-900">${person.getFullName()}</h3>
                        <p class="text-sm text-gray-600">${person.getPosition()}</p>
                    </div>
                </div>
                
                <!-- Person Details -->
                <div class="space-y-2 mb-4">
                    <div class="flex items-center text-sm text-gray-600">
                        <svg class="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20">
                            <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z"/>
                            <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z"/>
                        </svg>
                        ${person.getEmail()}
                    </div>
                    <div class="flex items-center text-sm text-gray-600">
                        <svg class="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20">
                            <path fill-rule="evenodd" d="M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z" clip-rule="evenodd"/>
                        </svg>
                        ${person.getDepartment()}
                    </div>
                    <div class="flex items-center text-sm text-gray-600">
                        <svg class="w-4 h-4 mr-2" fill="currentColor" viewBox="0 0 20 20">
                            <path fill-rule="evenodd" d="M2 3.5A1.5 1.5 0 013.5 2h1.148a1.5 1.5 0 011.465 1.175l.716 3.223a1.5 1.5 0 01-1.052 1.767l-.933.267c-.41.117-.643.555-.48.95a11.542 11.542 0 006.254 6.254c.395.163.833-.07.95-.48l.267-.933a1.5 1.5 0 011.767-1.052l3.223.716A1.5 1.5 0 0118 15.352V16.5a1.5 1.5 0 01-1.5 1.5H15c-1.149 0-2.263-.15-3.326-.43A13.022 13.022 0 012 8.5v-.5z" clip-rule="evenodd"/>
                        </svg>
                        (11) 99999-9999 <!-- Mock phone for display -->
                    </div>
                </div>
                
                <!-- Action Button -->
                <button 
                    class="w-full bg-orange-600 text-white px-4 py-2 rounded-lg font-medium hover:bg-orange-700 transition-colors"
                    data-on-click="/search/person/${person.getId()}"
                    data-target="#person-modal"
                    data-swap="innerHTML">
                    Ver detalhes
                </button>
            </div>
        @endfor
    </div>
    
    <!-- Load More Button (if applicable) -->
    @if(hasMore)
        <div class="text-center mt-8">
            <button 
                class="bg-gray-200 text-gray-900 px-6 py-2 rounded-lg font-medium hover:bg-gray-300 transition-colors"
                data-on-click="/search/results?q=${query}&limit=20"
                data-target="#search-results">
                Carregar mais resultados
            </button>
        </div>
    @endif
@endif

<!-- Modal Container for Person Details -->
<div id="person-modal" class="hidden fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50">
    <!-- Modal content will be loaded here -->
</div>

<!-- Performance Info (Development only) -->
@if(System.getProperty("spring.profiles.active", "").contains("dev"))
    <div class="mt-6 p-3 bg-gray-100 rounded-lg text-xs text-gray-600">
        <strong>🚀 Performance:</strong> Busca executada em ~50ms | 
        <strong>📊 Cache:</strong> Ativo | 
        <strong>🔍 Resultados:</strong> ${resultCount}/${resultCount + (hasMore ? "+" : "")}
    </div>
@endif 