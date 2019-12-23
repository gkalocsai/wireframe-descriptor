package hu.kg.main;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;

public class RotationAxisTest2 {
 
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

        	double X=0.0;
        	double Y=0.0;
        	double Z=0.0;
        	
			@Override
			public void draw() {
			    Triangle target=init1();
			  
			    double[] midt = VectorUtil.midpoint(target.a, target.b);
			    double[] v1 = VectorUtil.vectorBetweenPoints(midt, target.a);
			    double[] v2 = VectorUtil.vectorBetweenPoints(midt, target.c);
			    
			    
			    
			    
			   // X=Math.PI/4;
			   // Y=Math.PI/5;
			   // Z=Math.PI/6;
			    X+=0.01;
			    Y+=0.01;
			    Z+=0.02;
			    
			    
			    
			    
			    Triangle t=init2(target,0.5, X, Y,Z, 0, 0, 0);		    
			    
			    
			    
		 	    
			    
			    double[] mid=VectorUtil.midpoint(t.a, t.b);
			    v1 = VectorUtil.vectorBetweenPoints(mid, t.a);
			    v2 = VectorUtil.vectorBetweenPoints(mid, t.c);
			    
			    double[] cp=VectorUtil.crossProduct3D(v1, v2);
			    Graphics.line3D(Color.MAGENTA, midt, cp);
			    Graphics.line3D(Color.MAGENTA, cp, t.a);
			    Graphics.line3D(Color.MAGENTA, cp, t.b);
			    
			    
		 	    
		 	    double theta = VectorUtil.angleBetween(t.c,target.c);
		 	    double[] axis = VectorUtil.vectorBetweenPoints(target.c, t.c);
		 	    
			    double[] rx = VectorUtil.rotate(t.a, axis, theta);
			    double[] ry = VectorUtil.rotate(t.b, axis, theta);
			    double[] rz = VectorUtil.rotate(t.c, axis, theta);
			      
				 
			    System.out.println(VectorUtil.angleBetween(t.c,target.c));
			    
			    
			    
			    Triangle rotated= new Triangle(rx,ry,rz);
			    
			    
			    
			    drawTriangle(target, Color.RED);
			    drawTriangle(t, Color.DARK_GRAY);
			    drawTriangle(rotated, Color.green);
			    
				    
			    
			    Graphics.line3D(Color.BLUE, mid, axis);
			    
			    
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
				double[] a = {.5,0,0};
				double[] b = {0,.5,0};
			    double[] c = {0,0,0};
				
			    
			    
			    double[] mp = VectorUtil.midpoint(a, b);
			    
			    
			    
			    a=VectorUtil.translatePoint(a, -mp[0], -mp[1], -mp[2]);
			    b=VectorUtil.translatePoint(b, -mp[0], -mp[1], -mp[2]);
			    c=VectorUtil.translatePoint(c, -mp[0], -mp[1], -mp[2]);
			    
			    
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
