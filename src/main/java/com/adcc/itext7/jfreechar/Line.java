package com.adcc.itext7.jfreechar;

import java.awt.Font;
import java.io.File;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 * Created by ZP on 2017/7/27.
 */
public class Line {
    public static void main(String[] args) throws Exception {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(2000, "IBM", "一季度");
        ds.addValue(2300, "ORACLE", "一季度");
        ds.addValue(2800, "JBOSS", "一季度");
        ds.addValue(3300, "用友", "一季度");

        ds.addValue(4800, "IBM", "二季度");
        ds.addValue(4300, "ORACLE", "二季度");
        ds.addValue(3200, "JBOSS", "二季度");
        ds.addValue(1800, "用友", "二季度");

        ds.addValue(1500, "IBM", "三季度");
        ds.addValue(2600, "ORACLE", "三季度");
        ds.addValue(3900, "JBOSS", "三季度");
        ds.addValue(2100, "用友", "三季度");

        String title = "前三季度各大公司JEE AS销量统计" ;
        JFreeChart chart = ChartFactory.createLineChart(title, "季度", "销量(单位:万台)", ds, PlotOrientation.VERTICAL, true, false, false);

        //中文
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));//大标题

        //提示条
        chart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));

        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        //域轴字体
        plot.getDomainAxis().setLabelFont(new Font("宋体", Font.BOLD, 18));
        plot.getDomainAxis().setTickLabelFont(new Font("宋体", Font.PLAIN, 15));//小标签字体

        //range
        plot.getRangeAxis().setLabelFont(new Font("宋体", Font.BOLD, 15));

        plot.setForegroundAlpha(0.6f);

        ChartUtilities.saveChartAsJPEG(new File("src/main/resources/jfreechar/line.jpg"), chart, 800, 500);
    }
}
