package gg.jte.generated.ondemand.fragments;
@SuppressWarnings("unchecked")
public final class JtepersondetailsGenerated {
	public static final String JTE_NAME = "fragments/person-details.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,3,3,15,17,21,21,21,24,24,24,25,25,25,28,37,37,37,47,57,57,57,67,67,67,77,77,77,83,98,101,108,114,114,114,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, com.cear.showcase.dto.SearchResult person) {
		jteOutput.writeContent("\n<div class=\"bg-white rounded-lg max-w-md mx-auto shadow-xl\">\n    ");
		jteOutput.writeContent("\n    <div class=\"flex items-center justify-between p-6 border-b border-gray-200\">\n        <h3 class=\"text-lg font-semibold text-gray-900\">Detalhes da Pessoa</h3>\n        <button \n            class=\"text-gray-400 hover:text-gray-600 transition-colors\"\n            onclick=\"document.getElementById('person-modal').classList.add('hidden')\">\n            <svg class=\"w-6 h-6\" fill=\"none\" stroke=\"currentColor\" viewBox=\"0 0 24 24\">\n                <path stroke-linecap=\"round\" stroke-linejoin=\"round\" stroke-width=\"2\" d=\"M6 18L18 6M6 6l12 12\"/>\n            </svg>\n        </button>\n    </div>\n    \n    ");
		jteOutput.writeContent("\n    <div class=\"p-6\">\n        ");
		jteOutput.writeContent("\n        <div class=\"text-center mb-6\">\n            <div class=\"w-20 h-20 bg-orange-100 rounded-full flex items-center justify-center mx-auto mb-4\">\n                <span class=\"text-orange-600 font-bold text-2xl\">\n                    ");
		jteOutput.setContext("span", null);
		jteOutput.writeUserContent(person.getInitials());
		jteOutput.writeContent("\n                </span>\n            </div>\n            <h4 class=\"text-xl font-bold text-gray-900\">");
		jteOutput.setContext("h4", null);
		jteOutput.writeUserContent(person.getFullName());
		jteOutput.writeContent("</h4>\n            <p class=\"text-gray-600\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(person.getPosition());
		jteOutput.writeContent("</p>\n        </div>\n        \n        ");
		jteOutput.writeContent("\n        <div class=\"space-y-4\">\n            <div class=\"flex items-center p-3 bg-gray-50 rounded-lg\">\n                <svg class=\"w-5 h-5 text-gray-500 mr-3\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                    <path d=\"M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z\"/>\n                    <path d=\"M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z\"/>\n                </svg>\n                <div>\n                    <p class=\"text-sm text-gray-500\">Email</p>\n                    <p class=\"font-medium text-gray-900\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(person.getEmail());
		jteOutput.writeContent("</p>\n                </div>\n            </div>\n            \n            <div class=\"flex items-center p-3 bg-gray-50 rounded-lg\">\n                <svg class=\"w-5 h-5 text-gray-500 mr-3\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                    <path fill-rule=\"evenodd\" d=\"M2 3.5A1.5 1.5 0 013.5 2h1.148a1.5 1.5 0 011.465 1.175l.716 3.223a1.5 1.5 0 01-1.052 1.767l-.933.267c-.41.117-.643.555-.48.95a11.542 11.542 0 006.254 6.254c.395.163.833-.07.95-.48l.267-.933a1.5 1.5 0 011.767-1.052l3.223.716A1.5 1.5 0 0118 15.352V16.5a1.5 1.5 0 01-1.5 1.5H15c-1.149 0-2.263-.15-3.326-.43A13.022 13.022 0 012 8.5v-.5z\" clip-rule=\"evenodd\"/>\n                </svg>\n                <div>\n                    <p class=\"text-sm text-gray-500\">Telefone</p>\n                    <p class=\"font-medium text-gray-900\">(11) 99999-9999</p> ");
		jteOutput.writeContent("\n                </div>\n            </div>\n            \n            <div class=\"flex items-center p-3 bg-gray-50 rounded-lg\">\n                <svg class=\"w-5 h-5 text-gray-500 mr-3\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                    <path fill-rule=\"evenodd\" d=\"M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z\" clip-rule=\"evenodd\"/>\n                </svg>\n                <div>\n                    <p class=\"text-sm text-gray-500\">Departamento</p>\n                    <p class=\"font-medium text-gray-900\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(person.getDepartment());
		jteOutput.writeContent("</p>\n                </div>\n            </div>\n            \n            <div class=\"flex items-center p-3 bg-gray-50 rounded-lg\">\n                <svg class=\"w-5 h-5 text-gray-500 mr-3\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                    <path fill-rule=\"evenodd\" d=\"M6 6V5a6 6 0 1112 0v1h2a2 2 0 012 2v3.57A22.952 22.952 0 0110 13a22.95 22.95 0 01-12-1.43V8a2 2 0 012-2h2zm2-1a4 4 0 118 0v1H8V5zm1 5a1 1 0 011-1h.01a1 1 0 110 2H10a1 1 0 01-1-1z\" clip-rule=\"evenodd\"/>\n                </svg>\n                <div>\n                    <p class=\"text-sm text-gray-500\">Cargo</p>\n                    <p class=\"font-medium text-gray-900\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(person.getPosition());
		jteOutput.writeContent("</p>\n                </div>\n            </div>\n            \n            <div class=\"flex items-center p-3 bg-gray-50 rounded-lg\">\n                <svg class=\"w-5 h-5 text-gray-500 mr-3\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                    <path fill-rule=\"evenodd\" d=\"M10 18a8 8 0 100-16 8 8 0 000 16zm1-12a1 1 0 10-2 0v4a1 1 0 00.293.707l2.828 2.829a1 1 0 101.415-1.415L11 9.586V6z\" clip-rule=\"evenodd\"/>\n                </svg>\n                <div>\n                    <p class=\"text-sm text-gray-500\">ID no Sistema</p>\n                    <p class=\"font-medium text-gray-900\">");
		jteOutput.setContext("p", null);
		jteOutput.writeUserContent(person.getId());
		jteOutput.writeContent("</p>\n                </div>\n            </div>\n        </div>\n    </div>\n    \n    ");
		jteOutput.writeContent("\n    <div class=\"flex items-center justify-end space-x-3 p-6 border-t border-gray-200\">\n        <button \n            class=\"px-4 py-2 text-gray-700 bg-gray-200 rounded-lg hover:bg-gray-300 transition-colors\"\n            onclick=\"document.getElementById('person-modal').classList.add('hidden')\">\n            Fechar\n        </button>\n        <button \n            class=\"px-4 py-2 text-white bg-orange-600 rounded-lg hover:bg-orange-700 transition-colors\">\n            Contatar\n        </button>\n    </div>\n</div>\n\n<script>\n    ");
		jteOutput.writeContent("\n    document.getElementById('person-modal').classList.remove('hidden');\n    \n    ");
		jteOutput.writeContent("\n    document.getElementById('person-modal').addEventListener('click', function(e) {\n        if (e.target === this) {\n            this.classList.add('hidden');\n        }\n    });\n    \n    ");
		jteOutput.writeContent("\n    document.addEventListener('keydown', function(e) {\n        if (e.key === 'Escape') {\n            document.getElementById('person-modal').classList.add('hidden');\n        }\n    });\n</script> ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		com.cear.showcase.dto.SearchResult person = (com.cear.showcase.dto.SearchResult)params.get("person");
		render(jteOutput, jteHtmlInterceptor, person);
	}
}
