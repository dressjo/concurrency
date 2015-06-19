package home.jd.common.db.sample;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SampleDaoImpl implements SampleDao {
		
	@Autowired
	private JdbcTemplate jdbcTmplate;

	public List<Sample> listEmployees() throws DataAccessException {		
		
		RowMapper<Sample> mapper = new RowMapper<Sample>() {
			public Sample mapRow(ResultSet rs, int rowNum) throws SQLException {
				 Sample e = new Sample();		
				 e.setId(rs.getInt("EmployeE_ID"));
				 e.setName(rs.getString("Name"));
				 return e;
			}
		};
						
		return jdbcTmplate.query("select * from employees", mapper);
	}

}
