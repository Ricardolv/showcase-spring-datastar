@param com.cear.showcase.dto.SearchResult person

<div class="bg-white rounded-lg max-w-md mx-auto shadow-xl">
    <!-- Modal Header -->
    <div class="flex items-center justify-between p-6 border-b border-gray-200">
        <h3 class="text-lg font-semibold text-gray-900">Detalhes da Pessoa</h3>
        <button 
            class="text-gray-400 hover:text-gray-600 transition-colors"
            onclick="document.getElementById('person-modal').classList.add('hidden')">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"/>
            </svg>
        </button>
    </div>
    
    <!-- Modal Body -->
    <div class="p-6">
        <!-- Person Avatar Large -->
        <div class="text-center mb-6">
            <div class="w-20 h-20 bg-orange-100 rounded-full flex items-center justify-center mx-auto mb-4">
                <span class="text-orange-600 font-bold text-2xl">
                    ${person.getInitials()}
                </span>
            </div>
            <h4 class="text-xl font-bold text-gray-900">${person.getFullName()}</h4>
            <p class="text-gray-600">${person.getPosition()}</p>
        </div>
        
        <!-- Person Information -->
        <div class="space-y-4">
            <div class="flex items-center p-3 bg-gray-50 rounded-lg">
                <svg class="w-5 h-5 text-gray-500 mr-3" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z"/>
                    <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z"/>
                </svg>
                <div>
                    <p class="text-sm text-gray-500">Email</p>
                    <p class="font-medium text-gray-900">${person.getEmail()}</p>
                </div>
            </div>
            
            <div class="flex items-center p-3 bg-gray-50 rounded-lg">
                <svg class="w-5 h-5 text-gray-500 mr-3" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M2 3.5A1.5 1.5 0 013.5 2h1.148a1.5 1.5 0 011.465 1.175l.716 3.223a1.5 1.5 0 01-1.052 1.767l-.933.267c-.41.117-.643.555-.48.95a11.542 11.542 0 006.254 6.254c.395.163.833-.07.95-.48l.267-.933a1.5 1.5 0 011.767-1.052l3.223.716A1.5 1.5 0 0118 15.352V16.5a1.5 1.5 0 01-1.5 1.5H15c-1.149 0-2.263-.15-3.326-.43A13.022 13.022 0 012 8.5v-.5z" clip-rule="evenodd"/>
                </svg>
                <div>
                    <p class="text-sm text-gray-500">Telefone</p>
                    <p class="font-medium text-gray-900">(11) 99999-9999</p> <!-- Mock phone -->
                </div>
            </div>
            
            <div class="flex items-center p-3 bg-gray-50 rounded-lg">
                <svg class="w-5 h-5 text-gray-500 mr-3" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z" clip-rule="evenodd"/>
                </svg>
                <div>
                    <p class="text-sm text-gray-500">Departamento</p>
                    <p class="font-medium text-gray-900">${person.getDepartment()}</p>
                </div>
            </div>
            
            <div class="flex items-center p-3 bg-gray-50 rounded-lg">
                <svg class="w-5 h-5 text-gray-500 mr-3" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M6 6V5a6 6 0 1112 0v1h2a2 2 0 012 2v3.57A22.952 22.952 0 0110 13a22.95 22.95 0 01-12-1.43V8a2 2 0 012-2h2zm2-1a4 4 0 118 0v1H8V5zm1 5a1 1 0 011-1h.01a1 1 0 110 2H10a1 1 0 01-1-1z" clip-rule="evenodd"/>
                </svg>
                <div>
                    <p class="text-sm text-gray-500">Cargo</p>
                    <p class="font-medium text-gray-900">${person.getPosition()}</p>
                </div>
            </div>
            
            <div class="flex items-center p-3 bg-gray-50 rounded-lg">
                <svg class="w-5 h-5 text-gray-500 mr-3" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z" clip-rule="evenodd"/>
                </svg>
                <div>
                    <p class="text-sm text-gray-500">ID no Sistema</p>
                    <p class="font-medium text-gray-900">${person.getId()}</p>
                </div>
            </div>
        </div>
    </div>
    
    <!-- Modal Footer -->
    <div class="flex items-center justify-end space-x-3 p-6 border-t border-gray-200">
        <button 
            class="px-4 py-2 text-gray-700 bg-gray-200 rounded-lg hover:bg-gray-300 transition-colors"
            onclick="document.getElementById('person-modal').classList.add('hidden')">
            Fechar
        </button>
        <button 
            class="px-4 py-2 text-white bg-orange-600 rounded-lg hover:bg-orange-700 transition-colors">
            Contatar
        </button>
    </div>
</div>

<script>
    // Show modal
    document.getElementById('person-modal').classList.remove('hidden');
    
    // Close modal on background click
    document.getElementById('person-modal').addEventListener('click', function(e) {
        if (e.target === this) {
            this.classList.add('hidden');
        }
    });
    
    // Close modal on Escape key
    document.addEventListener('keydown', function(e) {
        if (e.key === 'Escape') {
            document.getElementById('person-modal').classList.add('hidden');
        }
    });
</script> 