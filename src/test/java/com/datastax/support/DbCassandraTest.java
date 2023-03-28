package com.datastax.support;

import org.junit.jupiter.api.Test;

import javolution.testing.AssertionException;
import org.apache.spark.SparkException;
import org.apache.tinkerpop.gremlin.spark.structure.Spark;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Mike Zhang on 28/3/2023.
 */

class DbCassandraTest
{
  @Test
    void testConnection(){
      SparkCassandraConnector sparkCassandraConnector = new SparkCassandraConnector();
      assertThrows(SparkException.class,()->{
        sparkCassandraConnector.getSparkContext();
      });


  }
}