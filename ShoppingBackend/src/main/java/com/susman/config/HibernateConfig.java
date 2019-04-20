package com.susman.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
@ComponentScan(basePackages = { "com.susman" })
@EnableTransactionManagement
public class HibernateConfig {
	private final static String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final static String DATABASE_DRIVER_CLASS = "oracle.jdbc.driver.OracleDriver";
	private final static String DATABASE_USERNAME = "system";
	private final static String DATABASE_PASSWORD = "manager";
	private final static String DATABASE_DIALECT = "org.hibernate.dialect.Oracle10gDialect";

	@Bean("dataSource")
	public DataSource getDataSource() throws PropertyVetoException {
		DriverManagerDataSource dmds=new DriverManagerDataSource();
		dmds.setDriverClassName(DATABASE_DRIVER_CLASS);
		dmds.setUrl(DATABASE_URL);
		dmds.setUsername(DATABASE_USERNAME);
		dmds.setPassword(DATABASE_PASSWORD);
		
		ComboPooledDataSource cpds=new ComboPooledDataSource();
		cpds.setUser(DATABASE_USERNAME);
		cpds.setPassword(DATABASE_PASSWORD);
		cpds.setDriverClass(DATABASE_DRIVER_CLASS);
		cpds.setJdbcUrl(DATABASE_URL);
		
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(DATABASE_DRIVER_CLASS);
		bds.setUrl(DATABASE_URL);
		bds.setUsername(DATABASE_USERNAME);
		bds.setPassword(DATABASE_PASSWORD);
		return dmds;
	}

	@Bean
	public SessionFactory getSessionFactory(DataSource ds) {
		LocalSessionFactoryBuilder lsfb = new LocalSessionFactoryBuilder(ds);
		lsfb.addProperties(getHibernateProperty());
		lsfb.scanPackages("com.susman.dto");
		return lsfb.buildSessionFactory();
	}

	private Properties getHibernateProperty() {
		Properties p = new Properties();
		p.put("hibernate.dialect", DATABASE_DIALECT);
		p.put("hibernate.show_sql", "true");
		p.put("hibernate.format_sql", "true");
		p.put("hibernate.hbm2ddl.auto", "update");
		return p;
	
	}

	@Bean
	public HibernateTransactionManager getTransactionManager(SessionFactory factory) {
		HibernateTransactionManager mgr = new HibernateTransactionManager(factory);
		return mgr;
	}

}
