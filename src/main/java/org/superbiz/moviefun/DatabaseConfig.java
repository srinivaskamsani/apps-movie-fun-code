package org.superbiz.moviefun;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class DatabaseConfig {

    @Bean
    DatabaseServiceCredentials serviceCredentials(@Value("${VCAP_SERVICES}")String vcapServices){
        return new DatabaseServiceCredentials(vcapServices);
    }

    @Bean
    HibernateJpaVendorAdapter jpaVendorAdapter() {
        HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
        jpaVendorAdapter.setDatabase(Database.MYSQL);
        jpaVendorAdapter.setDatabasePlatform("org.hibernate.dialect.MySQL5InnoDBDialect");
        jpaVendorAdapter.setGenerateDdl(true);
        return jpaVendorAdapter;
    }

}


//    To enable the EntityManager used by your AlbumsBean and MoviesBean classes, you will do the following:
//
//        Declare a bean that returns a HibernateJpaVendorAdapter. This will be used as an argument when you create your EntityManager with your custom configuration.
//        Set it up with MYSQL database type.
//        Set the database platform to "org.hibernate.dialect.MySQL5Dialect".
//        Enable DDL Generation.
//
//        Declare a bean for each database that takes both a DataSource and a HibernateJpaVendorAdapter as arguments and returns a LocalContainerEntityManagerFactoryBean. This method:
//        sets the data source
//        sets the Jpa Vendor adapter
//        sets the packages to scan to the package of the repository (using setPackagesToScan)
//        sets a persistence unit name unique to each database
//
//        The LocalContainerEntityManagerFactoryBean will be used to create your EntityManagers.
