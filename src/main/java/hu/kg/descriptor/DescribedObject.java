package hu.kg.descriptor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import display.graphutil.Object3D;
import hu.kg.math.Transformation;

public class DescribedObject {

	private String name;
	private Map<String, Vertex> name2Vertex=new HashMap<String, Vertex>();
	private List<DescribedFace> faces=new LinkedList<>();
	
	private int nextVertexId=1;
	
	private Map<String,Triangle> triangles=new HashMap<String, Triangle>();
	
	
	public DescribedObject(String currentObjectName) {
		 this.name = currentObjectName;
	}


	public DescribedObject(String currentObjectName, DescribedObject other) {
		this.name = currentObjectName;
		faces.addAll(other.copyFaces());
		triangles.putAll(other.copyTriangles());
		name2Vertex.putAll(other.copyVertices());
		
	}


	private Map<String,Vertex> copyVertices() {
		Map<String, Vertex> r=new HashMap<String, Vertex>();
		for(String k:name2Vertex.keySet()) {
			r.put(k, name2Vertex.get(k).copy());
		}
		return r;
	}


	private Map<String,Triangle> copyTriangles() {
		
		Map<String, Triangle> result = new HashMap<String, Triangle>();
		for(String otherTriangleKey:triangles.keySet()) {
			result.put(otherTriangleKey, triangles.get(otherTriangleKey));
		}
		return result;
	}


	private List<DescribedFace> copyFaces() {
		List<DescribedFace> result=new LinkedList<DescribedFace>();
		for(DescribedFace x:faces) {
			
			result.add(x.copy());
		}
		
		return result;
	}


	public String getName() {
		return name;
	}


	public Map<String, Vertex> getName2Vertex() {
		return name2Vertex;
	}


	public List<DescribedFace> getFaces() {
		return faces;
	}

	
	public void renameTriangle(String oldTriangleName, String newTriangleName) {
	    Triangle x = triangles.get(oldTriangleName);
	    triangles.put(newTriangleName, x);
	    triangles.remove(oldTriangleName);
	    
	}
	
	
	public Triangle getTriangle(String triangleName) {
        return triangles.get(triangleName);        
	}

	
	public void glue(String baseTriangle, DescribedObject other, String movingTraingleName) {
		Triangle base = getTriangle(baseTriangle);
		Triangle moving = other.getTriangle(movingTraingleName);
		Transformation tr=new Transformation(moving,base);
		for(String k:other.name2Vertex.keySet()) {
			other.name2Vertex.get(k).transform(tr);
		}
		renameVertices();
	
		for(String vname:other.name2Vertex.keySet()) {
			name2Vertex.put(vname, other.name2Vertex.get(vname));
		}
		
		for(DescribedFace df: other.faces) {
			this.faces.add(df);
		}
		
		for(String tkey:other.triangles.keySet()) {
			this.triangles.put(tkey, other.triangles.get(tkey));
		}
		
	}


	public void addTriangle(DescribedTriangle dt) {

		DescribedFace vNames = dt.verticeNames;
		String as = vNames.vertices.get(0);
		Vertex aa = name2Vertex.get(as);
		double[] a=aa.getPoint();

		String bs = vNames.vertices.get(1);
		Vertex bb = name2Vertex.get(bs);
		double[] b=bb.getPoint();

		String cs = vNames.vertices.get(2);
		Vertex cc = name2Vertex.get(cs);
		double[] c=cc.getPoint();
		triangles.put(dt.name, new Triangle(a, b, c));

	}


	public Object3D getObject3D() {
	
		Set<String> vertexNames = name2Vertex.keySet();
		double[][] points=new double[vertexNames.size()][];
		int[][] nfaces=new int[faces.size()][];
		
		Map<String, Integer> name2PointIndex=new HashMap<String, Integer>();
		int i=0;
		for(String name:vertexNames) {
			    points[i]=name2Vertex.get(name).getPoint();
		    	name2PointIndex.put(name, i);
		    	i++;
		}
		
		i=0;
		for(DescribedFace df:faces) {
			nfaces[i]=new int[df.vertices.size()];
			int k=0;
			for(String vname:df.vertices) {
				nfaces[i][k] = name2PointIndex.get(vname);
				k++;
			}
			i++;
		}	
		return new Object3D(points, nfaces);
		
	}


	public void addFace(DescribedFace dface) {
		faces.add(dface);
	}
	
	
	private void renameVertices() {
		Map<String, String> oldname2NewName=new HashMap<String, String>();
		for(String name:name2Vertex.keySet()) {    
		    	oldname2NewName.put(name,""+nextVertexId);
		    	nextVertexId++;
		}
		
		for(String oname:oldname2NewName.keySet()) {
			Vertex value = name2Vertex.get(oname);
			name2Vertex.remove(oname);
			name2Vertex.put(oldname2NewName.get(oname),value);
		}
		
		for(DescribedFace df:faces) {	
			List<String> oldList = df.vertices;
			List<String> nl=new LinkedList<String>();
			for(String oname:oldList) {
				nl.add(oldname2NewName.get(oname));
				
			}
			df.vertices = nl;
		}
	}
	
}
