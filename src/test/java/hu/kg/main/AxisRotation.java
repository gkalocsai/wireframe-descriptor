package hu.kg.main;

public class AxisRotation {

	
	public double[] rotate(double[] v, double[] axis, double rad) {
		
		
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
	
	
	
}
