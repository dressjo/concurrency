package home.jd.common.db;

import home.jd.common.db.sample.SampleTestBean;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
@ComponentScan(value = "home.jd.common.db.sample")
public class CommonDBConfig {
	
	@Bean
	public SampleTestBean getSpringTestBean() {
		return new SampleTestBean("SampleTestBean");
	}
	
	@Bean(destroyMethod = "shutdown")
	public DataSource getDataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
				.addScript("schema.sql").addScript("test-data.sql").build();
		return db;
	}

	@Bean
	public JdbcTemplate setDataSource(DataSource dataSource) {
		return new JdbcTemplate(getDataSource());
	}

}
