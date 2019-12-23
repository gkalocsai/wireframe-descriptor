package hu.kg.main;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;

public class RotationAxisTest {
 
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

        	double X=0.0;
        	double Y=0.0;
        	double Z=0.0;
        	
			@Override
			public void draw() {
			    Triangle target=init1();
			  
			    X=Math.PI/3;
			    Y=Math.PI/4;
			    Z=Math.PI/5;
			    Triangle t=init2(target,1, X, Y,Z, 0, 0, 0);		    

//			    drawTriangle(target,30);
//			    drawTriangle(t,11);
//			    
			  
			    double thetaa = VectorUtil.angleBetween(target.a, t.a);
			    double thetab = VectorUtil.angleBetween(target.b, t.b);
			    double thetac = VectorUtil.angleBetween(target.c, t.c);
			    
			    
			    double[] ap = VectorUtil.crossProduct3D(t.a, target.a);
			    double[] bp = VectorUtil.crossProduct3D(t.b, target.b);
			    double[] cp = VectorUtil.crossProduct3D(t.c, target.c);
			    
			    
			    System.out.println(cp[0]+" "+cp[1]+" "+cp[2]);
			    
			    
			    double[] rx = VectorUtil.rotate(t.a, ap, thetaa);
			    double[] ry = VectorUtil.rotate(t.b, bp, thetab);
			    double[] rz = VectorUtil.rotate(t.c, cp, thetac);
			    
			    
			    Triangle rotated= new Triangle(rx,ry,rz);
			    //rotated.scaleAllCoords(0.5);
			    
			    
			    drawTriangle(target, 42);
			    drawTriangle(t, 40);
			    drawTriangle(rotated, 11);
				    
			    
			    
			    
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
				Graphics.line3D(Color.RED, t.a, t.c);
				Graphics.line3D(Color.YELLOW, t.b, t.c);
				
				
			}



			private Triangle init1() {
				double[] a = {0,0.5,0};
				double[] b = {0.3,0,0};
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
