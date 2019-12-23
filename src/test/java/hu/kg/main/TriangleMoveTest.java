package hu.kg.main;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;

public class TriangleMoveTest {
 
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

        	double X=0.0;
        	double Y=0.0;
        	double Z=0.0;
        	
			@Override
			public void draw() {
			    Triangle target=init1();
			  
			    Triangle t=init2(target,1.5, X, Y,Z, -0.2, -0.3, 0.0);		    
			    X=Math.PI/2.2;
			    Y=0;
			    Z=0;
//			    drawTriangle(target,30);
//			    drawTriangle(t,11);
//			    
			    double scale = VectorUtil.distanceOfPoints(target.a, target.b) /VectorUtil.distanceOfPoints(t.a, t.b);			    
			    
			    double[] b2= VectorUtil.scaledPoint(t.a,t.b,scale);
			    double[] c2= VectorUtil.scaledPoint(t.a,t.c,scale);
			    

			    double[] midPoint=VectorUtil.midpoint(t.a, b2);
			    
			    double[] am = VectorUtil.translatePoint(t.a, -midPoint[0], -midPoint[1], -midPoint[2]);
			    double[] bm = VectorUtil.translatePoint(b2, -midPoint[0], -midPoint[1], -midPoint[2]);
			    double[] cm = VectorUtil.translatePoint(c2, -midPoint[0], -midPoint[1], -midPoint[2]);
				
			    
			    
			    midPoint=VectorUtil.midpoint(target.a, target.b);
				    
			    double[] aom = VectorUtil.translatePoint(target.a, -midPoint[0], -midPoint[1], -midPoint[2]);
			    double[] bom = VectorUtil.translatePoint(target.b, -midPoint[0], -midPoint[1], -midPoint[2]);
			    double[] com = VectorUtil.translatePoint(target.c, -midPoint[0], -midPoint[1], -midPoint[2]);


			    Triangle targetMoved=new Triangle(aom, bom, com);
			    drawTriangle(targetMoved,42);
			    
			    Triangle movedBack=new Triangle(am,bm,cm); 
			    drawTriangle(movedBack,40);
			    
			    
			    double theta = VectorUtil.angleBetween(com, cm);
			    System.out.println(theta);
			    double[] crossp = VectorUtil.crossProduct3D(com, cm);
			    
			    System.out.println(crossp[0]+" "+crossp[1]+" "+crossp[2]);
			    
			    double[] arm = VectorUtil.rotate(am, crossp, theta);
			    double[] brm = VectorUtil.rotate(bm, crossp, theta);
			    double[] crm = VectorUtil.rotate(cm, crossp, theta);
			    
			    
			    
			    Triangle rotated=new Triangle(arm, brm, crm);
			    drawTriangle(rotated,11);
			    
			    
			    
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



			private void drawTriangle(Triangle t, int color) {
				Graphics.filledTriangle3D(ColorTable.getOne(color), t.a,t.b,t.c);
				Graphics.line3D(Color.WHITE, t.a, t.b);
				Graphics.line3D(Color.BLUE, t.a, t.c);
				Graphics.line3D(Color.ORANGE, t.b, t.c);
				
				
			}



			private Triangle init1() {
				double[] a = {0,0.5,0};
				double[] b = {0.3,0,0};
			    double[] c = {0,0,0};
				
			
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
