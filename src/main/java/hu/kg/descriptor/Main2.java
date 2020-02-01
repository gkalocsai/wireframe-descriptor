package hu.kg.descriptor;

import java.io.IOException;

import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import display.graphutil.Object3D;
import hu.kg.main.StringLoadUtil;

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
		        Object3D complex=d.getObject3D("Complex");
		          
		        pitch+=0.00522254;
			    yaw+=0.004341;
			    roll+=0.00222;
			    complex.rotateAroundOrigo(pitch, yaw, roll);
			
			    Graphics.show(complex);		        
			}	
		};
		
		new Window(l, "proba");		
	}

}
