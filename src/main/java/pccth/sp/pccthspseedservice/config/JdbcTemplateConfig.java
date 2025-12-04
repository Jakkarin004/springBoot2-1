package pccth.sp.pccthspseedservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class JdbcTemplateConfig {

	@Bean
	@Primary
	public JdbcTemplate jdbcTemplateDatasource(DataSource ds) {
		return new JdbcTemplate(ds);
	}
}
