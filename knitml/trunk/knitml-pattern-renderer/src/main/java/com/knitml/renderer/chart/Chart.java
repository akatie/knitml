package com.knitml.renderer.chart;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import com.knitml.core.common.KnittingShape;
import com.knitml.core.common.Side;
import com.knitml.core.model.operations.DiscreteInlineOperation;
import com.knitml.core.model.operations.inline.OperationGroup;

/**
 * <p>
 * A Chart represents the graphical layout of a set of operations in a set of
 * rows. It will have taken into account the layout of the stitches as they
 * relate to each other (i.e. wrong-side rows will already have had their
 * operations flipped and inverted). However, each operation is represented by a
 * "logical" symbol, not the actual symbol used for display in the chart. In
 * addition, the rows and stitches themselves are in logical order (i.e. the
 * first row in the list is the first row worked, not the last).
 * 
 * <p>
 * A {@link com.knitml.renderer.chart.writer.ChartWriter} will take this logical
 * graph of operations and display it according to the conventions of that
 * writer. Most {@link com.knitml.renderer.chart.writer.ChartWriter}
 * implementations will want to start at the end of the graph and work their way
 * backwards so that it displays like a "normal" chart.
 * 
 * @see com.knitml.renderer.chart.writer.ChartWriter
 * @author Jonathan Whitall (fiddlerpianist@gmail.com)
 * 
 */
/**
 * @author i264193
 *
 */
public class Chart {

	private KnittingShape shape = KnittingShape.FLAT;
	private int startingRowNumber = 1;
	private int width;
	private Side startingSide = Side.RIGHT;
	private List<List<ChartElement>> graph;
	private Map<ChartElement, DiscreteInlineOperation> legend = new EnumMap<ChartElement, DiscreteInlineOperation>(
			ChartElement.class);
	private String title;

	public Map<ChartElement, DiscreteInlineOperation> getLegend() {
		return legend;
	}

	public Chart() {
		this.graph = new ArrayList<List<ChartElement>>();
	}

	public Chart(List<List<ChartElement>> graph) {
		this.graph = graph;
	}

	public void addToLegend(ChartElement chartElement,
			DiscreteInlineOperation operation) {
		if (!legend.containsKey(chartElement)) {
			DiscreteInlineOperation singleOp = operation;
			if (!(operation instanceof OperationGroup)) {
				singleOp = operation.canonicalize().get(0);
			}
			legend.put(chartElement, singleOp);
		}
	}

	public KnittingShape getShape() {
		return shape;
	}

	public void setShape(KnittingShape shape) {
		this.shape = shape;
	}

	public int getStartingRowNumber() {
		return startingRowNumber;
	}

	public void setStartingRowNumber(int startingRowNumber) {
		this.startingRowNumber = startingRowNumber;
	}

	public Side getStartingSide() {
		return startingSide;
	}

	public void setStartingSide(Side startingSide) {
		this.startingSide = startingSide;
	}

	public List<List<ChartElement>> getGraph() {
		return graph;
	}

	public void setGraph(List<List<ChartElement>> graph) {
		this.graph = graph;
	}

	public void addRow(List<ChartElement> row) {
		this.graph.add(row);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

}
