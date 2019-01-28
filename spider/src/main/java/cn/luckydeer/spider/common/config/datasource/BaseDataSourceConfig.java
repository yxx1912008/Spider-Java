package cn.luckydeer.spider.common.config.datasource;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 基础数据源配置
 * 主数据源
 * @author yuanxx
 * @version $Id: BaseDataSourceConfig.java, v 0.1 2019年1月26日 下午2:52:43 yuanxx Exp $
 */
@Configuration
@MapperScan(basePackages = "cn.luckydeer.spider.dao.cat", sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class BaseDataSourceConfig {

    @Value("${mybatis.base.mapper-locations}")
    private String baseMapperLocation;

    /**
     * 
     * 注解： @Primary 用于设置主数据源
     * 数据源前缀
     * @return
     * @author yuanxx @date 2019年1月26日
     */
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.base")
    @Primary
    public DataSource setDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 
     * 注解：
     * @param dataSource
     * @return
     * @author yuanxx @date 2019年1月28日
     */
    @Bean(name = "baseTransactionManager")
    @Primary
    public DataSourceTransactionManager setTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource)
                                                                                                     throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /** 设置配置文件路径  */
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(baseMapperLocation));
        return bean.getObject();
    }

    @Bean(name = "baseSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
                                                                                                                            throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
