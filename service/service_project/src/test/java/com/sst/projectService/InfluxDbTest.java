package com.sst.projectService;

import com.sst.commonutils.DeviceInfo;
import com.sst.commonutils.InfluxDbUtils;
import org.influxdb.InfluxDB;
import org.influxdb.dto.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Random;

@SpringBootTest
class InfluxDbTest {
    @Autowired
    InfluxDbUtils influxDbUtils;

    @Test
    void testInsert() {
        Random random = new Random();
        InfluxDB influxDB = influxDbUtils.influxDbBuild();
        for (int i = 0; i < 100; i++) {
            DeviceInfo deviceInfo = DeviceInfo.builder().deviceId("device01")
                    .field("field2").value(random.nextDouble()).build();
            Point point = Point.measurementByPOJO(deviceInfo.getClass())
                    .addFieldsFromPOJO(deviceInfo)
                    .build();
            influxDB.write(influxDbUtils.database, influxDbUtils.getRetentionPolicy(), point);
        }
    }
    @Test
    void testBatchInsert() throws InterruptedException {
        Random random = new Random();
        InfluxDB influxDB = influxDbUtils.influxDbBuild();
        BatchPoints batchPoints = BatchPoints.database("zutai").consistency(InfluxDB.ConsistencyLevel.ONE).build();
        for (int i = 0; i < 100; i++) {
            DeviceInfo deviceInfo = DeviceInfo.builder().deviceId("device0" + random.nextInt(2))
                    .field("field" + random.nextInt(2))
                    .value(random.nextDouble()*100).build();
            Point point = Point.measurementByPOJO(deviceInfo.getClass())
                    .addFieldsFromPOJO(deviceInfo)
                    .build();
            influxDB.write(point);
        }
        influxDB.write(batchPoints);
    }

    @Test
    void queryDate(){
        InfluxDB influxDB = influxDbUtils.influxDbBuild();
        QueryResult queryResult = influxDB.query(new Query("SELECT * FROM device_info"));
        System.out.println(queryResult.getResults());
    }


}
