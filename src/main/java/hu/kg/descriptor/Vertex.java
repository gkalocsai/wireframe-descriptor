package hu.kg.descriptor;

import hu.kg.math.Transformation;
import hu.kg.math.VectorUtil;

public class Vertex {

	
	double coords[];
   
	
	public Vertex( double x, double y, double z) {	
		coords=new double[3];
		coords[0] = x;
		coords[1] = y;
		coords[2] = z;
	}
	public Vertex(double[] coords) {
	    this(coords[0], coords[1], coords[2]);
		
	}
	public double getX() {
		return coords[0];
	}
	public double getY() {
		return coords[1];
	}
	public double getZ() {
		return coords[2];
	}
	
	public Vertex copy() {
		return new Vertex(coords);
	}
	
	public double[] getPoint() {
		return coords;
	}
	
	
	public void transform(Transformation trans) {
		
		translate(trans.trans0);
		paraScale(trans.bcVector, trans.paraScale);
		translate(trans.trans0Back);
		translate(trans.trans1);
		
		if(Math.abs(trans.angleOfPlanes) > 0.0000000001) rotateToSamePlane(trans.intersectionLine, trans.angleOfPlanes);
		if(Math.abs(trans.angleBetweenSides) > 0.0000000001) rotateOnPlane(trans.axisOfOnPlaneRotation, trans.angleBetweenSides);
		scalePoint(trans.scale);	
		translate(trans.trans2);	
	}

	
	public void translate(double[] offsetVector) {
		for(int i=0;i<coords.length;i++) {
			coords[i]+=offsetVector[i];
		}	
	}
	
	public void paraScale(double[] bcVector, double paraScale) {
		coords = VectorUtil.paralellScale(coords, bcVector,paraScale);	
	}
	
	public void rotateToSamePlane(double[] intersectionLine, double angleOfPlanes) {
		coords = VectorUtil.rotate(coords, intersectionLine, angleOfPlanes);		
	}
	
	public void rotateOnPlane(double[] axisOfOnPlaneRotation, double angleBetweenSides) {
		coords = VectorUtil.rotate(coords, axisOfOnPlaneRotation, angleBetweenSides);		
	}
	
	public void scalePoint(double scale) {
		coords = VectorUtil.scaledPoint(VectorUtil.ORIGO, coords, scale);
	}
	
	
	public String toString() {
		
	   return coords[0]+", "+coords[1]+", "+coords[2];
	}
}
