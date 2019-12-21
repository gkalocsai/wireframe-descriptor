package hu.kg.main;

import org.junit.Test;

public class UnitVectorTest {


	
	@Test
	public void unitVecTest() {
		
		
		double[] a = {3,4,6};
		double[] u=VectorUtil.unitVector(a );
		
		System.out.println(VectorUtil.distanceOfPoints(VectorUtil.ORIGO, u));
		
		
		
		
		
		
	}
	
	
}
