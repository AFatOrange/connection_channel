package com.connection.channel.main.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * sqlitel连接配置
 * @author AFatOrange
 *
 */
@Configuration//注册到springboot容器，相当于原来xml文件里的<beans>
//下面要进行扫包，目的是标清楚为谁添加的数据源，这样对应的包里函数执行数据库操作的时候，就知道要执行的数据库账号
//密码，表，以及事务处理之类的。
@MapperScan(basePackages= {"com.connection.channel.main.sqlite"},sqlSessionFactoryRef="sqliteSqlSessionFactory")
public class SQLiteDatasourceConfig {
	
	//获得sqlite文件路径
	@Value("${sqlite.spring.datasource.jdbc-url}")
	String url;
	@Value("${sqlite.spring.datasource.driver-class-name}")
	String driverClassName;
	@Bean(name="sqliteDataSource")//注入到这个容器
	//@Primary//primary是设置优先，因为有多个数据源，在没有明确指定用哪个的情况下，会用带有primary的，这个注解必须有一个数据源要添加
	public DataSource DataSource() {
		//return DataSourceBuilder.create().build();
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driverClassName);        
		dataSource.setUrl(url);        
		dataSource.setUsername("");        
		dataSource.setPassword("");         
		return dataSource;

	}

	@Bean(name="sqliteSqlSessionFactory")
	//@Primary
	//@Qualifier("xxx")的含义是告诉他使用哪个DataSource
	public SqlSessionFactory SqlSessionFactory(@Qualifier("sqliteDataSource") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean=new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		return bean.getObject();
	}
	
	@Bean(name="sqliteTransactionManager")//配置事务
	//@Primary
	public DataSourceTransactionManager TransactionManager(@Qualifier("sqliteDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
	
	@Bean(name="sqliteSqlSessionTemplate")
	//@Primary
	public SqlSessionTemplate SqlSessionTemplate(@Qualifier("sqliteSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}

