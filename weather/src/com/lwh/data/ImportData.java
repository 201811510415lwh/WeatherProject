package com.lwh.data;


import com.lwh.writable.WeatherWritable;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
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
 * @ClassName ImportData
 * @Description 读取并清洗数据，存入hdfs
 * @Author Lin
 * @Date 2021/12/17 17:36
 * @Version 1.0
 **/

public class ImportData {
    public static final String INPUT_PATH = "hdfs://a1:9000/weather";
    public static final String OUTPUT_PATH = "hdfs://a1:9000/weather_output";

    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Path outPut_Path = new Path(OUTPUT_PATH);
        FileSystem fs = FileSystem.get(new URI(INPUT_PATH), conf);
        if (fs.exists(outPut_Path)) {
            fs.delete(outPut_Path, true);
        }

        //mapreduce的执行环节
        Job job = Job.getInstance(conf, "weather");
        FileInputFormat.setInputPaths(job, INPUT_PATH);
        FileOutputFormat.setOutputPath(job, outPut_Path);
        job.setMapperClass(MyMapper.class);
//        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(WeatherWritable.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(WeatherWritable.class);
        job.setOutputValueClass(NullWritable.class);
//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(NullWritable.class);
//        job.setOutputKeyClass(Text.class);
//        job.setOutputValueClass(NullWritable.class);

        job.waitForCompletion(true);

    }


    static class MyMapper extends Mapper<LongWritable, Text, WeatherWritable, NullWritable> {

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
            String MODA = line.substring(18, 22).trim();
            String YEARMODA = YEAR + MODA;//年月日
            String TEMP = line.substring(24, 28).trim();//气温
            String DEWP = line.substring(35, 39).trim();//露点温度
            String SLP = line.substring(46, 52).trim();//海平面气压
            String STP = line.substring(57, 63).trim();//本站气压
            String VISIB = line.substring(68, 73).trim();//能见度
            String WDSP = line.substring(78, 83).trim();//风向风速
            String MXSPD = line.substring(88, 93).trim();//最大风速
            String GUST = line.substring(95, 100).trim();//阵风
            String MAX = line.substring(102, 108).trim();//最高温度
            String MIN = line.substring(110, 116).trim();//最低温度
            String PRCP = line.substring(118, 124).trim();//降水量
            PRCP = PRCP.replaceAll("[a-zA-Z]", "");
            BigDecimal rainfall = new BigDecimal(PRCP);
            String SNDP = line.substring(125, 130).trim();//雪深
            //标志当天是否发生了（1表示发生了，0表示没有发生）总共有六位数分别为:雾/雨/雪/冰雹/雷/台风
            String FRSHTT = line.substring(132, 138).trim();
            
            context.write(new WeatherWritable(STN,YEARMODA,new BigDecimal(TEMP),
                    new BigDecimal(MAX),new BigDecimal(MIN),
                    rainfall,FRSHTT),
                    NullWritable.get());
//            context.write(new Text(STN+YEARMODA+"\\t"+TEMP+MAX+MIN), NullWritable.get());
        }
    }


//    static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
//        @Override
//        protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context) throws IOException, InterruptedException {
//            long count = 0;
//            for (LongWritable v2 : v2s) {
//                count += v2.get();
//            }
//            context.write(k2, new LongWritable(count));
//        }
//    }


}
