package gg.jte.generated.ondemand.layout;
import gg.jte.Content;
@SuppressWarnings("unchecked")
public final class JtemainGenerated {
	public static final String JTE_NAME = "layout/main.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,10,10,10,10,12,17,20,25,25,29,29,29,40,40,40,2,3,3,3,3};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String title, Content content) {
		jteOutput.writeContent("\n<!DOCTYPE html>\n<html lang=\"pt-BR\">\n<head>\n    <meta charset=\"UTF-8\">\n    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n    <title>");
		jteOutput.setContext("title", null);
		jteOutput.writeUserContent(title);
		jteOutput.writeContent(" - CEAR Showcase</title>\n    \n    ");
		jteOutput.writeContent("\n    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\n    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\n    <link href=\"https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap\" rel=\"stylesheet\">\n    \n    ");
		jteOutput.writeContent("\n    <link href=\"/css/styles.css\" rel=\"stylesheet\">\n    \n    ");
		jteOutput.writeContent("\n    <script type=\"module\" defer src=\"https://cdn.jsdelivr.net/npm/@sudodevnull/datastar@latest\"></script>\n</head>\n<body class=\"bg-gray-50 font-sans\">\n    <header class=\"bg-white shadow-sm border-b border-gray-200 sticky top-0 z-40\">\n        ");
		gg.jte.generated.ondemand.components.JtenavbarGenerated.render(jteOutput, jteHtmlInterceptor, "");
		jteOutput.writeContent("\n    </header>\n    \n    <main class=\"min-h-screen\">\n        ");
		jteOutput.setContext("main", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </main>\n    \n    <footer class=\"bg-gray-900 text-gray-300 py-12 mt-16\">\n        <div class=\"container-showcase\">\n            <div class=\"text-center\">\n                <p>&copy; 2024 CEAR - UFPB. Projeto de demonstração.</p>\n            </div>\n        </div>\n    </footer>\n</body>\n</html> ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String title = (String)params.getOrDefault("title", "CEAR Showcase");
		Content content = (Content)params.get("content");
		render(jteOutput, jteHtmlInterceptor, title, content);
	}
}
