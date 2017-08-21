package com.adcc.itext7.jfreechar;

import java.awt.Font;
import java.io.File;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Created by ZP on 2017/7/27.
 */
public class Pie {
    public static void main(String[] args) throws Exception {
        //
        String title = "各大公司JEE AS市场占有率统计" ;
        DefaultPieDataset ds = new DefaultPieDataset();
        ds.setValue("IBM", 2000);
        ds.setValue("ORACLE", 3500);
        ds.setValue("JBOSS", 1570);
        ds.setValue("用友", 4400);
        JFreeChart chart = ChartFactory.createPieChart3D(title, ds, true, false, false);

        //中文
        chart.getTitle().setFont(new Font("宋体", Font.BOLD, 25));//标题字体
        chart.getLegend().setItemFont(new Font("宋体", Font.PLAIN, 18));

        //绘图区
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setLabelFont(new Font("宋体", Font.PLAIN, 15));

        //背景
        //chart.setBackgroundImage(ImageIO.read(new File("f:/sunset.jpg")));//图表区背景
        //plot.setBackgroundImage(ImageIO.read(new File("f:/water.jpg")));

        //设置分裂效果
        plot.setExplodePercent("IBM", 0.1f);
        plot.setExplodePercent("JBOSS", 0.2f);

        //设置前景色透明度
        plot.setForegroundAlpha(0.7f);

        //设置标签生成器
        //{0}:公司名称
        //{1}:销量
        //{2}:百分比
        //{3}:总量
        //{4}:
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}({1}/{3}-{2})"));
        ChartUtilities.saveChartAsJPEG(new File("src/main/resources/jfreechar/pie.jpg"), chart, 800, 500);
    }
}
