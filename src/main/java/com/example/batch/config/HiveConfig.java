package com.example.batch.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class HiveConfig {



	@Value("${hive.username}")
	private String userName;

	@Value("${hive.password}")
	private String password;

	@Value("${hive.url}")
	private String hiveConnectionURL;
	
//	@Value("${hive.kerberos.keyTabLocation}")
//	private String keyTabLocation;
//
//	@Value("${hive.kerberos.krb5Location}")
//	private String krb5Location;
//
//	@Value("${hive.kerberos.principle}")
//	private String principle;

	public DataSource getHiveDataSource() throws IOException {

//		System.setProperty("javax.security.auth.useSubjectCredsOnly", "true");
//		System.setProperty("java.security.krb5.conf", this.krb5Location);
//		org.apache.hadoop.conf.Configuration conf = new org.apache.hadoop.conf.Configuration();
//		conf.set("hadoop.security.authentication", "kerberos");
//		UserGroupInformation.setConfiguration(conf);
//		UserGroupInformation.loginUserFromKeytab(this.principle, this.keyTabLocation);
		
//		org.apache.hive.jdbc.HiveDriver d;
		DataSource dataSource = DataSourceBuilder.create().
				driverClassName("org.apache.hive.jdbc.HiveDriver")
				.username(userName)
				.password(password)
				.url(hiveConnectionURL).build();

		return dataSource;
	}
	
	@Bean(name = "jdbcTemplate")
	JdbcTemplate getJDBCTemplate() throws IOException {
		return new JdbcTemplate(getHiveDataSource());
	}
}