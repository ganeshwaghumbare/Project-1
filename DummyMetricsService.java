/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coe.bigdata.mpoc.service;

import coe.bigdata.mpoc.model.AWSInfo;
import coe.bigdata.mpoc.model.CILog;
import coe.bigdata.mpoc.model.FileMetrics;
import coe.bigdata.mpoc.model.LatencyMetrics;
import coe.bigdata.mpoc.model.MainLogFact;
import coe.bigdata.mpoc.model.SpkeyFact;
import coe.bigdata.mpoc.model.ThroughputMetrics;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yogeshk
 */
public class DummyMetricsService implements MetricsService {

    @Override
    public List<FileMetrics> getFileMetrics() {
        List<FileMetrics> items = new ArrayList<>();
        items.add(new FileMetrics(System.currentTimeMillis(), "SomeFileName1", 256.987, 5.987));
        items.add(new FileMetrics(System.currentTimeMillis(), "SomeFileName2", 256.876, 6.876));
        items.add(new FileMetrics(System.currentTimeMillis(), "SomeFileName3", 256.654, 4.678));
        items.add(new FileMetrics(System.currentTimeMillis(), "SomeFileName4", 256.876, 5.8765));
        items.add(new FileMetrics(System.currentTimeMillis(), "SomeFileName5", 256.678, 4.987));
        items.add(new FileMetrics(System.currentTimeMillis(), "SomeFileName6", 25.9876, 3.1));
        return items;
    }

    @Override
    public List<CILog> getLastNCILogs(int count) {
        List<CILog> items = new ArrayList<>();
        double latency[] = {2.234, 3.876, 2.896, 4.098, 3.456, 
            1.276, 2.987, 3,065, 2.234, 3.876, 
            2.896, 4.887, 3.955, 1.777, 2.543};
        for (int i=1; i<=count;i++) {
            items.add(new CILog(System.currentTimeMillis(), System.currentTimeMillis(), latency[i], "spkey-"+i, "mapi-"+i, "type-"+i, "ip-"+i));
        }
        return items;
    }

    @Override
    public List<CILog> findCILogsByLogTime(long startTime, long endTime, int limit) {
        return getLastNCILogs(10);
    }

    @Override
    public List<MainLogFact> getMainLogFacts() {
        List<MainLogFact> items = new ArrayList<>();
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "ABC", "spkey-1", "100", 1065));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "ABC", "spkey-1", "200", 2065));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "ABC", "spkey-1", "300", 1098));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test","ABC", "spkey-1", "400", 1119));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "ABC", "spkey-1", "500", 1234));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "IBN", "spkey-2", "100", 2345));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "IBN", "spkey-2", "200", 3123));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "IBN", "spkey-2", "300", 23));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "IBN", "spkey-2", "400", 5986));
        items.add(new MainLogFact(System.currentTimeMillis(),"test","test", "IBN", "spkey-2", "500", 1065));
        return items;
    }

    @Override
    public List<SpkeyFact> getLastNMinSpkeyFacts(int count) {
        List<SpkeyFact> items = new ArrayList<>();
        for (int i=1;i<=count;i++) {
            long aggregationTime = System.currentTimeMillis();
            long databaseTime = System.currentTimeMillis();
            double latency = (databaseTime - aggregationTime) / 1000.0;
            items.add(new SpkeyFact(aggregationTime, "spkey-"+i, String.valueOf((i%3)*100+100),(i%4)*231+431, databaseTime, latency));
        }
        return items;
    }
/*
    @Override
    public List<SpkeyFact> findSpkeyFacts(long startTime, long endTime, int limit) {
        return getLastNMinSpkeyFacts(5);
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(long startTime, long endTime, String spkey, int limit) {
        return getLastNMinSpkeyFacts(10);
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(long startTime, long endTime, String spkey, String status, int limit) {
        return getLastNMinSpkeyFacts(15);
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(String spkey, int limit) {
        return getLastNMinSpkeyFacts(7);
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(String spkey, String status, int limit) {
        return getLastNMinSpkeyFacts(8);
    }*/

    @Override
    public List<LatencyMetrics> getLatencyMetricsAfterId(long id) {
        List<LatencyMetrics> items = new ArrayList<>();
        long time = System.currentTimeMillis();
        items.add(new LatencyMetrics(1, time, 2.1, "Kafka.consumer.WritetMsgToLocalDisk"));
        items.add(new LatencyMetrics(2, time+5000, 2.3, "KafkaConsumerSpout"));
        items.add(new LatencyMetrics(3, time+10000, 2.0, "Kafka.consumer.WritetMsgToLocalDisk"));
        items.add(new LatencyMetrics(4, time+15000, 1.1, "KafkaConsumerSpout"));
        items.add(new LatencyMetrics(5, time+20000, 3.1, "Kafka.consumer.WritetMsgToLocalDisk"));
        items.add(new LatencyMetrics(6, time+25000, 2.1, "KafkaConsumerSpout"));
        items.add(new LatencyMetrics(7, time+30000, 4.1, "Kafka.consumer.WritetMsgToLocalDisk"));
        items.add(new LatencyMetrics(8, time+35000, 5.1, "KafkaConsumerSpout"));
        
        items.add(new LatencyMetrics(1, time, 2.1, "Kafka.Consumer.Cassandra"));
        items.add(new LatencyMetrics(2, time+5000, 2.3, "OneMinuteAggregatorBolt"));
        items.add(new LatencyMetrics(3, time+10000, 2.0, "Kafka.Consumer.Cassandra"));
        items.add(new LatencyMetrics(4, time+15000, 1.1, "OneMinuteAggregatorBolt"));
        items.add(new LatencyMetrics(5, time+20000, 3.1, "Kafka.Consumer.Cassandra"));
        items.add(new LatencyMetrics(6, time+25000, 2.1, "OneMinuteAggregatorBolt"));
        items.add(new LatencyMetrics(7, time+30000, 4.1, "Kafka.Consumer.Cassandra"));
        items.add(new LatencyMetrics(8, time+35000, 5.1, "OneMinuteAggregatorBolt"));
        return items;
    }

    @Override
    public List<ThroughputMetrics> getThroughputMetricsForTime(long time) {
        List<ThroughputMetrics> metrics = new ArrayList<>();
        metrics.add(new ThroughputMetrics(time, time-100, time+200, 500, "Kafka.consumer.WritetMsgToLocalDisk"));
        metrics.add(new ThroughputMetrics(time, time-150, time+250, 400, "Kafka.Consumer.Cassandra"));
        metrics.add(new ThroughputMetrics(time, time-50, time+150, 700, "KafkaConsumerSpout"));
        metrics.add(new ThroughputMetrics(time, time-100, time+200, 750, "OneMinuteAggregatorBolt"));
        return metrics;
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(long startTime, int limit) {
        return getLastNMinSpkeyFacts(7);
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(long startTime, String spkey, int limit) {
        return getLastNMinSpkeyFacts(7);
    }

    @Override
    public List<SpkeyFact> findSpkeyFacts(long startTime, String spkey, String status, int limit) {
     return getLastNMinSpkeyFacts(7);
    }

    @Override
    public List<AWSInfo> getAWSInfo() {
        System.out.println("In Metrics.getAWSInfo()...");
        List<AWSInfo> awsInfoList = new ArrayList<>();
        System.out.println("awsInfoList: "+awsInfoList);
        awsInfoList.add(new AWSInfo("1", "JMeter", "C1.xlarge", "7", "4VCPU, 8ECU", "7.5 GB", "2*40 GB SSD", "Medium", ""));
        awsInfoList.add(new AWSInfo("2", "Kafka", "M1.xlarge", "7", "4VCPU, 8ECU", "15 GB", "4*420 GB", "High", ""));
        awsInfoList.add(new AWSInfo("3", "Disk Writer", "C1.xlarge", "4", "4VCPU, 8ECU", "7.5 GB", "2*40 GB SSD", "Medium", ""));
        awsInfoList.add(new AWSInfo("4", "Cassandra Writer", "C1.xlarge", "1", "4VCPU, 8ECU", "7.5 GB", "2*40 GB SSD", "Medium", ""));
        return awsInfoList;
    }
}
