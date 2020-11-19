package com.eventoapp.Interfaces;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("dev")
public class DataConfiguration {

	// Conexão com o banco de dados
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		// Definindo a url que vai ser utilizada:
		dataSource.setUrl("jdbc:mysql://localhost:3306/galaxyeventos?useSSL=false");
		// login e senha do banco de dados:
		dataSource.setUsername("root");
		dataSource.setPassword("galaxyeventos");
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		// Criando uma conexão com o hiibernate:
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		// Defininco qual database estamos utilizando:
		adapter.setDatabase(Database.MYSQL);
		adapter.setShowSql(true);
		// Permite que o hibernate crie as tabelas automaticamente:
		adapter.setGenerateDdl(true);
		// Definindo o tipo de "dialeto" a ser utilizado, no nosso caso "MySQLDialect":
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		// Permite que o hibernate prepare a conexão automaticamente:
		adapter.setPrepareConnection(true);
		return adapter;
	}
}