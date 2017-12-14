package fr.dta.Jdbc_spring.configuration;

import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.jolbox.bonecp.BoneCPDataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableTransactionManagement
public class JPAConfiguration {

	/**
	 * Configuration du pool avec BoneCP
	 */
	@Autowired
	private Environment env;
	
	
	


	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BoneCPDataSource dataSource = new BoneCPDataSource();
		dataSource.setDriverClass(env.getRequiredProperty("driverClass"));
		dataSource.setJdbcUrl(env.getRequiredProperty("jdbcUrl"));
		dataSource.setUsername(env.getRequiredProperty("jdbcUsername"));
		dataSource.setPassword(env.getRequiredProperty("jdbcPassword"));
		dataSource.setIdleConnectionTestPeriodInMinutes(Integer.parseInt(env.getRequiredProperty("idleConnectionTestPeriodInMinutes")));
		dataSource.setIdleMaxAgeInMinutes(Integer.parseInt(env.getRequiredProperty("idleMaxAgeInMinutes")));
		dataSource.setMaxConnectionsPerPartition(Integer.parseInt(env.getRequiredProperty("maxConnectionsPerPartition")));
		dataSource.setMinConnectionsPerPartition(Integer.parseInt(env.getRequiredProperty("minConnectionsPerPartition")));
		dataSource.setPartitionCount(Integer.parseInt(env.getRequiredProperty("partitionCount")));
		dataSource.setAcquireIncrement(Integer.parseInt(env.getRequiredProperty("acquireIncrement")));
		dataSource.setStatementsCacheSize(Integer.parseInt(env.getRequiredProperty("statementsCacheSize")));
		
		
		return dataSource;

	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}
	
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
	LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
	emf.setDataSource(dataSource());
	emf.setPackagesToScan(new String[] { "fr.dta.Jdbc_spring.model" });
	JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
	emf.setJpaVendorAdapter(vendorAdapter);
	emf.setJpaProperties(additionalProperties());
	return emf;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
	JpaTransactionManager transactionManager = new JpaTransactionManager();
	transactionManager.setEntityManagerFactory(emf);
	return transactionManager;
	}
	
	@Bean
	Properties additionalProperties() {
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.setProperty("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		properties.setProperty("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		properties.setProperty("hibernate.use_second_level_cache", env.getRequiredProperty("hibernate.use_second_level_cache"));
		return properties;
		}
	
	
}
