package com.adcc.itext7.jfreechar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.*;
import java.io.File;

/**
 * Created by ZP on 2017/7/27.
 */
public class TestBar {
    public static void main(String[] args) throws Exception {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        ds.addValue(200, "POS", "一季度");
        ds.addValue(230, "CLS", "一季度");
        ds.addValue(280, "OFF", "一季度");
        ds.addValue(330, "ON", "一季度");

        ds.addValue(480, "POS", "二季度");
        ds.addValue(430, "CLS", "二季度");
        ds.addValue(320, "OFF", "二季度");
        ds.addValue(180, "ON", "二季度");

        ds.addValue(150, "POS", "三季度");
        ds.addValue(260, "CLS", "三季度");
        ds.addValue(390, "OFF", "三季度");
        ds.addValue(210, "ON", "三季度");

        String title = "前三季度用户报文流量统计" ;
        JFreeChart chart = ChartFactory.createBarChart3D(title, "季度", "流量(单位:万份)", ds, PlotOrientation.VERTICAL, true, false, false);

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

        ChartUtilities.saveChartAsJPEG(new File("src/main/resources/jfreechar/msgRateBar.jpeg"), chart, 600, 375);// 800 500
    }
}
