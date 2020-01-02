package hu.kg.descriptor;

import java.awt.Color;
import java.util.List;

public class Face {

	
	Vertex[] vertices;
	Color color;
	String name;
	
	public Face(List<Vertex> vl, Color color) {
		vertices=new Vertex[vl.size()];
		int i=0;
		for(Vertex v: vl) {
			vertices[i++] =v;
		}
		this.color = color;
	}

	public Vertex[] getVertices() {
		return vertices;
	}

	public Color getColor() {
		return color;
	}

	public void setName(String name) {
		this.name = name;
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
