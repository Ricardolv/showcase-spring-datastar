@param String error = "Erro desconhecido"

<div class="bg-white rounded-lg max-w-md mx-auto shadow-xl">
    <!-- Error Header -->
    <div class="flex items-center justify-between p-6 border-b border-gray-200">
        <h3 class="text-lg font-semibold text-red-600">Erro</h3>
        <button 
            class="text-gray-400 hover:text-gray-600 transition-colors"
            onclick="document.getElementById('person-modal').classList.add('hidden')">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"/>
            </svg>
        </button>
    </div>
    
    <!-- Error Body -->
    <div class="p-6 text-center">
        <div class="text-6xl mb-4">❌</div>
        <h4 class="text-lg font-semibold text-gray-900 mb-2">Ops!</h4>
        <p class="text-gray-600 mb-6">${error}</p>
        
        <button 
            class="px-4 py-2 text-white bg-red-600 rounded-lg hover:bg-red-700 transition-colors"
            onclick="document.getElementById('person-modal').classList.add('hidden')">
            Fechar
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
</script> 