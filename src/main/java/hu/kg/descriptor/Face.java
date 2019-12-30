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

	public Vertex[] getVertices() {
		return vertices;
	}

	public Color getColor() {
		return color;
	}
	
	
	
	
}
