package home.jd.common.db.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SampleTestComponentBean {
	
	private String name = "SpringTestComponentBean"; 
	
	@Autowired
	private SampleTestBean testBean;
	
	public SampleTestComponentBean() {
		
	}
	
	public SampleTestComponentBean(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getTestBeanName() {
		return testBean.getName();
	}

}
