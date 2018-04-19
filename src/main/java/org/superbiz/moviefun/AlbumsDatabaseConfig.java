package org.superbiz.moviefun;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;
import org.superbiz.moviefun.albums.AlbumsBean;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
public class AlbumsDatabaseConfig {

    @Bean
    LocalContainerEntityManagerFactoryBean albumsEntityManagerFactory(DataSource albumsDataSource, HibernateJpaVendorAdapter jpaVendorAdapter) {
        LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
        factoryBean.setDataSource(albumsDataSource);
        factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
        factoryBean.setPackagesToScan(AlbumsBean.class.getPackage().getName());
        factoryBean.setPersistenceUnitName("albums");
        return factoryBean;
    }

    @Bean
    PlatformTransactionManager albumsPlatformTransactionManager(EntityManagerFactory albumsEntityManagerFactory){
        return new JpaTransactionManager(albumsEntityManagerFactory);
    }

    @Bean
    public TransactionTemplate albumsTransactionTemplate(PlatformTransactionManager albumsPlatformTransactionManager){
        return new TransactionTemplate(albumsPlatformTransactionManager);
    }



}
