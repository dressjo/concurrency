package ch5_5;


import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.mockito.Mockito;

public class FactorizerCached implements Servlet {
	
	private ServletConfig config;
	
	private final Computable<BigInteger, BigInteger[]> computable = new Computable<BigInteger, BigInteger[]>() {
		@Override
		public BigInteger[] compute(BigInteger arg) {
			return FactorizerCached.factor(arg);
		}
	};
	
	private final Computable<BigInteger, BigInteger[]> cache = new Memorizer<BigInteger, BigInteger[]>(
			computable);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		this.config = config;	
	}


	@Override
	public ServletConfig getServletConfig() {
		return config;
	}


	@Override
	public void service(ServletRequest req, ServletResponse resp)
			throws ServletException, IOException {
						
			BigInteger i = BigInteger.valueOf(new Random().nextInt()).abs();
			BigInteger[] value = cache.compute(i);
			for(BigInteger single : value) {
			   System.out.println(single);
			}
			
			// should encode response
		
	}


	@Override
	public String getServletInfo() {
		return "Factorizer servlet";
	}


	@Override
	public void destroy() {
	}
	
	private static final BigInteger[] factor(BigInteger arg) {
		BigInteger[] retVal = new BigInteger[2];
		retVal[0] = arg.add(BigInteger.valueOf(new Random().nextInt()));
		retVal[1] = arg.add(BigInteger.valueOf(new Random().nextInt()));
		return retVal;
	}
	
	public static void  getStubRequest() {
		return;
	}
	
	public static void  getStubResponse() {
		return;
	}
	
	public static void main(String[] args) {
		
		Servlet servlet = new FactorizerCached();
		ServletRequest request = Mockito.mock(ServletRequest.class);
		ServletResponse response = Mockito.mock(ServletResponse.class);
		
		try {
			servlet.service(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
