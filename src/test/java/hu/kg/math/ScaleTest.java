package hu.kg.math;

import org.junit.Test;

import hu.kg.math.Triangle;
import hu.kg.math.VectorUtil;

public class ScaleTest {
	
	@Test
	public void x() {
		
	    Triangle t1=init1();
	    
	    double X=0.0; 
    	double Y=0.0;
    	double Z=0.0;
    	
	    
	    Triangle t2=init2(t1,2, X, Y,Z, 0, 0, 0);		    
	    
	    
	    
	    double[] r = VectorUtil.scaledPoint(t1.a, t2.a, 0.5);
	    
	    
	    System.out.println(t1.a[0]+ " "+t1.a[1]+" "+t1.a[2]);
	    System.out.println(t2.a[0]+ " "+t2.a[1]+" "+t2.a[2]);
	    
	    System.out.println(r[0]+ " "+r[1]+" "+r[2]);
	    
	    
	    
		
	}

	
	private Triangle init1() {
		double[] a = {0,0.5,0};
		double[] b = {0.5,0,0};
	    double[] c = {0.3,0.3,0};
		
	    
	    
	    double[] mp = VectorUtil.midpoint(a, b);
	    
	    
	    
	    a=VectorUtil.translatePoint(a, -mp[0], -mp[1], -mp[2]);
	    b=VectorUtil.translatePoint(b, -mp[0], -mp[1], -mp[2]);
	    c=VectorUtil.translatePoint(c, -mp[0], -mp[1], -mp[2]);
	    
	    
		return new Triangle(a,b,c);
		
		
		
	}
	
	private Triangle init2(Triangle base, double scale, double X, double Y, double Z, double oX, double oY, double oZ) {
		
		
		Triangle t=base.copy();
		t.scaleAllCoords(scale);
		
		double[] am = t.a;
		double[] bm = t.b;
	    double[] cm = t.c;
						
	    double[] midPoint=VectorUtil.midpoint(am, bm);
		
	    
	    am=VectorUtil.translatePoint(am, -midPoint[0], -midPoint[1], -midPoint[2]);
	    bm=VectorUtil.translatePoint(bm, -midPoint[0], -midPoint[1], -midPoint[2]);
	    cm=VectorUtil.translatePoint(cm, -midPoint[0], -midPoint[1], -midPoint[2]);
		
	
	    
	    
	    double[] ar = am;
		double[] br = bm;
		double[] cr = cm;
		
			
	    ar = VectorUtil.rotateAroundOrigo(ar, X, Y, Z);
		br = VectorUtil.rotateAroundOrigo(br, X, Y, Z);
		cr = VectorUtil.rotateAroundOrigo(cr, X, Y, Z);
//			
						
		
		ar=VectorUtil.translatePoint(ar, oX, oY, oZ);
		br=VectorUtil.translatePoint(br, oX, oY, oZ);
		cr=VectorUtil.translatePoint(cr, oX, oY, oZ);
		
	
		return new Triangle(ar, br, cr);
	}


}
