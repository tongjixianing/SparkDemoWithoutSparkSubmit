package com.datastax.support;

/**
 * Created by Mike Zhang on 28/3/2023.
 */

public class SparkCassandraDemo
{
    public static void main(String args[]) {
        SparkCassandraConnector sparkCassandraConnector = new SparkCassandraConnector();
        sparkCassandraConnector.getCassandraTableRows(sparkCassandraConnector.getSparkContext());
    }
}
