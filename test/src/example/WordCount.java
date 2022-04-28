package example;

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
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @ClassName WordCount
 * @Description 测试MapReduce
 * @Author Lin
 * @Date 2021/12/16 21:35
 * @Version 1.0
 **/
public class WordCount {

    public static final String INPUT_PATH = "hdfs://a1:9000/testMR";
    public static final String OUTPUT_PATH = "hdfs://a1:9000/output";

    public static void main(String[] args) throws URISyntaxException, IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Path outPut_Path = new Path(OUTPUT_PATH);
        FileSystem fs = FileSystem.get(new URI(INPUT_PATH), conf);
        if (fs.exists(outPut_Path)) {
            fs.delete(outPut_Path, true);
        }

        //mapreduce的执行环节
        Job job = Job.getInstance(conf, "WordCount");
        FileInputFormat.setInputPaths(job, INPUT_PATH);
        FileOutputFormat.setOutputPath(job, outPut_Path);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        job.waitForCompletion(true);

    }


    static class MyMapper extends Mapper<LongWritable, Text, Text, LongWritable> {//分别是k1，v1，k2，v2的数据类型

        @Override
        protected void map(LongWritable k1, Text v1, Context context) throws IOException, InterruptedException {
            String[] splits = v1.toString().split(" ");
            for (String split : splits) {
                context.write(new Text(split), new LongWritable(1));
            }
        }
    }


    static class MyReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
        @Override
        protected void reduce(Text k2, Iterable<LongWritable> v2s, Context context) throws IOException, InterruptedException {
            long count = 0;
            for (LongWritable v2 : v2s) {
                count += v2.get();
            }
            context.write(k2, new LongWritable(count));
        }
    }

}
