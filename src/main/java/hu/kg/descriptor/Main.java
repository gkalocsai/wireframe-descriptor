package hu.kg.descriptor;

import java.awt.Color;
import java.io.IOException;

import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import display.graphutil.Object3D;
import hu.kg.main.StringLoadUtil;
import hu.kg.math.Transformation;

public class Main {

	public static void main(String[] args) throws IOException {
	    
		Descriptor d=new Descriptor(StringLoadUtil.load(args[0]));
		
		Object3D w= new Object3D(d.faces.get("HexaPrism"), d.triangles.get("HexaPrism"));
		Object3D c= new Object3D(d.faces.get("Cube"), d.triangles.get("Cube"));
		
		Triangle cubeT = c.getTriangleByIndex(0);
		Triangle hexaT = w.getTriangleByIndex(0);
		
		System.out.println(cubeT);
		System.out.println(hexaT);
		
	    System.out.println("T:      "+cubeT);
	    System.out.println("Target: "+ hexaT);
	    
		Transformation trans=new Transformation(cubeT, hexaT);
		
		
		
		
		
		Looper l=new Looper() {
			
			
			double pitch=0.0;
			double yaw=0.0;
			double roll=0.0;
			
			
			
			@Override
			public void setEventCallbacks(long handle) {}
			
			@Override
			public void draw() {
		        Object3D hexa=w.copy();
		        Object3D cube=c.copy();
		        
		        //current.scaleParalell(1.5, 0);

		        
		        
		        
		    
		        hexa.rotateAroundOrigo(pitch, yaw, roll);
		        Transformation tr=new Transformation(cube.getTriangleByIndex(0), hexa.getTriangleByIndex(0));
                
		        Triangle t=cube.getTriangleByIndex(0);
		        t.translate(tr.trans0);
		 	    t.paraScale(tr.bcVector, tr.paraScale);
		 	   
		 	    t.scaleAllCoords(tr.scale);
		 	  
		 	    cube.transform(tr);
              
		        pitch+=0.00522254;
			    yaw+=0.004341;
			    roll+=0.00222;
			    
			    drawTriangle(cube.getTriangleByIndex(0), Color.GREEN,Color.CYAN);
			    drawTriangle(hexa.getTriangleByIndex(0), Color.RED, Color.YELLOW);

			    
		        for(int[] face: hexa.faces) {
		        	
		        	for(int i=0;i<face.length;i++) {

						double[] a=hexa.points[face[i]];
						double[] b=hexa.points[face[((i+1) % face.length)]];
		        		Graphics.line3D(Color.WHITE, a, b);
		        	}
				}
		        
                for(int[] face: cube.faces) {
		        	
		        	for(int i=0;i<face.length;i++) {

						double[] a=cube.points[face[i]];
						double[] b=cube.points[face[((i+1) % face.length)]];
		        		Graphics.line3D(Color.WHITE, a, b);
		        	}
				}
		        
		        	
		        

		        
			}
			private void drawTriangle(Triangle t, Color color, Color color2) {
				//Graphics.filledTriangle3D(ColorTable.getOne(color), t.a,t.b,t.c);
				Graphics.line3D(Color.WHITE, t.a, t.b);
				
				Graphics.line3D(color, t.a, t.c);
				Graphics.line3D(color2, t.b, t.c);
				
				
			}
		};
		
		
		new Window(l, "proba");
		
		
		
	}

}
