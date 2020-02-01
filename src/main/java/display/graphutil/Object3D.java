package display.graphutil;

import hu.kg.math.VectorUtil;

public class Object3D {


	public double[][] points;
	public int[][] faces;
	
	public String[] surfaceIds;
	
	public Object3D(double[][] points, int[][] faces, String[] surfaceIdA) {
		super();
		this.points = points;
		this.faces = faces;
		this.surfaceIds=surfaceIdA;
		
		for(String s: surfaceIds) {
			System.out.println(s);
		}
	}
		
	public void rotateAroundOrigo(double pitch, double yaw, double roll ) {
		for(int i=0;i<points.length;i++) {
			points[i] = VectorUtil.rotateAroundOrigo(points[i], pitch, yaw, roll);
		}
	}

}
