package hu.kg.descriptor;

import java.util.Arrays;

import hu.kg.math.VectorUtil;


public class Triangle {

	public double[] a;
	public double[] b;
	public double[] c;
	
	
	public Triangle(double[] a, double[] b, double[] c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	
	
	public static boolean isTriangle(double[] a, double[] b, double[] c) {
		double ab = VectorUtil.distanceOfPoints(a, b);
		double ac = VectorUtil.distanceOfPoints(a, c);
		double bc = VectorUtil.distanceOfPoints(b, c);
		
		if(ab+ac < bc ) return false;
		if(bc+ac < ab ) return false;
		if(ab+bc < ac ) return false;		
		return true;
	}

	public Triangle copy() {
		
		double[] a2 = Arrays.copyOf(a, 3);
		double[] b2 = Arrays.copyOf(b, 3);
		double[] c2 = Arrays.copyOf(c, 3);
			
	
		return new Triangle(a2, b2, c2);
	}

	public void translate(double[] t) {
	
		this.a=VectorUtil.translatePoint(a, t[0], t[1], t[2]);
		this.b=VectorUtil.translatePoint(b, t[0], t[1], t[2]);
		this.c=VectorUtil.translatePoint(c, t[0], t[1], t[2]);
		
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
	
	public Triangle rotate(double[] axis, double rad) {
            double[] rrx = VectorUtil.rotate(a, axis, rad);
		    double[] rry = VectorUtil.rotate(b, axis, rad);
		    double[] rrz = VectorUtil.rotate(c, axis, rad);
		    return new Triangle(rrx,rry,rrz);
	}



	public void paraScale(double[] bcVector, double paraScale) {
		a=VectorUtil.paralellScale(a,bcVector,paraScale);
		b=VectorUtil.paralellScale(b,bcVector,paraScale);
		c=VectorUtil.paralellScale(c,bcVector,paraScale);
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(a[0]+" "+a[1]+" "+a[2]+" | "+b[0]+" "+b[1]+" "+b[2]+" | "+c[0]+" "+c[1]+" "+c[2]);
		return sb.toString();
		
		
	}
	
	
}