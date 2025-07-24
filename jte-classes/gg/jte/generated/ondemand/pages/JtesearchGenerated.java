package gg.jte.generated.ondemand.pages;
@SuppressWarnings("unchecked")
public final class JtesearchGenerated {
	public static final String JTE_NAME = "pages/search.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,4,4,7,7,9,19,21,25,25,26,26,26,27,27,27,28,32,32,33,33,33,34,34,34,35,39,39,40,40,40,41,41,41,44,48,64,90,117,124,126,134,143,145,151,156,162,165,171,181,183,189,197,197,197,197,197,197,0,1,2,2,2,2};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Integer totalPeople, Integer totalDepartments, Integer totalPositions) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, "Active Search", new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <div class=\"container-showcase py-8\">\n            ");
				jteOutput.writeContent("\n            <div class=\"text-center mb-8\">\n                <h1 class=\"text-3xl font-bold text-gray-900 mb-4\">\n                    🔍 Active Search\n                </h1>\n                <p class=\"text-lg text-gray-600 max-w-2xl mx-auto\">\n                    Busca em tempo real usando Datastar. Digite para ver a mágica do hypermedia acontecendo!\n                </p>\n            </div>\n\n            ");
				jteOutput.writeContent("\n            <div class=\"grid grid-cols-1 md:grid-cols-3 gap-6 mb-8\">\n                ");
				gg.jte.generated.ondemand.components.JtecardGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
					public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
						jteOutput.writeContent("\n                        <div class=\"text-3xl font-bold text-orange-600\">");
						jteOutput.setContext("div", null);
						jteOutput.writeUserContent(totalPeople);
						jteOutput.writeContent("</div>\n                    ");
					}
				}, "Pessoas", "Total no sistema");
				jteOutput.writeContent("\n                ");
				gg.jte.generated.ondemand.components.JtecardGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
					public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
						jteOutput.writeContent("\n                        <div class=\"text-3xl font-bold text-blue-600\">");
						jteOutput.setContext("div", null);
						jteOutput.writeUserContent(totalDepartments);
						jteOutput.writeContent("</div>\n                    ");
					}
				}, "Departamentos", "Áreas disponíveis");
				jteOutput.writeContent("\n                ");
				gg.jte.generated.ondemand.components.JtecardGenerated.render(jteOutput, jteHtmlInterceptor, new gg.jte.html.HtmlContent() {
					public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
						jteOutput.writeContent("\n                        <div class=\"text-3xl font-bold text-green-600\">");
						jteOutput.setContext("div", null);
						jteOutput.writeUserContent(totalPositions);
						jteOutput.writeContent("</div>\n                    ");
					}
				}, "Cargos", "Posições diferentes");
				jteOutput.writeContent("\n            </div>\n\n            ");
				jteOutput.writeContent("\n            <div class=\"bg-white rounded-xl border border-gray-200 shadow-sm p-6 mb-8\">\n                <form data-store='{\"searchQuery\": \"\", \"selectedDepartment\": \"\", \"selectedPosition\": \"\"}'>\n                    <div class=\"grid grid-cols-1 md:grid-cols-3 gap-4 mb-4\">\n                        ");
				jteOutput.writeContent("\n                        <div class=\"md:col-span-1\">\n                            <label for=\"search-input\" class=\"block text-sm font-medium text-gray-700 mb-2\">\n                                Buscar pessoas\n                            </label>\n                            <input \n                                type=\"text\" \n                                id=\"search-input\"\n                                name=\"q\"\n                                placeholder=\"Nome, email, telefone...\"\n                                data-model=\"searchQuery\"\n                                data-on-input.debounce.300ms=\"/search/results\"\n                                data-target=\"#search-results\"\n                                class=\"w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500 focus:border-transparent\">\n                        </div>\n\n                        ");
				jteOutput.writeContent("\n                        <div>\n                            <label for=\"department-select\" class=\"block text-sm font-medium text-gray-700 mb-2\">\n                                Departamento\n                            </label>\n                            <select \n                                id=\"department-select\"\n                                name=\"department\"\n                                data-model=\"selectedDepartment\"\n                                data-on-change=\"/search/results\"\n                                data-target=\"#search-results\"\n                                class=\"w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500\">\n                                <option value=\"\">Todos os departamentos</option>\n                                <option value=\"Engenharia\">Engenharia</option>\n                                <option value=\"Pesquisa\">Pesquisa</option>\n                                <option value=\"Administração\">Administração</option>\n                                <option value=\"Projetos\">Projetos</option>\n                                <option value=\"Qualidade\">Qualidade</option>\n                                <option value=\"Inovação\">Inovação</option>\n                                <option value=\"Sustentabilidade\">Sustentabilidade</option>\n                                <option value=\"Energia Solar\">Energia Solar</option>\n                                <option value=\"Energia Eólica\">Energia Eólica</option>\n                                <option value=\"Biomassa\">Biomassa</option>\n                            </select>\n                        </div>\n\n                        ");
				jteOutput.writeContent("\n                        <div>\n                            <label for=\"position-select\" class=\"block text-sm font-medium text-gray-700 mb-2\">\n                                Cargo\n                            </label>\n                            <select \n                                id=\"position-select\"\n                                name=\"position\"\n                                data-model=\"selectedPosition\"\n                                data-on-change=\"/search/results\"\n                                data-target=\"#search-results\"\n                                class=\"w-full px-4 py-3 border border-gray-300 rounded-lg focus:outline-none focus:ring-2 focus:ring-orange-500\">\n                                <option value=\"\">Todos os cargos</option>\n                                <option value=\"Engenheiro\">Engenheiro</option>\n                                <option value=\"Pesquisador\">Pesquisador</option>\n                                <option value=\"Analista\">Analista</option>\n                                <option value=\"Coordenador\">Coordenador</option>\n                                <option value=\"Gerente\">Gerente</option>\n                                <option value=\"Diretor\">Diretor</option>\n                                <option value=\"Técnico\">Técnico</option>\n                                <option value=\"Assistente\">Assistente</option>\n                                <option value=\"Especialista\">Especialista</option>\n                                <option value=\"Consultor\">Consultor</option>\n                            </select>\n                        </div>\n                    </div>\n\n                    ");
				jteOutput.writeContent("\n                    <p class=\"text-sm text-gray-500\">\n                        💡 A busca acontece em tempo real conforme você digita. Use os filtros para refinar os resultados.\n                    </p>\n                </form>\n            </div>\n\n            ");
				jteOutput.writeContent("\n            <div id=\"search-results\" class=\"min-h-96\">\n                ");
				jteOutput.writeContent("\n                <div class=\"text-center py-12 text-gray-500\">\n                    <div class=\"text-4xl mb-4\">🔍</div>\n                    <p class=\"text-lg\">Digite algo na busca para ver os resultados aparecerem!</p>\n                    <p class=\"text-sm mt-2\">Ou use os filtros para explorar por departamento ou cargo.</p>\n                </div>\n            </div>\n\n            ");
				jteOutput.writeContent("\n            <div id=\"loading-overlay\" class=\"hidden fixed inset-0 bg-black bg-opacity-25 flex items-center justify-center z-50\" data-loading>\n                <div class=\"bg-white rounded-lg p-6 flex items-center space-x-3\">\n                    <div class=\"animate-spin rounded-full h-6 w-6 border-b-2 border-orange-600\"></div>\n                    <span class=\"text-gray-700\">Buscando...</span>\n                </div>\n            </div>\n        </div>\n\n        ");
				jteOutput.writeContent("\n        <style>\n            ");
				jteOutput.writeContent("\n            [data-loading] {\n                opacity: 0.7;\n                pointer-events: none;\n            }\n            \n            ");
				jteOutput.writeContent("\n            #search-results {\n                transition: opacity 0.2s ease-in-out;\n            }\n            \n            ");
				jteOutput.writeContent("\n            input:focus, select:focus {\n                box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.1);\n            }\n        </style>\n\n        ");
				jteOutput.writeContent("\n        <script>\n            document.addEventListener('DOMContentLoaded', function() {\n                ");
				jteOutput.writeContent("\n                const searchInput = document.getElementById('search-input');\n                if (searchInput) {\n                    searchInput.focus();\n                }\n                \n                ");
				jteOutput.writeContent("\n                fetch('/search/results?limit=6')\n                    .then(response => response.text())\n                    .then(html => {\n                        document.getElementById('search-results').innerHTML = html;\n                    })\n                    .catch(error => {\n                        console.error('Error loading initial results:', error);\n                    });\n                \n                ");
				jteOutput.writeContent("\n                document.addEventListener('keydown', function(e) {\n                    ");
				jteOutput.writeContent("\n                    if ((e.ctrlKey || e.metaKey) && e.key === 'k') {\n                        e.preventDefault();\n                        searchInput.focus();\n                    }\n                    \n                    ");
				jteOutput.writeContent("\n                    if (e.key === 'Escape' && document.activeElement === searchInput) {\n                        searchInput.value = '';\n                        searchInput.dispatchEvent(new Event('input'));\n                    }\n                });\n            });\n        </script>\n    ");
			}
		});
		jteOutput.writeContent(" ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Integer totalPeople = (Integer)params.getOrDefault("totalPeople", 0);
		Integer totalDepartments = (Integer)params.getOrDefault("totalDepartments", 0);
		Integer totalPositions = (Integer)params.getOrDefault("totalPositions", 0);
		render(jteOutput, jteHtmlInterceptor, totalPeople, totalDepartments, totalPositions);
	}
}
