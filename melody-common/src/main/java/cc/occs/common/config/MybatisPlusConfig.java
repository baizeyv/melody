package cc.occs.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@MapperScan(value = {"cc.occs.**.mapper"})
@EnableTransactionManagement
public class MybatisPlusConfig {
}
