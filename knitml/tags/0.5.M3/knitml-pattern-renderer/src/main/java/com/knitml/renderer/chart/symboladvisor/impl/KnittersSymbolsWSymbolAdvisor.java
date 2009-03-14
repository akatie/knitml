package com.knitml.renderer.chart.symboladvisor.impl;

import static com.knitml.renderer.chart.ChartElement.K;
import static com.knitml.renderer.chart.ChartElement.K2TOG;
import static com.knitml.renderer.chart.ChartElement.NS;
import static com.knitml.renderer.chart.ChartElement.P;
import static com.knitml.renderer.chart.ChartElement.SL;
import static com.knitml.renderer.chart.ChartElement.SSK;
import static com.knitml.renderer.chart.ChartElement.YO;

import java.util.HashMap;
import java.util.Map;

import com.knitml.renderer.chart.ChartElement;
import com.knitml.renderer.chart.symboladvisor.ChartSymbolAdvisor;

public class KnittersSymbolsWSymbolAdvisor extends
		AbstractHtmlStylesheetProvider implements ChartSymbolAdvisor {

	private static Map<ChartElement, String> symbols = new HashMap<ChartElement, String>();

	static {
		symbols.put(K, "a");
		symbols.put(P, "P");
		symbols.put(K2TOG, "/");
		symbols.put(SSK, "\\");
		symbols.put(YO, "o");
		symbols.put(NS, "C");
		symbols.put(SL, "S");
	}

	public String getSymbol(ChartElement element) {
		return symbols.get(element);
	}

	public String getStyleClassPrefix() {
		return "ks";
	}

	protected String getRelativePathToCssFile() {
		return "ks.css";
	}
}
