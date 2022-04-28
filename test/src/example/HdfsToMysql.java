package example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @ClassName HdfsToMysql
 * @Description TODO
 * @Author Lin
 * @Date 2021/12/17 14:48
 * @Version 1.0
 **/
public class HdfsToMysql {

    public static void main(String[] args) throws Exception{
        /**
         * 创建filesystem对象，连接hdfs
         */
        URI uri = new URI("hdfs://192.168.183.136:9000");
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(uri, conf);
        /**
         * 连接数据库
         */
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC","root","root");
        PreparedStatement ps = conn.prepareStatement("insert into wordcount(word,count) values (?,?)");

        /**
         * 从hdfs中读取数据,切分数据，插入MySQL
         */
        FSDataInputStream in = fs.open(new Path("/output/part-r-00000"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line=null;
        while ((line=br.readLine())!=null){
            String[] split = line.split("[ +\t]");
            ps.setString(1,split[0]);
            ps.setInt(2, Integer.parseInt(split[1]));
            ps.executeUpdate();
        }
        ps.close();
        in.close();
        br.close();

    }

}
