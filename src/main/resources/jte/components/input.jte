@param String name = ""
@param String type = "text"
@param String placeholder = ""
@param String label = ""

<div class="space-y-1">
    @if(!label.isEmpty())
        <label for="${name}" class="block text-sm font-medium text-gray-700">
            ${label}
        </label>
    @endif
    
    <input type="${type}"
           id="${name}"
           name="${name}"
           placeholder="${placeholder}"
           class="w-full px-3 py-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent">
</div> 