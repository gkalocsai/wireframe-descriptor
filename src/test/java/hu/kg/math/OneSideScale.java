package hu.kg.math;

import org.junit.Test;

public class OneSideScale {

	
    @Test
    public void test() {
    	
    	double[] t1a= {0,0.5,0};
    	double[] t1b= {0.5,0,0};
    	double[] t1c= {0,0.0,0};
  
    	
      	double[] t2a= {0,0.5,0}; 
    	double[] t2b= {0.75,0,0};
    	double[] t2c= {0,0.0,0.0};
  
    	
    	 
        double scaleFactorWithVectorA = getScale(t1a, t1b, t1c, t2a, t2b, t2c);
	    System.out.println(scaleFactorWithVectorA);
	    
    	
    	
	    
	    double[] t22a = VectorUtil.scaledPoint(t2a, VectorUtil.vectorBetweenPoints(t2a, t2c), scaleFactorWithVectorA);
	    double[] t22b = VectorUtil.scaledPoint(t2b, VectorUtil.vectorBetweenPoints(t2b, t2c), scaleFactorWithVectorA);
	    double[] t22c = VectorUtil.scaledPoint(t2c, VectorUtil.vectorBetweenPoints(t2c, t2c), scaleFactorWithVectorA);
	    
	    
	    
	    
	    scaleFactorWithVectorA = getScale(t1a, t1b, t1c, t22a, t22b, t22c);
	    System.out.println(scaleFactorWithVectorA);
	    
    }

	private double getScale(double[] t1a, double[] t1b, double[] t1c, double[] t2a, double[] t2b, double[] t2c) {
		double acBCtarget = VectorUtil.distanceOfPoints(t2a, t2c) / VectorUtil.distanceOfPoints(t2b, t2c);
	    double acBCt = VectorUtil.distanceOfPoints(t1a, t1c) / VectorUtil.distanceOfPoints(t1b, t1c);
	    
	    double scaleFactorWithVectorA = acBCtarget/acBCt;
		return scaleFactorWithVectorA;
	}
}
