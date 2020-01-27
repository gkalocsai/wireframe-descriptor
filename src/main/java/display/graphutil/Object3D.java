package display.graphutil;

import hu.kg.math.VectorUtil;

public class Object3D {


	public double[][] points;
	public int[][] faces;
	
	public Object3D(double[][] points, int[][] faces) {
		super();
		this.points = points;
		this.faces = faces;
	}
	
	
	
	
//	
//	
//	
//	public Object3D(List<Face> list, List<Face> triangles) { 
//
//		Map<String,Vertex> vs =new LinkedHashMap<>();
//
//		for(Face f:list) {
//			for(Vertex v:f.getVertices()) {
//				vs.put(v.getName(),v);
//			}
//		}
//	
//		faces=new int[list.size()][];
//	
//
//		Map<String, Integer> name2PointIndex=new HashMap<String, Integer>();
//		points=new double[vs.keySet().size()][];
//		int pointIndex=0;
//		for(String key:vs.keySet()) {
//			Vertex x=vs.get(key);
//			points[pointIndex] = new double[3];
//			points[pointIndex][0]=x.getX();
//			points[pointIndex][1]=x.getY();
//			points[pointIndex][2]=x.getZ();
//			name2PointIndex.put(x.getName(), pointIndex);
//			pointIndex++;
//		}
//		int lIndex=0;
//		for(Face f:list) {
//			Vertex[] va = f.getVertices();
//
//			faces[lIndex] = new int[va.length];
//
//			for(int i=0;i<va.length;i++) {
//				faces[lIndex][i] = name2PointIndex.get(va[i].getName());   		
//			}
//			lIndex++;
//		}
//
//		int sIndex = 0;
//		for(Face t:triangles) {
//			Vertex[] va = t.getVertices();
//			stickers[sIndex][0] = name2PointIndex.get(va[0].getName()); 
//			stickers[sIndex][1] = name2PointIndex.get(va[1].getName());
//			stickers[sIndex][2] = name2PointIndex.get(va[2].getName());
//			sIndex++;
//		}
//
//	}
//
//	private Object3D(double[][] points, int[][] faces, int[][] stickers) {
//		super();
//		this.points = points;
//		this.faces = faces;
//		this.stickers = stickers;
//
//	}
//
//	public Object3D copy() {
//		double[][] np = new double[points.length][3];		
//		for(int i=0;i<points.length;i++) {
//			np[i][0] = points[i][0];
//			np[i][1] = points[i][1];
//			np[i][2] = points[i][2];
//		}
//		return new Object3D(np, faces, stickers);
//	}
//
//
//
//	public void translate(double offsetX, double offsetY, double offsetZ) {
//		for(int i=0;i<points.length;i++) {
//			points[i] = VectorUtil.translatePoint(points[i], offsetX, offsetY, offsetZ);
//		}
//	}
//
//
//	public void rotate(double[] axis, double rad) {
//		for(int i=0;i<points.length;i++) {
//			points[i] = VectorUtil.rotate(points[i], axis, rad);
//		}
//	}
//
	public void rotateAroundOrigo(double pitch, double yaw, double roll ) {
		for(int i=0;i<points.length;i++) {
			points[i] = VectorUtil.rotateAroundOrigo(points[i], pitch, yaw, roll);
		}
	}
//
//
//
//	public void scaleParalell(double newScale, int stickerIndex) {
//		double[] a=points[stickers[stickerIndex][0]];
//		double[] b=points[stickers[stickerIndex][1]];
//		double[] c=points[stickers[stickerIndex][2]];
//
//		double currentScale= VectorUtil.distanceOfPoints(b, c)/VectorUtil.distanceOfPoints(c, a);
//   
//		double scale=newScale/currentScale;
//
//		translate(-c[0], -c[1], -c[2]);
//
//		for(int i=0;i<points.length;i++)  {
//			double[] p = points[i];
//			double[] para = VectorUtil.perp(p, VectorUtil.vectorBetweenPoints(b, c));
//			double[] perp = VectorUtil.paralell(p, VectorUtil.vectorBetweenPoints(b, c));
//			double[] scaledPara = VectorUtil.scaledPoint(VectorUtil.ORIGO, para, scale);
//			
//			points[i]=VectorUtil.addVectors(perp,scaledPara);
//		}
//	}
//

}
