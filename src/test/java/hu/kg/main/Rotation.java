package hu.kg.main;

public class Rotation {

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
	
	
	
	
}
