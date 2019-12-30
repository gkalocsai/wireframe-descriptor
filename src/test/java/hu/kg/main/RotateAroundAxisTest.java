package hu.kg.main;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import hu.kg.math.VectorUtil;

public class RotateAroundAxisTest {

	
	
	
	@Test
	public void t() {


		Looper l=new Looper() {


			double alfa = 0.0;
			double[] axis = {1,1,1};
			@Override
			public void draw() {

				Random r=new Random(22453);
				
				Triangle t2 = Triangle.randomTriangle(r);

				double[] ra=VectorUtil.rotate(t2.a, axis, alfa);
				double[] rb=VectorUtil.rotate(t2.b, axis, alfa);
				double[] rc=VectorUtil.rotate(t2.c , axis, alfa);
				
                

				Graphics.filledTriangle3D(ColorTable.getOne(r.nextInt()), t2.a,t2.b,t2.c);
				Graphics.triangle3D(Color.ORANGE, t2.a,t2.b,t2.c);
				Graphics.line3D(Color.WHITE, t2.a, t2.b);


				Graphics.filledTriangle3D(ColorTable.getOne(r.nextInt()), ra,rb,rc);
				Graphics.triangle3D(Color.ORANGE, ra,rb,rc);
				Graphics.line3D(Color.WHITE, ra, rb);

				
				alfa+=0.01;
				
			}

			@Override
			public void setEventCallbacks(long handle) {
				// TODO Auto-generated method stub

			}


		};
		new Window(l, "TrianglesWithHeight");
	}

}
