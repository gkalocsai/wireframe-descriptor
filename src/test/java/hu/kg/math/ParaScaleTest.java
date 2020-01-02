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

public class ParaScaleTest {
 
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

       
        	
			@Override
			public void draw() {
			    Triangle target=init1(); 
			    Triangle t=init2();		    
			    
			//    target.translate(VectorUtil.vectorBetweenPoints(target.c, VectorUtil.ORIGO));
				    
			    System.out.println("T:      "+t);
			    System.out.println("Target: "+ target);
			    
			    
			    
		 	    Transformation tr=new Transformation(t, target);
		 	    
		 	    double targetA = VectorUtil.distanceOfPoints(target.b, target.c);
		 	    double targetB = VectorUtil.distanceOfPoints(target.a, target.c);
		 	    double targetC = VectorUtil.distanceOfPoints(target.a, target.b);
		 	    

		 	    System.out.println("Target triangle: "+targetA+" "+targetB+" "+targetC);
		 	    
		 	       
		 	    t.translate(tr.trans0);
		 	    t.paraScale(tr.bcVector, tr.paraScale);
		 	    
		 	    t.scaleAllCoords(tr.scale);
		 	    
		 	    double tA = VectorUtil.distanceOfPoints(t.b, t.c);
		 	    double tB = VectorUtil.distanceOfPoints(t.a, t.c);
		 	    double tC = VectorUtil.distanceOfPoints(t.a, t.b);
		 	    
		 	    double diff = tC*tC-(tA*tA+tB*tB);
		 	    System.out.println(diff);
		 	    
		 	    
		 	    System.out.println("T2 triangle: "+tA+" "+tB+" "+tC);
		 	    
		 				    
		 	    drawTriangle(t, Color.GREEN);
			    drawTriangle(target, Color.RED);
			  //  drawTriangle(t, Color.GREEN);
				
			    
			    
			  
			}

			
			
		



			private Triangle init1() {
				double[] a = {0.5,0,0};
				double[] b = {0.25,0.5,0};
				double[] c = {0.25,0,0};
				   
			    return new Triangle(a,b,c);
				
			}

			private Triangle init2() {
				
				double[] a = {0.75,0,0};
				double[] b = {0,0.75,0};
			    double[] c = {0,0,0};
				
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
