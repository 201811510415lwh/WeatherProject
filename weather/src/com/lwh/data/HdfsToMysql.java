package com.lwh.data;

import com.lwh.utils.JdbcUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @ClassName HdfsToMysql
 * @Description 把hdfs上的数据转存到MySQL数据库中
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
        Connection conn = JdbcUtils.getConnection();
        conn.setAutoCommit(false);
        String sql = "insert into t_weather(stn,yearmoda,temp,max_temp,min_temp,prcp,frshtt) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        /**
         * 从hdfs中读取数据,切分数据，插入MySQL
         */
        FSDataInputStream in = fs.open(new Path("/weather_output/part-r-00000"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line=null;
        int i=1;
        while ((line=br.readLine())!=null && i<= 8005){
            String[] split = line.split("[ +\t]");
            ps.setString(1,split[0]);
            ps.setString(2,split[1]);
            ps.setBigDecimal(3, new BigDecimal(split[2]));
            ps.setBigDecimal(4, new BigDecimal(split[3]));
            ps.setBigDecimal(5, new BigDecimal(split[4]));
            ps.setBigDecimal(6, new BigDecimal(split[5]));
            ps.setString(7,split[6]);

            ps.addBatch();


            if(i % 500 == 0 || i == 8005){
                //2.执行batch
                ps.executeBatch();
                //3.清空batch
                ps.clearBatch();
            }
            i++;
        }
        conn.commit();
        JdbcUtils.close(conn);
        ps.close();
        in.close();
        br.close();
    }

}
