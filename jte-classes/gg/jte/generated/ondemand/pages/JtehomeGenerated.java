package gg.jte.generated.ondemand.pages;
@SuppressWarnings("unchecked")
public final class JtehomeGenerated {
	public static final String JTE_NAME = "pages/home.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,3,3,6,6,57,57,57,57,57,57,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, String activeRoute) {
		jteOutput.writeContent("\n");
		gg.jte.generated.ondemand.layout.JtemainGenerated.render(jteOutput, jteHtmlInterceptor, title, new gg.jte.html.HtmlContent() {
			public void writeTo(gg.jte.html.HtmlTemplateOutput jteOutput) {
				jteOutput.writeContent("\n        <div class=\"container-showcase py-12\">\n            <div class=\"text-center mb-12\">\n                <h1 class=\"text-4xl font-bold text-gray-900 mb-4\">\n                    🚀 CEAR Showcase\n                </h1>\n                <p class=\"text-xl text-gray-600 mb-8\">\n                    Demonstração de Datastar + Tailwind CSS + Spring Boot\n                </p>\n                \n                <div class=\"flex justify-center space-x-4\">\n                    <a href=\"/search\" class=\"showcase-button-primary\">\n                        🔍 Active Search\n                    </a>\n                    <a href=\"/dashboard\" class=\"showcase-button-secondary\">\n                        📊 Dashboard\n                    </a>\n                    <a href=\"/forms\" class=\"showcase-button-outline\">\n                        📝 Formulários\n                    </a>\n                </div>\n            </div>\n            \n            <div class=\"grid md:grid-cols-3 gap-8\">\n                <div class=\"showcase-card text-center\">\n                    <div class=\"text-3xl mb-4\">⚡</div>\n                    <h3 class=\"text-lg font-semibold mb-2\">Datastar</h3>\n                    <p class=\"text-gray-600\">Framework hipermídia para interatividade reativa sem JavaScript complexo</p>\n                </div>\n                \n                <div class=\"showcase-card text-center\">\n                    <div class=\"text-3xl mb-4\">🎨</div>\n                    <h3 class=\"text-lg font-semibold mb-2\">Tailwind CSS</h3>\n                    <p class=\"text-gray-600\">Framework CSS utility-first com paleta CEAR personalizada</p>\n                </div>\n                \n                <div class=\"showcase-card text-center\">\n                    <div class=\"text-3xl mb-4\">🍃</div>\n                    <h3 class=\"text-lg font-semibold mb-2\">Spring Boot</h3>\n                    <p class=\"text-gray-600\">Backend Java minimalista com templates JTE type-safe</p>\n                </div>\n            </div>\n            \n            <div class=\"mt-12 text-center\">\n                <p class=\"text-gray-500\">\n                    <strong>Teste de funcionalidade:</strong> \n                    <a href=\"/test\" class=\"text-orange-600 hover:text-orange-700\">Controller Test</a> | \n                    <a href=\"/health\" class=\"text-orange-600 hover:text-orange-700\">Health Check</a>\n                </p>\n            </div>\n        </div>\n    ");
			}
		});
		jteOutput.writeContent(" ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "CEAR Showcase");
		String activeRoute = (String)params.getOrDefault("activeRoute", "home");
		render(jteOutput, jteHtmlInterceptor, title, activeRoute);
	}
}
