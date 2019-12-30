package display.graphutil;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import hu.kg.descriptor.Face;
import hu.kg.descriptor.Triangle;
import hu.kg.descriptor.Vertex;
import hu.kg.math.VectorUtil;

public class Object3D {

	
	public double[][] points;
	public int[][] lines;
	List<Triangle> stickers = new LinkedList<>();
	
	
	
	
	
	public Object3D(List<Face> list) { //Object3D o3d) {
	
        Map<String,Vertex> vs =new LinkedHashMap<>();
        int lineCount=0;
        for(Face f:list) {
        	lineCount+=f.getVertices().length;
			for(Vertex v:f.getVertices()) {
				vs.put(v.getName(),v);
			}
		}
        lines=new int[lineCount][2];
        
        Map<String, Integer> name2PointIndex=new HashMap<String, Integer>();
        points=new double[vs.keySet().size()][];
        int pointIndex=0;
        for(String key:vs.keySet()) {
        	Vertex x=vs.get(key);
        	points[pointIndex] = new double[3];
        	points[pointIndex][0]=x.getX();
        	points[pointIndex][1]=x.getY();
        	points[pointIndex][2]=x.getZ();
        	name2PointIndex.put(x.getName(), pointIndex);
        	pointIndex++;
        }
        int lIndex=0;
        for(Face f:list) {
        	Vertex[] va = f.getVertices();
        	for(int i=0;i<va.length-1;i++) {
        		lines[lIndex][0] = name2PointIndex.get(va[i].getName()); 
        		lines[lIndex][1] = name2PointIndex.get(va[i+1].getName());
        	    lIndex++;    		
        	}
        	lines[lIndex][0] = name2PointIndex.get(va[va.length-1].getName());
        	lines[lIndex][1] = name2PointIndex.get(va[0].getName());
        	lIndex++;
        }
	}

	
	
	
	private Object3D(double[][] points, int[][] lines, List<Triangle> stickers) {
		super();
		this.points = points;
		this.lines = lines;
		this.stickers = stickers;
	}

	public Object3D copy() {
         double[][] np = new double[points.length][3];		
         for(int i=0;i<points.length;i++) {
        	 np[i][0] = points[i][0];
        	 np[i][1] = points[i][1];
        	 np[i][2] = points[i][2];
         }
         return new Object3D(np, lines, stickers);
	}



	public void transForm(double offsetX, double offsetY, double offsetZ) {
		for(int i=0;i<points.length;i++) {
			points[i] = VectorUtil.translatePoint(points[i], offsetX, offsetY, offsetZ);
		}
	}
	
	
	public void rotate(double[] axis, double rad) {
		for(int i=0;i<points.length;i++) {
			points[i] = VectorUtil.rotate(points[i], axis, rad);
		}
	}
	
	public void rotateAroundOrigo(double pitch, double yaw, double roll ) {
		for(int i=0;i<points.length;i++) {
			points[i] = VectorUtil.rotateAroundOrigo(points[i], pitch, yaw, roll);
		}
	}
	

}
