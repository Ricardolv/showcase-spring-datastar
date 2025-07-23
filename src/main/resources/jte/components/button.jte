@param String text = ""
@param String type = "button"
@param String variant = "primary"

<%-- Determine button classes based on variant --%>
!{String buttonClasses = "";}
@if(variant.equals("primary"))
    !{buttonClasses = "bg-orange-600 text-white hover:bg-orange-700";}
@elseif(variant.equals("secondary"))
    !{buttonClasses = "bg-gray-200 text-gray-900 hover:bg-gray-300";}
@else
    !{buttonClasses = "bg-orange-600 text-white hover:bg-orange-700";}
@endif

<button type="${type}" class="${buttonClasses} px-4 py-2 rounded-lg font-medium transition-colors">
    ${text}
</button> 