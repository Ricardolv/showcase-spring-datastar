package gg.jte.generated.ondemand.fragments;
@SuppressWarnings("unchecked")
public final class JtesearchresultsGenerated {
	public static final String JTE_NAME = "fragments/search-results.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,6,6,9,9,14,14,14,16,16,21,21,21,23,23,25,25,25,28,28,28,30,30,33,33,35,38,39,39,40,43,44,44,46,46,49,50,50,57,57,59,59,61,61,64,64,66,66,68,72,72,72,76,76,76,77,77,77,81,88,88,88,94,94,94,100,104,107,107,107,107,113,113,116,117,117,121,121,121,121,126,126,127,127,129,131,134,135,135,139,139,139,139,139,139,141,141,141,141,141,0,1,2,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.List<com.cear.showcase.dto.SearchResult> results, String query, Integer resultCount, Boolean hasMore, Boolean showingExample) {
		jteOutput.writeContent("\n");
		jteOutput.writeContent("\n<div class=\"flex items-center justify-between mb-6\">\n    <div>\n        ");
		if (showingExample) {
			jteOutput.writeContent("\n            <h2 class=\"text-lg font-semibold text-gray-900\">\n                Exemplos de pessoas no sistema\n            </h2>\n            <p class=\"text-sm text-gray-600\">\n                ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(resultCount);
			jteOutput.writeContent(" pessoas mostradas como exemplo\n            </p>\n        ");
		} else if (query.isEmpty()) {
			jteOutput.writeContent("\n            <h2 class=\"text-lg font-semibold text-gray-900\">\n                Resultados filtrados\n            </h2>\n            <p class=\"text-sm text-gray-600\">\n                ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(resultCount);
			jteOutput.writeContent(" pessoas encontradas\n            </p>\n        ");
		} else {
			jteOutput.writeContent("\n            <h2 class=\"text-lg font-semibold text-gray-900\">\n                Resultados para \"");
			jteOutput.setContext("h2", null);
			jteOutput.writeUserContent(query);
			jteOutput.writeContent("\"\n            </h2>\n            <p class=\"text-sm text-gray-600\">\n                ");
			jteOutput.setContext("p", null);
			jteOutput.writeUserContent(resultCount);
			jteOutput.writeContent(" pessoas encontradas\n            </p>\n        ");
		}
		jteOutput.writeContent("\n    </div>\n    \n    ");
		if (resultCount > 0) {
			jteOutput.writeContent("\n        <div class=\"flex items-center space-x-2\">\n            ");
			gg.jte.generated.ondemand.components.JtebadgeGenerated.render(jteOutput, jteHtmlInterceptor, "${resultCount} resultados", "primary");
			jteOutput.writeContent("\n            ");
			if (hasMore) {
				jteOutput.writeContent("\n                ");
				gg.jte.generated.ondemand.components.JtebadgeGenerated.render(jteOutput, jteHtmlInterceptor, "Mais resultados disponíveis", "warning");
				jteOutput.writeContent("\n            ");
			}
			jteOutput.writeContent("\n        </div>\n    ");
		}
		jteOutput.writeContent("\n</div>\n\n");
		jteOutput.writeContent("\n");
		if (results.isEmpty()) {
			jteOutput.writeContent("\n    <div class=\"text-center py-12\">\n        <div class=\"text-6xl mb-4\">🔍</div>\n        <h3 class=\"text-lg font-medium text-gray-900 mb-2\">\n            Nenhum resultado encontrado\n        </h3>\n        <p class=\"text-gray-600\">\n            ");
			if (!query.isEmpty()) {
				jteOutput.writeContent("\n                Tente buscar por outro termo ou use filtros diferentes.\n            ");
			} else {
				jteOutput.writeContent("\n                Use os filtros acima para encontrar pessoas por departamento ou cargo.\n            ");
			}
			jteOutput.writeContent("\n        </p>\n    </div>\n");
		} else {
			jteOutput.writeContent("\n    <div class=\"grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-6\">\n        ");
			for (var person : results) {
				jteOutput.writeContent("\n            <div class=\"bg-white rounded-lg border border-gray-200 shadow-sm hover:shadow-md transition-shadow p-6\">\n                ");
				jteOutput.writeContent("\n                <div class=\"flex items-center mb-4\">\n                    <div class=\"w-12 h-12 bg-orange-100 rounded-full flex items-center justify-center mr-4\">\n                        <span class=\"text-orange-600 font-bold text-lg\">\n                            ");
				jteOutput.setContext("span", null);
				jteOutput.writeUserContent(person.getInitials());
				jteOutput.writeContent("\n                        </span>\n                    </div>\n                    <div class=\"flex-1\">\n                        <h3 class=\"font-semibold text-gray-900\">");
				jteOutput.setContext("h3", null);
				jteOutput.writeUserContent(person.getFullName());
				jteOutput.writeContent("</h3>\n                        <p class=\"text-sm text-gray-600\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(person.getPosition());
				jteOutput.writeContent("</p>\n                    </div>\n                </div>\n                \n                ");
				jteOutput.writeContent("\n                <div class=\"space-y-2 mb-4\">\n                    <div class=\"flex items-center text-sm text-gray-600\">\n                        <svg class=\"w-4 h-4 mr-2\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                            <path d=\"M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z\"/>\n                            <path d=\"M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z\"/>\n                        </svg>\n                        ");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(person.getEmail());
				jteOutput.writeContent("\n                    </div>\n                    <div class=\"flex items-center text-sm text-gray-600\">\n                        <svg class=\"w-4 h-4 mr-2\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                            <path fill-rule=\"evenodd\" d=\"M5.05 4.05a7 7 0 119.9 9.9L10 18.9l-4.95-4.95a7 7 0 010-9.9zM10 11a2 2 0 100-4 2 2 0 000 4z\" clip-rule=\"evenodd\"/>\n                        </svg>\n                        ");
				jteOutput.setContext("div", null);
				jteOutput.writeUserContent(person.getDepartment());
				jteOutput.writeContent("\n                    </div>\n                    <div class=\"flex items-center text-sm text-gray-600\">\n                        <svg class=\"w-4 h-4 mr-2\" fill=\"currentColor\" viewBox=\"0 0 20 20\">\n                            <path fill-rule=\"evenodd\" d=\"M2 3.5A1.5 1.5 0 013.5 2h1.148a1.5 1.5 0 011.465 1.175l.716 3.223a1.5 1.5 0 01-1.052 1.767l-.933.267c-.41.117-.643.555-.48.95a11.542 11.542 0 006.254 6.254c.395.163.833-.07.95-.48l.267-.933a1.5 1.5 0 011.767-1.052l3.223.716A1.5 1.5 0 0118 15.352V16.5a1.5 1.5 0 01-1.5 1.5H15c-1.149 0-2.263-.15-3.326-.43A13.022 13.022 0 012 8.5v-.5z\" clip-rule=\"evenodd\"/>\n                        </svg>\n                        (11) 99999-9999 ");
				jteOutput.writeContent("\n                    </div>\n                </div>\n                \n                ");
				jteOutput.writeContent("\n                <button \n                    class=\"w-full bg-orange-600 text-white px-4 py-2 rounded-lg font-medium hover:bg-orange-700 transition-colors\"\n                    data-on-click=\"/search/person/");
				jteOutput.setContext("button", "data-on-click");
				jteOutput.writeUserContent(person.getId());
				jteOutput.setContext("button", null);
				jteOutput.writeContent("\"\n                    data-target=\"#person-modal\"\n                    data-swap=\"innerHTML\">\n                    Ver detalhes\n                </button>\n            </div>\n        ");
			}
			jteOutput.writeContent("\n    </div>\n    \n    ");
			jteOutput.writeContent("\n    ");
			if (hasMore) {
				jteOutput.writeContent("\n        <div class=\"text-center mt-8\">\n            <button \n                class=\"bg-gray-200 text-gray-900 px-6 py-2 rounded-lg font-medium hover:bg-gray-300 transition-colors\"\n                data-on-click=\"/search/results?q=");
				jteOutput.setContext("button", "data-on-click");
				jteOutput.writeUserContent(query);
				jteOutput.setContext("button", null);
				jteOutput.writeContent("&limit=20\"\n                data-target=\"#search-results\">\n                Carregar mais resultados\n            </button>\n        </div>\n    ");
			}
			jteOutput.writeContent("\n");
		}
		jteOutput.writeContent("\n\n");
		jteOutput.writeContent("\n<div id=\"person-modal\" class=\"hidden fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50\">\n    ");
		jteOutput.writeContent("\n</div>\n\n");
		jteOutput.writeContent("\n");
		if (System.getProperty("spring.profiles.active", "").contains("dev")) {
			jteOutput.writeContent("\n    <div class=\"mt-6 p-3 bg-gray-100 rounded-lg text-xs text-gray-600\">\n        <strong>🚀 Performance:</strong> Busca executada em ~50ms | \n        <strong>📊 Cache:</strong> Ativo | \n        <strong>🔍 Resultados:</strong> ");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(resultCount);
			jteOutput.writeContent("/");
			jteOutput.setContext("div", null);
			jteOutput.writeUserContent(resultCount + (hasMore ? "+" : ""));
			jteOutput.writeContent("\n    </div>\n");
		}
		jteOutput.writeContent(" ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		java.util.List<com.cear.showcase.dto.SearchResult> results = (java.util.List<com.cear.showcase.dto.SearchResult>)params.get("results");
		String query = (String)params.getOrDefault("query", "");
		Integer resultCount = (Integer)params.getOrDefault("resultCount", 0);
		Boolean hasMore = (Boolean)params.getOrDefault("hasMore", false);
		Boolean showingExample = (Boolean)params.getOrDefault("showingExample", false);
		render(jteOutput, jteHtmlInterceptor, results, query, resultCount, hasMore, showingExample);
	}
}
