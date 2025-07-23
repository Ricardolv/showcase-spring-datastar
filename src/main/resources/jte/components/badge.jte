@param String text
@param String variant = "primary"

<%-- Determine badge classes based on variant --%>
!{String badgeClasses = "";}
@if(variant.equals("success"))
    !{badgeClasses = "bg-green-100 text-green-800";}
@elseif(variant.equals("warning"))
    !{badgeClasses = "bg-yellow-100 text-yellow-800";}
@elseif(variant.equals("danger"))
    !{badgeClasses = "bg-red-100 text-red-800";}
@else
    !{badgeClasses = "bg-orange-100 text-orange-800";}
@endif

<span class="${badgeClasses} inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium">
    ${text}
</span> 