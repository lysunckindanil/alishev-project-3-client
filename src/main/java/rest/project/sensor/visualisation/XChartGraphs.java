package rest.project.sensor.visualisation;

import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

public class XChartGraphs {
    public static void printGraph(XYChart chart, String seriesName, int[] xAxis, int[] yAxis) {
        chart.addSeries(seriesName, xAxis, yAxis);
        new SwingWrapper<>(chart).displayChart();
    }

    public static XYChart getGraph(String xAxisTitle, String yAxisTitle, int min, int max) {
        XYChart chart = new XYChartBuilder().xAxisTitle(xAxisTitle).yAxisTitle(yAxisTitle).build();
        chart.getStyler().setYAxisMin((double) min);
        chart.getStyler().setYAxisMax((double) max);
        return chart;
    }
}
