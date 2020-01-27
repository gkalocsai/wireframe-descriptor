package hu.kg.descriptor;

import java.awt.Color;
import java.util.List;

public class Face {

	
	Vertex[] vertices;
	Color color;
	
	
	public Face(List<Vertex> vl, Color color) {
		vertices=new Vertex[vl.size()];
		int i=0;
		for(Vertex v: vl) {
			vertices[i++] =v;
		}
		this.color = color;
	}

	private Face(Vertex[] nv, Color color) {
		this.vertices = nv;
		this.color = color;
		
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public Color getColor() {
		return color;
	}

	
	
	public Face copy() {
		Vertex[] nv=new Vertex[vertices.length];
		for(int i=0;i<vertices.length;i++) {
			nv[i]=vertices[i].copy();
		}	
		return new Face(nv,color);
	}
	
	
	
	
}
