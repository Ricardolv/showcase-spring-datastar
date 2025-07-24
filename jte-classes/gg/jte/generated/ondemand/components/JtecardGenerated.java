package gg.jte.generated.ondemand.components;
import gg.jte.Content;
@SuppressWarnings("unchecked")
public final class JtecardGenerated {
	public static final String JTE_NAME = "components/card.jte";
	public static final int[] JTE_LINE_INFO = {0,0,2,2,2,2,7,7,7,9,9,10,10,10,11,11,12,12,13,13,13,14,14,16,16,19,19,19,21,21,21,2,3,4,4,4,4};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, Content content, String title, String subtitle) {
		jteOutput.writeContent("\n<div class=\"bg-white rounded-xl border border-gray-200 shadow-sm hover:shadow-md transition-shadow p-6\">\n    ");
		if (!title.isEmpty() || !subtitle.isEmpty()) {
			jteOutput.writeContent("\n        <div class=\"mb-4\">\n            ");
			if (!title.isEmpty()) {
				jteOutput.writeContent("\n                <h3 class=\"text-lg font-semibold text-gray-900 mb-1\">");
				jteOutput.setContext("h3", null);
				jteOutput.writeUserContent(title);
				jteOutput.writeContent("</h3>\n            ");
			}
			jteOutput.writeContent("\n            ");
			if (!subtitle.isEmpty()) {
				jteOutput.writeContent("\n                <p class=\"text-sm text-gray-600\">");
				jteOutput.setContext("p", null);
				jteOutput.writeUserContent(subtitle);
				jteOutput.writeContent("</p>\n            ");
			}
			jteOutput.writeContent("\n        </div>\n    ");
		}
		jteOutput.writeContent("\n    \n    <div class=\"card-content\">\n        ");
		jteOutput.setContext("div", null);
		jteOutput.writeUserContent(content);
		jteOutput.writeContent("\n    </div>\n</div> ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		Content content = (Content)params.get("content");
		String title = (String)params.getOrDefault("title", "");
		String subtitle = (String)params.getOrDefault("subtitle", "");
		render(jteOutput, jteHtmlInterceptor, content, title, subtitle);
	}
}
