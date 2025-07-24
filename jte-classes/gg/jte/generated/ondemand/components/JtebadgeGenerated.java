package gg.jte.generated.ondemand.components;
@SuppressWarnings("unchecked")
public final class JtebadgeGenerated {
	public static final String JTE_NAME = "components/badge.jte";
	public static final int[] JTE_LINE_INFO = {0,0,0,0,0,3,3,4,4,5,5,6,6,7,7,8,8,9,9,10,10,11,11,12,12,13,13,15,15,15,15,16,16,16,17,17,17,0,1,1,1,1};
	public static void render(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, String text, String variant) {
		jteOutput.writeContent("\n");
		jteOutput.writeContent("\n");
		String badgeClasses = "";
		jteOutput.writeContent("\n");
		if (variant.equals("success")) {
			jteOutput.writeContent("\n    ");
			badgeClasses = "bg-green-100 text-green-800";
			jteOutput.writeContent("\n");
		} else if (variant.equals("warning")) {
			jteOutput.writeContent("\n    ");
			badgeClasses = "bg-yellow-100 text-yellow-800";
			jteOutput.writeContent("\n");
		} else if (variant.equals("danger")) {
			jteOutput.writeContent("\n    ");
			badgeClasses = "bg-red-100 text-red-800";
			jteOutput.writeContent("\n");
		} else {
			jteOutput.writeContent("\n    ");
			badgeClasses = "bg-orange-100 text-orange-800";
			jteOutput.writeContent("\n");
		}
		jteOutput.writeContent("\n\n<span class=\"");
		jteOutput.setContext("span", "class");
		jteOutput.writeUserContent(badgeClasses);
		jteOutput.setContext("span", null);
		jteOutput.writeContent(" inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium\">\n    ");
		jteOutput.setContext("span", null);
		jteOutput.writeUserContent(text);
		jteOutput.writeContent("\n</span> ");
	}
	public static void renderMap(gg.jte.html.HtmlTemplateOutput jteOutput, gg.jte.html.HtmlInterceptor jteHtmlInterceptor, java.util.Map<String, Object> params) {
		String text = (String)params.get("text");
		String variant = (String)params.getOrDefault("variant", "primary");
		render(jteOutput, jteHtmlInterceptor, text, variant);
	}
}
