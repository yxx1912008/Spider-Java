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
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;

/**
 * 电影网站数据源配置
 * @author yuanxx
 * @version $Id: BaseDataSourceConfig.java, v 0.1 2019年1月26日 下午2:52:43 yuanxx Exp $
 */
@Configuration
@MapperScan(basePackages = "cn.luckydeer.spider.dao.movie", sqlSessionTemplateRef = "movieSqlSessionTemplate")
public class MovieDataSourceConfig {

    @Value("${mybatis.movie.mapper-locations}")
    private String movieMapperLocation;

    /**
     * 
     * 注解： @Primary 用于设置主数据源
     * 数据源前缀
     * @return
     * @author yuanxx @date 2019年1月26日
     */
    @Bean(name = "movieDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.movie")
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
    @Bean(name = "movieTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("movieDataSource") DataSource dataSource) {

        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "movieSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("movieDataSource") DataSource dataSource)
                                                                                                      throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        /** 设置配置文件路径  */
        bean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(movieMapperLocation));
        return bean.getObject();
    }

    @Bean(name = "movieSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("movieSqlSessionFactory") SqlSessionFactory sqlSessionFactory)
                                                                                                                             throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
