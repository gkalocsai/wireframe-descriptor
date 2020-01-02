package hu.kg.math;

import hu.kg.descriptor.Triangle;

public class Transformation {



	public double[] trans0;
	public double paraScale;
	public double[] bcVector;
	
	
	public double[] trans1;

	public double scaleOnX;
	
	public double[] intersectionLine;
	public double angleOfPlanes;	 
	
	public double[] axisOfOnPlaneRotation;
	public double angleBetweenSides;

	public double[] trans2;
    public double scale;



	public Transformation (Triangle from, Triangle to) {
				
		from=from.copy();
		to=to.copy();
		
		this.trans0=VectorUtil.scaledPoint(from.c, VectorUtil.ORIGO, 1);
		from.translate(trans0);
		
		

		double preferredScale = VectorUtil.distanceOfPoints(to.b, to.c)/VectorUtil.distanceOfPoints(to.c,to.a);   
		double currentScale = VectorUtil.distanceOfPoints(from.b, from.c)/VectorUtil.distanceOfPoints(from.c,from.a);
		this.paraScale = preferredScale/currentScale;
		
		this.bcVector = VectorUtil.vectorBetweenPoints(from.b, from.c);
		from.paraScale(bcVector, paraScale);
		
		
		double[] mp = VectorUtil.midpoint(from.a, from.b);	 
		trans1=VectorUtil.scaledPoint(VectorUtil.ORIGO, mp, -1);
		from.translate(trans1);
		mp = VectorUtil.midpoint(to.a, to.b);	 
		this.trans2=mp;  
		to.translate(VectorUtil.scaledPoint(VectorUtil.ORIGO, mp, -1));


		this.scale =  VectorUtil.distanceOfPoints(to.a, to.c) /VectorUtil.distanceOfPoints(from.a, from.c) ;
		from.scaleAllCoords(scale);
	   
		double[] midT = VectorUtil.midpoint(to.a, to.b);
		double[] v1 = VectorUtil.vectorBetweenPoints(midT, to.a);
		double[] v2 = VectorUtil.vectorBetweenPoints(midT, to.c);
		double[] normT=VectorUtil.crossProduct3D(v1, v2);

    	

		double[] mid = VectorUtil.midpoint(from.a, from.b);
		v1 = VectorUtil.vectorBetweenPoints(mid, from.a);
		v2 = VectorUtil.vectorBetweenPoints(mid, from.c);
		double[] norm = VectorUtil.crossProduct3D(v1, v2);
		
		intersectionLine = VectorUtil.crossProduct3D(norm, normT);
		Triangle rotated=from;
		if(Math.abs(intersectionLine[0]) +Math.abs(intersectionLine[1])+Math.abs(intersectionLine[2]) >0.00000000001) {
			
			this.angleOfPlanes=VectorUtil.signedAngle(norm, normT,intersectionLine);
			double[] rx = VectorUtil.rotate(from.a, intersectionLine, angleOfPlanes);
			double[] ry = VectorUtil.rotate(from.b, intersectionLine, angleOfPlanes);
			double[] rz = VectorUtil.rotate(from.c, intersectionLine, angleOfPlanes);
			
			rotated= new Triangle(rx,ry,rz);
		}
		
		
	    double[] midR = VectorUtil.midpoint(rotated.a, rotated.b);
	    double[] v1r = VectorUtil.vectorBetweenPoints(midR, rotated.a);
	    double[] v2r = VectorUtil.vectorBetweenPoints(midR, rotated.c);
	    double[] normR=VectorUtil.crossProduct3D(v1r, v2r);
	    
	    this.axisOfOnPlaneRotation=VectorUtil.scaledPoint(VectorUtil.ORIGO, normR, -1);
	    this.angleBetweenSides=-VectorUtil.signedAngle(to.c, rotated.c,axisOfOnPlaneRotation);
	    
	    this.angleBetweenSides=-VectorUtil.signedAngle(VectorUtil.vectorBetweenPoints(to.a, to.c), VectorUtil.vectorBetweenPoints(rotated.a, rotated.c),axisOfOnPlaneRotation);
	    
	}
}
