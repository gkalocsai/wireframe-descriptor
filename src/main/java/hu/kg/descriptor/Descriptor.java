package hu.kg.descriptor;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Descriptor {

	Map<String, Vertex> vertex=new HashMap<String, Vertex>();
	//key = object.vertexName
	
	Map<String, List<Face>> faces=new HashMap<String, List<Face>>();
	//key = objectName
	
	
	public Descriptor(String descString) {
		String[] lines = descString.split("\\\n");
		String currentObjectName="";
		for(String l:lines)  {
			l=l.trim();
			if(l.isEmpty() || l.startsWith("#")) continue;
			if(l.startsWith("=")) {
				currentObjectName=l.substring(1).trim();
				faces.put(currentObjectName, new LinkedList<Face>());
			}
			if(Character.isLetter(l.charAt(0))) {
				String key=getVertexName(l);
				double[] koords=getVertexCoords(l);
				Vertex v=new Vertex(key, koords);
				vertex.put(currentObjectName+"."+key,v);
			}
			
			if(l.startsWith("{")) {
				List<Vertex> vl=getFace(currentObjectName,l.substring(1, l.length()-1));
				Face f=new Face(vl, null);
				faces.get(currentObjectName).add(f);
			}
		}
	}

	private List<Vertex> getFace(String objectName,String l) {
		List<Vertex> face=new LinkedList<Vertex>();
		String[] names = l.split(",");
		for(String key:names) {
			face.add(vertex.get(objectName+"."+key));
		}
		return face;
	}


	private double[] getVertexCoords(String l) {
		String w=l.substring(l.indexOf('(')+1, l.indexOf(')'));
		String[] parts = w.split(",");
		double[] result=new double[3];
		
		result[0] = Double.valueOf(parts[0]);
		result[1] = Double.valueOf(parts[1]);
		result[2] = Double.valueOf(parts[2]);
		return result;
	}


	private String getVertexName(String l) {
		return l.substring(0, l.indexOf('('));
	}
}


