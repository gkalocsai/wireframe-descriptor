
package hu.kg.descriptor;

import java.util.HashMap;
import java.util.Map;

import display.graphutil.Object3D;

public class Descriptor {


	Map<String,DescribedObject> objects=new HashMap<String, DescribedObject>();
	
	//	        Complex[Cube]	
	//          H:HexaPrism  
	//          H.triangle -> t2
	//          Triang <- H.t2
	
	public Descriptor(String descString) {
		String[] lines = descString.split("\\\n");
		String currentObjectName="";
		DescribedObject dobj = null;
		
		Map<String,DescribedObject> locals=null;
		
		for(String l:lines)  {
			l=l.trim();
			if(l.isEmpty() || l.startsWith("#")) continue;
			else if(l.matches("[a-zA-Z0-9]+")) {
				currentObjectName=l;
				locals=null;
				dobj=new DescribedObject(currentObjectName);
				this.objects.put(currentObjectName,dobj);

			}
			else if(l.contains("[")) {
				int i=l.indexOf("[");
				currentObjectName=l.substring(0, i).trim();
				String base=l.substring(i+1,l.length()-1);
				dobj=new DescribedObject(currentObjectName,objects.get(base));	
				this.objects.put(currentObjectName,dobj);
				locals=new HashMap<String, DescribedObject>();
			}
			else if(l.indexOf('(')>=0) {
				String key=getVertexName(l);
				double[] koords=getVertexCoords(l);
				Vertex v=new Vertex(koords);
				dobj.getName2Vertex().put(key, v);
			}
			else if(l.indexOf(':')>=0) {
				String key=l.substring(0,l.indexOf(":")).trim();
				String type=l.substring(l.indexOf(":")+1).trim();
				DescribedObject t=new DescribedObject(key, objects.get(type));
				locals.put(key, t);
			}
			else if(l.indexOf("->")>=0) {   //rename
				String leftPart=l.substring(0,l.indexOf("->")).trim();
				String newTriangleName=l.substring(l.indexOf("->")+2).trim();
				String localObjectName = leftPart.substring(0,leftPart.indexOf(".")).trim();
				String oldTriangleName=leftPart.substring(leftPart.indexOf(".")+1).trim();
				
				DescribedObject local=locals.get(localObjectName);
				local.renameTriangle(oldTriangleName,newTriangleName);
				
			}

			else if(l.startsWith("{")) {
				String innserString = l.substring(1, l.length()-1);
				String[] vs=innserString.split(",");
				DescribedFace dface=new DescribedFace();
				for(String v:vs) {
					dface.vertices.add(v);
				}
				dobj.addFace(dface);
			}
			else if(l.indexOf("<-")>=0) {
				String baseTriangle=l.substring(0,l.indexOf("<-")).trim();
				String rightPart=l.substring(l.indexOf("<-")+2).trim();
				String localObjectName = rightPart.substring(0,rightPart.indexOf(".")).trim();
				String movingTraingleName=rightPart.substring(rightPart.indexOf(".")+1).trim();
				
				DescribedObject base=objects.get(currentObjectName);
				DescribedObject other=locals.get(localObjectName);
				base.glue(baseTriangle,other, movingTraingleName);
				
			}	
			else if(l.indexOf('<')>=0) {
				String triangleName=l.substring(0, l.indexOf("<"));
				String edges = l.substring(l.indexOf('<')+1, l.length()-1);
				String[] vs=edges.split(",");

				DescribedTriangle dTriangle=new DescribedTriangle();
				dTriangle.name=triangleName;

				for(String vname:vs) {
					dTriangle.verticeNames.addName(vname);
				}
				dobj.addTriangle(dTriangle);
				
			}


		}
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


	Object3D getObject3D(String name) {
		return objects.get(name).getObject3D();
	}
}


