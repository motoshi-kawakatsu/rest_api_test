package hello.domain;

import java.net.URI;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PoolingDataSourceConfig {

	@Autowired
	DataSourceConfigProperties	prop;
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		BasicDataSource	dataSource = new BasicDataSource();

        String	username;
        String	password;
        String	url;

        String	databaseUrl = System.getenv("DATABASE_URL");
        if(databaseUrl != null) {
            URI		dbUri = URI.create(databaseUrl);

            url = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
            username = dbUri.getUserInfo().split(":")[0];
            password = dbUri.getUserInfo().split(":")[1];
        } else {
        	url = prop.getUrl();
        	username = prop.getUsername();
        	password = prop.getPassword();
        }

        dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setDefaultAutoCommit(false);
		dataSource.setMaxTotal(96);
		dataSource.setMaxIdle(16);
		dataSource.setMinIdle(0);
		dataSource.setMaxWaitMillis(60000);
		
		return dataSource;
	}
}
