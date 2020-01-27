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
	public double[] trans0Back;



	public Transformation (Triangle from, Triangle to) {
				
		from=from.copy();
		to=to.copy();
		
		this.trans0=VectorUtil.scaledPoint(from.c.getPoint(), VectorUtil.ORIGO, 1);
		this.trans0Back=new double[3];
		trans0Back[0] = -trans0[0];
		trans0Back[1] = -trans0[1];
		trans0Back[2] = -trans0[2];
		
		from.translate(trans0);
		
		

		double preferredScale = VectorUtil.distanceOfPoints(to.b.getPoint(), to.c.getPoint())/VectorUtil.distanceOfPoints(to.c.getPoint(),to.a.getPoint());   
		double currentScale = VectorUtil.distanceOfPoints(from.b.getPoint(), from.c.getPoint())/VectorUtil.distanceOfPoints(from.c.getPoint(),from.a.getPoint());
		this.paraScale = preferredScale/currentScale;
		
		this.bcVector = VectorUtil.vectorBetweenPoints(from.b.getPoint(), from.c.getPoint());
		from.paraScale(bcVector, paraScale);
		
		
		double[] mp = VectorUtil.midpoint(from.a.getPoint(), from.b.getPoint());	 
		trans1=VectorUtil.scaledPoint(VectorUtil.ORIGO, mp, -1);
		from.translate(trans1);
		mp = VectorUtil.midpoint(to.a.getPoint(), to.b.getPoint());	 
		this.trans2=mp;  
		to.translate(VectorUtil.scaledPoint(VectorUtil.ORIGO, mp, -1));


		this.scale =  VectorUtil.distanceOfPoints(to.a.getPoint(), to.c.getPoint()) /VectorUtil.distanceOfPoints(from.a.getPoint(), from.c.getPoint()) ;
		from.scaleAllCoords(scale);
	   
		double[] midT = VectorUtil.midpoint(to.a.getPoint(), to.b.getPoint());
		double[] v1 = VectorUtil.vectorBetweenPoints(midT, to.a.getPoint());
		double[] v2 = VectorUtil.vectorBetweenPoints(midT, to.c.getPoint());
		double[] normT=VectorUtil.crossProduct3D(v1, v2);

    	

		double[] mid = VectorUtil.midpoint(from.a.getPoint(), from.b.getPoint());
		v1 = VectorUtil.vectorBetweenPoints(mid, from.a.getPoint());
		v2 = VectorUtil.vectorBetweenPoints(mid, from.c.getPoint());
		double[] norm = VectorUtil.crossProduct3D(v1, v2);
		
		intersectionLine = VectorUtil.crossProduct3D(norm, normT);
		Triangle rotated=from;
		if(Math.abs(intersectionLine[0]) +Math.abs(intersectionLine[1])+Math.abs(intersectionLine[2]) >0.00000000001) {
			
			this.angleOfPlanes=VectorUtil.signedAngle(norm, normT,intersectionLine);
			double[] rx = VectorUtil.rotate(from.a.getPoint(), intersectionLine, angleOfPlanes);
			double[] ry = VectorUtil.rotate(from.b.getPoint(), intersectionLine, angleOfPlanes);
			double[] rz = VectorUtil.rotate(from.c.getPoint(), intersectionLine, angleOfPlanes);
			
			rotated= new Triangle(rx,ry,rz);
		}
		
		
	    double[] midR = VectorUtil.midpoint(rotated.a.getPoint(), rotated.b.getPoint());
	    double[] v1r = VectorUtil.vectorBetweenPoints(midR, rotated.a.getPoint());
	    double[] v2r = VectorUtil.vectorBetweenPoints(midR, rotated.c.getPoint());
	    double[] normR=VectorUtil.crossProduct3D(v1r, v2r);
	    
	    this.axisOfOnPlaneRotation=VectorUtil.scaledPoint(VectorUtil.ORIGO, normR, -1);
	    this.angleBetweenSides=-VectorUtil.signedAngle(to.c.getPoint(), rotated.c.getPoint(),axisOfOnPlaneRotation);
	    
	    this.angleBetweenSides=-VectorUtil.signedAngle(VectorUtil.vectorBetweenPoints(to.a.getPoint(), to.c.getPoint()), VectorUtil.vectorBetweenPoints(rotated.a.getPoint(), rotated.c.getPoint()),axisOfOnPlaneRotation);
	    
	}
}
