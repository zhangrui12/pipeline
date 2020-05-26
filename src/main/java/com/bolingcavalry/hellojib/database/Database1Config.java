package com.bolingcavalry.hellojib.database;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * @author liaohua1
 * @date 2020/5/26 11:06
 */
@Data
@Component
@ConfigurationProperties(prefix = "database1")
public class Database1Config {
    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private String databaseName;

    public DataSource createDataSource() {
        DruidDataSource result = new DruidDataSource();
        result.setUsername(getUsername());
        result.setUrl(getUrl());
        result.setDriverClassName(getDriverClassName());
        result.setPassword(getPassword());
        return result;
    }

}
