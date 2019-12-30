package hu.kg.descriptor;

import java.awt.Color;
import java.io.IOException;

import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import display.graphutil.Object3D;
import hu.kg.main.StringLoadUtil;

public class Main {

	public static void main(String[] args) throws IOException {
	    
		Descriptor d=new Descriptor(StringLoadUtil.load(args[0]));
	
	
		
		Object3D w= new Object3D(d.faces.get("HexaPrism"));
	
		
		Looper l=new Looper() {
			
			double angle=0.0;
			double pitch=0.0;
			double yaw=0.0;
			double roll=0.0;
			
			double[] axis= {1,0.5,0};
			
			@Override
			public void setEventCallbacks(long handle) {}
			
			@Override
			public void draw() {
		        Object3D current=w.copy();
		        //current.transForm(0, 0, -0.5);
		        current.rotateAroundOrigo(pitch, yaw, roll);

		        angle+=0.01;
		        pitch+=0.00522254;
			    yaw+=0.004341;
			    roll+=0.00222;
			    
		        
		        for(int[] p: current.lines) {
					double[] a=current.points[p[0]];
					double[] b=current.points[p[1]];
					Graphics.line3D(Color.WHITE, a, b);
				}
		        
			}
		};
		
		
		
		
		new Window(l, "proba");
		
		
		
		
	}

}
