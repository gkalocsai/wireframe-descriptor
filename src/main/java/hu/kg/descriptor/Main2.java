package hu.kg.descriptor;

import java.awt.Color;
import java.io.IOException;

import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import display.graphutil.Object3D;
import hu.kg.main.StringLoadUtil;
import hu.kg.math.Transformation;

public class Main2 {

	public static void main(String[] args) throws IOException {
	    
		Descriptor d=new Descriptor(StringLoadUtil.load(args[0]));
		Looper l=new Looper() {
			
			
			double pitch=0.0;
			double yaw=0.0;
			double roll=0.0;
			
			
			
			@Override
			public void setEventCallbacks(long handle) {}
			
			@Override
			public void draw() {
		        Object3D comőplex=d.getObject3D("Complex");
		          
		        pitch+=0.00522254;
			    yaw+=0.004341;
			    roll+=0.00222;
			      comőplex.rotateAroundOrigo(pitch, yaw, roll);
			  //  drawTriangle(cube.getTriangleByIndex(0), Color.GREEN,Color.CYAN);
			 //   drawTriangle(hexa.getTriangleByIndex(0), Color.RED, Color.YELLOW);

			    
		        for(int[] face: comőplex.faces) {
		        	
		        	for(int i=0;i<face.length;i++) {

						double[] a=comőplex.points[face[i]];
						double[] b=comőplex.points[face[((i+1) % face.length)]];
		        		Graphics.line3D(Color.WHITE, a, b);
		        	}
				}
		        
                		        
		        	
		        

		        
			}
			private void drawTriangle(Triangle t, Color color, Color color2) {
				//Graphics.filledTriangle3D(ColorTable.getOne(color), t.a,t.b,t.c);
				Graphics.line3D(Color.WHITE, t.a.getPoint(), t.b.getPoint());
				
				Graphics.line3D(color, t.a.getPoint(), t.c.getPoint());
				Graphics.line3D(color2, t.b.getPoint(), t.c.getPoint());
				
				
			}
		};
		
		
		new Window(l, "proba");
		
		
		
	}

}
