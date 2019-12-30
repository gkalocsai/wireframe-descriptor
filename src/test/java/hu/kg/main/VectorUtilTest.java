package hu.kg.main;


import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;
import hu.kg.math.VectorUtil;

public class VectorUtilTest {

	@Test
	public void t() {


		Looper l=new Looper() {


			@Override
			public void draw() {

				Random r=new Random(22453);

				Triangle t2 = Triangle.randomTriangle(r);
				Triangle t1 = Triangle.randomTriangle(r);
				t1.sortAB();



				Graphics.filledTriangle3D(ColorTable.getOne(r.nextInt()), t1.a,t1.b,t1.c);
				Graphics.triangle3D(Color.ORANGE, t1.a,t1.b,t1.c);
				Graphics.line3D(Color.WHITE, t1.a, t1.b);


				double[] z= VectorUtil.cutPoint(t1.a, t1.b, t1.c);
				Graphics.line3D(Color.DARK_GRAY, z, t1.c);



				Graphics.filledTriangle3D(ColorTable.getOne(r.nextInt()), t2.a,t2.b,t2.c);
				Graphics.triangle3D(Color.ORANGE, t2.a,t2.b,t2.c);
				Graphics.line3D(Color.WHITE, t2.a, t2.b);


				double[] z2= VectorUtil.cutPoint(t2.a, t2.b, t2.c);
				Graphics.line3D(Color.DARK_GRAY, z2, t2.c);

			}

			@Override
			public void setEventCallbacks(long handle) {
				// TODO Auto-generated method stub

			}


		};
		new Window(l, "TrianglesWithHeight");
	}

}
