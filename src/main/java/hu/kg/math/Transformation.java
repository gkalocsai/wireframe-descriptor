package hu.kg.math;

public class Transformation {


	
	double[] trans1;
	double[] XYintersectionLine;
	double angleWithXY;	 

	double[] axisOfXYPlaneRotation;
	double angleOnXY;	
	
	double scaleOnX;
	
	double[] intersectionLine;
	double angleOfPlanes;	 
	
	double[] axisOfOnPlaneRotation;
	double angleBetweenSides;

	double[] trans2;
    double scale;
	


	public Transformation (Triangle from, Triangle to) {
		
		from=from.copy();
		to=to.copy();
		

		
		double scaleCA = VectorUtil.distanceOfPoints(to.a, to.c)/VectorUtil.distanceOfPoints(from.a, from.c);
		double scaleCB = VectorUtil.distanceOfPoints(to.b, to.c)/VectorUtil.distanceOfPoints(from.b, from.c)  ;

		this.scaleOnX=scaleCA/scaleCB;


		
		
		double[] mp = VectorUtil.midpoint(from.a, from.b);	 
		trans1=VectorUtil.scaledPoint(VectorUtil.ORIGO, mp, -1);
		from.translate(trans1);
		mp = VectorUtil.midpoint(to.a, to.b);	 
		this.trans2=mp;  
		to.translate(VectorUtil.scaledPoint(VectorUtil.ORIGO, mp, -1));


		this.scale =  VectorUtil.distanceOfPoints(to.a, to.b) /VectorUtil.distanceOfPoints(from.a, from.b) ;
	    System.out.println(this.scale);
	    
		    
	
		    

		double[] midT = VectorUtil.midpoint(to.a, to.b);
		double[] v1 = VectorUtil.vectorBetweenPoints(midT, to.a);
		double[] v2 = VectorUtil.vectorBetweenPoints(midT, to.c);
		double[] normT=VectorUtil.crossProduct3D(v1, v2);


		

		double[] mid = VectorUtil.midpoint(from.a, from.b);
		v1 = VectorUtil.vectorBetweenPoints(mid, from.a);
		v2 = VectorUtil.vectorBetweenPoints(mid, from.c);
		double[] norm = VectorUtil.crossProduct3D(v1, v2);
		
		intersectionLine = VectorUtil.crossProduct3D(norm, normT);
		this.angleOfPlanes=VectorUtil.signedAngle(norm, normT,intersectionLine);
		
	    double[] rx = VectorUtil.rotate(from.a, intersectionLine, angleOfPlanes);
	    double[] ry = VectorUtil.rotate(from.b, intersectionLine, angleOfPlanes);
	    double[] rz = VectorUtil.rotate(from.c, intersectionLine, angleOfPlanes);
	      
	    Triangle rotated= new Triangle(rx,ry,rz);
		
		
	    double[] midR = VectorUtil.midpoint(rotated.a, rotated.b);
	    double[] v1r = VectorUtil.vectorBetweenPoints(midR, rotated.a);
	    double[] v2r = VectorUtil.vectorBetweenPoints(midR, rotated.c);
	    double[] normR=VectorUtil.crossProduct3D(v1r, v2r);
	    
	    this.axisOfOnPlaneRotation=VectorUtil.scaledPoint(VectorUtil.ORIGO, normR, -1);
	    this.angleBetweenSides=-VectorUtil.signedAngle(to.c, rotated.c,axisOfOnPlaneRotation);
	    
	}
}
