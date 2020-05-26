package com.bolingcavalry.hellojib.database;

import com.bolingcavalry.hellojib.config.DatabaseShardingAlgorithm;
import com.bolingcavalry.hellojib.config.TableShardingAlgorithm;
import com.bolingcavalry.hellojib.constant.Constant;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.TableShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.keygen.DefaultKeyGenerator;
import com.dangdang.ddframe.rdb.sharding.keygen.KeyGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liaohua1
 * @date 2020/5/26 11:05
 */

@Configuration
public class DataSourceConfig {

    @Autowired
    private Database0Config database0Config;

    @Autowired
    private Database1Config database1Config;

    @Autowired
    private DatabaseShardingAlgorithm databaseShardingAlgorithm;

    @Autowired
    private TableShardingAlgorithm tableShardingAlgorithm;

    @Bean
    public KeyGenerator keyGenerator() {
        return new DefaultKeyGenerator();
    }

    @Bean
    public DataSource getDataSource() throws Exception {
        return buildDataSource();
    }

    private DataSource buildDataSource() throws Exception {
        // 分库设置，设置两个数据库
        Map<String, DataSource> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put(database0Config.getDatabaseName(), database0Config.createDataSource());
        dataSourceMap.put(database1Config.getDatabaseName(), database1Config.createDataSource());

        // 默认数据库
        DataSourceRule dataSourceRule = new DataSourceRule(dataSourceMap, database0Config.getDatabaseName());

        // 分表设置，大致思想就是将查询虚拟表Goods根据一定规则映射到真实表中去
        TableRule orderTableRule = TableRule.builder(Constant.LOGIC_TABLE)
                .actualTables(Arrays.asList(Constant.GOODS0, Constant.GOODS1))
                .dataSourceRule(dataSourceRule)
                .build();

        // 分库设置
        ShardingRule shardingRule = ShardingRule.builder()
                .dataSourceRule(dataSourceRule)
                .tableRules(Collections.singletonList(orderTableRule))
                .databaseShardingStrategy(new DatabaseShardingStrategy(Constant.GOODS_ID, databaseShardingAlgorithm))
                .tableShardingStrategy(new TableShardingStrategy(Constant.GOODS_TYPE, tableShardingAlgorithm))
                .build();

        return ShardingDataSourceFactory.createDataSource(shardingRule);
    }

}
