package com.flyinghead.vf4;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.flyinghead.vf4.db.Match;
import com.flyinghead.vf4.db.Player;

@Configuration
@EnableTransactionManagement
public class Vf4Config {

	@Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setAnnotatedClasses(Player.class, Match.class);
        sessionFactory.setHibernateProperties(hibernateProperties());

        return sessionFactory;
    }
	
    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        String dbPassword = System.getProperty("vf4.db.password", null);
        if (dbPassword == null) {
	        // test
	        dataSource.setDriverClassName("org.h2.Driver");
	        dataSource.setUrl("jdbc:h2:~/flycast-dev/vf4");
	        dataSource.setUsername("sa");
	        dataSource.setPassword("");
        }
        else {
        	// production
	        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://localhost:3306/vf4?allowPublicKeyRetrieval=true&useSSL=false");
	        dataSource.setUsername("vf4");
	        dataSource.setPassword(dbPassword);
        }
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    private final Properties hibernateProperties() {
    	Properties hibernateProperties = new Properties();
    	if (System.getProperty("vf4.db.password", null) == null) {
    		// test
    		hibernateProperties.setProperty("hibernate.hbm2ddl.auto", "update");
    		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
    		hibernateProperties.setProperty("hibernate.show_sql", "true");
    	}
    	else {
    		// production
    		hibernateProperties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
    	}

        return hibernateProperties;
    }

}
