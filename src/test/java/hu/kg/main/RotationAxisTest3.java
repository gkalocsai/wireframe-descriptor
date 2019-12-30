package hu.kg.main;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import hu.kg.math.VectorUtil;

public class RotationAxisTest3 {
 
	@Test
	public void t() {
		
		
        Looper l=new Looper() {

        	double X=0.0;
        	double Y=0.0;
        	double Z=0.0;
        	
			@Override
			public void draw() {
			    Triangle target=init1();
			  
			    double[] midT = VectorUtil.midpoint(target.a, target.b);
			    double[] v1 = VectorUtil.vectorBetweenPoints(midT, target.a);
			    double[] v2 = VectorUtil.vectorBetweenPoints(midT, target.c);
			    double[] normT=VectorUtil.crossProduct3D(v1, v2);
			    
			    
			    
			   // X=Math.PI/4;
			   // Y=Math.PI/5;
			   // Z=Math.PI/6;
			    X+=0.0522254;
			    Y+=0.04341;
			    Z+=0.0222;
			    
			    
			    
			    
			    Triangle t=init2(target,1, X, Y,Z, 0, 0, 0);		    
			    
			    
			    
		 	    
			    
			    double[] mid=VectorUtil.midpoint(t.a, t.b);
			    v1 = VectorUtil.vectorBetweenPoints(mid, t.a);
			    v2 = VectorUtil.vectorBetweenPoints(mid, t.c);
			    
			    double[] norm=VectorUtil.crossProduct3D(v1, v2);
			    
			    
			    
			    Graphics.line3D(Color.GREEN, VectorUtil.ORIGO, norm);
			    Graphics.line3D(Color.RED, VectorUtil.ORIGO, normT);
			    
			    
			    double[] axis = VectorUtil.crossProduct3D(norm, normT);
			    
			    
			    double theta=VectorUtil.signedAngle(norm, normT,axis);
			    
			    
			    double[] rotatedNorm = VectorUtil.rotate(norm, axis, theta);
			    
			    
			    
			    rotatedNorm=VectorUtil.scaledPoint(VectorUtil.ORIGO, rotatedNorm, 0.2);
			    
			    Graphics.line3D(Color.MAGENTA, VectorUtil.ORIGO, VectorUtil.scaledPoint(VectorUtil.ORIGO, axis, 4.0));
			    Graphics.line3D(Color.ORANGE, VectorUtil.ORIGO, rotatedNorm);
				   
			    
			    //
		     //   drawTriangle(target, Color.RED);
			    
			    
			    double[] rx = VectorUtil.rotate(t.a, axis, theta);
			    double[] ry = VectorUtil.rotate(t.b, axis, theta);
			    double[] rz = VectorUtil.rotate(t.c, axis, theta);
			      
				 
			    
			    
			    
			    Triangle rotated= new Triangle(rx,ry,rz);
			    drawTriangle(rotated, Color.CYAN);
			    drawTriangle(t, Color.CYAN);
				 
			    
			    //  drawTriangle(target, Color.RED);
			 
			    
			    double[] midR = VectorUtil.midpoint(rotated.a, rotated.b);
			    double[] v1r = VectorUtil.vectorBetweenPoints(midR, rotated.a);
			    double[] v2r = VectorUtil.vectorBetweenPoints(midR, rotated.c);
			    double[] normR=VectorUtil.crossProduct3D(v1r, v2r);
			    
			    normR=VectorUtil.scaledPoint(VectorUtil.ORIGO, normR, -2.5);
			    
			    Graphics.line3D(Color.BLUE, VectorUtil.ORIGO, normR);
				
			       
			    
			   
			    
			    double gamma=-VectorUtil.signedAngle(target.c, rotated.c,normR);
			    
				
			    
			    
			    System.out.println("GAmma:" +Math.toDegrees(gamma));
			    
			    
			    double[] rrx = VectorUtil.rotate(rotated.a, normR, gamma);
			    double[] rry = VectorUtil.rotate(rotated.b, normR, gamma);
			    double[] rrz = VectorUtil.rotate(rotated.c, normR, gamma);
			    
			    Triangle result= new Triangle(rrx,rry,rrz);
			    
			    drawTriangle(result, Color.GREEN);
			    
			    
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
