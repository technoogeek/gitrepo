package com.greenello.app;

import java.io.IOException;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

public class WordCount {

  public static class Map extends MapReduceBase implements
  Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    public void map(LongWritable key, Text value,
    OutputCollector<Text, IntWritable> output, Reporter reporter)
    throws IOException {

      // value = dear bear river

      String line = value.toString();
      StringTokenizer tokenizer = new StringTokenizer(line);

      while (tokenizer.hasMoreTokens()) {
        value.set(tokenizer.nextToken());
        output.collect(value, new IntWritable(1));

        // // I am fine I am fine
        // v
        // I 1
        // am 1
        // fine 1
        // I 1
        // am 1
        // fine 1

        // I (1,1)
        // am (1,1)
        // fine (1,1)

      }

    }
  }

  public static class Reduce extends MapReduceBase implements
  Reducer<Text, IntWritable, Text, IntWritable> {

    // K, list (values)
    @Override
    public void reduce(Text key, Iterator<IntWritable> values,
    OutputCollector<Text, IntWritable> output, Reporter reporter)
    throws IOException {

      // key = I
      // values = (1,1,1,1,1)

      int sum = 0;
      while (values.hasNext()) {
        sum += values.next().get();
        // sum = sum + 1;
      }

      // beer,3

      output.collect(key, new IntWritable(sum));
      // hisham 4
      // hisham:4
    }
  }

  public static void main(String[] args) throws Exception {

    JobConf conf = new JobConf(WordCount.class);
    conf.setJobName("wordcount");

    // conf.set("mapred.textoutputformat.separator", ";");

    conf.setMapperClass(Map.class);
    conf.setReducerClass(Reduce.class);

    conf.setOutputKeyClass(Text.class);
    conf.setOutputValueClass(IntWritable.class);

    conf.setInputFormat(TextInputFormat.class);
    conf.setOutputFormat(TextOutputFormat.class);

    FileInputFormat.setInputPaths(conf, new Path(args[0]));
    FileOutputFormat.setOutputPath(conf, new Path(args[1]));

    JobClient.runJob(conf);

  }
}
