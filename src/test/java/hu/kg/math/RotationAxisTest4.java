package hu.kg.math;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import hu.kg.descriptor.Triangle;
import hu.kg.math.Transformation;
import hu.kg.math.VectorUtil;

public class RotationAxisTest4 {
 
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

        	double X=0.0;
        	double Y=0.0;
        	double Z=0.0;
        	
			@Override
			public void draw() {
			    Triangle target=init1(); 
			    Triangle t=init2(target,1.5, X, Y,Z, 0, 0, 0);		    
			    
			    
			    
		 	    Transformation tr=new Transformation(t, target);
			    
				t.translate(tr.trans1);
							
				Triangle samePlane=t.rotate(tr.intersectionLine, tr.angleOfPlanes);
			    Triangle result  = samePlane.rotate(tr.axisOfOnPlaneRotation, tr.angleBetweenSides);
			    
			    result.scaleAllCoords(tr.scale);
			    
			    result.translate(tr.trans2);

			    drawTriangle(samePlane, Color.CYAN);
			    drawTriangle(t, Color.CYAN);
			    
			    
			    
			    drawTriangle(target, Color.RED);
			    drawTriangle(result, Color.GREEN);
				
			    
			    
			    X+=0.0522254;
			    Y+=0.04341;
			    Z+=0.0222;
			    
			 
			    
			}

			
			
			private Triangle init2(Triangle base, double scale, double X, double Y, double Z, double oX, double oY, double oZ) {
				
				
				Triangle t=base.copy();
				t.scaleAllCoords(scale);
				
				double[] am = t.a;
				double[] bm = t.b;
			    double[] cm = t.c;
								
			    double[] midPoint=VectorUtil.midpoint(am, bm);
				
			    
			    am=VectorUtil.translatePoint(am, -midPoint[0], -midPoint[1], -midPoint[2]);
			    bm=VectorUtil.translatePoint(bm, -midPoint[0], -midPoint[1], -midPoint[2]);
			    cm=VectorUtil.translatePoint(cm, -midPoint[0], -midPoint[1], -midPoint[2]);
				
			
			    
			    
			    double[] ar = am;
				double[] br = bm;
				double[] cr = cm;
				
	  			
			    ar = VectorUtil.rotateAroundOrigo(ar, X, Y, Z);
				br = VectorUtil.rotateAroundOrigo(br, X, Y, Z);
				cr = VectorUtil.rotateAroundOrigo(cr, X, Y, Z);
//					
								
				
				ar=VectorUtil.translatePoint(ar, oX, oY, oZ);
				br=VectorUtil.translatePoint(br, oX, oY, oZ);
				cr=VectorUtil.translatePoint(cr, oX, oY, oZ);
				
			
				return new Triangle(ar, br, cr);
			}



			private void drawTriangle(Triangle t, Color color) {
				//Graphics.filledTriangle3D(ColorTable.getOne(color), t.a,t.b,t.c);
				Graphics.line3D(Color.WHITE, t.a, t.b);
				Graphics.line3D(color, t.a, t.c);
				Graphics.line3D(Color.YELLOW, t.b, t.c);
				
				
			}



			private Triangle init1() {
				double[] a = {0,0.5,0};
				double[] b = {0.5,0,0.5};
			    double[] c = {0.3,0.3,0};
				
			    return new Triangle(a,b,c);
				
			}



			@Override
			public void setEventCallbacks(long handle) {
				// TODO Auto-generated method stub
				
			}
			
			
        };
		new Window(l, "TrianglesWithHeight");
	}
	
}
