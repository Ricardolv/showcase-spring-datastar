@import gg.jte.Content

@param Content content
@param String title = ""
@param String subtitle = ""

<div class="bg-white rounded-xl border border-gray-200 shadow-sm hover:shadow-md transition-shadow p-6">
    @if(!title.isEmpty() || !subtitle.isEmpty())
        <div class="mb-4">
            @if(!title.isEmpty())
                <h3 class="text-lg font-semibold text-gray-900 mb-1">${title}</h3>
            @endif
            @if(!subtitle.isEmpty())
                <p class="text-sm text-gray-600">${subtitle}</p>
            @endif
        </div>
    @endif
    
    <div class="card-content">
        ${content}
    </div>
</div> 