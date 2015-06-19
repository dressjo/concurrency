package ch3_1;

import home.jd.common.db.CommonDBConfig;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ThreadLocalTest {

	private static ApplicationContext ctx;

	public static void main(String[] args) {

	
		ctx = new AnnotationConfigApplicationContext(
				CommonDBConfig.class);
		
		DataSource ds = ctx.getBean(DataSource.class);
		ThreadLocalResource.setDataSource(ds);
		
		new Thread(new ThreadLocalRunnable("Number 1")).start();
		new Thread(new ThreadLocalRunnable("Number 2")).start();
		
	}

}
