package hu.kg.descriptor;

import java.util.Arrays;

import hu.kg.math.VectorUtil;


public class Triangle {

	public Vertex a;
	public Vertex b;
	public Vertex c;
	
	
	public Triangle(double[] a, double[] b, double[] c) {
		super();
		this.a = new Vertex(a);
		this.b = new Vertex(b);
		this.c = new Vertex(c);
		
	}

	
	
	public Triangle(Vertex a, Vertex b, Vertex c) {
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
		
		
		return new Triangle(a.copy(), b.copy(), c.copy());
		
	}

	public void translate(double[] t) {
	
		a.translate(t);
		b.translate(t);
		c.translate(t);
		
	}



	public void scaleAllCoords(double scale) {
	
		a.coords[0]*=scale;
		a.coords[1]*=scale;
		a.coords[2]*=scale;
		
		b.coords[0]*=scale;
		b.coords[1]*=scale;
		b.coords[2]*=scale;
		
		c.coords[0]*=scale;
		c.coords[1]*=scale;
		c.coords[2]*=scale;
		
				
	}
	
	public Triangle rotate(double[] axis, double rad) {
            double[] rrx = VectorUtil.rotate(a.coords, axis, rad);
		    double[] rry = VectorUtil.rotate(b.coords, axis, rad);
		    double[] rrz = VectorUtil.rotate(c.coords, axis, rad);
		    return new Triangle(rrx,rry,rrz);
	}



	public void paraScale(double[] bcVector, double paraScale) {
		a=new Vertex(VectorUtil.paralellScale(a.coords,bcVector,paraScale));
		b=new Vertex(VectorUtil.paralellScale(b.coords,bcVector,paraScale));
		c=new Vertex(VectorUtil.paralellScale(c.coords,bcVector,paraScale));
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append(a+" | "+b+" | "+c );
		return sb.toString();
		
		
	}
	
	
}