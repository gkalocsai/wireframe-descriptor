package hu.kg.math;

public class VectorUtil {


	public static final double[] ORIGO= {0.0, 0.0, 0.0};


	public static double[] crossProduct3D(double[] a, double b[]) {
		double[] result=new double[3];

		result[0] = a[1]*b[2]-a[2]*b[1];
		result[1] = a[2]*b[0]-a[0]*b[2];
		result[2] = a[0]*b[1]-a[1]*b[0];
		return result;	
	}


	public static double dotProduct(double[] a, double[] b) {
		double result=0;
		for(int i=0;i<a.length;i++) {
			result+=a[i]*b[i];
		}
		return result;
	}


	public static double[] midpoint(double[] a, double[] b) {
		double[] result = new double[a.length];
		for(int i=0;i<a.length;i++) {
			result[i] = ( a[i]+b[i] )/2.0;
		}
		return result;
	}

	public static double squaredDistance(double[] a, double[] b) {
		double result = 0;
		for(int i=0;i<a.length;i++) {
			double k=( a[i]-b[i] );
			result+= k*k;
		}
		return result;
	}

	public static double distanceOfPoints(double[] a, double[] b) {
		return Math.sqrt(squaredDistance(a, b));
	}

	public static double vectorLength(double[] a) {
		return Math.sqrt(squaredDistance(a, ORIGO));
	}


	public static double signedAngle(double[] a, double[] b, double[] normal) {
		
		double[] crossP = crossProduct3D(a, b);
		normal=unitVector(normal);
		
		double dotp = dotProduct(crossP, normal);
		double dotpAB=dotProduct(a, b);
		
		return Math.atan2(dotp, dotpAB);
		
		
	}

	
	public static double[] vectorBetweenPoints(double[] a, double[] b) {
		double[] result = new double[a.length];
		for(int i=0;i<a.length;i++) {
			result[i] =  b[i]-a[i] ;
		}
		return result;

	}


	public static double[] translatePoint(double[] a, double x, double y,  double z) {
		double[] result = new double[a.length];

		result[0] =  a[0]+x;
		result[1] =  a[1]+y;
		result[2] =  a[2]+z;

		return result;

	}


	//ab oldal a leghosszabb

	public static double[] cutPoint(double[] a, double[] b, double[] c) {
		double[] result=new double[3];


		double ab=distanceOfPoints(a, b);
		double sab=squaredDistance(a, b);
		double sac=squaredDistance(a, c);
		double sbc=squaredDistance(b, c);

		double d=((sab+sac)-(sbc)) / (2*ab);

		//double d=cosA*ac;

		double v[]=vectorBetweenPoints(a, b);


		for(int i=0;i<v.length;i++) {	
			result[i]=(v[i]*d)/ab+a[i];
		}
		return result;
	}


	public static double[] scaledPoint(double[] a, double[] b, double scale) {

		double v[]=vectorBetweenPoints(a, b);
		
		double[] result = new double[3];
		for(int i=0;i<v.length;i++) {	
			result[i]=(v[i]*scale)+a[i];
		}
		return result;
	}
	
	
	public static double[] shrear(double[] a, double[] b, double scale) {
	    double v[]=vectorBetweenPoints(b, a);
		
		double[] result = new double[3];
		for(int i=0;i<v.length;i++) {	
			result[i]=(v[i]*scale)+a[i];
		}
		return result;
	
	
	}

	public static double[] rotateAroundOrigo(double[] a, double X, double Y, double Z) {
		
		double[] r = new double[3];

		double sx = Math.sin(X);
		double sy = Math.sin(Y);
		double sz = Math.sin(Z);
		
		double cx = Math.cos(X);
		double cy = Math.cos(Y);
		double cz = Math.cos(Z);
		
		
		r[0]= a[0]*(cy*cz+sx*sy*sz) + a[1]*(-cy*sz+cz*sx*sy)+a[2]*(cx*sy);
		
		r[1]= a[0]*(cx*sz) + a[1]*(cx*cz) +a[2]* (-sx);
		
		r[2]= a[0]*(-cz*sy+cy*sx*sz) + a[1]*(sy*sz+cy*cz*sx) +a[2]* (cx*cy);
		
		
		
		return r;
	}
	
	public static double[] unitVector(double[] a) {
		
		
		
		double length=distanceOfPoints(ORIGO, a);
		double[] r=new double[a.length];
		for(int i=0;i<a.length;i++) {
			r[i] = a[i]/length;
		}
		return r;
		
	}
    
	
	public static double[] rotate(double[] v, double[] axis, double rad) {
			
		double[] k=VectorUtil.unitVector(axis);
		double[] result = new double[3];
		
		double cosRad = Math.cos(rad);
		for(int i=0;i<v.length;i++) {
			result[i] = v[i]*cosRad;
		}
		
		
		double dP=VectorUtil.dotProduct(k, v);
		for(int i=0;i<k.length;i++) {
			result[i]+=(1-cosRad)*dP*k[i];
		}
		
		double[] cP=VectorUtil.crossProduct3D(k, v);
		
		for(int i=0;i<cP.length;i++) {
			result[i]+=Math.sin(rad) * cP[i];
		}

		return result;
	}
	
	public static boolean isClockwise(double[] a, double[] b, double[] c) {
		//  (b-a) x (c-b)
		double[] ab=VectorUtil.vectorBetweenPoints(a, b);
		double[] bc=VectorUtil.vectorBetweenPoints(b, c);
		double[] crossp=VectorUtil.crossProduct3D(ab, bc);
		
		return crossp[2] < 0;
	}


	
	public static double[] perp(double[] v, double[] otherVector) {
		
		double[] result = crossProduct3D(v, otherVector);
		double pLength = distanceOfPoints(ORIGO, otherVector);
		result[0]/=pLength; result[1]/=pLength; result[2]/=pLength;
		result = crossProduct3D(otherVector,result);
		result[0]/=pLength; result[1]/=pLength; result[2]/=pLength;
		return result;
	}
	
	public static double[] paralell(double[] v, double[] planeNormal) {
	  
		double[] result=new double[3];
		double pLength = distanceOfPoints(ORIGO, planeNormal);
		pLength*=pLength;
		double m = dotProduct(v, planeNormal);
		result[0]=m* planeNormal[0]/pLength;
		result[1]=m* planeNormal[1]/pLength;
		result[2]=m* planeNormal[2]/pLength;
		return result;
	}


	public static double[] addVectors(double[] a, double[] b) {
		
		
		double[] result=new double[a.length];
		for(int i=0;i<a.length;i++) {
			result[i]=a[i]+b[i];
		}
		return result;
	}


	public static double[] paralellScale(double[] p, double[] bcVector, double paraScale) {
	
		
		double[] para = paralell(p, bcVector);
		double[] scaledPara = VectorUtil.scaledPoint(VectorUtil.ORIGO, para, paraScale);

		
		double[] perp = VectorUtil.perp(p, bcVector);
		return VectorUtil.addVectors(perp,scaledPara);

	}
	
}
