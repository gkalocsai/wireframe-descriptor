package hu.kg.main;

import java.util.Arrays;
import java.util.PrimitiveIterator.OfDouble;
import java.util.Random;
import java.util.stream.DoubleStream;

import hu.kg.math.VectorUtil;



public class Triangle {

	double[] a;
	double[] b;
	double[] c;


	public Triangle(double[] a, double[] b, double[] c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}



	public void sortAB() {
		double ab = VectorUtil.distanceOfPoints(a, b);
		double ac = VectorUtil.distanceOfPoints(a, c);
		double bc = VectorUtil.distanceOfPoints(b, c);
		
		boolean clockwise=isClockwise(a,b,c);
		
		if(ab >= ac && ab >= bc) return;
		if(bc >= ac && bc >= ab) {
			double[] swap=c;
			c=a;
			a=swap;
			return;
		}else {
			double[] swap=c;
			c=b;
			b=swap;
		}
		
		if(clockwise !=isClockwise(a,b,c)) {
			double[] swap=a;
			a=b;
			b=swap;
		}
	}
	
	
	private boolean isClockwise(double[] a, double[] b, double[] c) {
		//  (b-a) x (c-b)
		double[] ab=VectorUtil.vectorBetweenPoints(a, b);
		double[] bc=VectorUtil.vectorBetweenPoints(b, c);
		double[] crossp=VectorUtil.crossProduct3D(ab, bc);
		
		return crossp[2] < 0;
	}



	public static Triangle randomTriangle(Random r) {

		DoubleStream ds = r.doubles(-1, 1);
		OfDouble it = ds.iterator();
		
		double[] ra = null; 
		double[] rb = null;
		double[] rc = null;
		

		while(ra == null || !isTriangle(ra,rb,rc)) {
			ra = createRandomPoint(it);
    		rb = createRandomPoint(it);
			rc= createRandomPoint(it);
		}

		double xOffset=it.nextDouble()*.9;
		double yOffset=it.nextDouble()*.9;
		double zOffset=it.nextDouble()*.9;
		
		ra=VectorUtil.translatePoint(ra, xOffset, yOffset, zOffset);
		rb=VectorUtil.translatePoint(rb, xOffset, yOffset, zOffset);
		rc=VectorUtil.translatePoint(rc, xOffset, yOffset, zOffset);
	
		return new Triangle(ra, rb, rc);
	}

	private static boolean isTriangle(double[] a, double[] b, double[] c) {
		double ab = VectorUtil.distanceOfPoints(a, b);
		double ac = VectorUtil.distanceOfPoints(a, c);
		double bc = VectorUtil.distanceOfPoints(b, c);
		
		if(ab+ac < bc ) return false;
		if(bc+ac < ab ) return false;
		if(ab+bc < ac ) return false;		
		return true;
	}



	private static double[] createRandomPoint(OfDouble it) {
		double[] r=new double[3];
		
		r[0]=it.nextDouble()/10;
		r[1]=it.nextDouble()/10;
		r[2]=it.nextDouble()/10;
		
		return r;
	}



	public Triangle copy() {
		
		double[] a2 = Arrays.copyOf(a, 3);
		double[] b2 = Arrays.copyOf(b, 3);
		double[] c2 = Arrays.copyOf(c, 3);
			
	
		return new Triangle(a2, b2, c2);
	}



	public void scaleAllCoords(double scale) {
		a[0]*=scale;
		a[1]*=scale;
		a[2]*=scale;
		
		b[0]*=scale;
		b[1]*=scale;
		b[2]*=scale;
		
		c[0]*=scale;
		c[1]*=scale;
		c[2]*=scale;
		
		
	}

}
