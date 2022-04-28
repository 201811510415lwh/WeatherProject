package com.lwh.data;


import com.lwh.writable.WeatherCountWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ClassName WeatherCount
 * @Description 统计
 * @Author Lin
 * @Date 2021/12/27 15:34
 * @Version 1.0
 **/

public class WeatherCount {
    public static final String INPUT_PATH = "hdfs://a1:9000/weather";
    public static final String OUTPUT_PATH = "hdfs://a1:9000/weather_count_output";

    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Path outPut_Path = new Path(OUTPUT_PATH);
        FileSystem fs = FileSystem.get(new URI(INPUT_PATH), conf);
        if (fs.exists(outPut_Path)) {
            fs.delete(outPut_Path, true);
        }

        //mapreduce的执行环节
        Job job = Job.getInstance(conf, "weatherCount");
        FileInputFormat.setInputPaths(job, INPUT_PATH);
        FileOutputFormat.setOutputPath(job, outPut_Path);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(WeatherCountWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(WeatherCountWritable.class);

        job.waitForCompletion(true);

    }


    static class MyMapper extends Mapper<LongWritable, Text, Text, WeatherCountWritable> {

        @Override
        protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {
            String line = v1.toString();

            if(line.trim().length()==0) {
                return;
            }

            if(line.startsWith("STN")) {
                return;//忽略第一行标题头
            }

            String STN = line.substring(0, 6).trim();//气象站号
            String YEAR = line.substring(14, 18).trim();
            String TEMP = line.substring(24, 28).trim();//气温
            String MAX = line.substring(102, 108).trim();//最高温度
            String MIN = line.substring(110, 116).trim();//最低温度
            String PRCP = line.substring(118, 124).trim();//降水量
            PRCP = PRCP.replaceAll("[a-zA-Z]", "");
            BigDecimal rainfall = new BigDecimal(PRCP);
            //标志当天是否发生了（1表示发生了，0表示没有发生）总共有六位数分别为:雾/雨/雪/冰雹/雷/台风
            String FRSHTT = line.substring(132, 138).trim();
            String rainy = "010000";// 雨天格式
            // 转为二进制,参数"2"表示转为二进制数
            int a = Integer.parseInt(FRSHTT, 2);
            int b = Integer.parseInt(rainy, 2);
            int rainyDays = 0;
            // 按位与运算,结果与rainy对应二进制数相同则输出
            if ((a & b) == b) {//其实就是a的第二位数字要是1才说明是雨天
                rainyDays = 1;
            }

            context.write(new Text(STN+YEAR),new WeatherCountWritable(new BigDecimal(TEMP),
                    new BigDecimal(MAX),new BigDecimal(MIN),
                    rainfall,new Integer(rainyDays)));


        }
    }


    static class MyReducer extends Reducer<Text, WeatherCountWritable, Text, WeatherCountWritable> {
        @Override
        protected void reduce(Text k2, Iterable<WeatherCountWritable> v2s, Context context) throws IOException, InterruptedException {
            //求平均温
            double avgValue;//年度平均温度
            double sumValue= 0;//年度总和
            int count=0;
            double maxValue=0;//年度最高温度
            double minValue=1000;//年度最低温度
            double rainfall=0;//年度最高温度
            int sum = 0;//降雨天数
            for(WeatherCountWritable temp:v2s){
                sumValue += (temp.getTemp()).doubleValue();
                count++;
                //求最高温
                maxValue = Math.max(maxValue,(temp.getMax()).doubleValue());
                //求最低温
                minValue = Math.min(minValue,(temp.getMin()).doubleValue());
                //求年降雨量
                rainfall += (temp.getPrcp().doubleValue());
                //求年降雨天数
                sum += temp.getRainyDays();
            }
            avgValue = (sumValue / count);

            WeatherCountWritable weatherCountWritable = new WeatherCountWritable(
                    new BigDecimal(avgValue),new BigDecimal(maxValue),
                    new BigDecimal(minValue),new BigDecimal(rainfall),sum);
            context.write(k2, weatherCountWritable);

        }
    }


}
