package home.jd.common.db;

import static java.lang.System.out;
import home.jd.common.db.sample.Sample;
import home.jd.common.db.sample.SampleDao;
import home.jd.common.db.sample.SampleTestBean;
import home.jd.common.db.sample.SampleTestComponentBean;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class SpringCoreTest {

	private static ApplicationContext ctx;

	public static void main(String[] args) {

		ctx = new AnnotationConfigApplicationContext(
				CommonDBConfig.class);
		SampleTestBean testBean = ctx.getBean(SampleTestBean.class);
		System.out.println(testBean.getName());

		DataSource ds = ctx.getBean(DataSource.class);
		try {
			out.println(ds.getConnection() != null ? "got connection"
					: "no connection");
		} catch (SQLException e) {
			out.println("Could open connection");
		}

		SampleTestComponentBean testComponentBean = ctx
				.getBean(SampleTestComponentBean.class);
		out.println(testComponentBean.getName());
		out.println(testComponentBean.getTestBeanName());

		SampleDao dao = ctx.getBean(SampleDao.class);
		for (Sample emp : dao.listEmployees()) {
			out.println(emp.getName());
		}
		
	}

}
