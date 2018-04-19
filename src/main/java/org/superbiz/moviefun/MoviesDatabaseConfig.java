package org.superbiz.moviefun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.superbiz.moviefun.movies.MoviesBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class MoviesDatabaseConfig {
    @Bean
    LocalContainerEntityManagerFactoryBean moviesEntityManagerFactory(DataSource moviesDataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(moviesDataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(MoviesBean.class.getPackage().getName());
        factoryBean.setPersistenceUnitName("movies");
        return factoryBean;
    }


    @Bean
    PlatformTransactionManager moviesPlatformTransactionManager(EntityManagerFactory moviesEntityManagerFactory){
        return new JpaTransactionManager(moviesEntityManagerFactory);
    }

    @Bean
    public TransactionTemplate moviesTransactionTemplate(PlatformTransactionManager moviesPlatformTransactionManager){
        return new TransactionTemplate(moviesPlatformTransactionManager);
    }
}
