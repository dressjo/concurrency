package ch5_5;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<BigInteger,BigInteger>{

	@Override
	public BigInteger compute(BigInteger arg) {		
		 return new BigInteger(arg.toString());
	}

}
