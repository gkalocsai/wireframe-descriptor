package hu.kg.math;

import java.awt.Color;
import java.util.Random;

import org.junit.Test;

import color.ColorTable;
import display.glwrap.Looper;
import display.glwrap.Window;
import display.graphutil.Graphics;

public class VectorSeparation {


	@Test
	public void t() {


		Looper l=new Looper() {


			@Override
			public void draw() {
				Random r=new Random(2);
			

				for(int i=0;i<1;i++) {

					
					double[] p1 = createPoint(r);
					double[] p2 = createPoint(r);
					
					Color color1 = ColorTable.getOne(r.nextInt());
					Color color2 = ColorTable.getOne(r.nextInt());
					
					
					
					
					double p1z = 0.1-p1[2]/10.0;
						
					
					double[] p1lu= {p1[0]-p1z,p1[1]+p1z,0};
					double[] p1ru= {p1[0]+p1z,p1[1]+p1z,0};
					double[] p1lb= {p1[0]-p1z,p1[1]-p1z,0};
					double[] p1rb= {p1[0]+p1z,p1[1]-p1z,0};
	
                    double p2z = 0.1-p2[2]/10.0;
						
					
					double[] p2lu= {p2[0]-p2z,p2[1]+p2z,0};
					double[] p2ru= {p2[0]+p2z,p2[1]+p2z,0};
					double[] p2lb= {p2[0]-p2z,p2[1]-p2z,0};
					double[] p2rb= {p2[0]+p2z,p2[1]-p2z,0};
	
					
					double[] paralell=VectorUtil.perp(p1, p2);
					double[] perp=VectorUtil.paralell(p1, p2);
					
					
					

					Graphics.line3D(color1, p1ru, p1lu);
					Graphics.line3D(color1, p1rb, p1ru);
					Graphics.line3D(color1, p1lb, p1lu);
					Graphics.line3D(color1, p1lb, p1rb);
					
					Graphics.line3D(color2, p2ru, p2lu);
					Graphics.line3D(color2, p2rb, p2ru);
					Graphics.line3D(color2, p2lb, p2lu);
					Graphics.line3D(color2, p2lb, p2rb);
					
					
                    Graphics.line3D(color1, p1, VectorUtil.ORIGO);
					
					Graphics.line3D(color2, p2, VectorUtil.ORIGO);

					Graphics.line3D(Color.BLUE, paralell, VectorUtil.ORIGO);

					Graphics.line3D(Color.CYAN, perp, VectorUtil.ORIGO);
					
					
					
					
				}
		}
				@Override
				public void setEventCallbacks(long handle) {
					// TODO Auto-generated method stub

				}


				private double[] createPoint(Random r) {
					double[] result=new double[3];
					result[0] = (r.nextDouble()*1)-0.5;
					result[1] = (r.nextDouble()*1)-0.5;
					result[2] = (r.nextDouble()*2)-1;

					return result;
				}


		};
		new Window(l, "TrianglesWithHeight");
	}

}
