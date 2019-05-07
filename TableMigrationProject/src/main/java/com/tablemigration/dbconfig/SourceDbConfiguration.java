
  package com.tablemigration.dbconfig;
  
  import java.util.HashMap;
  
  import javax.sql.DataSource;
  
  import org.springframework.beans.factory.annotation.Autowired; import
  org.springframework.beans.factory.annotation.Configurable; import
  org.springframework.boot.context.properties.ConfigurationProperties; import
  org.springframework.context.annotation.Bean; import
  org.springframework.context.annotation.Primary; import
  org.springframework.context.annotation.PropertySource; import
  org.springframework.context.annotation.PropertySources; import
  org.springframework.core.env.Environment; import
  org.springframework.data.jpa.repository.config.EnableJpaRepositories; import
  org.springframework.jdbc.datasource.DriverManagerDataSource; import
  org.springframework.orm.jpa.JpaTransactionManager; import
  org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean; import
  org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter; import
  org.springframework.transaction.PlatformTransactionManager;
  
  @Configurable
  
  @PropertySources({@PropertySource("classpath:application.properties")})
  
  @EnableJpaRepositories( entityManagerFactoryRef = "dslEntityManager",
  transactionManagerRef = "dslTransactionManager", basePackages =
  {"com.tablemigration.employeeRepository"} ) public class
  SourceDbConfiguration {
  
  @Autowired private Environment environment;
  
  @Primary
  
  @Bean(name="sourceDataSource")
  
  @ConfigurationProperties(prefix = "source.sourcedatasource") public
  DataSource dslDataSource() { DriverManagerDataSource dataSource = new
  DriverManagerDataSource();
  dataSource.setDriverClassName(environment.getProperty(
  "source.sourcedatasource.driver-class-name"));
  dataSource.setUrl(environment.getProperty("source.sourcedatasource.url"));
  dataSource.setUsername(environment.getProperty(
  "source.sourcedatasource.username"));
  dataSource.setPassword(environment.getProperty(
  "source.sourcedatasource.password")); return dataSource; }
  
  @Bean public LocalContainerEntityManagerFactoryBean dslEntityManager() {
  LocalContainerEntityManagerFactoryBean local = new
  LocalContainerEntityManagerFactoryBean();
  local.setDataSource(dslDataSource()); local.setPackagesToScan(new String[]
  {"com.tablemigration.ModelandEntity"});
  local.setPersistenceUnitName("source_database");
  
  HibernateJpaVendorAdapter va = new HibernateJpaVendorAdapter();
  local.setJpaVendorAdapter(va);
  
  HashMap<String , Object> properties = new HashMap<String, Object>();
  
  properties.put("hibernate.dialect",environment.getProperty(
  "spring.jpa.properties.hibernate.dialect"));
  properties.put("hibernate.show_sql",
  environment.getProperty("spring.jpa.show_sql"));
  local.setJpaPropertyMap(properties); local.afterPropertiesSet(); return
  local; }
  
  public PlatformTransactionManager dslTransactionManager() {
  JpaTransactionManager jtm = new JpaTransactionManager();
  jtm.setEntityManagerFactory(dslEntityManager().getObject()); return jtm; } }
 