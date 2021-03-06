package hu.kg.descriptor;

import java.util.LinkedList;
import java.util.List;

public class DescribedFace {

	List<String> vertices=new LinkedList<>();
	public void addName(String vertexName) {
		vertices.add(vertexName);
	}
	public List<String> getVertices() {
		return vertices;
	}
	public DescribedFace copy() {
		DescribedFace df=new DescribedFace();
		
		for(String s:vertices) {
			
			df.addName(s);
		}
		df.surfaceId = this.surfaceId;
		return df;
	}

	private String surfaceId;
	
	public String getSurfaceId() {
		return surfaceId;
	}
	public void setSurfaceId(String surfaceId) {
		this.surfaceId = surfaceId;
	}
	
	
	
	
	
	
}
