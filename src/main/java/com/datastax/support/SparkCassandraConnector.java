package com.datastax.support;

import java.util.Arrays;
import java.util.Map;

import com.datastax.bdp.spark.DseSparkConfHelper;
import com.datastax.spark.connector.japi.CassandraJavaUtil;
import com.datastax.spark.connector.japi.CassandraRow;
import com.datastax.spark.connector.japi.rdd.CassandraTableScanJavaRDD;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import com.datastax.spark.connector.*;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * Created by Mike Zhang on 28/3/2023.
 */

public class SparkCassandraConnector
{
    public JavaSparkContext getSparkContext()
    {
        // Set up the DSE Spark configuration
        SparkConf conf = DseSparkConfHelper.enrichSparkConf(new SparkConf())
                                           .setAppName("DseExample")
                                           .setMaster("local[*]")
                                           .set("spark.cassandra.connection.host", "35.91.145.66");

        // Create a Spark context
        JavaSparkContext sc = new JavaSparkContext(conf);

        return sc;
    }

    public void getCassandraTableRows(JavaSparkContext sc){

        // Read data from DSE table
        CassandraTableScanJavaRDD<CassandraRow> rdd = CassandraJavaUtil.javaFunctions(sc)
                                                                       .cassandraTable("mykeyspace", "mytable")
                                                                       .select("c1", "c2", "c3")
                                                                       .where("c1 = ?", "1");

        // Print the data to the console
        rdd.foreach(row -> {
            int column1Value = row.getInt("c1");
            int column2Value = row.getInt("c2");
            System.out.println("column1Value: " + column1Value);
            System.out.println("column2Value: " + column2Value);
        });

        // Close the Spark context
        sc.stop();
    }
}
