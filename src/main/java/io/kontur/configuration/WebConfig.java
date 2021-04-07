package io.kontur.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;




@EnableJpaAuditing

@Configuration
@ComponentScan("io.kontur")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:message_source/configuration.properties")
public class WebConfig implements WebMvcConfigurer {
  @Value("${message_source.baseName}")
  private String baseNames;

  @Bean
  public MessageSource messageSource() {
    ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
    messageSource.setBasenames(baseNames);
    messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
    messageSource.setFallbackToSystemLocale(true);
    return messageSource;
  }
//
//  @Bean
//  public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
//    return new PersistenceExceptionTranslationPostProcessor();
//  }
//
//  @Bean
//  public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//    return new PropertySourcesPlaceholderConfigurer();
//  }
//
//  @Bean
//  @Primary
//  public PlatformTransactionManager getTransactionManager(
//      EntityManagerFactory entityManagerFactory) {
//    JpaTransactionManager transactionManager = new JpaTransactionManager();
//    transactionManager.setEntityManagerFactory(entityManagerFactory);
//    return transactionManager;
//  }
//
//  @Bean
//  public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
//        new LocalContainerEntityManagerFactoryBean();
//    entityManagerFactoryBean.setDataSource(dataSource);
//    entityManagerFactoryBean.setPackagesToScan("io.kontur");
//    JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//    entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
//    return entityManagerFactoryBean;
//  }
}
