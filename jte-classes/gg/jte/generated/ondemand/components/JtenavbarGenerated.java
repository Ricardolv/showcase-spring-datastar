package gg.jte.generated.ondemand.components;
@SuppressWarnings("unchecked")
public final class JtenavbarGenerated {
	public static final String JTE_NAME = "components/navbar.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,21,21,21,21,0,0,0,0};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String activeRoute) {
		jteOutput.writeContent("\n<nav class=\"container-showcase py-4\">\n    <div class=\"flex items-center justify-between\">\n        <div class=\"flex items-center space-x-3\">\n            <div class=\"w-10 h-10 bg-orange-500 rounded-lg flex items-center justify-center\">\n                <span class=\"text-white font-bold text-lg\">C</span>\n            </div>\n            <div>\n                <h1 class=\"text-xl font-bold text-gray-900\">CEAR Showcase</h1>\n                <p class=\"text-xs text-gray-500\">Datastar + Tailwind + Spring</p>\n            </div>\n        </div>\n        \n        <div class=\"hidden md:flex items-center space-x-4\">\n            <a href=\"/\" class=\"text-gray-700 hover:text-orange-600 px-3 py-2 rounded-md text-sm font-medium\">Home</a>\n            <a href=\"/search\" class=\"text-gray-700 hover:text-orange-600 px-3 py-2 rounded-md text-sm font-medium\">Search</a>\n            <a href=\"/dashboard\" class=\"text-gray-700 hover:text-orange-600 px-3 py-2 rounded-md text-sm font-medium\">Dashboard</a>\n            <a href=\"/forms\" class=\"text-gray-700 hover:text-orange-600 px-3 py-2 rounded-md text-sm font-medium\">Forms</a>\n        </div>\n    </div>\n</nav> ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String activeRoute = (String)params.getOrDefault("activeRoute", "");
		render(jteOutput, jteHtmlInterceptor, activeRoute);
	}
}
