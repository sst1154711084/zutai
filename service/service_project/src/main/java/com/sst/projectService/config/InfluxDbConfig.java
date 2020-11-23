package com.sst.projectService.config;

import com.sst.commonutils.InfluxDbUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class InfluxDbConfig {
    @Value("${spring.influx.url:''}")
    private String influxDBUrl;

    @Value("${spring.influx.user:}")
    private String userName;

    @Value("${spring.influx.password:}")
    private String password;

    @Value("${spring.influx.database:''}")
    private String database;

    @Value("${spring.influx.retentionPolicy:''}")
    private String retentionPolicy;
    @Bean
    public InfluxDbUtils influxDbUtils() {
        return new InfluxDbUtils(userName, password, influxDBUrl, database, retentionPolicy);
    }
}
