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
import java.sql.PreparedStatement;

/**
 * @ClassName HdfsToMysql_02
 * @Description TODO
 * @Author Lin
 * @Date 2021/12/28 15:44
 * @Version 1.0
 **/
public class HdfsToMysql_02 {
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
        String sql = "insert into t_weather_count(stn,`year`,temp,max_temp,min_temp,rainfall,rainyDays) values (?,?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);

        /**
         * 从hdfs中读取数据,切分数据，插入MySQL
         */
        FSDataInputStream in = fs.open(new Path("/weather_count_output/part-r-00000"));
        BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));
        String line= null;
        while ((line=br.readLine())!=null ){
            String[] split = line.split("[ +\t]");
            ps.setString(1,split[0].substring(0, 6));
            ps.setString(2,split[0].substring(6));
            ps.setBigDecimal(3, new BigDecimal(split[1]));
            ps.setBigDecimal(4, new BigDecimal(split[2]));
            ps.setBigDecimal(5, new BigDecimal(split[3]));
            ps.setBigDecimal(6, new BigDecimal(split[4]));
            ps.setInt(7, Integer.parseInt(split[5]));
            ps.executeUpdate();
        }
        JdbcUtils.close(conn);
        ps.close();
        in.close();
        br.close();
    }
}
