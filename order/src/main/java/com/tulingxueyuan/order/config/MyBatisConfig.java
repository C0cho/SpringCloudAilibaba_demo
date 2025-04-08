package com.tulingxueyuan.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.tulingxueyuan.order.mapper")   // 扫描Mapper接口，整合MyBatis
public class MyBatisConfig {

}
