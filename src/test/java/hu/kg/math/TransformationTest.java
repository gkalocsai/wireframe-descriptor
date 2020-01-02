package hu.kg.math;

import java.awt.Color;

import org.junit.Test;

import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import hu.kg.descriptor.Triangle;
import hu.kg.math.Transformation;
import hu.kg.math.VectorUtil;

public class TransformationTest {

	
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

        	double X=0.0;
        	double Y=0.0;
        	double Z=0.0;
        	
			@Override
			public void draw() {
			    Triangle target=init1(); 
			    Triangle t=init2();		    
			        
		 	    Transformation tr=new Transformation(t, target);
                t.paraScale(tr.bcVector,tr.paraScale);
                t.scaleAllCoords(tr.scale);
                
                System.out.println(tr.axisOfOnPlaneRotation[0]);
                t=t.rotate(tr.axisOfOnPlaneRotation, Math.PI/6);
                
                
                
                
				
                drawTriangle(target, Color.RED);
                drawTriangle(t, Color.GREEN);
		 	    
		 	   
				
			
			    
						 
			    
			}

			
			
			private Triangle init2() {
				
				double[] a = {0.75,0,0};
				double[] b = {0,0.75,0};
			    double[] c = {0,0,0};
				   
				return new Triangle(a,b,c);
					
			}

			private Triangle init1() {
				double[] a = {-0.5,0,0};
				double[] b = {0.5,0,0};
				double[] c = {0.25,0.5,0};
				
				
				return new Triangle(a,b,c);				
			}


			private void drawTriangle(Triangle t, Color color) {
				//Graphics.filledTriangle3D(ColorTable.getOne(color), t.a,t.b,t.c);
				Graphics.line3D(Color.WHITE, t.a, t.b);
				Graphics.line3D(color, t.a, t.c);
				Graphics.line3D(Color.YELLOW, t.b, t.c);
				
				
			}






			@Override
			public void setEventCallbacks(long handle) {
				// TODO Auto-generated method stub
				
			}
			
			
        };
		new Window(l, "TrianglesWithHeight");
	}
}
